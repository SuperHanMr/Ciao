package com.example.hanyonghui.ciao.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hanyonghui.ciao.bean.bean.LibraryBean;
import com.example.hanyonghui.ciao.view.fragment.LibraryTabFragment;

import java.util.List;

/**
 * Created by hanyonghui on 2017/7/25.
 */

public class LibrayTabFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] str = {"所有植物","菜","花","多肉","树木","水生","藤曼"};

    private   List<LibraryTabFragment> fragments;
    private List<LibraryBean.CBean> libraryBeen;

    public LibrayTabFragmentAdapter(FragmentManager fm ,List<LibraryTabFragment> fragments, List<LibraryBean.CBean> libraryBeen) {
        super(fm);
        this.fragments = fragments;
        this.libraryBeen = libraryBeen;
    }

    @Override
    public Fragment getItem(int position) {
        LibraryTabFragment fragment = fragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return str[position];
    }
}
