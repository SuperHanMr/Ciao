package com.example.hanyonghui.ciao.view.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.EqipmentListBean;
import com.example.hanyonghui.ciao.bean.connector.OnDragAndSwipeListener;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.LampStiingActivity;
import com.example.hanyonghui.ciao.view.activity.PumpStiingActivity;

import com.example.hanyonghui.ciao.view.views.SwipeLayout;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/24.
 * 内容 配置WIFI
 */

public class MeEauipmentRecyclerViewAdapter extends RecyclerView.Adapter<MeEauipmentRecyclerViewAdapter.MyMeViewHoder> implements OnDragAndSwipeListener{

    private Context context;
    private List<EqipmentListBean.CBean> mList;
    private HttpParams params = new HttpParams();

    public MeEauipmentRecyclerViewAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }
    @Override
    public MyMeViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyMeViewHoder viewHoder = new MyMeViewHoder(LayoutInflater.from(context).inflate(R.layout.me_quipment_item,parent,false));
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(MyMeViewHoder holder, final int position) {
        final int devicetype = mList.get(position).getDevicetype();
        // 判断不同type 绑定不同数据
        switch (devicetype){
            case 4:
                holder.ivHead.setImageResource(R.drawable.pump_icon);
                break;
            case 5:
                holder.ivHead.setImageResource(R.drawable.value_icon);
                break;
            case 1:
                holder.ivHead.setImageResource(R.drawable.detect_icon);
                break;
            case 6:
                holder.ivHead.setImageResource(R.drawable.lamp_icon);
                break;
        }

        holder.tvQuipmentName.setText(mList.get(position).getDn());
        holder.tvLine.setText(mList.get(position).getOnline()==1?"在线":"离线");




        holder.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.putString(KeyUtils.EQUIPMENTNAME,mList.get(position).getDn());
                if (devicetype==6){
                    SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,6);
                    context.startActivity(new Intent(context, LampStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID,mList.get(position).getDid()));
                }else {
                    SPUtils.putInt(KeyUtils.EQUIPMENTTYPE,devicetype);
                    context.startActivity(new Intent(context, PumpStiingActivity.class).putExtra(KeyUtils.EQUIPMENTNAMEID,mList.get(position).getDid()));
                }
            }
        });




        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(position+"");
                String did = mList.get(position).getDid();
                params.put("uid", SPUtils.getString(KeyUtils.USERID));
                params.put("did",did);
                NetworkReuset.getInstance().PostReuset(RequestUrls.DEKETEEQUIPMENTNAME, params, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            mList.remove(position);
                            notifyDataSetChanged();
                    }
                });
            }
        });

    }

    public void onItemDismiss(final int position){

            LogUtils.d(position+"");

    }


    public void setEquipmentData(List<EqipmentListBean.CBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size()!=0?mList.size():0;
    }


    class MyMeViewHoder extends RecyclerView.ViewHolder{

        private final ImageView ivHead;
        private final TextView tvQuipmentName;
        private final TextView tvLine;
        private final ImageView modify;

        public MyMeViewHoder(View itemView) {
            super(itemView);
            ivHead = (ImageView) itemView.findViewById(R.id.me_quipment_item_iv_head);
            tvQuipmentName = (TextView) itemView.findViewById(R.id.me_tv_ciao);
            tvLine = (TextView) itemView.findViewById(R.id.me_quipment_item_tv_line);
            modify = (ImageView) itemView.findViewById(R.id.tv_modify);

        }
    }
}
