package com.kevin.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld Configuration class
 * @Author:Kevin
 * @Date:Created in 21:33 2021/1/12
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld(){
        return "hello,world";
    }
}
