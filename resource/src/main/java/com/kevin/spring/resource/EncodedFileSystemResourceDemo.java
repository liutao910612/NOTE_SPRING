package com.kevin.spring.resource;

import com.kevin.base.utils.ResourceUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;

/**
 * 带有字符编码的{@link FileSystemResource}示例
 * @Author:Kevin
 * @Date:Created in 12:02 2021/1/2
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir") +"/resource/src/main/java/com/kevin/spring/resource/EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        //FileSystemResource => WritableResource => Resource
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFile);
        System.out.println(ResourceUtil.getContent(fileSystemResource,"UTF-8"));
    }
}
