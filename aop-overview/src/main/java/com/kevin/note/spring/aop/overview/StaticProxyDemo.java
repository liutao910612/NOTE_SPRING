package com.kevin.note.spring.aop.overview;

/**
 * 静态代理示例
 *
 * @Author:Kevin
 * @Date:Created in 20:20 2021/4/29
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }
}
