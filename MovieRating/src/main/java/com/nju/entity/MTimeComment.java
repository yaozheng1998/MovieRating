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
@Table(name = "mtime_comments")
public class MTimeComment  {

    @Id
    private int id;            // 评论id
    private int movieId;       // 电影id
    private String caimg;       // 用户头像
    private String ca;          // 用户名
    private String cd;          // 评论时间
    private String ce;          // 评论内容
    private int commentCount;   // 点赞数
    private double cr;          // 该评论给电影的评分

}
