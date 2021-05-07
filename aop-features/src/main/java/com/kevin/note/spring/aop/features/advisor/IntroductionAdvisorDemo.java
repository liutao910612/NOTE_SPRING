package com.kevin.note.spring.aop.features.advisor;

import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * {@link IntroductionAdvisor} 示例
 *
 * @Author:Kevin
 * @Date:Created in 19:56 2021/5/7
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {

    public static void main(String[] args) {
        IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();

        //使用该构造器会使得IntroductionInfo失效
        //ProxyFactory proxyFactory = new ProxyFactory(target);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(
                new MethodBeforeAdvice() {
                    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
                        System.out.printf("[MethodBeforeAdvice]，method : %s \n", method);
                    }
                },
                new IntroductionInfo() {

                    public Class<?>[] getInterfaces() {
                        return new Class[]{EchoService.class};
                    }
                }));

        Object proxy = proxyFactory.getProxy();
        EchoService echoService = (EchoService)proxy;
        echoService.echo("Hello,World");

        Comparable comparable = (Comparable)proxy;
        comparable.compareTo(null);
    }

    public String echo(String message) throws NullPointerException {
        return "IntroductionAdvisorDemo : " + message;
    }

    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }
}
