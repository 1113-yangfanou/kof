package com.yang.utils;


import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtil {
    private static final String url = "https://api-cn.faceplusplus.com/facepp/v3/face/analyze";
    private static final String api_key = "7M-nNVlV2-d9xNy6AHwvAN_vFSolbWex";
    private static final String api_secret = "HaiZTaPB--Az745QmCvEy_kWQoYBLdAn";
    public static String result(String face_token) {
        Map<String, String> map = new HashMap<>();
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("api_key", api_key));
        list.add(new BasicNameValuePair("api_secret", api_secret));
        list.add(new BasicNameValuePair("face_tokens", face_token));
        list.add(new BasicNameValuePair("return_landmark", "2"));
        list.add(new BasicNameValuePair("return_attributes", "gender,beauty,age,emotion,skinstatus"));
        return httpRequest(url, list);
    }
    private static String httpRequest(String RequestUrl, List<BasicNameValuePair> list) {
        StringBuffer buffer = new StringBuffer();
        try {
            HttpPost post = new HttpPost(RequestUrl);
            post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

