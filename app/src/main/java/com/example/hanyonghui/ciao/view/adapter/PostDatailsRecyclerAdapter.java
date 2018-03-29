package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PostDatailsBean;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hanyonghui on 2017/9/18.
 */

public class PostDatailsRecyclerAdapter extends RecyclerView.Adapter<PostDatailsRecyclerAdapter.PostDatailsViewhodler> {

    private Context context;

    List<PostDatailsBean.CBean.CommontsBean> beanList;

    public PostDatailsRecyclerAdapter(Context context) {
        this.context = context;
        beanList = new ArrayList<>();
    }

    @Override
    public PostDatailsViewhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        PostDatailsViewhodler hodler = new PostDatailsViewhodler(LayoutInflater.from(context).inflate(R.layout.post_recycler_item,parent,false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(PostDatailsViewhodler holder, int position) {
        // 获取评论者的信息
        PostDatailsBean.CBean.CommontsBean.UserBeanX user = beanList.get(position).getUser();
        if (!user.getPurl().equals("")){
            Picasso.with(context).load(user.getPurl()).into(holder.ivHead);
        }else {
            Picasso.with(context).load(RequestUrls.MOHEAD).into(holder.ivHead);
        }

        holder.tvCriticismName.setText(user.getNn());
        holder.tvContent.setText(beanList.get(position).getBody());
    }

    public void setBeanList (List<PostDatailsBean.CBean.CommontsBean> beanList){
        this.beanList = beanList;
         notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class PostDatailsViewhodler extends RecyclerView.ViewHolder{

        private final TextView tvContent;
        private final TextView tvCriticismName;
        private final CircleImageView ivHead;

        public PostDatailsViewhodler(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.post_recycler_criticism_content);
            tvCriticismName = (TextView) itemView.findViewById(R.id.post_recycler_criticism_name);
            ivHead = (CircleImageView) itemView.findViewById(R.id.post_recycler_criticism_head);
        }
    }

}
