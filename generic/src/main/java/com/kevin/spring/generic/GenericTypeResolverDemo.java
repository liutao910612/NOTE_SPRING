package com.kevin.spring.generic;

import com.kevin.base.utils.PrintUtil;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

/**
 * {@link GenericTypeResolver} demo
 *
 * @Author:Kevin
 * @Date:Created in 17:11 2021/1/9
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> containingClass = GenericTypeResolverDemo.class;

        //String 是Comparable<String>具体化
        displayReturnTypeGenericInfo("getString", containingClass,Comparable.class,null);

        //ArrayList<Object>是List泛型参数类型的具体化
        displayReturnTypeGenericInfo("getList", containingClass,List.class,null);

        //StringList也是List泛型参数类型的具体化
        displayReturnTypeGenericInfo("getStringList", containingClass,List.class,null);

        //具备ParameterizedType返回，否则null

    }

    private static void displayReturnTypeGenericInfo(String methodName, Class<?> containingClass,Class<?> genericInfo, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName);


        //声明类GenericTypeResolverDemo.class
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);

        //常规类作为方法返回值
        PrintUtil.print(
                String.format("GenericTypeResolver.resolveReturnType(%s,%s)=%s"
                        , methodName
                        , containingClass.getSimpleName()
                        , returnType)
        );

        //常规类型不具备泛型参数类型 List<E>
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericInfo);
        PrintUtil.print(
                String.format("GenericTypeResolver.resolveReturnTypeArgument(%s,%s)=%s"
                        , methodName
                        , containingClass.getSimpleName()
                        , returnTypeArgument)
        );
    }

    public static String getString() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    public static List<Object> getList() {  //泛型参数具体化
        return null;
    }
}
