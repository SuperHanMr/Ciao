package com.example.hanyonghui.ciao.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CommuntiyPostListBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.PostDatailsModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.MessageActivity;
import com.example.hanyonghui.ciao.view.activity.PostingActivity;
import com.example.hanyonghui.ciao.view.adapter.ConmunityPostsListAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.melnykov.fab.FloatingActionButton;

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
 * Created by hanyonghui on 2017/7/21.
 * 社区
 */

public class CommunityFragment extends Fragment {


    private static final String TAG = "CommunityFragment";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.community_search)
    LinearLayout communitySearch;
    @BindView(R.id.community_search_ed)
    EditText communitySearchEd;

    private HttpParams params = new HttpParams();
    private XRecyclerView recyclerView;
    private ConmunityPostsListAdapter adapter;
    private List<CommuntiyPostListBean.CBean.DBean> dBean = new ArrayList<>();

    // 上拉下拉
    private int page = 1;
    private int total;
    private FloatingActionButton fab;
    private int pages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_fragment, container, false);
        ButterKnife.bind(this, view);
        recyclerView = (XRecyclerView) view.findViewById(R.id.community_recyclerview);
        fab = (FloatingActionButton) view.findViewById(R.id.community_fab);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(); // 初始化View
        loData(true); // 加载数据
    }

    private void loData(final boolean isLoData ) {

        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        params.put("pageNo", page);
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTLIST, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null) {
                    Gson gson = new Gson();
                    CommuntiyPostListBean bean = gson.fromJson(s, CommuntiyPostListBean.class);
                    total = bean.getC().getTotal();
                    pages = bean.getC().getPages();
                    // 获取到帖子列表集合
                    List<CommuntiyPostListBean.CBean.DBean> d = bean.getC().getD();

                    if (isLoData) {
                        dBean.clear();
                        dBean.addAll(d);
                    } else {
                        dBean.addAll(d);
                    }
                    adapter.setmList(dBean);
                }

            }
        });
    }

    private void initView() {

        fab.attachToRecyclerView(recyclerView);
        //初始化RecyclerView 设置管理器 以及Adapter
        // RecyclerView 下拉刷洗 上拉加载更多
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);


        //设置RecyclerView 管理器
        LinearLayoutManager mMabager = new LinearLayoutManager(getContext());
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mMabager);
        // 给RecyclerView 设置Adapter
        adapter = new ConmunityPostsListAdapter(getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            // 上拉刷新数据
            @Override
            public void onRefresh() {
                page = 1;
                loData(true);
                recyclerView.refreshComplete();
            }

            // 下拉加载更多
            @Override
            public void onLoadMore() {

                page++;
                if (pages >= page) {
                    loData(false);
                } else {
                    MyToast.show(getContext(), "没有更多数据");
                }
                recyclerView.refreshComplete();
            }
        });

        communitySearchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    ((InputMethodManager) communitySearchEd.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    LogUtils.e("点击了搜索"+v.getText().toString().trim());
                    params.put("search",v.getText().toString().trim());
                    loData(true);
                    communitySearchEd.setText("");
                    return true;
                }


                return false;
            }
        });

    }


    // TODO 更新帖子列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusGetInform(PostDatailsModel model) {
        // 收到帖子更新状态 更新帖子列表

        loData(true);
    }


    @Override
    public void onDestroyView() {
        LogUtils.d("销毁CommunityFragment社区");
        page = 1;
        params.clear();
        super.onDestroyView();
        EventBus.getDefault().unregister(this);


    }

    @OnClick({R.id.me_post_list, R.id.community_postmessage, R.id.community_fab,R.id.community_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_post_list:
                // 搜索帖子
                toolbar.setVisibility(View.GONE);
                communitySearch.setVisibility(View.VISIBLE);
                break;
            case R.id.community_postmessage:
                // 帖子消息
                startActivity(new Intent(getActivity(), MessageActivity.class));

                break;
            case R.id.community_fab:
                startActivity(new Intent(getContext(), PostingActivity.class));
                break;
            case R.id.community_quxiao:
                toolbar.setVisibility(View.VISIBLE);
                communitySearch.setVisibility(View.GONE);
                params.clear();
                page = 1;
                loData(true);
                break;
        }
    }
}
