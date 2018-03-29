package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.hhl.library.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyonghui on 2017/8/26.
 */


public class TimeDataAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private Context mContext;

    private List<String> mList;

    public TimeDataAdapter (Context context){
        this.mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.timedata_item,null);
        TextView textView= (TextView) view.findViewById(R.id.time_tv_tag);
        textView.setText(mList.get(position));
        return view;
    }

    public void onlyAddAll(List<String> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return false;
    }
}
