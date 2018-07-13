package com.nju.service.impl;

import com.nju.dao.DoubanCommentDao;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.entity.Comment;
import com.nju.entity.DoubanComment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    DoubanCommentDao doubanCommentDao;
    @Autowired
    MovieDao movieDao;

    @Override
    public User getUserData(String userId) {

        return userDao.findByUserId(userId);
    }

    @Override
    public List<Movie> getLikeMovies(String userId) {

        User user = userDao.findByUserId(userId);
        String[] likes = user.getLikes().split(",");
        List<Integer> movieId = new ArrayList<>();
        for (int i = 0; i < likes.length; i++) {
            movieId.add(Integer.parseInt(likes[i]));
        }
        List<Movie> movies = movieDao.findAllByDoubanId(movieId);

        return movies;
    }

    @Override
    public List<Comment> getMyComments(String userId) {

        List<DoubanComment> doubanComments = doubanCommentDao.findByName(userId);
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < doubanComments.size(); i++) {
            Comment comment = new Comment();
        }
        return null;
    }

    @Override
    public boolean likeOrUnlike(String userId, int mid) {

        User user = userDao.findByUserId(userId);

        if (user == null) {
            return false;
        }

        String collected = user.getCollected();
        String movieId = mid + "";

        if (collected == null) {
            collected = movieId;
        } else {
            int idx = collected.indexOf(movieId);
            if (idx >= 0) {
                collected = collected.substring(0, idx) + collected.substring(idx + movieId.length() + 1, collected.length() - 1);
            } else {
                collected += "," + movieId;
            }
        }

        user.setCollected(collected);
        try {
            userDao.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean writeComment(String userId, int mid, Comment comment) {
        User user = userDao.findByUserId(userId);
        DoubanComment doubanComment = new DoubanComment();
        doubanComment.setDoubanId(mid);
        doubanComment.setUid(user.getUserId());
        doubanComment.setAvatar(comment.getAvatar());
        doubanComment.setSignature(user.getSignature());
        doubanComment.setName(userId);
        doubanComment.setContent(comment.getContent());
        doubanComment.setCreate_at(comment.getDate());
        doubanComment.setRating((int) comment.getRate());
        try {
            doubanCommentDao.save(doubanComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
