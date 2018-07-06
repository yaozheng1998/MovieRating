package com.nju.dao;

import com.nju.entity.HotMovie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by stephen on 2018/7/6
 */
public interface HotMovieDao extends JpaRepository<HotMovie, Integer> {
}
