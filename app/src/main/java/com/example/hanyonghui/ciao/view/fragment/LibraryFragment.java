package com.example.hanyonghui.ciao.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.LibraryBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.AddTagModel;
import com.example.hanyonghui.ciao.bean.eventbusmodel.TagModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;

import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.AddPlantsDatavtivity;
import com.example.hanyonghui.ciao.view.activity.TagActivity;
import com.example.hanyonghui.ciao.view.adapter.LibrayTabFragmentAdapter;
import com.example.hanyonghui.ciao.view.base.BaseFragment;
import com.example.hanyonghui.ciao.view.views.WeiboDialogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.viewpagerindicator.TabPageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
/**
 * Created by hanyonghui on 2017/7/21.
 */

public class LibraryFragment extends BaseFragment {


    private LibraryBean libraryBean;
    private ViewPager mVp;
    private TabPageIndicator mTabvp;

    private List<LibraryTabFragment> fragments;
    private List<LibraryBean.CBean> beanList;
    private Dialog dialog;

    private   HttpParams params  = new HttpParams();


    @Override
    protected void initTitle() {
        teLeft.setText("添加植物");
        teLeft.setVisibility(SPUtils.getBoolean(KeyUtils.ADMINISTRATORS,false)?View.VISIBLE:View.GONE);
        teLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddPlantsDatavtivity.class);
                getContext().startActivity(intent);
            }
        });
        setTitle("植物库");
        tvFight.setText("筛选");
        tvFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TagActivity.class);
                getContext().startActivity(intent);
            }
        });


        shousuoIcon.setVisibility(SPUtils.getBoolean(KeyUtils.ADMINISTRATORS,false)?View.GONE:View.VISIBLE);
        shousuoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhiwuSearch.setVisibility(View.VISIBLE);
                shousuoIcon.setVisibility(View.GONE);
            }
        });

        zhiwuQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhiwuSearch.setVisibility(View.GONE);
                shousuoIcon.setVisibility(View.VISIBLE);
                zhiwuSearchEd.setText("");
            }
        });



        zhiwuShousuo.setOnClickListener(new View.OnClickListener() {
            private String plandata;
            @Override
            public void onClick(View v) {
                // 获取搜索植物的关键字 上传服务器
                plandata = zhiwuSearchEd.getText().toString().trim();
                LogUtils.e(plandata);
                params.put("search", plandata);
                EventBus.getDefault().post(new TagModel(params,LibraryTabFragment.typeFrgamnt));
            }
        });

    }

    // 进行网络请求
    @Override
    protected void loData() {
        dialog = WeiboDialogUtils.createLoadingDialog(getContext(), "加载中...");
        NetworkReuset.getInstance().
                GetReuset(RequestUrls.LIBRARYURL, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                // 加载进度条
                processData(s);

            }
        });

    }

    // 创建布局内容
    @Override
    public View createContent() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.library_fragment, (ViewGroup) getView(),false);
        mVp = (ViewPager) view.findViewById(R.id.vp_libray);
        mTabvp = (TabPageIndicator) view.findViewById(R.id.tabpageindicator_libray);
        initViewpager();
        return view;
    }
    // 初始化ViewPageri
    private void initViewpager() {
        fragments = new ArrayList<>();
        for (int i=0;i<=6;i++){
            LibraryTabFragment fragment = new LibraryTabFragment();
            fragment.type = i;
            fragments.add(fragment);
        }
        // 设置适配器
        LibrayTabFragmentAdapter adapter = new LibrayTabFragmentAdapter(getFragmentManager(),fragments,beanList);
        mVp.setAdapter(adapter);
        mTabvp.setViewPager(mVp);
    }
    // 从服务器上获取的字符串 转成json对象
    private void processData(String json) {
        Gson gson = new Gson();
        libraryBean = gson.fromJson(json, LibraryBean.class);
        beanList = libraryBean.getC();
        View view = createContent();
        addView(view);
        WeiboDialogUtils.closeDialog(dialog);

    }
    @Override
    public void onDestroyView() {
        LogUtils.d("销毁LibraryFragment植物库");
        super.onDestroyView();
    }
}
