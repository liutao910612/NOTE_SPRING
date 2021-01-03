package com.kevin.spring.i18n;

import com.kevin.base.utils.Constant;
import com.kevin.base.utils.PrintUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 动态（更新）资源{@link MessageSource}实现
 * <p>
 * 实现步骤：
 * </p>
 * 1.定位资源位置（Properties文件）
 * 2.初始化Properties对象
 * 3.实现AbstractMessageSource#resolveCode方法
 * 4.监听资源文件（Java NIO 2 WatchService）
 * 5.使用线程池处理文件变化
 * 6.重新装载Properties对象
 *
 * @Author:Kevin
 * @Date:Created in 13:33 2021/1/3
 * @see MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String SOURCE_FILE_NAME = "msg.properties";
    private static final String RESOURCE_PATH = "/META-INF/"+SOURCE_FILE_NAME;

    private final Properties messageProperties;
    private final Resource messagePropertiesResource;
    private final ExecutorService executorService;
    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        this.messagePropertiesResource = getMessagePropertiesResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        //监听资源文件（Java NIO 2 WatchService）
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if (this.messagePropertiesResource.isFile()) { //判断是否为文件
            //获取对应文件系统中的文件
            try {
                File messagePropertiesFile = this.messagePropertiesResource.getFile();
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();

                //获取当前OS文件系统
                FileSystem fileSystem = FileSystems.getDefault();

                //新建WatchService
                WatchService watchService = fileSystem.newWatchService();

                //获取资源文件所在目录
                Path dirPath = messagePropertiesFilePath.getParent();

                //注册WatchService到dirPath,并且关心修改时间
                dirPath.register(watchService, ENTRY_MODIFY);

                //处理资源文件变化（异步）
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理资源文件变化（异步）
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            WatchKey watchKey = watchService.take(); //take发生阻塞
            try {
                while (true) {
                    //watchKey是否有效
                    if (watchKey.isValid()) {
                        for (WatchEvent watchEvent : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();

                            //目录路径（监听的注册目录）
                            Path dirPath = (Path) watchable;
                            //事件所关联的对象即注册目录的子文件（或子文件夹）
                            //事件发生源是相对路径
                            Path fileRelativePath = (Path) watchEvent.context();

                            if(SOURCE_FILE_NAME.equals(fileRelativePath.getFileName().toString())){
                                //处理为绝对路径
                                Path filePath = dirPath.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                PrintUtil.print("修改的文件 ： " + filePath);
                                synchronized (messageProperties) {
                                    messageProperties.clear();
                                    messageProperties.putAll(properties);
                                }
                            }

                        }
                    }
                }
            } finally {
                if(watchKey != null){
                    watchKey.reset(); //重置watchKey
                }
            }
        });
    }


    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, Constant.Encode.UTF8);

        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        return resourceLoader.getResource(RESOURCE_PATH);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader == null ? new DefaultResourceLoader() : this.resourceLoader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        int  i = 0;
        while (i < 10000){
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            PrintUtil.print(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
