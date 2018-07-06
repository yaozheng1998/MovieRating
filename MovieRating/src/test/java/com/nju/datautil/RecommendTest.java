package com.nju.datautil;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.nju.IntegrationApplication;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.recommend.SimilarityCalculation;
import com.nju.recommend.WordModelFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;

/**
 * create by stephen on 2018/7/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class RecommendTest {

    private static final String SIMILARITY_FILE = "data/test/similarity.txt";

    @Autowired
    MovieDao movieDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SimilarityCalculation similarityCalculation;

    @Test
    public void test() {
        System.out.println(similarityCalculation.getSimilarityMatrix()[0][1]);
    }

    @Test
    public void calculate() {
        List<Movie> movies = movieDao.findAll();
        double[][] similarityMatrix = new double[movies.size()][movies.size()];
        for (double[] doubles : similarityMatrix) {
            Arrays.fill(doubles, 0);
        }

        Map<String, Integer> doubanId2Id = new HashMap<>(); // 豆瓣电影id-电影id的映射
        for (Movie movie : movies) {
            doubanId2Id.put(String.valueOf(movie.getDoubanId()), movie.getId());
        }

        // 电影id-喜欢的人数
        int[] likeSum = new int[movies.size()];
        Arrays.fill(likeSum, 0);

        List<User> users = userDao.findAll();
        for (User user : users) {
            if (user.getLikes().isEmpty() && user.getCollected().isEmpty()) continue;

            // 将收藏电影和喜欢的电影拼接
            String temp = (user.getLikes().isEmpty() ? "" : user.getLikes()) + ","
                    + (user.getCollected().isEmpty() ? "" : user.getCollected());

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
                similarityMatrix[i][j] = (similarityMatrix[i][j] * 0.5) / Math.sqrt(likeSum[i] * likeSum[j]);
            }
        }

        // 基于内容
        DocVectorModel docVectorModel = WordModelFactory.getDocModel();

        for (int i = 0; i < movies.size(); ++i) {
            for (int j = 0; j < movies.size(); ++j) {
                if (i != j && similarityMatrix[i][j] == 0) {
                    double temp = docVectorModel.similarity(movies.get(i).getTag().replace(",", ""),
                            movies.get(j).getTag().replace(",", ""));
                    similarityMatrix[i][j] += temp * 0.5;
                }
            }
        }

        try (FileWriter writer = new FileWriter(SIMILARITY_FILE, false)) {

            for (int i = 0; i < movies.size(); ++i) {
                for (int j = 0; j < movies.size(); ++j) {
                    if (j == 0) {
                        writer.write(similarityMatrix[i][j] + "");
                    } else {
                        writer.write("," + similarityMatrix[i][j]);
                    }
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reLoad() {
        double[][] matrix = new double[119][119];
        int row = 0;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(SIMILARITY_FILE)));) {

            String text;
            String[] temp;
            while ((text = bufferedReader.readLine()) != null) {
                temp = text.split(",");
                for (int j = 0; j < 119; ++j) {
                    matrix[row][j] = Double.valueOf(temp[j]);
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        double max = -1;
        int maxX = 0, maxY = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] > 0.1) {
                    System.out.println(matrix[i][j]);
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }

        System.out.println("max: " + max + "  " + maxX + "  " + maxY);

    }
}
