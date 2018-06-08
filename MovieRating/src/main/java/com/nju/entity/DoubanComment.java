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
    private int dooubanId;          // 豆瓣里的电影id
    private String uid;             // 用户id
    private String avatar;          // 用户头像
    private String signature;       // 用户前面
    private String name;            // 用户名
    private String content;         // 评论内容
    private String create_at;       // 评论时间
    private int rating;          // 评论评分
}
