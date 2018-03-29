package com.example.hanyonghui.ciao.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by hanyonghui on 2017/7/21.
 */

public class MianViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MianViewPagerAdapter(FragmentManager fm,List fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
