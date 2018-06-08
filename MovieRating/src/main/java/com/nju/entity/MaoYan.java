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
@Table(name = "maoyan")
public class MaoYan {

    @Id
    private int movieNo;    // 电影id
    private String name;    // 电影名
    private double score;   // 电影评分

}
