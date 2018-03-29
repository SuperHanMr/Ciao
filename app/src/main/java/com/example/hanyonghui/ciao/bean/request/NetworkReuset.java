package com.example.hanyonghui.ciao.bean.request;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

/**
 * Created by hanyonghui on 2017/7/22.
 */

public class NetworkReuset {

    private static NetworkReuset  instance = null;

    public static NetworkReuset getInstance(){
        if (instance==null){
            instance = new NetworkReuset();
        }
        return instance;
    }


    public void PostReuset(String url,HttpParams params,StringCallback callback){
        OkGo.post(url)
            .params(params)
            .execute(callback);
    }


    // get请求
    public void GetReuset(String url, StringCallback callback){
        OkGo.get(url)
            .cacheMode(CacheMode.DEFAULT)
            .execute(callback);
    }
}
