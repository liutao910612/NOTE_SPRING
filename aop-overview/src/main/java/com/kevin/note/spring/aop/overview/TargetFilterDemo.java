package com.kevin.note.spring.aop.overview;

import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * AOP目标过滤示例
 *
 * @Author:Kevin
 * @Date:Created in 20:44 2021/4/29
 */
public class TargetFilterDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        String className = "com.kevin.note.spring.aop.overview.EchoService";

        //获取当前线程classLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(className);

        //方法定义String echo(String message) throws NullPointerException
        //Spring反射工具
        Method method = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(method);

        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        System.out.println("仅抛出NullPointException方法为：" + method);
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    public boolean matches(Method method) {
                        Class[] exceptionTypes = method.getExceptionTypes();
                        return exceptionTypes.length == 1 && NullPointerException.class.equals(exceptionTypes[0]);
                    }
                });
    }
}
