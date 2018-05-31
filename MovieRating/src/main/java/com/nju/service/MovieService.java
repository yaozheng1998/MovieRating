package com.nju.service;

import com.nju.entity.Comment;
import com.nju.entity.Movie;

import java.util.ArrayList;

/**
 * create by stephen on 2018/5/31
 */

public interface MovieService {
    /**
     * 获取全部电影数据
     * @return Movie的ArrayList
     */
    ArrayList<Movie> loadAllMovies();

    /**
     * 获取综合评分（三家网站评分中取最高的）最高的10部热门电影
     * @return Movie的ArrayList
     */
    ArrayList<Movie> loadTopTenMovies();

    /**
     * 模糊搜索
     * @param keyword 搜索的关键字（可能是中文，可能是拼音）
     * @return 符合条件的Movie的ArrayList
     */
    ArrayList<Movie> searchMovie(String keyword);

    /**
     * 加载对应id的Movie
     * @param id Movie的id
     * @return 对应的Movie
     */
    Movie loadMovie(int id);

    /**
     * 获取movieId影片对应的所有影评（三家）
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    ArrayList<Comment> loadAllComments(int movieId);

    /**
     * 获取豆瓣上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    ArrayList<Comment> loadCommentsFromDouban(int movieId);

    /**
     * 获取猫眼上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    ArrayList<Comment> loadCommentsFromMaoyan(int movieId);

    /**
     * 获取时光网上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    ArrayList<Comment> loadCommentsFromTime(int movieId);
}
