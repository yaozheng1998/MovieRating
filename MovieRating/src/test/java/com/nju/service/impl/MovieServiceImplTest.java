package com.nju.service.impl;

import com.nju.IntegrationApplication;
import com.nju.dao.*;
import com.nju.entity.Comment;
import com.nju.entity.MTime;
import com.nju.entity.MaoYan;
import com.nju.entity.Movie;
import com.nju.service.CommentService;
import com.nju.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class MovieServiceImplTest {

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MaoYanScoreDao maoYanScoreDao;
    @Autowired
    private MTimeScoreDao mTimeScoreDao;

    @Autowired
    private MovieService movieService;
    @Autowired
    private CommentService commentService;

    @Test
    public void test1() throws Exception {
        List<MTime> mTimes = mTimeScoreDao.findAll();
        for (MTime mTime: mTimes) {
            if (mTime.getRate() < 0) {
                mTime.setRate(0);
            }
        }
        mTimeScoreDao.saveAll(mTimes);

        List<MaoYan> maoYans = maoYanScoreDao.findAll();
        for (MaoYan maoYan: maoYans) {
            if (maoYan.getScore() < 0) {
                maoYan.setScore(0);
            }
        }
        maoYanScoreDao.saveAll(maoYans);
    }

    @Test
    public void test2() throws Exception {
        List<Movie> movies = movieDao.findAll();
        for (Movie movie: movies) {

            MTime mTime = mTimeScoreDao.getOne(movie.getMtimeId());
            MaoYan maoYan = maoYanScoreDao.getOne(movie.getMaoyanId());
            movie.setMTimeRating(mTime.getRate());
            movie.setMaoyanRating(maoYan.getScore());
        }
        movieDao.saveAll(movies);
    }


    @Test
    public void test3() throws Exception {
        List<Comment> comments =commentService.loadCommentsFromMaoyan(3);
        System.out.println(comments.size());
        System.out.println(comments.get(0).toString());
    }


}