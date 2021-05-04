package com.kevin.note.spring.aop.features.pointcut;

import com.kevin.note.spring.aop.overview.EchoService;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author:Kevin
 * @Date:Created in 17:02 2021/5/2
 */
public class EchoServiceMethodPointcut implements Pointcut {

    public static final EchoServiceMethodPointcut INSTANCE = new EchoServiceMethodPointcut();

    private EchoServiceMethodPointcut() {
    }

    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> clazz) {
                return EchoService.class.isAssignableFrom(clazz);// 凡是EchoService接口或者子类均可
            }
        };
    }

    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            public boolean matches(Method method, Class<?> targetClass) {  //echo(String)
                return "echo".equals(method.getName()) &&
                        method.getParameterTypes().length == 1 &&
                        Objects.equals(String.class, method.getParameterTypes()[0]);
            }

            public boolean isRuntime() {
                return false;
            }

            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
