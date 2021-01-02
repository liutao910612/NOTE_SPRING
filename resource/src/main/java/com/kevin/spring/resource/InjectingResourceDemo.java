package com.kevin.spring.resource;

import com.kevin.base.utils.PrintUtil;
import com.kevin.base.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入{@link Resource}对象demo
 * @Author:Kevin
 * @Date:Created in 15:39 2021/1/2
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    public void init(){
        PrintUtil.print(ResourceUtil.getContent(resource));
        Stream.of(resource).map(ResourceUtil::getContent).forEach(PrintUtil::print);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingResourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}
