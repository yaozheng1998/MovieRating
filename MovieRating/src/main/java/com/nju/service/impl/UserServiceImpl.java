package com.nju.service.impl;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User getUserData(String username) {
        return null;
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
