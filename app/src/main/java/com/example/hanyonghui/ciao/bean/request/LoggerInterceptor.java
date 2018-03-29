package com.example.hanyonghui.ciao.bean.request;

/**
 * Created by BM-WGX on 2017/2/5.
 */

import android.util.Log;

import com.example.hanyonghui.ciao.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;


public class LoggerInterceptor implements Interceptor {

    public static final String TAG = "NetWorkLogger";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        printRequestMessage(request);
        Response response = chain.proceed(request);
        printResponseMessage(response);
        return response;
    }
    /**
     * 打印请求消息
     *
     * @param request 请求的对象
     */
    private void printRequestMessage(Request request) {
        if (request == null) {
            return;
        }
        LogUtils.e("Url   : " + request.url().url().toString());
        LogUtils.e("Method: " + request.method());
        LogUtils.e("Heads : " + request.headers());
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return;
        }
        try {
            final Buffer bufferedSink = new Buffer();
            requestBody.writeTo(bufferedSink);
            Charset charset = requestBody.contentType().charset();
            charset = charset == null ? Charset.forName("utf-8") : charset;
            LogUtils.e("请求参数 : "+bufferedSink.readString(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private void printResponseMessage(Response response) {
        if (response == null || !response.isSuccessful()) {
            return;
        }
       ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
//            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        Charset charset = Util.UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null&&contentType.charset()!=null) {
            charset = contentType.charset();
        }
        if (contentLength != 0) {
            String result = buffer.clone()
                    .readString(charset)
                    ;
            LogUtils.e("Response: " + result);
        }
    }
}