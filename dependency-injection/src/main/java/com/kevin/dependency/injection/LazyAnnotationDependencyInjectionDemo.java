package com.kevin.dependency.injection;

import com.kevin.dependency.injection.annotation.InjectUser;
import com.kevin.dependency.injection.annotation.MyAutowired;
import com.kevin.ioc.overview.domain.User;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * {@link ObjectProvider}实现延迟依赖注入
 *
 * @Author:Kevin
 * @Date:Created in 14:51 2020/12/6
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> objectProvider;

    @MyAutowired
    private User myUser;

    @InjectUser
    private User injectUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> autowire = new HashSet<>();
//        autowire.add(Autowired.class);
//        autowire.add(InjectUser.class);
//
//        //@Autowired + 新注解@InjectUser
//        beanPostProcessor.setAutowiredAnnotationTypes(autowire);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> autowire = new HashSet<>();
        autowire.add(Autowired.class);
        autowire.add(InjectUser.class);

        //@Autowired + 新注解@InjectUser
        beanPostProcessor.setAutowiredAnnotationTypes(autowire);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找AnnotationDependencyFieldInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.objectProvider);
        System.out.println(demo.myUser);
        System.out.println(demo.injectUser);
        //显示关闭Spring应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder init(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
