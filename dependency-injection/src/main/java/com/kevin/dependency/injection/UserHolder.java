package com.kevin.dependency.injection;

import com.kevin.base.domain.User;

/**
 * @Author:Kevin
 * @Date:Created in 21:59 2020/12/8
 */
public class UserHolder {

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
