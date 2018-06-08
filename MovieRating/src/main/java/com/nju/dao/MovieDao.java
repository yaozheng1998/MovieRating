package com.nju.dao;

import com.nju.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by stephen on 2018/5/31
 */
public interface MovieDao extends JpaRepository<Movie, Integer> {

    public List<Movie> findAllByNameLike(String name);

}
