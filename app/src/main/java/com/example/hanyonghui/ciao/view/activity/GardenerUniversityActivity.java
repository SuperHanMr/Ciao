package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/9/12.
 */

public class GardenerUniversityActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sproutguide_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
    }


    @OnClick({R.id.university_brck, R.id.university_seeds_ll, R.id.university_maker_ll, R.id.university_lifestyle_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.university_brck:
                finish();
                break;
            case R.id.university_seeds_ll:  // webView 跳转到种子俱乐部
                startActivity(new Intent(GardenerUniversityActivity.this,GardenerWebViewActivity.class).putExtra("type",1+""));
                break;
            case R.id.university_maker_ll:  // webView 跳转到创客教育
                startActivity(new Intent(GardenerUniversityActivity.this,GardenerWebViewActivity.class).putExtra("type",2+""));
                break;
            case R.id.university_lifestyle_ll:  // webView 跳转到生活美学
                startActivity(new Intent(GardenerUniversityActivity.this,GardenerWebViewActivity.class).putExtra("type",3+""));

                break;
        }
    }
}
