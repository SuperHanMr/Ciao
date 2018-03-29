package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.LibraryBean;
import com.example.hanyonghui.ciao.bean.connector.OnListeningType;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.hhl.library.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private  Context mContext;
    private  List<LibraryBean.CBean> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {

        return mDataList.size();
    }
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        final LibraryBean.CBean cBean = mDataList.get(position);
        textView.setText(cBean.getContent());
        return view;
    }


    public void onlyAddAll(List<LibraryBean.CBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<LibraryBean.CBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }


    @Override
    public boolean isSelectedPosition(int position) {
        return false;
    }
}
