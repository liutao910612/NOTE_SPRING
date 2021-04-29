package com.kevin.note.spring.aop.overview;

/**
 * ClassLoader demo
 * @Author:Kevin
 * @Date:Created in 19:19 2021/4/25
 */
public class ClassLoadingDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader.getParent();
        while (true){
            if(parentClassLoader == null){
                break;
            }

            System.out.println(parentClassLoader);
            parentClassLoader = parentClassLoader.getParent();
        }
    }
}
