package com.example.hanyonghui.ciao.view.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by hanyonghui on 2017/7/18.
 */

abstract public class BaseFragment extends Fragment {

    @BindView(R.id.zhiwu_quxiao)
   public RelativeLayout zhiwuQuxiao;
    @BindView(R.id.zhiwu_search)
   public LinearLayout zhiwuSearch;

    Unbinder unbinder;
    @BindView(R.id.zhiwu_shousuo)
    public ImageView zhiwuShousuo;
    @BindView(R.id.zhiwu_search_ed)
    public EditText zhiwuSearchEd;
    @BindView(R.id.shousuo_icon)
    public ImageView shousuoIcon;

    private ImageView baseFragmentToolbarIvLeft;

    private TextView baseFragmentToolbarTv;

    public ImageView baseFragmentToolbarIvFight;

    private FrameLayout mContainer;

    public Toolbar toolbar;
    public TextView tvFight;
    public TextView teLeft;


    // 创建Fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // 初始化布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        baseFragmentToolbarIvLeft = (ImageView) view.findViewById(R.id.base_fragment_toolbar_iv_left);
        baseFragmentToolbarTv = (TextView) view.findViewById(R.id.base_fragment_toolbar_tv);
        baseFragmentToolbarIvFight = (ImageView) view.findViewById(R.id.base_fragment_toolbar_iv_fight);
        mContainer = (FrameLayout) view.findViewById(R.id.container);
        tvFight = (TextView) view.findViewById(R.id.base_fragment_toolbar_tv_fight);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        teLeft = (TextView) view.findViewById(R.id.base_fragment_toolbar_tv_left);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
        loData();
    }

    protected abstract void loData();

    // 初始化标题 让每个子类去实现
    protected abstract void initTitle();


    // 设置标题内容
    public void setTitle(String title) {
        baseFragmentToolbarTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        baseFragmentToolbarTv.setText(title);
    }
//    //设置左边的图标
//    public void setLeftIcon(int r){
//        baseFragmentToolbarIvLeft.setImageResource(r);
//    }
//    // 设置右边的图标
//    public void setRightIcon(int r){
//        baseFragmentToolbarIvFight.setImageResource(r);
//    }


    // 左边的图标是否显示
    public void isVisibility(boolean is) {
        if (is) {
            baseFragmentToolbarIvLeft.setVisibility(View.VISIBLE);
        } else {
            baseFragmentToolbarIvLeft.setVisibility(View.GONE);
        }
    }

    // 创建内容方法
    public abstract View createContent();

    //向FrameLayout容器添加内容
    public void addView(View view) {
        // 清空里面的内容
        mContainer.removeAllViews();
        mContainer.addView(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
