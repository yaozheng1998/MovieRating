package com.nju.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * create by stephen on 2018/5/31
 * 数据实体类Movie属性注意：包含三个评分
 */
@Data
@Entity
@Table(name = "movieInfo")
public class Movie {

    @Id
    private int id;            // 电影id
    private int doubanId;      // 豆瓣网的id
    private int mtimeId;       // 时光网的id
    private int maoyanId;      // 猫眼网的id
    private String name;        // 电影名
    private String byname;      // 英文名/别名
    private int year;           // 年份
    private String countries;   // 上映地区
    private String image;       // 电影海报
    private String summary;     // 电影概述
    private String genres;      // 分类
    private String directors;   // 导演
    private String casts;       // 主演
    private double doubanRating;    // 豆瓣评分
    private double mTimeRating;    // 时光评分
    private double maoyanRating;    // 猫眼评分
    private double rating;      // 综合评分 三者取平均

}
