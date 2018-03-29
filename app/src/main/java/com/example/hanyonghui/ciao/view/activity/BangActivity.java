package com.example.hanyonghui.ciao.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.view.views.WeiboDialogUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/9/12.
 */

public class BangActivity extends AutoLayoutActivity {

    @BindView(R.id.bang_webview)
    WebView bangWebview;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bang_actvity);
        ButterKnife.bind(this);
        initView();
        loData();
        APP.getApp().addActivity(this);
    }

    private void loData() {
        bangWebview.loadUrl("http://www.xhoogee.com/us");
    }

    private void initView() {

        bangWebview.getSettings().setBlockNetworkImage(true);
        bangWebview.getSettings().setBlockNetworkImage(false);
        WebSettings ws = bangWebview.getSettings();
        ws.setAppCacheEnabled(true);
        ws.setAppCacheMaxSize(1024*10);
        ws.setBuiltInZoomControls(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        bangWebview.getSettings().setJavaScriptEnabled(true);
        bangWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        bangWebview.getSettings().setAppCacheEnabled(true);
        bangWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        bangWebview.getSettings().setAppCacheMaxSize(1024*1024*8);

        bangWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });

    }


    @OnClick(R.id.bang_back)
    public void onViewClicked() {
        finish();
    }
}
