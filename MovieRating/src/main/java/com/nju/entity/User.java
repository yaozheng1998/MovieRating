package com.nju.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户类
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private int id;
    private String userId;
    private String username;//用户名
    private String password;//密码
    private String avatar;//头像url地址
    private String likes;
    private String dislikes;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
