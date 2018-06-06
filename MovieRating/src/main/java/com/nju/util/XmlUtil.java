package com.nju.util;


import com.nju.entity.Comment;
import com.nju.entity.DoubanComment;
import com.nju.entity.User;
import com.thoughtworks.xstream.XStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XML数据集成的工具类
 */
public class XmlUtil {

    public static final String COMMENT_XSLT = "MovieRating/src/main/java/com/nju/xml/style.xslt";

    private static TransformerFactory tf = TransformerFactory.newInstance();

    /**
     * 使用XSLT转换XML文件
     *
     * @param srcXml 源XML
     * @param xslt   XSLT文件路径
     */
    public static String transformXmlByXslt(String srcXml, String xslt) {
        // 获取转换器工厂
        String result = null;
        try {
            // 获取转换器对象实例
            Transformer transformer = tf.newTransformer(new StreamSource(xslt));
            // 进行转换
            ByteArrayInputStream bis = new ByteArrayInputStream(srcXml.getBytes());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transformer.transform(new StreamSource(bis), new StreamResult(bos));
            result = bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 调用toXML 将对象转成字符串
     */
    public static String beanToXml(Object object) {
        XStream xStream = new XStream();
        xStream.alias("comments", List.class);
        xStream.alias("user", User.class);
        xStream.alias("doubanComment", DoubanComment.class);
        xStream.alias("comment", Comment.class);
        return xStream.toXML(object);
    }

    /**
     * 调用toXML 将对象转成字符串
     */
    public static List xmlToBean(String xml) {
        XStream xStream = new XStream();
        xStream.alias("comments", List.class);
        xStream.alias("user", User.class);
        xStream.alias("doubanComment", DoubanComment.class);
        xStream.alias("comment", Comment.class);
        return (List) xStream.fromXML(xml);
    }


    public static void main(String[] args) {
        DoubanComment doubanComment1 = new DoubanComment();
        User user1 = new User();
        user1.setName("anxinyi");
        user1.setAvatarUrl("http://www.baidu.com");
        doubanComment1.setUser(user1);
        doubanComment1.setDate("2018-06-06");
        doubanComment1.setDoubanRate(4.8);
        doubanComment1.setDoubanContent("阿斯顿克服恐惧啊邵栋副科级阿克琉斯决定放辣椒看风景啊类阿看电视剧" +
                "阿看第六集 阿都是会计法累计收到反馈啦就是代理反馈记录啊");

        DoubanComment doubanComment2 = new DoubanComment();
        User user2 = new User();
        user2.setName("wangyy");
        user2.setAvatarUrl("http://www.google.com");
        doubanComment2.setUser(user2);
        doubanComment2.setDate("2018-06-01");
        doubanComment2.setDoubanRate(4);
        doubanComment2.setDoubanContent("述我们看到了结果，但是貌似和我们想要的不太一样呀，怎么有些是全路径名称呢？" +
                "这里解决方法很简单。主要讲思路。因为关系到xml转对象时候封装是否报错的问题。" +
                "默认情况下当对象没有名字的时候，例如最一开始的对象 以及对象中的集合类型中泛型的类型，" +
                "这些我们都没办法给他起名字的，默认情况下它是全路径名称。所以如果我们不处理，" +
                "当xml转对象的时候传来的xml直接都是名字");

        List<DoubanComment> list = new ArrayList<>();
        list.add(doubanComment1);
        list.add(doubanComment2);

        String srcXml = beanToXml(list);
        System.out.println(srcXml);

        List list1 = xmlToBean(srcXml);
        System.out.println(list1.get(0).toString());
        System.out.println(list1.get(1).toString());

        System.out.println();
        System.out.println();

        String targetXml = transformXmlByXslt(srcXml, COMMENT_XSLT);
        System.out.println(targetXml);
    }
}