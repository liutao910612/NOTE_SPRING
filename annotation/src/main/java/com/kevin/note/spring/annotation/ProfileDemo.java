package com.kevin.note.spring.annotation;

import com.kevin.base.utils.PrintUtil;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile} demo
 *
 * @Author:Kevin
 * @Date:Created in 22:07 2021/1/12
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);

        //获取Environment对象(可配置的)
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd"); //默认的Profiles = ['odd']

        //增加的活跃的profiles
        environment.setActiveProfiles("even");

        context.refresh();
        int number = context.getBean("number",Integer.class);
        PrintUtil.print(number);
        context.close();
    }

    @Bean(name = "number")
    @Profile("odd")
    public int odd(){
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even")
    @Conditional(EvenProfileCondition.class)
    public int even(){
        return 2;
    }
}
