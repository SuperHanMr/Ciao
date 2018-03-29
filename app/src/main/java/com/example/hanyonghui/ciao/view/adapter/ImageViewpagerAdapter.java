package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hanyonghui.ciao.R;
import com.squareup.picasso.Picasso;

/**
 * Created by hanyonghui on 2017/9/18.
 */

public class ImageViewpagerAdapter extends PagerAdapter {

    private Context context;
    private String[] url;

    public ImageViewpagerAdapter(Context context, String[] url) {
        this.context = context;
        this.url = url;
    }


    @Override
    public int getCount() {
        return url.length!=0?url.length:0;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewlargerimage_activity,container,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.viewlarger_iv);
        Picasso.with(context).load(url[position]).into(imageView);
        container.addView(view);
        return view;
    }
}
