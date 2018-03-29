package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CodeBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.InspectionPhoneUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
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
 * Created by hanyonghui on 2017/9/21.
 */

public class ForGetPasswordActicity extends AutoLayoutActivity {

    @BindView(R.id.login_ed_phone)
    EditText loginEdPhone;
    @BindView(R.id.login_ed_code)
    EditText loginEdCode;
    @BindView(R.id.registered_ed_pawssword)
    EditText registeredEdPawssword;
    @BindView(R.id.registered_ed_confirm_pawssword)
    EditText registeredEdConfirmPawssword;
    @BindView(R.id.login_tv_code)
    TextView loginTvCode;
    @BindView(R.id.login_rl_code)
    RelativeLayout loginRlCode;
    private HttpParams params = new HttpParams();
    private MyCountDownTimer myCountDownTimer;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasswprd_activity);
        ButterKnife.bind(this);
        myCountDownTimer = new MyCountDownTimer(60000,1000);
    }

    @OnClick({R.id.forgetpassword_back, R.id.forgetpassword_tijiao, R.id.login_rl_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetpassword_back:
                finish();
                break;
            case R.id.forgetpassword_tijiao:
                // 提及修改的密码
                forGetPassword();

                break;
            case R.id.login_rl_code:
                //获取验证码
                getYzCode();

                break;
        }
    }

    private void forGetPassword() {
        params.clear();
        String password = registeredEdConfirmPawssword.getText().toString().trim();
        String code = loginEdCode.getText().toString().trim();
        params.put("pn", phone);
        params.put("pw", password);
        params.put("vc", code);

        NetworkReuset.getInstance().PostReuset(RequestUrls.FORGETPASSWORD, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.e(s);
                finish();
            }
        });




    }

   private void getYzCode(){
       // 手机号
       phone = loginEdPhone.getText().toString().trim();
       boolean isPhone = InspectionPhoneUtils.validateUserName(phone);
       if (!isPhone) {
           MyToast.show(ForGetPasswordActicity.this, "请输入正确的手机号");
           return;
       }
       params.clear();
       params.put("pn", phone);
       params.put("from", "2");
       NetworkReuset.getInstance().PostReuset(RequestUrls.GETCODE, params, new StringCallback() {
           @Override
           public void onSuccess(String s, Call call, Response response) {
               LogUtils.d(s);
               Gson gson = new Gson();
               CodeBean codeBean = gson.fromJson(s, CodeBean.class);
               int s1 = codeBean.getH().getS();
               switch (s1){
                   case 1:
                       myCountDownTimer.start();
                       break;
                   case 2:
                       MyToast.show(ForGetPasswordActicity.this,"发送验证码失败");
                       break;
                   case 3:
                       MyToast.show(ForGetPasswordActicity.this,"该手机号未注册");
                       break;
               }

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
            //防止计时过程中重复点击
            loginRlCode.setClickable(false);
            loginTvCode.setText(l / 1000 + "s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            loginTvCode.setText("重新获取验证码");
            //设置可点击
            loginRlCode.setClickable(true);
        }
    }
}
