package com.nju.datautil;

import com.hankcs.hanlp.HanLP;
import java.util.List;

/**
 * @Author YZ
 * @Date 30/06/2018
 */
public class ProcessWords {
    //对豆瓣中的评论进行分词，1、选取关键词；2、进行情感分析，褒贬；存放进数据库
    public static void main(String[]args){
        String content="“蓝色”，代表的是忧郁、压力、孤寂……正如这部电影带给人的感受。\n" +
                "\n" +
                "电影中的女主角为了给父亲赡养费，白天在医院做护士，晚上还要到酒店做小姐，对待生活已经接近麻木；\n" +
                "\n" +
                "而男主角则是一个民工，为了生存整日在工地上干体力活，身边不停有人因为意外而死去，每天都活在绝望之中；\n" +
                "\n" +
                "他们两个人，都是城市中最渺小、最没有希望、最微不足道的一群人。\n" +
                "\n" +
                "在经历了一天天充满沮丧的生活后，他们终于找到的出路，也不过是与同样“孤寂”的对方以笨拙的方式相互取暖。\n" +
                "\n" +
                "但谁又忍心苛责和嘲笑他们呢？\n" +
                "\n" +
                "对于处在绝望之中的人来说，努力地活下去，就已经意味着一种胜利……";
        List<String> keywordList= HanLP.extractKeyword(content,5);
        System.out.print(keywordList);
    }
}
