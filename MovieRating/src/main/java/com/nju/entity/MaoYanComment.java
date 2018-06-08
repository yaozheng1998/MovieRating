package com.nju.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create by stephen on 2018/6/8
 */
@Data
@Entity
@Table(name = "maoyan_comments")
public class MaoYanComment {

    @Id
    private int id;            // 评论id
    private int movieNo;        // 电影id
    private String username;    // 用户名
    private String avatar;      // 用户头像
    private String commentTime; // 评论时间
    private String commentText; // 评论内容
    private int commentUp;      // 点赞数
    private double userRate;    // 该评论给电影的评分
}
