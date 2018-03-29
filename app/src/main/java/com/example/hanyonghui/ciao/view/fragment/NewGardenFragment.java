package com.example.hanyonghui.ciao.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.EqipmentListBean;
import com.example.hanyonghui.ciao.bean.connector.SimpleTouchHelperCallback;
import com.example.hanyonghui.ciao.bean.eventbusmodel.EqipmentListModle;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.AddEqipmentActivity;
import com.example.hanyonghui.ciao.view.adapter.MeEauipmentRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/7/21.
 */

public class NewGardenFragment extends Fragment {

    private static final String TAG = "NewGardenFragment";
    @BindView(R.id.mequipmnet_recyclerview)
    XRecyclerView mequipmnetRecyclerview;

    private View view;

    private HttpParams params = new HttpParams();
    private MeEauipmentRecyclerViewAdapter adapter;
    private List<EqipmentListBean.CBean> cBeen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mequipment_activity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        iniData();
    }

    private void iniData() {
        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        NetworkReuset.getInstance().PostReuset(RequestUrls.EQUIPMENTNAMELIST, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                processData(s);
            }
        });
    }

    private void processData(String s) {
        Gson gson = new Gson();
        EqipmentListBean eqipmentListBean = gson.fromJson(s, EqipmentListBean.class);
        List<List<EqipmentListBean.CBean>> c = eqipmentListBean.getC();
        adapter.setEquipmentData(c.get(3));
    }

    private void initView() {
        adapter = new MeEauipmentRecyclerViewAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mequipmnetRecyclerview.setLayoutManager(manager);
        mequipmnetRecyclerview.setPullRefreshEnabled(false);// 不让刷新
        mequipmnetRecyclerview.setAdapter(adapter);
    }


    @OnClick({R.id.mequipmnet_back, R.id.mequipmnet_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mequipmnet_back:
                getActivity().finish();
                break;
            case R.id.mequipmnet_add:
                startActivity(new Intent(getActivity(),AddEqipmentActivity.class));
                break;
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusEqipme(EqipmentListModle model){
        iniData();
        LogUtils.d("刷新列表");
    }

    @Override
    public void onDestroyView() {
        LogUtils.d("销毁花园NewGardenFragment");
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
