package com.kevin.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入注解
 *
 * @Author:Kevin
 * @Date:Created in 16:01 2020/12/13
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
