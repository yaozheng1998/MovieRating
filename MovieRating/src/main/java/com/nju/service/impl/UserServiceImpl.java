package com.nju.service.impl;

import com.nju.dao.DoubanCommentDao;
import com.nju.dao.UserDao;
import com.nju.entity.Comment;
import com.nju.entity.DoubanComment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    DoubanCommentDao doubanCommentDao;

    @Override
    public User getUserData(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public List<Movie> getRecommendedMovies(String username) {

        return null;
    }

    @Override
    public List<Movie> getLikeMovies(String username) {
        return null;
    }

    @Override
    public List<Comment> getMyComments(String username) {

        List<DoubanComment> doubanComments = doubanCommentDao.findByName(username);
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < doubanComments.size(); i++) {
            Comment comment=new Comment();
        }
        return null;
    }

    @Override
    public boolean likeOrUnlike(String username, int mid) {
        return false;
    }

    @Override
    public boolean writeComment(String username, int mid, Comment comment) {
        return false;
    }
}
