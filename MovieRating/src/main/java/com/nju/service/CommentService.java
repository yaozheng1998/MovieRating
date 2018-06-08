package com.nju.service;

import com.nju.entity.Comment;

import java.util.List;

/**
 * create by stephen on 2018/6/8
 */
public interface CommentService {

    /**
     * 获取movieId影片对应的所有影评（三家）
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    List<Comment> loadAllComments(int movieId);

    /**
     * 获取豆瓣上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    List<Comment> loadCommentsFromDouban(int movieId);

    /**
     * 获取猫眼上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    List<Comment> loadCommentsFromMaoyan(int movieId);

    /**
     * 获取时光网上的movieId对应的所有影评
     * @param movieId movie的id
     * @return 对应的Comment的ArrayList
     */
    List<Comment> loadCommentsFromTime(int movieId);
}
