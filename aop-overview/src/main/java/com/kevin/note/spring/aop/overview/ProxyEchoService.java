package com.kevin.note.spring.aop.overview;

/**
 * @Author:Kevin
 * @Date:Created in 20:25 2021/4/29
 */
public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    public String echo(String message) {
        long startTime = System.currentTimeMillis();
        String result = echoService.echo(message);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("echo方法执行时间：" + costTime + "ms.");
        return result;
    }
}
