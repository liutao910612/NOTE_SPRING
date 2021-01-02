package com.kevin.spring.resource.springx;

import com.kevin.base.utils.PrintUtil;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 简单地继承{@link sun.net.www.protocol.x.Handler}类
 * @Author:Kevin
 * @Date:Created in 16:51 2021/1/2
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    //-Djava.protocol.handler.pkgs=com.kevin.spring.resource
    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/production.properties");  //类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        PrintUtil.print(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
