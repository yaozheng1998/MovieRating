package com.nju.service;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获得某个用户数据
     * @param username 用户id
     * @return 该用户数据类
     */
    User getUserData(String username);


    /**
     * 获取给该用户推荐的电影列表
     * @param username 用户id
     * @return 推荐给该用户的电影列表
     */
    List<Movie> getRecommendedMovies(String username);


    /**
     * 获取该用户收藏喜欢过的电影列表
     * @param username 用户id
     * @return 该用户收藏过的所有电影
     */
    List<Movie> getLikeMovies(String username);

    /**
     * 获取该用户的评论 ----- 该评论类中要添加一个属性标明是哪个电影的评论--最好包含该电影id
     * @param username
     * @return 该用户所有评论的列表
     */
    List<Comment> getMyComments(String username);

    /**
     * 收藏或取消收藏某部电影
     * @param username 用户id
     * @param mid 电影id
     * @return 该操作是否执行成功
     */
    boolean likeOrUnlike(String username, int mid);

//    还有评论的接口

    /**
     * 撰写评论
     * @param username 用户id
     * @param mid 电影id
     * @param comment 评论类，里面包含评论日期、评论时间等内容
     * @return 该操作是否执行成功
     */
    boolean writeComment(String username, int mid, Comment comment);


}
