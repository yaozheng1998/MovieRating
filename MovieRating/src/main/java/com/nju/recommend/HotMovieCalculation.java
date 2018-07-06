package com.nju.recommend;

import antlr.StringUtils;
import com.nju.dao.HotMovieDao;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.datautil.StringUtil;
import com.nju.entity.HotMovie;
import com.nju.entity.Movie;
import com.nju.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * create by stephen on 2018/7/6
 */
@Component
@Transactional
public class HotMovieCalculation {

    @Autowired
    private HotMovieDao hotMovieDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private UserDao userDao;

    /**
     * 每天一点更新一次相似度矩阵
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void calculate() {
        List<Movie> movies = movieDao.findAll();
        Map<String, Integer> doubanId2Id = new HashMap<>(); // 豆瓣电影id-电影id的映射
        for (Movie movie : movies) {
            doubanId2Id.put(String.valueOf(movie.getDoubanId()), movie.getId());
        }

        // 二维数组 保存电影id 和 对应的喜欢的人数
        int[][] movieIdToLikeNum = new int[movies.size()][2];
        for (int i = 0; i < movieIdToLikeNum.length; ++i) {
            movieIdToLikeNum[i][0] = i+1;
            movieIdToLikeNum[i][1] = 0;
        }

        List<User> users = userDao.findAll();
        for (User user : users) {
            String likeAll = (StringUtil.isEmpty(user.getLikes()) ? "" : user.getLikes()) + ","
                    + (StringUtil.isEmpty(user.getCollected()) ? "" : user.getCollected());
            if (likeAll.isEmpty()) continue;
            String[] likeDoubanId = likeAll.split(",");
            for (String s: likeDoubanId) {
                int id = doubanId2Id.getOrDefault(s, -1);
                if (id == -1) continue;
                movieIdToLikeNum[id-1][1]++;
            }
        }

        Arrays.sort(movieIdToLikeNum, (o1, o2) -> Integer.compare(o2[1] - o1[1], 0));

        List<HotMovie> res = new ArrayList<>();
        for (int i=0; i<RecommendConfig.HOT_SIZE; ++i) {
            HotMovie hotMovie = new HotMovie();
            hotMovie.setMovieId(movieIdToLikeNum[i][0]);
            hotMovie.setRank(i);
            res.add(hotMovie);
        }

        for (int i=0; i<movieIdToLikeNum.length; ++i) {
            System.out.println(movieIdToLikeNum[i][0] + " " + movieIdToLikeNum[i][1]);
        }


        hotMovieDao.deleteAll();
        hotMovieDao.saveAll(res);
    }
}
