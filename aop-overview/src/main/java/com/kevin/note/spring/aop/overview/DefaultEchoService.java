package com.kevin.note.spring.aop.overview;

/**
 * 默认{@link EchoService}实现
 *
 * @Author:Kevin
 * @Date:Created in 20:23 2021/4/29
 */
public class DefaultEchoService implements EchoService {

    public String echo(String message) {
        return "[ECHO]" + message;
    }
}
