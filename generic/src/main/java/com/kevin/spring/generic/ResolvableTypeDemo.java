package com.kevin.spring.generic;

import com.kevin.base.utils.PrintUtil;
import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} demo
 *
 * @Author:Kevin
 * @Date:Created in 9:58 2021/1/10
 */
public class ResolvableTypeDemo {
    public static void main(String[] args) {
        //工厂创建
        //StringList <- ArrayList <- AbstractList <- List
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        resolvableType.getSuperType(); // ArrayList
        resolvableType.getSuperType().getSuperType(); // AbstractList
        PrintUtil.print(resolvableType.asCollection().resolve());  //获取Raw Type
        PrintUtil.print(resolvableType.asCollection().resolveGeneric(0)); //获取泛型参数类型
    }
}
