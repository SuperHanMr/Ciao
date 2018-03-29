package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/8/19.
 * 添加设备
 */
public class AddEqipmentActivity extends AutoLayoutActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addeaipmet_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
    }


    @OnClick({R.id.addedqipme_iv_exit, R.id.addedqipme_iv_plant, R.id.addedqipme_iv_pumps, R.id.addedqipme_iv_valve, R.id.addedqipme_iv_monitoring, R.id.addedqipme_iv_lamp, R.id.addedqipme_iv_farm, R.id.addedqipme_iv_family})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addedqipme_iv_exit:
                finish();
                break;
            case R.id.addedqipme_iv_plant:
                break;
            case R.id.addedqipme_iv_pumps:
                startActivity(new Intent(AddEqipmentActivity.this,PromptingActivity.class));
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,4);
                finish();
                break;
            case R.id.addedqipme_iv_valve:
                startActivity(new Intent(AddEqipmentActivity.this,PromptingActivity.class));
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,5);
                finish();
                break;
            case R.id.addedqipme_iv_monitoring:
                startActivity(new Intent(AddEqipmentActivity.this,PromptingActivity.class));
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,1);
                finish();
                break;
            case R.id.addedqipme_iv_lamp:
                startActivity(new Intent(AddEqipmentActivity.this,PromptingActivity.class));
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,6);
                finish();
                break;
            case R.id.addedqipme_iv_farm:

                break;
            case R.id.addedqipme_iv_family:

                break;
        }
    }

}
