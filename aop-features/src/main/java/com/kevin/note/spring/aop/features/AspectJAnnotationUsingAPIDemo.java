package com.kevin.note.spring.aop.features;

import com.kevin.note.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Kevin
 * @Date:Created in 5:39 2021/5/2
 */
public class AspectJAnnotationUsingAPIDemo {
    public static void main(String[] args) {

        //通过创建一个HashMap缓存，作为被代理对象
        Map<String, Object> cache = new HashMap();


        //创建Proxy工厂
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        proxyFactory.addAspect(AspectConfiguration.class);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
                if ("put".equals(method.getName())
                        && args.length == 2) {
                    System.out.printf("[MethodBeforeAdvice] 当前存放是 key :%s, value :%s \n", args[0], args[1]);
                }
            }
        });

        //添加AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target)
                    throws Throwable {
                if ("put".equals(method.getName())
                        && args.length == 2) {
                    System.out.printf("[AfterReturningAdvice] 当前存放是key : %s,新存放的value : %s,之前关联的value : %s\n"
                            , args[0]        //key
                            , args[1]        //new value
                            , returnValue    //old value
                    );
                }
            }
        });

        //存储对象
        //cache.put("1","A");
        Map<String, Object> proxy = proxyFactory.getProxy();
        proxy.put("1", "A");
        proxy.put("1", "B");
        System.out.println(proxy.get("1"));
    }
}
