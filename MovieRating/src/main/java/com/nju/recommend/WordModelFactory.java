package com.nju.recommend;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * 演示词向量的训练与应用
 */
public class WordModelFactory {

    private static WordVectorModel wordVectorModel;
    private static DocVectorModel docVectorModel;

    public static synchronized WordVectorModel getWordModel() {
        if (wordVectorModel == null) {
            try {
                wordVectorModel = new WordVectorModel(RecommendConfig.MODEL_FILE_NAME);
                return wordVectorModel;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordVectorModel;
    }

    public static synchronized DocVectorModel getDocModel() {
        if (docVectorModel == null) {
            if (wordVectorModel == null) {
                try {
                    wordVectorModel = new WordVectorModel(RecommendConfig.MODEL_FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                docVectorModel = new DocVectorModel(wordVectorModel);
            }
        }
        return docVectorModel;
    }
}
