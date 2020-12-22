package com.kevin.bean.life.cycle;

import com.kevin.base.domain.SuperUser;
import com.kevin.base.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Author:Kevin
 * @Date:Created in 21:09 2020/12/22
 */
class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {

            //把XML配置完成的SuperUser bean覆盖掉
            return new SuperUser();
        }
        return null;  //返回为空的时候会继续创建bean
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            User user = (User) bean;
            user.setId(2L);
            user.setName("kevin");

            //"user"对象不允许属性赋值（填入）（配置元信息->属性值）
            return false;
        }
        return true;
    }
}
