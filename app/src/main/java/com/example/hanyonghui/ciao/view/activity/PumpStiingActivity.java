package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CodeBean;
import com.example.hanyonghui.ciao.bean.bean.SheBeiSwichtBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.LongButtonView;
import com.example.hanyonghui.ciao.view.views.SildeView;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/26.
 */

public class PumpStiingActivity  extends AutoLayoutActivity{

    private LongButtonView buttonView;
    private HttpParams params = new HttpParams();
    private Switch aSwitch;
    private String id;

    private String title;
    private String type;

    private boolean isSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pumpstiing_activity);
        APP.getApp().addActivity(this);
        TextView textTitle = (TextView) findViewById(R.id.pumpstting_title);
        textTitle.setText(setTites());
        buttonView = (LongButtonView) findViewById(R.id.image_but);
        aSwitch = (Switch) findViewById(R.id.pumpstting_switch);
        id = getIntent().getStringExtra(KeyUtils.EQUIPMENTNAMEID);
        initData();
        setSwitch();
        buttonView.setListener(new LongButtonView.OnLongListener() {
            @Override
            public void onClick(Boolean is) {
                if (is){
                    // 浇水
                    startwetering(1);
                }else {
                    startwetering(2);
                }
            }
        });

        findViewById(R.id.stiing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,4);
                startActivity(new Intent(PumpStiingActivity.this,PumpTowStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID,id));
            }
        });


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (isSwitch){
                        isSwitch = false;
                    }else {
                        startActivity(new Intent(PumpStiingActivity.this,PumpTowStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID,id));
                    }
                }else {
                    automaticOn();
                }
            }
        });



        findViewById(R.id.pumpstting_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        findViewById(R.id.stiing_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转到设备的设置页面 修改设备名称 和检查更新
            startActivity(new Intent(PumpStiingActivity.this,EquipmentSttringActivity.class).putExtra("id",id));

            }
        });
    }


    // 开启自动浇水


    // 关闭自动浇水
    private void automaticOn() {
        params.clear();
        params.put("did",id);
        params.put("open",0);
        params.put("type",type);
        NetworkReuset.getInstance().PostReuset(RequestUrls.AUTOMATICSWICTH, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });

    }

    private String setTites() {
        switch (SPUtils.getInt(KeyUtils.EQUIPMENTTYPE)){
            case 4:
                title = "智能自动浇水泵设置";
                type = KeyUtils.TYPE_B1;
                break;
            case 5:
                title = "智能自动浇灌阀设置";
                type = KeyUtils.TYPE_F1;
                break;
            case 1:
                title = "Ciao园叮检测仪";
                break;
        }
        return title;
    }

    private void initData() {
        params.put("did",id);
        NetworkReuset.getInstance().PostReuset(RequestUrls.INITTIME, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
            }
        });

    }
    private void startwetering(int type) {
        params.clear();
        params.put("did",id);
        params.put("isopen",type);
        params.put("type",getType());
        NetworkReuset.getInstance().PostReuset(RequestUrls.LAMP, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                switch (codeBean.getH().getS()){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        MyToast.show(PumpStiingActivity.this,"设备离线");
                        break;
                }

            }
        });

    }



    // TODO 查询是否开启了自动任务
    private void setSwitch(){
        params.clear();
        params.put("uid",SPUtils.getString(KeyUtils.USERID));// 用户ID
        params.put("did",id);// 设备ID
        NetworkReuset.getInstance().PostReuset(RequestUrls.ENQUIRIESEQUIPMENT, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                SheBeiSwichtBean sheBeiSwichtBean = gson.fromJson(s, SheBeiSwichtBean.class);
                if (sheBeiSwichtBean.getC().getOnline()==1){
                    isSwitch = false;
                    LogUtils.e(sheBeiSwichtBean.getC().getOnline()+"");
                    isSwitch = true;
                    aSwitch.setChecked(true);
                }else {
                    aSwitch.setChecked(false);
                }
            }
        });
    }


    private String getType(){
        String type = null;
        switch (SPUtils.getInt(KeyUtils.EQUIPMENTTYPE)){
            case 4:
                type = "B3";
                break;
            case 5:
                type = "F3";
                break;
            case 6:
                type = "D3";
                break;
        }
        return type;

    }

}
