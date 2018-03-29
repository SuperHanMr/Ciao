package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.Recognition;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/10/11.
 * Holle Android
 */

public class ImageViewRecognitionAdapter extends RecyclerView.Adapter<ImageViewRecognitionAdapter.MyRecognitionViewHodler>{

    private Context context;
    private List<Recognition.ResultBean> mlist;
    public ImageViewRecognitionAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    @Override
    public MyRecognitionViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecognitionViewHodler hodler = new MyRecognitionViewHodler(LayoutInflater.from(context).inflate(R.layout.recognition_item,parent,false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(MyRecognitionViewHodler holder, int position) {
        holder.tvPlanName.setText(mlist.get(position).getName());
        holder.tvTrusTed.setText(formatDouble(mlist.get(position).getScore())+"%");
    }

    @Override
    public int getItemCount() {
        return mlist.size()==0?0:mlist.size();
    }

    public void setData(List<Recognition.ResultBean> mlist){
        this.mlist = mlist;
        notifyDataSetChanged();
    }


    // 可信度为百分比 保留两位小数
    private String formatDouble(Object objects){
        java.text.DecimalFormat   df   =new  java.text.DecimalFormat("0.00");
        return  df.format(objects);
    }

    public static class MyRecognitionViewHodler extends RecyclerView.ViewHolder{

        private final TextView tvPlanName; // 植物名称
        private final TextView tvTrusTed; // 植物相似度

        public MyRecognitionViewHodler(View itemView) {
            super(itemView);
            tvPlanName = (TextView) itemView.findViewById(R.id.recognition_item_tv_pland_name);
            tvTrusTed = (TextView) itemView.findViewById(R.id.recognition_item_tv_trusted);

        }
    }

}
