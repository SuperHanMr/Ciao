package com.example.hanyonghui.ciao.view.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CodeBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.EqipmentListModle;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.CancelOrOkDialog;
import com.example.hanyonghui.ciao.view.views.SuperHanDialog;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/10/5.
 * Holle Android
 */

public class EquipmentSttringActivity extends AutoLayoutActivity {

    @BindView(R.id.equipment_ed_name)
    EditText equipmentEdName;
    private String name;
    private boolean is = false;
    private HttpParams params = new HttpParams();
    private String ID;
    private String trim;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_stting_ac);
        ButterKnife.bind(this);
        ID = getIntent().getStringExtra("id");
        intView();




    }

    private void intView() {
        name = SPUtils.getString(KeyUtils.EQUIPMENTNAME);
        equipmentEdName.setText(name);
        equipmentEdName.setSelection(equipmentEdName.getText().length());
        equipmentEdName.addTextChangedListener(new EditChangedListener());
    }

    @OnClick({R.id.back_rv, R.id.equipment_ll_updata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rv:
                onBackLisetener();
                break;
            case R.id.equipment_ll_updata:
                equipmentUpData();
                break;
        }
    }



   // 设备更新
    private void equipmentUpData() {
        params.clear();
        params.put("did",ID);
        NetworkReuset.getInstance().PostReuset(RequestUrls.EQIPMENTUPDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                int s1 = codeBean.getH().getS();
                switch (s1){
                    case 1:
                        new SuperHanDialog(EquipmentSttringActivity.this,"指令发送成功").show();
                        break;
                    case 2:
                        new SuperHanDialog(EquipmentSttringActivity.this,"指令发送失败").show();
                        break;
                    case 3:
                        new SuperHanDialog(EquipmentSttringActivity.this,"设备离线").show();
                        break;
                }

            }
        });

    }


    private void onBackLisetener() {
        if (is){
            CancelOrOkDialog dialog = new CancelOrOkDialog(this, "确定修改当前设备信息?") {
                @Override
                public void ok() {
                    super.ok();
                    modifyEquipmentInfo();
                    dismiss();
                }
                @Override
                public void quxiao() {
                    super.quxiao();
                    finish();
                    dismiss();
                }
            };
            dialog.show();
        }else {
            finish();
        }
    }



    //TODO 在这里做修改设备名称
    private void modifyEquipmentInfo() {

        params.clear();
        params.put("did",ID);
        params.put("online",1); // 这个参数 没啥意义
        params.put("name",trim);
        NetworkReuset.getInstance().PostReuset(RequestUrls.SETDEKETEEQUIPMENAME, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                EventBus.getDefault().post(new EqipmentListModle());
                finish();
            }
        });
    }

    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            trim = s.toString().trim();
            if (!name.equals(trim)){
                is = true;
            }
        }
    }

}
