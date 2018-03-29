package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CommuntiyPostListBean;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.view.activity.PostdetailsActivity;
import com.example.hanyonghui.ciao.view.views.GridViewInScroll;
import com.example.hanyonghui.ciao.view.views.ImageEditdingView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hanyonghui on 2017/9/14.
 *
 * 社区帖子适配器
 */

public class ConmunityPostsListAdapter extends RecyclerView.Adapter<ConmunityPostsListAdapter.CommunityViewHodler> {


    private Context context;
    private List<CommuntiyPostListBean.CBean.DBean> mList;
    private ImageView[] views;


    public ConmunityPostsListAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }


    @Override
    public CommunityViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        CommunityViewHodler hodler = new CommunityViewHodler(LayoutInflater.from(context).inflate(R.layout.community_post_item,parent,false));
        return hodler;
    }

    @Override
    public void onBindViewHolder (CommunityViewHodler holder, int position) {



        String createtime = mList.get(position).getCreatetime(); // 帖子时间

        String body = mList.get(position).getBody(); // 帖子内容

        final String postId = mList.get(position).getId();

        // 获取帖子UserBean
        CommuntiyPostListBean.CBean.DBean.UserBean user = mList.get(position).getUser();

        String purl = user.getPurl();// 用户头像
        String userNn = user.getNn();//  用户 Name
        String uid = user.getUid();// 用户Id
        if (!purl.equals("")){
            Picasso.with(context).load(purl).into(holder.ivHead);
        }else {
            Picasso.with(context).load(RequestUrls.MOHEAD).into(holder.ivHead);
        }

        holder.tvName.setText(userNn);
        holder.tvTime.setText(createtime);
        holder.tvContent.setText(body);

        // 获取帖子图片
        List<CommuntiyPostListBean.CBean.DBean.ImgsBean> imgs = mList.get(position).getImgs();
        // 这是 图片的集合 我要把集合传给 imgs GridViewInScroll 照片墙
        holder.tvThumbSum.setText(mList.get(position).getPraises()+""); // 获取点赞数
        holder.tvCommentaeies.setText(mList.get(position).getComments()+""); // 获取评论数

        List<String> urls = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            String imgurl = imgs.get(i).getImgurl();
            urls.add(imgurl);
        }
        CommunityGridViewAdapter adapter = new CommunityGridViewAdapter(context,urls);
        holder.gridView.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PostdetailsActivity.class).putExtra("twitterid",postId));
            }
        });

    }



    public void setmList(List<CommuntiyPostListBean.CBean.DBean> mList){
        this.mList = mList;
//        notifyItemChanged(0);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size()!=0?mList.size():0;
    }

    public class CommunityViewHodler extends RecyclerView.ViewHolder{


        private final CircleImageView ivHead;
        private final TextView tvName;
        private final TextView tvTime;
        private final TextView tvContent;
        private final TextView tvThumbSum; // 点赞数量
        private final TextView tvCommentaeies; // 评论数量
        private final GridViewInScroll gridView;


        public CommunityViewHodler(View itemView) {
            super(itemView);
            ivHead = (CircleImageView) itemView.findViewById(R.id.community_iv_head);
            tvName = (TextView) itemView.findViewById(R.id.community_tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.community_tv_time);
            tvContent = (TextView) itemView.findViewById(R.id.community_tv_content);
            gridView = (GridViewInScroll) itemView.findViewById(R.id.community_gridview);
            tvThumbSum = (TextView) itemView.findViewById(R.id.thmub_tv_sum);
            tvCommentaeies = (TextView) itemView.findViewById(R.id.community_tv_commentaries);

        }
    }
}
