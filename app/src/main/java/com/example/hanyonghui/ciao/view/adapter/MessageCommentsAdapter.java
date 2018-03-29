package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.MessageCommentsBean;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/27.
 * Holle Android
 */

public class MessageCommentsAdapter extends RecyclerView.Adapter<MessageCommentsAdapter.MyCommentsViewHodler> {

    List<MessageCommentsBean.CBean.DBean> lists;

    public MessageCommentsAdapter(Context context) {
        lists = new ArrayList<>();
        this.context = context;
    }

    private Context context;

    @Override
    public MyCommentsViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyCommentsViewHodler hodler = new MyCommentsViewHodler(LayoutInflater.from(context).inflate(R.layout.post_recycler_item,parent,false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(MyCommentsViewHodler holder, int position) {
        MessageCommentsBean.CBean.DBean.UserBean user = lists.get(position).getUser();
        if (!user.getPurl().equals("")){
            Picasso.with(context).load(user.getPurl()).error(R.drawable.ciao_logo).into(holder.headImageView);
        }else {
            Picasso.with(context).load(RequestUrls.MOHEAD).error(R.drawable.ciao_logo).into(holder.headImageView);
        }

        holder.tvName.setText(user.getNn());
        holder.tvContent.setText(lists.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return lists.size()==0?0:lists.size();
    }

    public void setLists(List<MessageCommentsBean.CBean.DBean> d){
        this.lists = d;
        notifyDataSetChanged();
    }

    public static class MyCommentsViewHodler extends RecyclerView.ViewHolder{

        private final CircleImageView headImageView;
        private final TextView tvName;
        private final TextView tvContent;

        public MyCommentsViewHodler(View itemView) {
            super(itemView);

            headImageView = (CircleImageView) itemView.findViewById(R.id.post_recycler_criticism_head);
            tvName = (TextView) itemView.findViewById(R.id.post_recycler_criticism_name);
            tvContent = (TextView) itemView.findViewById(R.id.post_recycler_criticism_content);
        }
    }

}
