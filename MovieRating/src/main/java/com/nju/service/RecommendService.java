package com.nju.service;

import com.nju.entity.Movie;

import java.util.List;

public interface RecommendService {

    /**
     * 获取给该用户推荐的电影列表
     * @param userId 用户id
     * @return 推荐给该用户的电影列表
     */
    List<Movie> getRecommendedMovies(String userId);

    /**
     * 用户喜欢推荐的电影 更新用户喜欢的电影列表
     */
    void likeRecommend(Movie movie, String userId);

    /**
     * 用户不喜欢推荐的电影 更新用户不喜欢的电影列表
     */
    void dislikeRecommend(Movie movie, String userId);

}
