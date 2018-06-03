package com.nju.datautil;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nju.dao.MTimeDao;
import com.nju.entity.MTime;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class MTimeDataSpider {

    @Autowired
    MTimeDao mTimeDao;

    public void catchMovieList() {
        String url = "https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=290";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=290");
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    // 打印响应内容
                    String responseBody = EntityUtils.toString(entity);
                    parseData(responseBody);
                }
            } finally {
//                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseData(String data) {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(data);
        JsonArray ms = json.get("ms").getAsJsonArray();
        for (int i = 0; i < ms.size(); i++) {
            JsonObject subObject = ms.get(i).getAsJsonObject();
            MTime mTime = new MTime();
            mTime.setMovieId(subObject.get("id").getAsLong());
            mTime.setName(subObject.get("tCn").getAsString());
            mTime.setRate(subObject.get("r").getAsDouble());
            mTimeDao.save(mTime);
            System.out.println(mTime);
        }
    }

}
