package com.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create by stephen on 2018/6/6
 */
@Data
@Entity
@Table(name = "douban_comments")
public class DoubanComment {

    @Id
    private int id;                 // 评论id
    private int doubanId;          // 豆瓣里的电影id
    private String uid;             // 用户id
    private String avatar;          // 用户头像
    private String signature;       // 用户前面
    private String name;            // 用户名
    private String content;         // 评论内容
    private String create_at;       // 评论时间
    private int rating;          // 评论评分

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(int doubanId) {
        this.doubanId = doubanId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
