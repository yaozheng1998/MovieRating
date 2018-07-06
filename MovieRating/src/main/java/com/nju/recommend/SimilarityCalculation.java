package com.nju.recommend;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.datautil.IOUtil;
import com.nju.datautil.StringUtil;
import com.nju.entity.Movie;
import com.nju.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 相似度计算的任务
 * create by stephen on 2018/7/6
 */
@Component
@Transactional
public class SimilarityCalculation {

    private MovieDao movieDao;

    private UserDao userDao;

    @Autowired
    public SimilarityCalculation(MovieDao movieDao, UserDao userDao) {
        this.movieDao = movieDao;
        this.userDao = userDao;
    }

    public double[][] getSimilarityMatrix() {
        if (!IOUtil.isFileExisted(RecommendConfig.SIMILARITY_FILE)) {
            calculate();
        }
        return IOUtil.readArrayFromFile(RecommendConfig.SIMILARITY_FILE);
    }

    /**
     * 每天两天更新一次相似度矩阵
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void calculate() {
        List<Movie> movies = movieDao.findAll();
        double[][] similarityMatrix = new double[movies.size()][movies.size()];
        for (double[] doubles : similarityMatrix) {
            Arrays.fill(doubles, 0);
        }
        contentBaseCalculate(similarityMatrix, movies);
        itemCFCalculate(similarityMatrix, movies);
        IOUtil.saveArrayToFile(similarityMatrix, RecommendConfig.SIMILARITY_FILE);
    }


    /**
     * 基于物品的协同过滤算法
     */
    private void contentBaseCalculate(double[][] similarityMatrix, List<Movie> movies) {
        Map<String, Integer> doubanId2Id = new HashMap<>(); // 豆瓣电影id-电影id的映射
        for (Movie movie : movies) {
            doubanId2Id.put(String.valueOf(movie.getDoubanId()), movie.getId());
        }

        // 电影id-喜欢的人数
        int[] likeSum = new int[movies.size()];
        Arrays.fill(likeSum, 0);

        List<User> users = userDao.findAll();
        for (User user : users) {
            if (StringUtil.isEmpty(user.getLikes()) && StringUtil.isEmpty(user.getCollected())) continue;

            // 将收藏电影和喜欢的电影拼接
            String temp = (StringUtil.isEmpty(user.getLikes()) ? "" : user.getLikes()) + ","
                    + (StringUtil.isEmpty(user.getCollected()) ? "" : user.getCollected());

            String[] likes = temp.split(",");
            for (int i = 0; i < likes.length; ++i) {
                int xMovieId = doubanId2Id.getOrDefault(likes[i], -1);
                if (xMovieId == -1) continue;
                likeSum[xMovieId - 1]++;   // 统计like的总数

                for (int j = 0; j < likes.length; ++j) {
                    if (i == j) continue;
                    int yMovieId = doubanId2Id.getOrDefault(likes[j], -1);
                    if (yMovieId == -1) continue;
                    similarityMatrix[xMovieId - 1][yMovieId - 1] += 1;
                }
            }
        }

        for (int i = 0; i < movies.size(); ++i) {
            for (int j = 0; j < movies.size(); ++j) {
                if (i == j) similarityMatrix[i][j] = 0;     // 忽略同一个电影相似度
                if (similarityMatrix[i][j] == 0) continue;
                similarityMatrix[i][j] = (similarityMatrix[i][j] *RecommendConfig.CONTENT_BASE_RATIO)
                        / Math.sqrt(likeSum[i] * likeSum[j]);
            }
        }

    }

    /**
     * 基于内容的相似度计算
     */
    private void itemCFCalculate(double[][] similarityMatrix, List<Movie> movies) {
        DocVectorModel docVectorModel = WordModelFactory.getDocModel();

        for (int i = 0; i < movies.size(); ++i) {
            for (int j = 0; j < movies.size(); ++j) {
                if (i != j && similarityMatrix[i][j] == 0) {
                    // 加上电影类别
                    String a =(movies.get(i).getTag() + movies.get(i).getGenres())
                            .replace(",", "")
                            .replace(" ", "");
                    String b =(movies.get(j).getTag() + movies.get(j).getGenres())
                            .replace(",", "")
                            .replace(" ", "");
                    double temp = docVectorModel.similarity(a,b);
                    similarityMatrix[i][j] += temp * RecommendConfig.ITEM_CF_RATIO;
                }
            }
        }
    }
}
