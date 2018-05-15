package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.SheBeiSwichtBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/29.
 * Holle Android
 */

public class LampStiingActivity extends AutoLayoutActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.ai_deng_switch)
    Switch aiDengSwitch;
    @BindView(R.id.rengong_deng_switch)
    Switch rengongDengSwitch;
    @BindView(R.id.liangdu_deng_switch)
    Switch liangduDengSwitch;


    @BindView(R.id.seekbar)
    SeekBar mSeekbar;
    @BindView(R.id.tv_liangdu)
    TextView tvLingdu;

    private float y1;
    private int liangdu = 50;

    private int mManual;


    private boolean isSwitch = false;
    private boolean isLiangdu = true;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lamp_activity);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra(KeyUtils.EQUIPMENTNAMEID);
        initView();

    }

    private void initView() {
        aiDengSwitch.setOnCheckedChangeListener(this);
        rengongDengSwitch.setOnCheckedChangeListener(this);
        liangduDengSwitch.setOnCheckedChangeListener(this);
        mSeekbar.setOnSeekBarChangeListener(this);
        mSeekbar.setProgress(10);
        mSeekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isLiangdu;
            }
        });

        // 查询任务
        setSwitch();

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (aiDengSwitch == buttonView) {
            if (isChecked) {
                rengongDengSwitch.setChecked(false);
                liangduDengSwitch.setChecked(false);
                openAIDeng(1);
            } else {
                LogUtils.e("---->关闭了AI任务");
                openAIDeng(2);
            }
        }


        if (rengongDengSwitch == buttonView) {
            if (isChecked) {
                aiDengSwitch.setChecked(false);
                liangduDengSwitch.setChecked(false);
//                openLaborDeng(1);

                if (isSwitch){
                    isSwitch = false;
                }else {
                    SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,6);
                    startActivity(new Intent(this, PumpTowStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID, id));

                }


            } else {
                LogUtils.e("---->关闭了人工任务");
                openLaborDeng(2);
            }
        }

        if (liangduDengSwitch == buttonView) {
            if (isChecked) {
                aiDengSwitch.setChecked(false);
                rengongDengSwitch.setChecked(false);

                isLiangdu = false;
            } else {
                LogUtils.e("---->关闭了亮度");
                openDiyLiangdu();
                isLiangdu = true;


            }
        }



    }


    // TODO 查询是否开启了自动任务
    private void setSwitch(){
        HttpParams params = new HttpParams();
        params.clear();
        params.put("uid",SPUtils.getString(KeyUtils.USERID));// 用户ID
        params.put("did",id);// 设备ID
        NetworkReuset.getInstance().PostReuset(RequestUrls.ENQUIRIESEQUIPMENT, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                SheBeiSwichtBean sheBeiSwichtBean = gson.fromJson(s, SheBeiSwichtBean.class);
                String autowater = sheBeiSwichtBean.getC().getAutowater();
                if (autowater.contains("D1")){
                    LogUtils.e("状态："+autowater);
                    isSwitch = true;
                    rengongDengSwitch.setChecked(true);
                }else {
                    rengongDengSwitch.setChecked(false);
                }


                if (autowater.contains("D4")){
                    aiDengSwitch.setChecked(true);
                }else {
                    aiDengSwitch.setChecked(false);
                }

            }
        });
    }

    /*
    开启AI智能灯任务
     */
    private void openAIDeng(int open) {
        HttpParams params = new HttpParams();
        params.put("did", id);
        params.put("open", open);
        NetworkReuset.getInstance().PostReuset(RequestUrls.AIOPENLIGHT, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });

    }

    /*
    开启人工智能灯任务
     */

    private void openLaborDeng(int open) {
        HttpParams params = new HttpParams();
        params.put("did", id);
        params.put("open", open);
        params.put("type","D1");
        NetworkReuset.getInstance().PostReuset(RequestUrls.AUTOMATICSWICTH, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
            }
        });
    }


    /*
    开启手动调节亮度的任务
     */

    private void openDiyLiangdu() {
        HttpParams params = new HttpParams();
        params.put("did", id);
        params.put("type", "D3");
        params.put("isopen",0);
        params.put("lightness", liangdu);
        NetworkReuset.getInstance().PostReuset(RequestUrls.LAMP, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });

    }


    @OnClick({R.id.pumpstting_back, R.id.stiing_rl, R.id.stiing,R.id.promping_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pumpstting_back:
                finish();
                break;
            case R.id.stiing_rl:
                startActivity(new Intent(LampStiingActivity.this,
                        EquipmentSttringActivity.class)
                        .putExtra("id", id));
                break;
            case R.id.stiing:
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,6);
                startActivity(new Intent(LampStiingActivity.this, PumpTowStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID, id));
                break;

            case R.id.promping_btn:
                SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,4);

                startActivity(new Intent(this, PumpStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID, id).putExtra(KeyUtils.ISLAMP,"lamp"));
                break;
        }
    }





    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mManual = progress;
        if (progress<=10) {
            mSeekbar.setProgress(10);
            tvLingdu.setText(10+"");
        }else {
            tvLingdu.setText(progress+"");
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        HttpParams params = new HttpParams();
        params.put("did",id);
        params.put("type","D3");
        params.put("isopen",1);
        params.put("lightness",mManual);
        NetworkReuset.getInstance().PostReuset(RequestUrls.MANUL, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });


    }
}
