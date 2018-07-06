package com.nju.recommend;

public interface RecommendConfig {

    // 相似矩阵存储的文件位置
    String SIMILARITY_FILE = "data/test/similarity.txt";

    // 基于内容和基于物品的协同过滤
    // 两种相似度算法的占比大小
     double CONTENT_BASE_RATIO = 0.5;
     double ITEM_CF_RATIO = 0.5;

     // 推荐的数量
     int RECOMMEND_SIZE = 5;
     // 热门电影的数量
     int HOT_SIZE = 5;



}
