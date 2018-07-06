package com.nju.service.impl;

import com.nju.dao.UserDao;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.recommend.SimilarityCalculation;
import com.nju.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by stephen on 2018/7/6
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private SimilarityCalculation similarityCalculation;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Movie> getRecommendedMovies(String username) {
        User user = userDao.findByUsername(username);

        return null;
    }


    @Override
    public void likeRecommend(Movie movie, String username) {
        User user = userDao.findByUsername(username);
        if (!user.getLikes().contains(String.valueOf(movie.getDoubanId()))) {
            user.setLikes(user.getLikes() + "," + String.valueOf(movie.getDoubanId()));
            userDao.save(user);
        }
    }

    @Override
    public void dislikeRecommend(Movie movie, String username) {
        User user = userDao.findByUsername(username);
        if (!user.getDislikes().contains(String.valueOf(movie.getDoubanId()))) {
            user.setDislikes(user.getDislikes() + "," + String.valueOf(movie.getDoubanId()));
            userDao.save(user);
        }
    }
}
