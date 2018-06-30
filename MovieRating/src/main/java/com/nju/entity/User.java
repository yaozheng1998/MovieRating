package com.nju.entity;

import lombok.Data;

/**
 * 用户类
 */
@Data
public class User {
    private String username;//用户名
    private String password;//密码
    private String avatar;//头像url地址

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
