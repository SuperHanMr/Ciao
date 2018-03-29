package com.example.hanyonghui.ciao.bean.eventbusmodel;

import com.lzy.okgo.model.HttpParams;

/**
 * Created by hanyonghui on 2017/8/7.
 */

public class TagModel {

    private HttpParams httpParams;

    private int type;
    public TagModel(HttpParams httpParams ,int type){
        this.httpParams = httpParams;
    }

    public HttpParams getHttpParams(){
        return httpParams;
    }

    public int getTypefrgemnt() {
        return type;
    }
}
