package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.MessageTumubBean;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/26.
 * Holle Android
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MyMessageThumbViewHodler> {


    private Context context;
    List<MessageTumubBean.CBean.DBean> thumbs;

    public MessageRecyclerAdapter(Context context) {
        this.context = context;
        thumbs = new ArrayList<>();
    }



    @Override
    public MyMessageThumbViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyMessageThumbViewHodler hodler = new MyMessageThumbViewHodler(LayoutInflater.from(context).inflate(R.layout.message_thumb_recycler_item,parent,false));
        return hodler;
    }


    @Override
    public void onBindViewHolder(MyMessageThumbViewHodler holder, int position) {
        MessageTumubBean.CBean.DBean.UserBean user = thumbs.get(position).getUser();
        if (!user.getPurl().equals("")){
            Picasso.with(context).load(user.getPurl()).error(R.drawable.ciao_logo).into(holder.headImageView);
        }else {
            Picasso.with(context).load(RequestUrls.MOHEAD).error(R.drawable.ciao_logo).into(holder.headImageView);
        }
        holder.tvName.setText(user.getNn());
    }

    @Override
    public int getItemCount() {
        return thumbs.size()==0?0:thumbs.size();
    }

    public void setThumbs (List<MessageTumubBean.CBean.DBean> thumbs){
        this.thumbs = thumbs;
        notifyDataSetChanged();
    }



    public static class MyMessageThumbViewHodler extends RecyclerView.ViewHolder{

        private final TextView tvName;
        private final CircleImageView headImageView;

        public MyMessageThumbViewHodler(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.post_recycler_criticism_name);
            headImageView = (CircleImageView) itemView.findViewById(R.id.post_recycler_criticism_head);
        }


    }

}
