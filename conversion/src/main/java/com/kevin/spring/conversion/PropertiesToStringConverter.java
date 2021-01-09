package com.kevin.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * {@link Properties} -> {@link String} {@link ConditionalGenericConverter} demo
 *
 * @Author:Kevin
 * @Date:Created in 14:03 2021/1/9
 * @see ConditionalGenericConverter
 * @see Properties
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType())
                && String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties) source;
        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> e : properties.entrySet()) {
            textBuilder.append(e.getKey()).append("=").append(e.getValue()).append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }
}
