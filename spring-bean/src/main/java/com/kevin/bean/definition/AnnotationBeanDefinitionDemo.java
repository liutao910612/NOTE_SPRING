package com.kevin.bean.definition;

import com.kevin.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解BeanDefinition示例
 *
 * @Author:Kevin
 * @Date:Created in 20:57 2020/12/2
 */
@Import(Config.class)  //3通过 @Import进行导入
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {

        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Configuration Class（配置类）
        applicationContext.register(Config.class);

        //启动应用上下文
        applicationContext.refresh();
        System.out.println("Config 类型的所有bean:" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有bean:" + applicationContext.getBeansOfType(User.class));

        //关闭应用上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",1L)
                .addPropertyValue("name","Kevin");

        //判断如果beanName的参数存在时
        if(StringUtils.hasText(beanName)){

            //注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{

            //非命名bean注册方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }

    }

    /**
     * 非命名Bean的注册方式
     * @param registry
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry){
        registerBeanDefinition(registry,null);
    }
}

@Component //2通过 @Component方式
class Config {

    /**
     * 通过java注解方式定义一个bean
     * @return
     */
    @Bean(name = {"user","userAlias"})//1通过 @Bean方式定义
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Kevin");
        return user;
    }
}
