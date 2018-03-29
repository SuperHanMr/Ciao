package com.example.hanyonghui.ciao.view.views;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/11.
 * Holle Android
 */

public class SuperHanDialog extends Dialog {


    public SuperHanDialog(Context context, String title) {
        //使用自定义Dialog样式
        super(context, R.style.custom_dialog);
        //指定布局
        setContentView(R.layout.dialog_tishi);
        //点击外部不可消失
        setCancelable(false);
        //设置标题
        TextView titleTv = (TextView) findViewById(R.id.dialog_title_tv_tishi);
        titleTv.setText(title);
        findViewById(R.id.ok_tv_tishi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramView) {
                dismiss();
            }
        });
    }

}
