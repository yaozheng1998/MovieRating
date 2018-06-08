package com.nju.service;

import com.nju.entity.Comment;
import com.nju.entity.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * create by stephen on 2018/5/31
 */

public interface MovieService {
    /**
     * 获取全部电影数据
     * @return Movie的ArrayList
     */
    List<Movie> loadAllMovies();

    /**
     * 获取综合评分（三家网站评分中取最高的）最高的10部热门电影
     * @return Movie的ArrayList
     */
    List<Movie> loadTopTenMovies();

    /**
     * 模糊搜索
     * @param keyword 搜索的关键字（可能是中文，可能是拼音）
     * @return 符合条件的Movie的ArrayList
     */
    List<Movie> searchMovie(String keyword);

    /**
     * 加载对应id的Movie
     * @param id Movie的id
     * @return 对应的Movie
     */
    Movie loadMovie(int id);


}
