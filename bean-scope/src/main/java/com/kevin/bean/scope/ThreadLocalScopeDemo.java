package com.kevin.bean.scope;

import com.kevin.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义Scope{@link ThreadLocalScope}示例
 *
 * @Author:Kevin
 * @Date:Created in 21:42 2020/11/12
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(scopeName = ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return buildUser();
    }

    private static User buildUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类)
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        //启动Spring应用上下文
        applicationContext.refresh();
        scopedBeanByLookup(applicationContext);
        //显示关闭Spring应用上下文
        applicationContext.close();
    }

    private static void scopedBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        Thread thread1 = new Thread(()->{
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println(Thread.currentThread().getId());
        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println(Thread.currentThread().getId());
        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println("user : " + applicationContext.getBean("user",User.class));
            System.out.println(Thread.currentThread().getId());
        });
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
