package com.nju.util;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * XML数据集成的工具类
 */
public class XmlUtil {

    private static TransformerFactory tf = TransformerFactory.newInstance();

    /**
     * 使用XSLT转换XML文件
     *
     * @param srcXml 源XML文件路径
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


    public static void main(String[] args) {
        String srcXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<staffs>\n" +
                "    <staff order=\"1\">\n" +
                "        <id>1</id>\n" +
                "        <name>wang</name>\n" +
                "        <age>27</age>\n" +
                "    </staff>\n" +
                "\n" +
                "    <staff order=\"2\">\n" +
                "        <id>2</id>\n" +
                "        <name>Li</name>\n" +
                "        <age>24</age>\n" +
                "    </staff>\n" +
                "\n" +
                "    <staff order=\"3\">\n" +
                "        <id>3</id>\n" +
                "        <name>Q1</name>\n" +
                "        <age>29</age>\n" +
                "    </staff>\n" +
                "</staffs>\n";
        String xslt = "MovieRating/src/main/java/com/nju/xml/style.xslt";

        System.out.println(transformXmlByXslt(srcXml, xslt));
    }
}