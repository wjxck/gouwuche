package com.bwei.wangjianxun20171123.net;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 作者：王建勋
 * 时间：2017-11-23 13:40
 * 类的用途：网络请求工具类
 */

public class HttpUtils {

    private static HttpUtils httpUtils;
    private final OkHttpClient client;

    private HttpUtils() {
        //创建OkHttpClient
        client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())//设置拦截器
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static HttpUtils getHttpUtils() {
        if (httpUtils == null) {
            //同步锁
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * GET请求
     *
     * @param url
     * @param callback
     */
    public void doGet(String url, Callback callback) {
        //创建Request
        Request request = new Request.Builder().url(url).build();
        //提交请求
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST请求
     * @param url
     * @param params
     * @param callback
     */
    public void doPost(String url, Map<String, String> params, Callback callback) {
        //创建FormBody
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        //创建Request
        Request request = new Request.Builder().url(url).post(formBody).build();
        //提交请求
        client.newCall(request).enqueue(callback);
    }

}
