package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.view.adapter.MeEauipmentRecyclerViewAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/8/24.
 */

public class MeEquipmentActivity extends AutoLayoutActivity {

    @BindView(R.id.mequipmnet_recyclerview)
    XRecyclerView mequipmnetRecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mequipment_activity);
        ButterKnife.bind(this);
        initView();
        APP.getApp().addActivity(this);
    }

    private void initView() {

        MeEauipmentRecyclerViewAdapter adapter = new MeEauipmentRecyclerViewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mequipmnetRecyclerview.setLayoutManager(manager);
        mequipmnetRecyclerview.setPullRefreshEnabled(false);
        mequipmnetRecyclerview.setAdapter(adapter);
    }

    @OnClick({R.id.mequipmnet_back, R.id.mequipmnet_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mequipmnet_back:
                finish();
                break;
            case R.id.mequipmnet_add:
                startActivity(new Intent(MeEquipmentActivity.this,AddEqipmentActivity.class));
                finish();
                break;
        }
    }

}
