package com.example.hanyonghui.ciao;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.hanyonghui.ciao.bean.request.LoggerInterceptor;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hanyonghui on 2017/7/18.
 */

public class APP extends Application{

    public static Context context;


    private List<Activity> activitys = null;

    private static APP  instance;


    public static Context getInstance() {
        return context;
    }

    public APP(){
        activitys = new LinkedList();
    }



    public static APP getApp(){

        if (instance==null){
            instance = new APP();
        }
        return   instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if(!activitys.contains(activity)){
                activitys.add(activity);
            }
        }else{
            activitys.add(activity);
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity  activity : activitys) {
                activity.finish();
            }
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);

        context=getApplicationContext();
        OkGo.getInstance().addInterceptor(new LoggerInterceptor());
    }
}
