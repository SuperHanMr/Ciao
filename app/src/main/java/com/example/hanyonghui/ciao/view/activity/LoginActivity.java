package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CodeBean;
import com.example.hanyonghui.ciao.bean.bean.LogdingBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.InspectionPhoneUtils;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/7/18.
 */

public class LoginActivity extends AutoLayoutActivity {

    @BindView(R.id.login_tv_logini)
    TextView loginTvLogini;
    @BindView(R.id.login_tv_registered)
    TextView loginTvRegistered;
    @BindView(R.id.login_letf_lin)
    View loginLetfLin;
    @BindView(R.id.login_right_lin)
    View loginRightLin;
    @BindView(R.id.login_ed_account)
    EditText loginEdAccount;
    @BindView(R.id.login_ed_pawssword)
    EditText loginEdPawssword;
    @BindView(R.id.login_tv_frgot_pawssword)
    TextView loginTvFrgotPawssword;
    @BindView(R.id.logdin_logdin)
    LinearLayout logdinLogdin;
    @BindView(R.id.login_ed_phone)
    EditText loginEdPhone;
    @BindView(R.id.login_ed_code)
    EditText loginEdCode;
    @BindView(R.id.login_rl_code)
    RelativeLayout loginRlCode;
    @BindView(R.id.registered_ed_pawssword)
    EditText registeredEdPawssword;
    @BindView(R.id.registered_ed_confirm_pawssword)
    EditText registeredEdConfirmPawssword;
    @BindView(R.id.logdin_registered)
    LinearLayout logdinRegistered;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_iv_weixi)
    ImageView loginIvWeixi;
    @BindView(R.id.login_tv_code)
    TextView loginTvCode;
    private HttpParams params = new HttpParams();
    private MyCountDownTimer myCountDownTimer;


    private String[] str = {"915c70af770848d78985bb8393361324","01322c23f06f4212b5b440e02b912f74 ","661869c431f643c88e63056f11bf508c"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉最顶部的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);
        myCountDownTimer = new MyCountDownTimer(60000,1000);
        //绑定ViewID
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
    }


    @OnClick({R.id.login_tv_logini, R.id.login_tv_registered, R.id.logdin_registered, R.id.login_btn, R.id.login_iv_weixi, R.id.login_rl_code, R.id.login_tv_frgot_pawssword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_logini:
                setRegisteredBox();
                break;
            case R.id.login_tv_registered:
                loginBtn.setText("注册");
                logdinRegistered.setVisibility(View.VISIBLE);
                logdinLogdin.setVisibility(View.GONE);
                loginLetfLin.setBackgroundColor(Color.GRAY);
                loginRightLin.setBackgroundColor(Color.BLACK);
                break;
            case R.id.login_btn:
                // 点击登陆btn
                String trim = loginBtn.getText().toString().trim();
                if (trim.equals("登陆")) {
                    getAccountPassword();
                } else {
                    registration();
                }
                break;
            case R.id.login_iv_weixi:
                loginToWeiXin();

                break;
            case R.id.login_rl_code:
                getCode();
                break;
            case R.id.login_tv_frgot_pawssword:

             startActivity(new Intent(LoginActivity.this,ForGetPasswordActicity.class));

                break;
        }
    }

    // 微信登陆
    private void loginToWeiXin() {
        IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.WEIXIN_APP_ID, true);
        mApi.registerApp(WXEntryActivity.WEIXIN_APP_ID);

        if (mApi != null && mApi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            mApi.sendReq(req);
        } else
            MyToast.show(this,"请先安装微信");
    }


    // 获取验证码
    private void getCode() {
        // 手机号
        String phone = loginEdPhone.getText().toString().trim();
        boolean isPhone = InspectionPhoneUtils.validateUserName(phone);
        if (!isPhone) {
            MyToast.show(LoginActivity.this, "请输入正确的手机号");
            return;
        }
        params.clear();
        params.put("pn", phone);
        params.put("from", "1");
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
                        MyToast.show(LoginActivity.this,"发送验证码失败");
                        break;
                    case 3:
                        MyToast.show(LoginActivity.this,"该手机号已注册");
                        break;
                }

            }
        });
    }

    // 注册账号
    private void registration() {
        // 手机号
        String phone = loginEdPhone.getText().toString().trim();
        boolean isPhone = InspectionPhoneUtils.validateUserName(phone);
        if (!isPhone) {
            MyToast.show(LoginActivity.this, "请输入正确的手机号");
            return;
        }

        // 验证码
        String code = loginEdCode.getText().toString().trim();
        if (code == null) {
            MyToast.show(LoginActivity.this, "验证码不能为空");
            return;
        }

        // 密码
        String pawssword = registeredEdPawssword.getText().toString().trim();
        String confirmPawssword = registeredEdConfirmPawssword.getText().toString().trim();
        if (!pawssword.equals(confirmPawssword)) {
            MyToast.show(LoginActivity.this, "两次密码不一致");
            return;
        }
        params.clear();
        params.put("pn", phone);
        params.put("pw", pawssword);
        params.put("vc", code);
        NetworkReuset.getInstance().PostReuset(RequestUrls.REGISTRATION, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.d(s);
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                int s1 = codeBean.getH().getS();
                switch (s1){
                    case 1:
                        //注册成功
                        setRegisteredBox();
                        MyToast.show(LoginActivity.this,"注册成功");
                        break;
                    case 2:
                        MyToast.show(LoginActivity.this,"注册失败,用户已经注册");
                        break;
                    case 3:
                        MyToast.show(LoginActivity.this,"注册失败,验证码错误");
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    // 登陆
    private void getAccountPassword() {
        String account = loginEdAccount.getText().toString().trim();
        String pawssword = loginEdPawssword.getText().toString().trim();
        params.clear();
        params.put("pn", account);
        params.put("pw", pawssword);
        NetworkReuset.getInstance().PostReuset(RequestUrls.LODING, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                logding(s);
            }


        });
    }

    private void logding(String json) {
        Gson gson = new Gson();
        LogdingBean logdingBean = gson.fromJson(json, LogdingBean.class);
        int s = logdingBean.getH().getS();
        if (s == 1) {
            // TODO 用EventBus把个人信息数据发送到我的界面
            // 并保存用户ID，后面会用到
            // 判断是不是管理员
            LogdingBean.CBean cBean = logdingBean.getC();
            SPUtils.putString(KeyUtils.USERID, cBean.getUid());
            for (int i = 0; i < str.length; i++) {
                if (str[i].equals(SPUtils.getString(KeyUtils.USERID))){
                    SPUtils.putBoolean(KeyUtils.ADMINISTRATORS,true);

                }
            }

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            MyToast.show(LoginActivity.this, "密码错误或者用户名不正确");
            return;
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
            loginTvCode.setText(l/1000+"s");

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
    private void setRegisteredBox(){
        loginBtn.setText("登陆");
        logdinRegistered.setVisibility(View.GONE);
        logdinLogdin.setVisibility(View.VISIBLE);
        loginLetfLin.setBackgroundColor(Color.BLACK);
        loginRightLin.setBackgroundColor(Color.GRAY);
    }

}
