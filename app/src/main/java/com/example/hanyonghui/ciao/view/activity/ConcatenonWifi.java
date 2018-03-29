package com.example.hanyonghui.ciao.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.ConfigWifiBean;
import com.example.hanyonghui.ciao.bean.bean.PlanTingBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.EqipmentListModle;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.ConcatenonWifiAdapter;
import com.example.hanyonghui.ciao.view.views.SuperHanDialog;
import com.example.hanyonghui.ciao.view.views.WeiboDialogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fogcloud.sdk.easylink.api.EasyLink;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;
import io.fogcloud.sdk.mdns.api.MDNS;
import io.fogcloud.sdk.mdns.helper.SearchDeviceCallBack;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/21.
 */

public class ConcatenonWifi extends AutoLayoutActivity {

    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
    @BindView(R.id.conactenon_pawssrod)
    EditText conactenonPawssrod;
    @BindView(R.id.conactenon_wifiname)
    TextView conactenonWifiname;
    @BindView(R.id.conactenon_wifi)
    TextView conactenonWifi;
    @BindView(R.id.conactenon_recyclerview)
    RecyclerView conactenonRecyclerview;

    private EasyLink easyLink;
    private String wifiName;
    private MDNS mdn;
    private String userID;
    private Dialog dialog;
    private ConcatenonWifiAdapter adapter;
    private  List<PlanTingBean> list;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    WeiboDialogUtils.closeDialog(dialog);
                    myCountDownTimer.cancel();
                    list = (List<PlanTingBean>) msg.obj;
                    adapter.setData(list);
                    break;
            }
        }
    };
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conactenonwifi_activity);
        ButterKnife.bind(this);
        baseActicityToolbarTv.setText("添加设备");
        easyLink = new EasyLink(this);
        wifiName = easyLink.getSSID();
        conactenonWifiname.setText(wifiName);
        userID = SPUtils.getString(KeyUtils.USERID);
        APP.getApp().addActivity(this);
        myCountDownTimer = new MyCountDownTimer(60000,1000);
        initView();
    }

    private void initView() {
        LinearLayoutManager mMabager = new LinearLayoutManager(this);
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        conactenonRecyclerview.setLayoutManager(mMabager);
        adapter = new ConcatenonWifiAdapter(this);
        conactenonRecyclerview.setAdapter(adapter);
        adapter.setConcatenonListener(new ConcatenonWifiAdapter.onConcatWifiLitener() {
            @Override
            public void Listener(String s) {
                dialog = WeiboDialogUtils.createLoadingDialog(ConcatenonWifi.this, "加载中...");
                configWifi(s);
            }
        });
    }

    @OnClick({R.id.base_acticity_toolbar_iv_left, R.id.conactenon_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_acticity_toolbar_iv_left:
                finish();
                break;
            case R.id.conactenon_btn:
                configuration();
                startfacility();
                break;
        }
    }

    private void configuration() {
        String password = conactenonPawssrod.getText().toString().trim();
        EasyLinkParams params = new EasyLinkParams();
        params.ssid = wifiName;
        params.password = password;
        params.runSecond = 6000;
        params.sleeptime = 20;
        easyLink.startEasyLink(params, new EasyLinkCallBack() {
            @Override
            public void onSuccess(int code, String message) {
                stopLink();
            }

            @Override
            public void onFailure(int code, String message) {
                MyToast.show(ConcatenonWifi.this, "配网失败");
            }
        });
    }

    // 搜索设备
    public void startfacility() {
        dialog = WeiboDialogUtils.createLoadingDialog(this, "搜索设备...");
        myCountDownTimer.start();
        mdn = new MDNS(this);
        String MDNSCODE = "_easylink._tcp.local.";
        mdn.startSearchDevices(MDNSCODE, new SearchDeviceCallBack() {

            @Override
            public void onDevicesFind(int code, JSONArray deviceStatus) {
                // 能搜索到设备 说明设备已经连上wifi了
                if (!deviceStatus.equals("")) {
                    LogUtils.e("---解析搜索设备的信息--->" + deviceStatus.toString());
                    processData(deviceStatus);
                }
            }
            @Override
            public void onSuccess(int code, String message) {
                LogUtils.e("---搜索设备成功----");
            }

            @Override
            public void onFailure(int code, String message) {
                LogUtils.e("---搜索设备失败---" + message);

            }

        });
    }

    // 解析设备信息 获取设备MAC
    private void processData(JSONArray deviceStatus) {
        Gson gson = new Gson();
        List<PlanTingBean> list = gson.fromJson(deviceStatus.toString(), new TypeToken<List<PlanTingBean>>() {
        }.getType());
        if (list.size() != 0 && list != null){
            LogUtils.e(Thread.currentThread().getName()); // 这个不是主线程
            stoSear();
            Message message = new Message();
            message.obj = list;
            message.what = 0;
            handler.sendMessage(message);
        }
    }


    // 把用户id和硬件mac上传到服务器
    private void configWifi(final String mac) {
        HttpParams params = new HttpParams();
        params.put("uid", userID);
        params.put("mac", mac);
        params.put("type", SPUtils.getInt(KeyUtils.EQUIPMENTTYPE));
        params.put("battery", 10);
        NetworkReuset.getInstance().PostReuset(RequestUrls.ADDDEVICE, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null) {
                    Gson gson = new Gson();
                    ConfigWifiBean configWifiBean = gson.fromJson(s, ConfigWifiBean.class);
                    switch (configWifiBean.getH().getS()) {
                        case 1:
                            WeiboDialogUtils.closeDialog(dialog);
                            MyToast.show(ConcatenonWifi.this, "配置成功");
                            EventBus.getDefault().post(new EqipmentListModle());
                            finish();
                            break;
                        case 2:
                            WeiboDialogUtils.closeDialog(dialog);
                            new SuperHanDialog(ConcatenonWifi.this,"配置失败").show();
                            break;
                        case 3:
                            WeiboDialogUtils.closeDialog(dialog);
                            new SuperHanDialog(ConcatenonWifi.this,"设备已被原账户绑定，请解绑后再添加").show();
                    }
                }
            }
        });

    }
    // 停止搜索设备
    private void stoSear() {
        mdn.stopSearchDevices(new SearchDeviceCallBack() {
            public void onSuccess(int code, String message) {
                Log.d("---停止搜索设备---", message);
            }


            @Override
            public void onFailure(int code, String message) {
                Log.d("---停止搜索设备---", message);
            }
        });
    }

    //停止配网
    private void stopLink() {
        easyLink.stopEasyLink(new EasyLinkCallBack() {
            @Override
            public void onSuccess(int code, String message) {
                Log.d("停止配网成功", code + message);
            }
            @Override
            public void onFailure(int code, String message) {
                Log.d("停止配网失败", code + message);
            }
        });
    }


    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //计时过程
        @Override
        public void onTick(long l) {
        }
        //计时完毕的方法
        @Override
        public void onFinish() {
            WeiboDialogUtils.closeDialog(dialog);
            MyToast.show(ConcatenonWifi.this, "搜索不到附近设备");
            stoSear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
