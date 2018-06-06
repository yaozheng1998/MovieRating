package com.nju.entity;

import lombok.Data;

/**
 * 评论类
 */
@Data
public class Comment {
    private User user;
    private String date;
    private String content;
    private double rate;

}
