package com.kevin.spring.resource;

import com.kevin.base.utils.PrintUtil;
import com.kevin.base.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入{@link ResourceLoader}对象demo
 *
 * @Author:Kevin
 * @Date:Created in 15:39 2021/1/2
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        PrintUtil.print("resourceLoader == autowiredResourceLoader ->" + (resourceLoader == autowiredResourceLoader));
        PrintUtil.print("resourceLoader == applicationContext ->" + (resourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingResourceLoaderDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
