package com.example.hanyonghui.ciao.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.LibraryListBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.TagModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.view.adapter.LibraryRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/7/25.
 *
 * 这个页面需要 下拉刷新 以及筛选过后的数据
 *
 */

public class LibraryTabFragment extends Fragment {

    private FrameLayout frameLayout;
    private XRecyclerView mXRecyclerView;
    private LibraryRecyclerViewAdapter adapter;
    private List<LibraryListBean.CBean.DBean> list = new ArrayList<>();
    private int page;
    public int type;
    private HttpParams params = new HttpParams();
    private boolean is = true;
    private int pages;
    public static  int typeFrgamnt ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.libray_tab_fragmet,container,false);
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.xrecyclerview);
        frameLayout = (FrameLayout) view.findViewById(R.id.lbrary_framelayout);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        // 设置Adapter
        adapter = new LibraryRecyclerViewAdapter(getContext());
        LinearLayoutManager mMabager = new LinearLayoutManager(getContext());
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(mMabager);
        mXRecyclerView.setAdapter(adapter);

        // 上拉 下拉加载
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                is = true;
                params.clear();
                if (type==0) {
                    params.put("pageNo",1);
                }else {
                    params.put("populars",type);
                    params.put("pageNo",1);
                }

                loData();
                mXRecyclerView.refreshComplete();
            }


            @Override
            public void onLoadMore() {
                is = false;
                if (page<pages){
                    page++;
                    params.put("pageNo",page);
                    loData();
                    mXRecyclerView.loadMoreComplete();
                }else {
                    MyToast.show(getContext(),"没有更多数据");
                    mXRecyclerView.loadMoreComplete();
                }
            }
        });
        typeFrgamnt = type;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            page = 1;
            if (type==0){
                params.put("pageNo",page);
            }else {
                params.put("populars",type);
                params.put("pageNo",page);
            }

            loData();
        }else {
            LogUtils.d("不可见");
        }

    }

    //加载数据 用POST请求
    public void loData(){
        OkGo.post(RequestUrls.LIBRARYLISTURL)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        processData(s);
                    }});
    }

    private void processData(String json) {
        Gson gson = new Gson();
        LibraryListBean bean = gson.fromJson(json, LibraryListBean.class);
        List<LibraryListBean.CBean.DBean> d = bean.getC().getD();
        pages = bean.getC().getPages();
        if (d==null||d.size()==0){
            MyToast.show(getContext(),"暂时没有数据");
            return;
        }

        // 设置数据

            if (is){
              list.clear();
              list.addAll(d);
            }else {
                list.addAll(d);
            }

            adapter.setData(list);
            adapter.notifyDataSetChanged();
            frameLayout.setVisibility(View.GONE);
            mXRecyclerView.setVisibility(View.VISIBLE);
    }

   @Subscribe(threadMode = ThreadMode.MAIN)
   public void eventBusHttparams(TagModel model){

       if (model.getTypefrgemnt()==type){
           HttpParams httpParams = model.getHttpParams();
           LogUtils.e(httpParams.toString());
           params.clear();
           list.clear();
           params.put(httpParams);
           loData();
       }

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

