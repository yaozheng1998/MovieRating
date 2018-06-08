package com.nju.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mtime")
public class MTime {

    @Id
    private int movieId;       // 电影id
    private String movieName;   // 电影名
    private double rate;        // 电影评分
}
