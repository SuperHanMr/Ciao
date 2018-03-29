package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CommuntiyPostListBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.PostDatailsModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.ConmunityPostsListAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/9/21.
 */

public class MePostListActivity extends AutoLayoutActivity {


    @BindView(R.id.community_recyclerview)
    XRecyclerView communityRecyclerview;
    private HttpParams params = new HttpParams();
    private ConmunityPostsListAdapter adapter;
    private List<CommuntiyPostListBean.CBean.DBean> dBean = new ArrayList<>();
    private boolean isLoData;
    private int page = 1;
    private int total;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_post_list);
        ButterKnife.bind(this);
        initView(); // 初始化View
        loData(); // 加载数据
    }

    private void loData() {

        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        params.put("pageNo", page);
        NetworkReuset.getInstance().PostReuset(RequestUrls.MEPOSTLIST, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null) {
                    Gson gson = new Gson();
                    CommuntiyPostListBean bean = gson.fromJson(s, CommuntiyPostListBean.class);
                    total = bean.getC().getTotal();
                    // 获取到帖子列表集合
                    List<CommuntiyPostListBean.CBean.DBean> d = bean.getC().getD();
                    if (isLoData){
                        dBean.clear();
                        dBean.addAll(d);
                    }else {
                        dBean.addAll(d);

                    }
                    adapter.setmList(dBean);
                }
            }
        });
    }

    private void initView() {
        //初始化RecyclerView 设置管理器 以及Adapter
        // RecyclerView 下拉刷洗 上拉加载更多
        communityRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        communityRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);

        //设置RecyclerView 管理器
        LinearLayoutManager mMabager = new LinearLayoutManager(this);
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        communityRecyclerview.setLayoutManager(mMabager);
        // 给RecyclerView 设置Adapter
        adapter = new ConmunityPostsListAdapter(this);
        communityRecyclerview.setAdapter(adapter);

        communityRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoData = true;
                page=1;
                loData();
                communityRecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                isLoData = false;
                page++;
                if (3>page){
                    loData();
                }else {
                    MyToast.show(MePostListActivity.this,"没有更多数据");
                }
                communityRecyclerview.refreshComplete();

            }
        });

    }



    @OnClick(R.id.me_post_list)
    public void onViewClicked() {
        finish();
    }

}
