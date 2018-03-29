package com.example.hanyonghui.ciao.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.hanyonghui.ciao.utils.LogUtils;

/**
 * Created by hanyonghui on 2017/8/30.
 */

public class LongButtonView extends android.support.v7.widget.AppCompatButton {


    public LongButtonView(Context context) {
        this(context,null);
    }

    public LongButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LongButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){

            listener.onClick(true);

        }else if (event.getAction()==MotionEvent.ACTION_UP){
            listener.onClick(false);
        }

        return true;
    }

    private  OnLongListener listener = null;

    public  interface OnLongListener{
        void onClick(Boolean is);
    }

    public void setListener(OnLongListener listener){
        this.listener = listener;
    }


}
