package com.kevin.base.domain;

/**
 * @Author:Kevin
 * @Date:Created in 20:52 2021/1/7
 */
public class Company {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
