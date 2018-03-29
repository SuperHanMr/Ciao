package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hanyonghui on 2017/9/12.
 */

public class TheFairActivity extends AutoLayoutActivity {


    @BindView(R.id.fair_webview)
    WebView fairWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_fair_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        initView();

    }

    private void initView() {
        fairWebview.getSettings().setBlockNetworkImage(true);
        fairWebview.getSettings().setBlockNetworkImage(false);
        WebSettings ws = fairWebview.getSettings();
        ws.setAppCacheEnabled(true);
        ws.setAppCacheMaxSize(1024 * 10);
        ws.setBuiltInZoomControls(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        fairWebview.getSettings().setJavaScriptEnabled(true);
        fairWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        fairWebview.getSettings().setAppCacheEnabled(true);
        fairWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        fairWebview.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        fairWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        fairWebview.loadUrl("http://www.xhoogee.com/");

    }


    @OnClick({R.id.fair_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fair_back:
                finish();
                break;
        }
    }
}
