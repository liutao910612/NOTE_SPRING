package com.kevin.note.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 偶数Profile条件
 *
 * @Author:Kevin
 * @Date:Created in 22:19 2021/1/12
 */
public class EvenProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        //条件上下文
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
