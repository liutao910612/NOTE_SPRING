package com.kevin.base.domain;

/**
 * @Author:Kevin
 * @Date:Created in 20:35 2020/12/22
 */
public class UserHolder {
    private User user;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
