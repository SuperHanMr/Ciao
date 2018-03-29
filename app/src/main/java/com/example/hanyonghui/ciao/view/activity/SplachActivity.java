package com.example.hanyonghui.ciao.view.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;
/**
 * Created by hanyonghui on 2017/7/18.
 */

public class SplachActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉最顶部的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
         setContentView(R.layout.splach_activity);
        APP.getApp().addActivity(this);

        final String id = SPUtils.getString(KeyUtils.USERID);
        LogUtils.d(id);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    if (id!=null){
                        startActivity(new Intent(SplachActivity.this,MainActivity.class));
                    }else {
                        startActivity(new Intent(SplachActivity.this,LoginActivity.class));
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
