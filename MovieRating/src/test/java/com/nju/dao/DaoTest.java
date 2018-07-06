package com.nju.dao;

import com.nju.IntegrationApplication;
import com.nju.dao.MTimeScoreDao;
import com.nju.dao.MaoYanScoreDao;
import com.nju.dao.MovieDao;
import com.nju.entity.MTime;
import com.nju.entity.MaoYan;
import com.nju.entity.Movie;
import com.nju.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * create by stephen on 2018/6/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class DaoTest {

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MaoYanScoreDao maoYanScoreDao;
    @Autowired
    private MTimeScoreDao mTimeScoreDao;


    @Test
    public void test() throws Exception {
    }

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

}
