package com.kevin.bean.life.cycle;

import com.kevin.base.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * @Author:Kevin
 * @Date:Created in 21:42 2020/12/23
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(bean instanceof UserHolder){
            UserHolder userHolder = (UserHolder)bean;
            userHolder.setDesc("The user holder V9");
            System.out.println("The user holder V9");
        }
    }
}
