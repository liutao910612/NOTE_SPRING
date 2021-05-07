package com.kevin.note.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Hello World模块{@link ImportSelector}实现
 *
 * @Author:Kevin
 * @Date:Created in 21:40 2021/1/12
 */
public class HelloWorldImportSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"HelloWorldConfiguration"}; //导入
    }
}
