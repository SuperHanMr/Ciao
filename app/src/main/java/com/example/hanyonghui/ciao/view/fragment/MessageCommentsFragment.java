package com.example.hanyonghui.ciao.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.MessageCommentsBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.MessageCommentsAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/26.
 * Holle Android
 */

public class MessageCommentsFragment extends Fragment {

    @BindView(R.id.message_text)
    TextView messageText;
    @BindView(R.id.message_comments_recyclerview)
    XRecyclerView messageCommentsRecyclerview;

    private HttpParams params = new HttpParams();
    private int page = 1;
    private MessageCommentsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_commentsfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loData();
    }

    private void loData() {
        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        params.put("pageNo", page);
        params.put("type", 1);

        NetworkReuset.getInstance().PostReuset(RequestUrls.MESSAGE, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                MessageCommentsBean messageCommentsBean = gson.fromJson(s, MessageCommentsBean.class);
                List<MessageCommentsBean.CBean.DBean> d = messageCommentsBean.getC().getD();
                if (d.size()!=0||d!=null){
                    adapter.setLists(d);
                    messageText.setVisibility(View.GONE);
                }else {
                    messageText.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    private void initView() {
        messageText.setText("暂时没有评论");
        adapter = new MessageCommentsAdapter(getContext());
        LinearLayoutManager mMabager = new LinearLayoutManager(getContext());
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        messageCommentsRecyclerview.setLayoutManager(mMabager);
        messageCommentsRecyclerview.setPullRefreshEnabled(false);
        messageCommentsRecyclerview.setAdapter(adapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
