package com.example.hanyonghui.ciao.bean.eventbusmodel;

import com.lzy.okgo.model.HttpParams;

/**
 * Created by hanyonghui on 2017/9/1.
 */

public class AddTagModel {

    private HttpParams httpParams;
    private int type;

    public AddTagModel(HttpParams httpParams,int type){

        this.httpParams = httpParams;
        this.type = type;
    }

    public HttpParams getHttpParams(){
        return httpParams;
    }


    public  int getTypeFragment(){
        return type;
    }
}
