package com.kevin.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的{@link FileSystemResourceLoader}示例
 * @Author:Kevin
 * @Date:Created in 12:02 2021/1/2
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") +"/resource/src/main/java/com/kevin/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();

        //最终底层也是在构建FileSystemResource对象
        Resource fileSystemResource = fileSystemResourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource,"UTF-8");

        //字符输入流
        try(Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }
    }
}
