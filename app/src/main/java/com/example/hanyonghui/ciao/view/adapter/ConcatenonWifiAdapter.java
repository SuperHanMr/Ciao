package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PlanTingBean;
import com.example.hanyonghui.ciao.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/10/10.
 * Holle Android
 */

public class ConcatenonWifiAdapter extends RecyclerView.Adapter<ConcatenonWifiAdapter.MyConcatenonViewHodler> {


    private Context context;
    private List<PlanTingBean> mList;
    private String mac;


    public ConcatenonWifiAdapter(Context context) {
        mList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MyConcatenonViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyConcatenonViewHodler hodler = new MyConcatenonViewHodler(LayoutInflater.from(context).inflate(R.layout.concatenonwifi_adapter_item,parent,false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(MyConcatenonViewHodler holder, final int position) {
        mac = mList.get(position).getMAC();
        holder.tvMac.setText(mac);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litener.Listener(mList.get(position).getMAC());
            }
        });

    }

    private onConcatWifiLitener litener;
    public interface onConcatWifiLitener{
        void Listener(String s);
    }

    public void setConcatenonListener(onConcatWifiLitener litener){
        this.litener = litener;
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }


    public  void setData(List<PlanTingBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public static class MyConcatenonViewHodler extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView tvMac;

        public MyConcatenonViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.conactenon_btn);
            tvMac = (TextView) itemView.findViewById(R.id.conactenon_tv_mac);
        }
    }

}
