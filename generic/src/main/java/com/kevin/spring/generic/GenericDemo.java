package com.kevin.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author:Kevin
 * @Date:Created in 16:26 2021/1/9
 */
public class GenericDemo {
    public static void main(String[] args) {

        //Java 7 Diamond语法
        Collection<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");

        //编译时错误
        //list.add(1);

        //泛型擦写
        Collection temp = list;
        temp.add(1);
        System.out.println(list);
    }
}
