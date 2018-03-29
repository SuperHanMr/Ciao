package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.view.activity.ViewLargerImageActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/15.
 */

public class CommunityGridViewAdapter extends ArrayAdapter<String> {

    private Context context;
    private final String[] url;


    public CommunityGridViewAdapter(Context context, List<String> datas) {
        super(context, 0, datas);
        url = new String[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
           url[i]= datas.get(i);
        }

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item_layout, parent,false);
        }
         // 获取图片的URL
        final String imageUrl = getItem(position);
        final ImageView imageview = (ImageView)convertView.findViewById(R.id.imageview);
        Picasso.with(context).load(imageUrl).into(imageview);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), ViewLargerImageActivity.class).putExtra("url",url).putExtra("pos",position));
            }
        });

        return convertView;

    }
}
