package com.example.hanyonghui.ciao.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

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

public class GardenerWebViewActivity extends AutoLayoutActivity {

    @BindView(R.id.gardener_tv_title)
    TextView gardenerTvTitle;
    @BindView(R.id.gardener_webview)
    WebView gardenerWebview;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardener_webview_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        String type = getIntent().getStringExtra("type");
        int i = Integer.parseInt(type);
        gardenerWebview.getSettings().setBlockNetworkImage(true);
        gardenerWebview.getSettings().setBlockNetworkImage(false);
        WebSettings ws = gardenerWebview.getSettings();
        ws.setAppCacheEnabled(true);
        ws.setAppCacheMaxSize(1024*10);
        ws.setBuiltInZoomControls(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        gardenerWebview.getSettings().setJavaScriptEnabled(true);
        gardenerWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        gardenerWebview.getSettings().setAppCacheEnabled(true);
        gardenerWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        gardenerWebview.getSettings().setAppCacheMaxSize(1024*1024*8);
        loDataWebView(i);

        gardenerWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });


    }
    private void loDataWebView(int i) {
        switch (i){
            case 1:
                gardenerTvTitle.setText("种子俱乐部");
                gardenerWebview.loadUrl("http://www.xhoogee.com/edu");
                break;
            case 2:
                gardenerTvTitle.setText("创客教育");
                gardenerWebview.loadUrl("http://www.xhoogee.com/edu");
                break;
            case 3:
                gardenerTvTitle.setText("生活美学");
                gardenerWebview.loadUrl("http://www.xhoogee.com/edu");
                break;
        }
    }

    @OnClick(R.id.gardner_back)
    public void onViewClicked() {
        finish();
    }
}
