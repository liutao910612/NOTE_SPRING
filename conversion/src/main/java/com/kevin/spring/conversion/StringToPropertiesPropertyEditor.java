package com.kevin.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.IllegalFormatCodePointException;
import java.util.Properties;

/**
 *
 * String -> Properties{@link PropertyEditor}
 * @Author:Kevin
 * @Date:Created in 21:15 2021/1/8
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    //1.实现setAsText(String)方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        //2.将String类型转换成Properties类型
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        //3.临时存储Properties对象
        setValue(properties);

        //next获取Properties临时对象#getValue();
    }
}
