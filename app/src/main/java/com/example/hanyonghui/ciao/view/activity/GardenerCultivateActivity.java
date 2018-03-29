package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.AddPlantsGuideBean;
import com.example.hanyonghui.ciao.bean.bean.PumpSttingTimeBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.view.adapter.GardenerCultivateAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/30.
 */

public class GardenerCultivateActivity extends AutoLayoutActivity {

    @BindView(R.id.gardener_edit_text)
    EditText gardenerEditText;
    @BindView(R.id.gardenerrecyclervew)
    RecyclerView gardenerrecyclervew;
    private GardenerCultivateAdapter adapter;
    private List<AddPlantsGuideBean> list = new ArrayList<>();
    private int i;
    private LinearLayoutManager mManager;
    private HttpParams params = new HttpParams();

    private String id;
    private String jsonList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardener_cultivate_activity);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra(KeyUtils.PLANDATAID);
        initView();
        APP.getApp().addActivity(this);
    }

    private void initView() {
        gardenerEditText.addTextChangedListener(textwa);
        adapter = new GardenerCultivateAdapter(this,id);
        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        gardenerrecyclervew.setLayoutManager(mManager);
        gardenerrecyclervew.setAdapter(adapter);
        adapter.setOnListenerGardenerData(new GardenerCultivateAdapter.OnListenerGardenerData() {
            @Override
            public void onGardenerData(String json) {
                jsonList  = json;
            }
        });
    }

    private TextWatcher textwa = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            LogUtils.d("beforeTextChanged----------------->" + gardenerEditText.getText().toString().trim());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            LogUtils.d("onTextChanged----------------->" + gardenerEditText.getText().toString().trim());
        }

        @Override
        public void afterTextChanged(Editable s) {
            String trim = gardenerEditText.getText().toString().trim();
            LogUtils.d(trim);
            if (!trim.equals("")) {
                i = Integer.parseInt(trim);
                if (i > 6) {
                    MyToast.show(GardenerCultivateActivity.this, "超出植物阶段");
                    return;
                }else {
                    for (int i1 = 0; i1 < i; i1++) {
                        AddPlantsGuideBean bean = new AddPlantsGuideBean();
                        list.add(bean);
                    }
                }
                adapter.setItem(list);
            }
        }
    };

    @OnClick(R.id.gardener_btn)
    public void onViewClicked() {
        commtionPlanData();
    }
    private void commtionPlanData() {
        params.put("str",jsonList);
        LogUtils.d(jsonList);
        NetworkReuset.getInstance().PostReuset(RequestUrls.ADDGRADENER, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                finish();
            }
        });
    }
}
