package com.nju.util;


import com.nju.entity.*;
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

    public static final String DOUBAN_COMMENT_XSLT = "src/main/resources/doubanTrans.xslt";
    public static final String MAOYAN_COMMENT_XSLT = "src/main/resources/maoyanTrans.xslt";
    public static final String MTIME_COMMENT_XSLT = "src/main/resources/mtimeTrans.xslt";

    private static TransformerFactory tf = TransformerFactory.newInstance();


    public static List<Comment> transMaoYan(List<MaoYanComment> maoYanComments) {
        String srcXml = XmlUtil.beanToXml(maoYanComments);
        String targetXml = XmlUtil.transformXmlByXslt(srcXml,XmlUtil.MAOYAN_COMMENT_XSLT);
        return (List<Comment>) XmlUtil.xmlToBean(targetXml);
    }

    public static List<Comment> transMTime(List<MTimeComment> mTimeComments) {
        String srcXml = XmlUtil.beanToXml(mTimeComments);
        String targetXml = XmlUtil.transformXmlByXslt(srcXml,XmlUtil.MTIME_COMMENT_XSLT);
        return (List<Comment>) XmlUtil.xmlToBean(targetXml);
    }

    @SuppressWarnings("uncheck")
    public static List<Comment> transDouban(List<DoubanComment> doubanComments) {
        String srcXml = XmlUtil.beanToXml(doubanComments);
        String targetXml = XmlUtil.transformXmlByXslt(srcXml,XmlUtil.DOUBAN_COMMENT_XSLT);
        return (List<Comment>) XmlUtil.xmlToBean(targetXml);
    }


    /**
     * 使用XSLT转换XML文件
     *
     * @param srcXml 源XML
     * @param xslt   XSLT文件路径
     */
    private static String transformXmlByXslt(String srcXml, String xslt) {
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
    private static String beanToXml(Object object) {
        XStream xStream = new XStream();
        xStream.alias("comments", List.class);
        xStream.alias("doubanComment", DoubanComment.class);
        xStream.alias("maoYanComment", MaoYanComment.class);
        xStream.alias("mTimeComment", MTimeComment.class);
        xStream.alias("comment", Comment.class);
        return xStream.toXML(object);
    }

    /**
     * 调用toXML 将对象转成字符串
     */
    private static List xmlToBean(String xml) {
        XStream xStream = new XStream();
        xStream.alias("comments", List.class);
        xStream.alias("doubanComment", DoubanComment.class);
        xStream.alias("maoYanComment", MaoYanComment.class);
        xStream.alias("mTimeComment", MTimeComment.class);
        xStream.alias("comment", Comment.class);
        return (List) xStream.fromXML(xml);
    }
}