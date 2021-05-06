package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.advice.MyThrowsAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @Author:Kevin
 * @Date:Created in 21:06 2021/5/6
 */
public class ThrowsAdviceDemo {

    public static void main(String[] args) {
        ThrowsAdviceDemo instance = new ThrowsAdviceDemo();
        ProxyFactory proxyFactory = new ProxyFactory(instance);
        proxyFactory.addAdvice(new MyThrowsAdvice());

        ThrowsAdviceDemo throwsAdviceDemo = (ThrowsAdviceDemo) proxyFactory.getProxy();
        throwsAdviceDemo.execute();
    }

    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For purpose.");
        }

        System.out.println("executing");
    }
}
