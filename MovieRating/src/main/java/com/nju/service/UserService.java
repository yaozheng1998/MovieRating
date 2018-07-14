package com.nju.service;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 获得某个用户数据
     *
     * @param userId 用户id
     * @return 该用户数据类
     */
    User getUserData(String userId);

    /**
     * 获取该用户收藏喜欢过的电影列表
     *
     * @param userId 用户id
     * @return 该用户收藏过的所有电影
     */
    List<Movie> getLikeMovies(String userId);

    /**
     * 获取该用户的评论 ----- 该评论类中要添加一个属性标明是哪个电影的评论--最好包含该电影id
     *
     * @param userId
     * @return 该用户所有评论的列表
     */
    Map<Movie, List<Comment>> getMyComments(String userId);

    /**
     * 收藏或取消收藏某部电影
     *
     * @param userId 用户id
     * @param mid    电影id
     * @return 该操作是否执行成功
     */
    boolean likeOrUnlike(String userId, int mid);

//    还有评论的接口

    /**
     * 撰写评论
     *
     * @param userId  用户id
     * @param mid     电影id
     * @param comment 评论类，里面包含评论日期、评论时间等内容
     * @return 该操作是否执行成功
     */
    boolean writeComment(String userId, int mid, Comment comment);

    /**
     * 删除自己写的评论
     *
     * @param userId    用户id
     * @param commentId 评论id
     * @return
     */
    boolean deleteComment(String userId, int commentId);
}
