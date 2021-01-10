package com.kevin.spring.generic;

import com.kevin.base.utils.PrintUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java generic API demo
 *
 * @Author:Kevin
 * @Date:Created in 16:48 2021/1/9
 */
public class GenericAPIDemo {
    public static void main(String[] args) {
        //原生类型 primitive types : int long float
        Class intClass = int.class;
        PrintUtil.print(intClass);

        //数组类型array types : int[] ,Object[]
        Class objectArrayClass = Object[].class;
        PrintUtil.print(objectArrayClass);

        //原始类型 raw types : java.lang.String
        Class rawClass = String.class;
        PrintUtil.print(rawClass);

        //泛型参数类型parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        PrintUtil.print(parameterizedType);

        //泛型类型变量 Type Variable: E
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables)
                .map(TypeVariable.class::cast)  //Type -> TypeVariable
                .forEach(PrintUtil::print);

    }
}
