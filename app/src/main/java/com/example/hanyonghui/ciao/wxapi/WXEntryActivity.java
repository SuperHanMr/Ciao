package com.example.hanyonghui.ciao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hanyonghui.ciao.bean.bean.WXTokenBean;
import com.example.hanyonghui.ciao.bean.bean.WXUserInfoBean;
import com.example.hanyonghui.ciao.bean.bean.WxUserIdBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.LoginActivity;
import com.example.hanyonghui.ciao.view.activity.MainActivity;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/27.
 * Holle Android
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String APP_SECRET = "4cd903b861af92480b9aca75d33a6c5a";
    private IWXAPI mWeixinAPI;
    public static final String WEIXIN_APP_ID = "wxaa2401aa0c5b2538";
    private static String uuid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }


    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.e("onReq");

    }

    @Override
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                LogUtils.e("ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) baseResp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                    LogUtils.e(code);
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                LogUtils.e("ERR_USER_CANCEL");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                LogUtils.e("ERR_AUTH_DENIED");
                //发送被拒绝
                finish();
                break;
            default:
                //发送返回
                break;
        }

    }

    private void getAccess_token(String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + WEIXIN_APP_ID
                + "&secret="
                + APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";

        NetworkReuset.getInstance().GetReuset(path, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                WXTokenBean wxTokenBean = gson.fromJson(s, WXTokenBean.class);
                String openid = wxTokenBean.getOpenid();
                String access_token = wxTokenBean.getAccess_token();
                getUserMessage(openid,access_token);
            }
        });

    }


    private void getUserMessage(final String openid, final String access_token){
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        NetworkReuset.getInstance().GetReuset(path, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                WXUserInfoBean wxUserInfoBean = gson.fromJson(s, WXUserInfoBean.class);
                String nickname = wxUserInfoBean.getNickname(); // 用户名称
                String headimgurl = wxUserInfoBean.getHeadimgurl(); // 用户头像
                WXLogin(nickname,headimgurl,access_token,openid);
            }
        });

    }

    private void WXLogin(String nickname, String headimgurl, String access_token, String openid) {
        HttpParams params = new HttpParams();
        params.put("username",nickname);
        params.put("iconUrl",headimgurl);
        params.put("token",access_token);
        params.put("openId",openid);
        NetworkReuset.getInstance().PostReuset(RequestUrls.WXLOGIN, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                WxUserIdBean wxUserIdBean = gson.fromJson(s, WxUserIdBean.class);
                String uid = wxUserIdBean.getC().getUid();
                SPUtils.putString(KeyUtils.USERID,uid);
                startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                finish();
            }
        });

    }

}
