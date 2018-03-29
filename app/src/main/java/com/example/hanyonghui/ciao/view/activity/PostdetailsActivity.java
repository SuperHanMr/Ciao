package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PostCommentBena;
import com.example.hanyonghui.ciao.bean.bean.PostDatailsBean;
import com.example.hanyonghui.ciao.bean.bean.UserBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.PostDatailsModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.CommunityGridViewAdapter;
import com.example.hanyonghui.ciao.view.adapter.PostDatailsRecyclerAdapter;
import com.example.hanyonghui.ciao.view.views.GridViewInScroll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/9/18.
 * <p>
 * 帖子详情
 */

public class PostdetailsActivity extends AutoLayoutActivity {


    @BindView(R.id.community_iv_head)
    CircleImageView communityIvHead;
    @BindView(R.id.community_tv_name)
    TextView communityTvName;
    @BindView(R.id.community_tv_time)
    TextView communityTvTime;
    @BindView(R.id.community_tv_content)
    TextView communityTvContent;
    @BindView(R.id.community_gridview)
    GridViewInScroll communityGridview;
    @BindView(R.id.community_tv_commentaries)
    TextView communityTvCommentaries;
    @BindView(R.id.thmub_tv_sum)
    TextView thmubTvSum;
    @BindView(R.id.datails_recyclerview)
    RecyclerView datailsRecyclerview;
    @BindView(R.id.datails_ed_comment)
    EditText datailsEdComment;
    @BindView(R.id.checkbox_thumb)
    CheckBox checkboxThumb;
    private String postId;
    private String uid;
    private HttpParams params = new HttpParams();
    private PostDatailsRecyclerAdapter criticismAdapter;
    private String previousId;
    private List<PostDatailsBean.CBean.CommontsBean> beanList;
    private String userid;
    private boolean isThumb; // 记录自己是否点赞
    private int top;

    private String thumbId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datails_activity);
        ButterKnife.bind(this);
        postId = getIntent().getStringExtra("twitterid");
        uid = getIntent().getStringExtra("userid");
        // 初始化帖子详情
        initView();
        loData();
    }






    private void initView() {
        criticismAdapter = new PostDatailsRecyclerAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        datailsRecyclerview.setLayoutManager(manager);
        datailsRecyclerview.setAdapter(criticismAdapter);


        datailsEdComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    LogUtils.d("发送评论");
                    sendOutComment();
                }
                return false;
            }
        });

        checkboxThumb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (top!=1){
                        thumbUp();
                    }
                }else {
                    cancleThumb();
                    top = 2;
                }
            }
        });
    }


    // 取消点赞
    private void cancleThumb() {
        params.clear();
        params.put("uid",SPUtils.getString(KeyUtils.USERID));
        params.put("commentId",thumbId);
        NetworkReuset.getInstance().PostReuset(RequestUrls.DELETETHUMB, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                MyToast.show(PostdetailsActivity.this,"取消点赞");
                EventBus.getDefault().post(new PostDatailsModel(1));
            }
        });

    }


    // 点赞
    private void thumbUp() {
        params.clear();
        params.put("twitterid",postId);
        params.put("uid",SPUtils.getString(KeyUtils.USERID));
        NetworkReuset.getInstance().PostReuset(RequestUrls.ADDTHUMB, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                PostCommentBena bena = gson.fromJson(s, PostCommentBena.class);
                userid = bena.getC().getUserid();
                EventBus.getDefault().post(new PostDatailsModel(1));
            }
        });

    }

    // 发送评论
    private void sendOutComment() {
        params.clear();
        String body = datailsEdComment.getText().toString().trim();
        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        params.put("twitterid", postId);
        params.put("body", body);
        params.put("parentId", previousId);
        NetworkReuset.getInstance().PostReuset(RequestUrls.ADDCOMMENT, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                processComment(s);
            }
        });

    }

    private void processComment(String s) {
        params.clear();
        Gson gson = new Gson();
        PostCommentBena postCommentBena = gson.fromJson(s, PostCommentBena.class);
        final PostDatailsBean.CBean.CommontsBean bean = new PostDatailsBean.CBean.CommontsBean();
        final PostDatailsBean.CBean.CommontsBean.UserBeanX user = new PostDatailsBean.CBean.CommontsBean.UserBeanX();
        bean.setBody(postCommentBena.getC().getBody());
        params.put("uid", postCommentBena.getC().getUserid());
        NetworkReuset.getInstance().PostReuset(RequestUrls.USERDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson json = new Gson();
                UserBean userBean = json.fromJson(s, UserBean.class);
                user.setPurl(userBean.getC().getPurl());
                user.setNn(userBean.getC().getNn());
                user.setUid(bean.getUserid());
                bean.setUser(user);
                beanList.add(0, bean);
                criticismAdapter.setBeanList(beanList);
                datailsEdComment.setText("");
                LogUtils.d("----------------->");
                EventBus.getDefault().post(new PostDatailsModel(1));
            }
        });

    }

    private void loData() {
        params.put("twitterid", postId);
        params.put("uid", uid);
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTDETAILS, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                processing(s);
            }


        });
    }

    private void processing(String s) {
        s=s.replace("\"user\":\"\"","\"user\":null");
        Gson gson = new Gson();
        PostDatailsBean bean = gson.fromJson(s, PostDatailsBean.class);
        PostDatailsBean.CBean.UserBean user = bean.getC().getUser();
        if (!user.getPurl().equals("")) {
            Picasso.with(this).load(user.getPurl()).into(communityIvHead);
        }
        communityTvName.setText(user.getNn());
        communityTvTime.setText(bean.getC().getCreatetime());
        communityTvContent.setText(bean.getC().getBody());
        List<PostDatailsBean.CBean.ImgsBean> imgs = bean.getC().getImgs();
        List<String> imageUrls = new ArrayList();
        for (int i = 0; i < imgs.size(); i++) {
            imageUrls.add(imgs.get(i).getImgurl());
        }
        CommunityGridViewAdapter adapter = new CommunityGridViewAdapter(this, imageUrls);
        communityGridview.setAdapter(adapter);
        communityTvCommentaries.setText(bean.getC().getComments() + "");
        thmubTvSum.setText(bean.getC().getPraises() + "");



        //******************** 评论区数据 ***********************//
        beanList = bean.getC().getCommonts();

        // 存进去点赞ID 匹配点赞ID里是否有自己
        for (int i = 0; i < beanList.size(); i++) {

            if (beanList.get(i).getIscomment()==2){
                // 获取点赞id
                thumbId= beanList.get(i).getId();
                if ( beanList.get(i).getUser().getUid().equals(SPUtils.getString(KeyUtils.USERID))){
                    top = 1;
                    checkboxThumb.setChecked(true);
                }
            }

        }


        // 遍历集合里面评论 为""说明只是点赞并没有评论 就删除不作为评论item
        Iterator<PostDatailsBean.CBean.CommontsBean> iterator = beanList.iterator();
        while (iterator.hasNext()){
            if ( iterator.next().getBody().equals("")) {
                iterator.remove();
            }
        }

        criticismAdapter.setBeanList(beanList);
        // 获取评论列表最后一个用户ID
        if (beanList.size() == 0 || beanList == null) {
            return;
        }
        previousId = beanList.get(beanList.size() - 1).getUser().getUid();
    }

    @OnClick({R.id.datails_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.datails_back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
