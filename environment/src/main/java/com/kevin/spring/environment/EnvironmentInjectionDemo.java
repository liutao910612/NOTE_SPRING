package com.kevin.spring.environment;

import com.kevin.base.utils.PrintUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入{@link Environment}demo
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2021/1/14
 */
public class EnvironmentInjectionDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    @Autowired
    private Environment environment2;

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentInjectionDemo.class);
        applicationContext.refresh();

        EnvironmentInjectionDemo environmentInjectionDemo = applicationContext.getBean(EnvironmentInjectionDemo.class);
        PrintUtil.print(environmentInjectionDemo.environment);
        PrintUtil.print(environmentInjectionDemo.environment2);
        PrintUtil.print(applicationContext.getEnvironment());
        PrintUtil.print(applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME));
        applicationContext.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
