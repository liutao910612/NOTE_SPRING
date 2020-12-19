package com.kevin.ioc.overview.domain;

import com.kevin.ioc.overview.annotation.Super;

/**
 * @Author:Kevin
 * @Date:Created in 21:57 2020/11/23
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SuperUser() {
    }

    public SuperUser(String address) {
        this.address = address;
    }

    public SuperUser(Long id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
