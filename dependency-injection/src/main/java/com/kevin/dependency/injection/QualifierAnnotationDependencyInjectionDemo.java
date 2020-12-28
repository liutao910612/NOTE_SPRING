package com.kevin.dependency.injection;

import com.kevin.base.domain.User;
import com.kevin.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier}注解依赖注入
 *
 * @Author:Kevin
 * @Date:Created in 14:51 2020/12/6
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") //指定Bean名称或者Id
    private User namedUser;

    @Autowired
    private Collection<User> users; //2 beans = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; //2 beans = user1 + user2 -> 4 beans = user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; //2 beans = user3 + user4

    //当前会有四个User bean:user,superUser,user1,user2
    @Bean
    @Qualifier  //使用Qualifier进行逻辑分组
    public User user1(){
        User user = new User();
        user.setId(6L);
        return user;
    }

    @Bean
    @Qualifier
    public User user2(){
        User user = new User();
        user.setId(7L);
        return user;
    }

    @Bean
    @UserGroup
    public User user3(){
        User user = new User();
        user.setId(8L);
        return user;
    }

    @Bean
    @UserGroup
    public User user4(){
        User user = new User();
        user.setId(9L);
        return user;
    }
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class(配置类) -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找AnnotationDependencyFieldInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.namedUser);
        System.out.println(demo.users);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.groupedUsers);
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
