package com.kevin.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} demo
 *
 * @Author:Kevin
 * @Date:Created in 20:58 2021/1/5
 * @see PropertyEditor
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {

        //模拟Spring Framework操作
        //有一段文本name=kevin
        String text = "name=kevin";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        //传递String类型的内容
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
    }
}
