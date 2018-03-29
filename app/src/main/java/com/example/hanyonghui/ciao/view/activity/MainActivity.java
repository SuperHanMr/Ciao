package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.view.adapter.MianViewPagerAdapter;
import com.example.hanyonghui.ciao.view.fragment.CommunityFragment;
import com.example.hanyonghui.ciao.view.fragment.LibraryFragment;
import com.example.hanyonghui.ciao.view.fragment.MeFragment;
import com.example.hanyonghui.ciao.view.fragment.NewGardenFragment;

import com.example.hanyonghui.ciao.view.views.NoSorollViewpager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R.id.main_vp)
    NoSorollViewpager mainVp;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        APP.getApp().addActivity(this);
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new NewGardenFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new LibraryFragment());
        fragments.add(new MeFragment());
        mainVp.setAdapter(new MianViewPagerAdapter(getSupportFragmentManager(), fragments));
        rgTab.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        // 监听点击第几个底部图标 从而改变切换响应的页面
        int item = 0;
        switch (checkedId) {
            case R.id.rb_new:
                item = 0;
                break;
            case R.id.rb_community:
                item = 1;
                break;
            case R.id.rb_library:
                item = 2;
                break;
            case R.id.rb_me:
                item = 3;
                break;
        }
        mainVp.setCurrentItem(item, false);
    }
}
