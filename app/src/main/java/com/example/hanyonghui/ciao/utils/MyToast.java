package com.example.hanyonghui.ciao.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanyonghui.ciao.R;

/**
 * Created by Apple on 2016/9/26.
 * 吐司的工具类
 */
public final  class MyToast {

    public static void show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void massageToast(Context context,int num){
        Toast toast = new Toast(context);
        View layout = View.inflate(context, R.layout.massage_toast, null);
        TextView textView = (TextView) layout.findViewById(R.id.toast_mun);
        textView.setText("当前亮度为"+num+"%");
        toast.setView(layout);
        // 设置土司显示在屏幕的位置
//        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 100);
        toast.show();
    }

}
