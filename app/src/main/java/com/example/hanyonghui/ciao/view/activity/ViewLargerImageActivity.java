package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.view.adapter.ImageViewpagerAdapter;
import com.example.hanyonghui.ciao.view.views.DepthPageTransformer;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by hanyonghui on 2017/9/15.
 */

public class ViewLargerImageActivity extends AutoLayoutActivity {

    private String url[];
    private int pointion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewpager);
        APP.getApp().addActivity(this);

        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        final TextView tvNmu = (TextView) findViewById(R.id.tv_num);
        // 获取传递过来的URL数组
        url = getIntent().getStringArrayExtra("url");
        // 获取传递过来的pointion
        pointion = getIntent().getIntExtra("pos", 0);

        ImageViewpagerAdapter adapter = new ImageViewpagerAdapter(this,url);
        pager.setAdapter(adapter);

        pager.setCurrentItem(pointion);//设置起始位置

        pager.setPageTransformer(true,new DepthPageTransformer()); // 修改过的ViewPager动画

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvNmu.setText((position + 1) + "/" + url.length);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
