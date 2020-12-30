package com.kevin.configuration.metadata;

import com.kevin.base.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "user"元素的{@link BeanDefinitionParser} 实现
 * @Author:Kevin
 * @Date:Created in 22:19 2020/12/30
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        setPropertyValue("city",element,builder);
    }


    private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder beanDefinitionBuilder){
        String attributeValue = element.getAttribute(attributeName);
        if(StringUtils.hasText(attributeValue)){
            beanDefinitionBuilder.addPropertyValue(attributeName,attributeValue);
        }
    }

}
