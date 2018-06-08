package com.nju.entity;

import lombok.Data;

/**
 * 评论类
 */
@Data
public class Comment {

    private int from;      // 0 豆瓣 1时光网 2猫眼
    private String user;    // 用户名
    private String avatar;      // 用户头像
    private String date; // 评论时间
    private String content; // 评论内容
    private int thumb;      // 点赞数
    private double rate;    // 该评论给电影的评分

}
