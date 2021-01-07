package com.kevin.spring.data.binding;

import com.kevin.base.domain.User;
import com.kevin.base.utils.PrintUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} demo
 *
 * @Author:Kevin
 * @Date:Created in 20:58 2021/1/5
 * @see DataBinder
 */
public class DataBinderDemo {
    public static void main(String[] args) {
        //创建空白对象
        User user = new User();
        //1.创建DataBinder
        DataBinder dataBinder = new DataBinder(user,"user");

        //2.创建PropertyValues
        Map<String,Object> source = new HashMap<>();
        source.put("id",1);
        source.put("name","kevin");

        //a .PropertyValues have the field that don't belong to User
        //DataBinder feature 1: ignore unknown field
        source.put("age",12);

        //b .PropertyValues have nested properties
        //DataBinder feature 2 : support nested properties
        source.put("company.name","huawei");

        //1.adjust ignoreUnknownFields true(default) -> false , will throw exception caused by unknown age property
        //dataBinder.setIgnoreUnknownFields(false);

        //2.adjust autoGrowNestedPaths true(default) -> false
        dataBinder.setAutoGrowNestedPaths(false);

        //3.adjust ignoreInvalidFields false(default) -> true
        dataBinder.setIgnoreInvalidFields(true);

        dataBinder.setRequiredFields("id","name","city");

        PropertyValues propertyValues = new MutablePropertyValues(source);
        dataBinder.bind(propertyValues);


        //3.输出User内容
        PrintUtil.print(user);

        //4.Get binding result (result include document information , don't throw exception)
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);

    }
}
