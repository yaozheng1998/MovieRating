package com.nju.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * create by stephen on 2018/7/6
 */
@Data
@Entity
@Table(name = "hot_movie")
public class HotMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int movieId;
    private int rank;
}
