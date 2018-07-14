package com.nju.service.impl;

import com.nju.dao.HotMovieDao;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.datautil.StringUtil;
import com.nju.entity.HotMovie;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.recommend.RecommendConfig;
import com.nju.recommend.SimilarityCalculation;
import com.nju.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * create by stephen on 2018/7/6
 */
@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private SimilarityCalculation similarityCalculation;

    @Autowired
    private UserDao userDao;
    @Autowired
    private MovieDao movieDao;

    @Autowired
    private HotMovieDao hotMovieDao;

    private double[][] similarityMatrix;

    @Override
    public List<Movie> getRecommendedMovies(String userId) {
        User user = userDao.findByUserId(userId);
        String likeAll = (StringUtil.isEmpty(user.getLikes()) ? "" : user.getLikes()) + ","
                + (StringUtil.isEmpty(user.getCollected()) ? "" : user.getCollected());

        // PART 1 推荐热门 喜欢的人数最多的电影
        if (StringUtil.isEmpty(likeAll)) {
            List<HotMovie> hotMovies = hotMovieDao.findAll();
            List<Integer> ids = new ArrayList<>();
            for (HotMovie hotMovie : hotMovies) {
                ids.add(hotMovie.getMovieId());
            }
            return movieDao.findAllById(ids);
        }

        // PART 2 个性化推荐
        if (similarityMatrix == null) {
            similarityMatrix = similarityCalculation.getSimilarityMatrix();
        }

        String[] likeDoubanId = likeAll.split(",");
        List<Movie> movies = movieDao.findAll();
        Map<String, Integer> doubanId2Id = new HashMap<>(); // 豆瓣电影id-电影id的映射
        for (Movie movie : movies) {
            doubanId2Id.put(String.valueOf(movie.getDoubanId()), movie.getId());
        }

        // 二维数组 保存电影id 和 对应的权重
        double[][] movieIdToWeight = new double[movies.size()][2];
        for (int i = 0; i<movieIdToWeight.length; ++i) {
            movieIdToWeight[i][0] = i;
            movieIdToWeight[i][1] = 0;
        }

        // 计算所有电影相似度
        for (String aLikeDoubanId : likeDoubanId) {
            int id = doubanId2Id.getOrDefault(aLikeDoubanId, -1);
            if (id == -1) continue;
            for (int i = 0; i < similarityMatrix[0].length; ++i) {
                if (i == id-1) continue;  // 排除自身
                movieIdToWeight[i][1] = Math.max(movieIdToWeight[i][1], similarityMatrix[id-1][i]);
            }
        }

        // 排序取前五
        Arrays.sort(movieIdToWeight, (o1, o2) -> o2[1] - o1[1] > 0 ? 1 : -1);

        List<Movie> result = new ArrayList<>();
        for (int i=0; i< RecommendConfig.RECOMMEND_SIZE; ++i) {
            result.add(movies.get((int)movieIdToWeight[i][0]));
        }
        return result;
    }


    @Override
    public void likeRecommend(Movie movie, String userId) {
        User user = userDao.findByUserId(userId);
        if (!user.getLikes().contains(String.valueOf(movie.getDoubanId()))) {
            user.setLikes(user.getLikes() + "," + String.valueOf(movie.getDoubanId()));
            userDao.save(user);
        }
    }

    @Override
    public void dislikeRecommend(Movie movie, String userId) {
        User user = userDao.findByUserId(userId);
        if (!user.getDislikes().contains(String.valueOf(movie.getDoubanId()))) {
            user.setDislikes(user.getDislikes() + "," + String.valueOf(movie.getDoubanId()));
            userDao.save(user);
        }
    }
}
