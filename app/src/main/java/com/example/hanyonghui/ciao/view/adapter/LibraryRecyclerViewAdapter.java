package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.LibraryListBean;

import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.view.activity.PlantDataAvtivity;
import com.example.hanyonghui.ciao.view.views.ImageEditdingView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hanyonghui on 2017/7/23.
 */

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.MyViewHolder> {


    List<List<LibraryListBean.CBean.DBean>> list;
    private List<LibraryListBean.CBean.DBean> bean;
    private Context context;

    public LibraryRecyclerViewAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<LibraryListBean.CBean.DBean> bean){
        this.bean = bean;
    }

    // 创建植物库列表item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder= new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.library_item_recycler,parent,false)) ;
        return holder;
    }


    // 绑定数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
            // 加载item头像
        if (!bean.get(position).getImgurl().equals("")){
            Picasso.with(context).load(bean.get(position).getImgurl()).into(holder.ivHead);
        }

        holder.tvName.setText(bean.get(position).getTypename());
        //点击进入植物资料卡
      holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d(bean.get(position).getId());
                context.startActivity(new Intent(context, PlantDataAvtivity.class)
                        .putExtra(KeyUtils.PLANDATAID,bean.get(position).getId()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return bean!=null?bean.size():0;
    }


    // 创建RecycelrViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        private final RelativeLayout rl;
        private final TextView tvName;
        private final CircleImageView ivHead;
        public MyViewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.library_item_rl);
            ivHead = (CircleImageView) itemView.findViewById(R.id.library_item_iv_head);
            tvName = (TextView) itemView.findViewById(R.id.library_item_tv_name);

        }
    }


}
