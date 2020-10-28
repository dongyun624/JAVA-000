package com.pratise.JavaNettyTask;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

/**
 * @author : dabing
 * @date : 2020/10/28
 */
public class OkHttpUtil {

    public String doRequest(){
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder().url("http://localhost:8808/test").build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            return null;
        } finally {
            client.clone();
        }
    }

}
