package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/8/21.
 */

public class PromptingActivity extends AutoLayoutActivity {

    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
    @BindView(R.id.ac_prompting_bg)
    ImageView acPromptingBg;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompting_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        baseActicityToolbarTv.setText("添加设备");
        int anInt = SPUtils.getInt(KeyUtils.EQUIPMENTTYPE);
        if (anInt==6){
            acPromptingBg.setImageResource(R.drawable.peng_bg_icon);
        }else {
            acPromptingBg.setImageResource(R.drawable.deng_bg_icon);
        }

    }


    @OnClick({R.id.base_acticity_toolbar_iv_left, R.id.promping_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_acticity_toolbar_iv_left:
                finish();
                break;
            case R.id.promping_btn:
                startActivity(new Intent(PromptingActivity.this, ConcatenonWifi.class));
                finish();
                break;
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
