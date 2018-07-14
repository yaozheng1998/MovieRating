package com.nju.entity;

import lombok.Data;

/**
 * 评论类
 */
@Data
public class Comment {

    private int id;
    private String from;
    private String user;    // 用户名
    private String avatar;      // 用户头像
    private String date; // 评论时间
    private String content; // 评论内容
    private int thumb;      // 点赞数
    private double rate;    // 该评论给电影的评分

    public Comment() {

    }

    public Comment(String from, String user, String avatar, String date, String content, int thumb, double rate) {
        this.from = from;
        this.user = user;
        this.avatar = avatar;
        this.date = date;
        this.content = content;
        this.thumb = thumb;
        this.rate = rate;
    }


}
