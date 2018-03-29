package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.view.adapter.MianViewPagerAdapter;
import com.example.hanyonghui.ciao.view.fragment.MessageCommentsFragment;
import com.example.hanyonghui.ciao.view.fragment.MessageInformFragment;
import com.example.hanyonghui.ciao.view.fragment.MessageThumbFragment;
import com.example.hanyonghui.ciao.view.views.NoSorollViewpager;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/26.
 * Holle Android
 */

public class MessageActivity extends AutoLayoutActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.message_vp)
    NoSorollViewpager messageVp;
    @BindView(R.id.message_rab)
    RadioGroup messageRab;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_ac);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        initView();

    }

    private void initView() {
        // 创建三个Fragment

        fragments = new ArrayList<>();
        fragments.add(new MessageInformFragment());
        fragments.add(new MessageCommentsFragment());
        fragments.add(new MessageThumbFragment());

        messageVp.setAdapter(new MianViewPagerAdapter(getSupportFragmentManager(), fragments));
        messageRab.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        // 监听点击第几个底部图标 从而改变切换响应的页面
        int item = 0;
        switch (checkedId) {
            case R.id.btn_0:
                item = 0;
                break;
            case R.id.btn_1:
                item = 1;
                break;
            case R.id.btn_2:
                item = 2;
                break;
        }
        messageVp.setCurrentItem(item, false);
    }


    @OnClick(R.id.message_back)
    public void onViewClicked() {
        finish();
    }

}
