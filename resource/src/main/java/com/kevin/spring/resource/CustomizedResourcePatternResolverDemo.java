package com.kevin.spring.resource;

import com.kevin.base.utils.ResourceUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.PathMatcher;
import java.util.stream.Stream;

/**
 * 自定义{@link ResourcePatternResolver} demo
 *
 * @Author:Kevin
 * @Date:Created in 12:02 2021/1/2
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        //读取当前package对应的所有的.java文件
        //*.java

        String currentPackagePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/com/kevin/spring/resource/";
        String locationPattern = currentPackagePath + "*.java";
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        Resource[] resourceList = resourcePatternResolver.getResources(locationPattern);
        Stream.of(resourceList).map(ResourceUtil::getContent).forEach(resource -> {
            System.out.println(resource);
        });

    }
}
