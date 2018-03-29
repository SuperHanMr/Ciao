package com.example.hanyonghui.ciao.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.UserBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.UserModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.activity.BangActivity;

import com.example.hanyonghui.ciao.view.activity.EquipmentSttringActivity;
import com.example.hanyonghui.ciao.view.activity.GardenerUniversityActivity;
import com.example.hanyonghui.ciao.view.activity.ImageViewRecognitionActivity;
import com.example.hanyonghui.ciao.view.activity.LampStiingActivity;
import com.example.hanyonghui.ciao.view.activity.MePostListActivity;
import com.example.hanyonghui.ciao.view.activity.StiingActivity;
import com.example.hanyonghui.ciao.view.activity.TheFairActivity;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/23.
 */

public class MeFragment extends Fragment {

    @BindView(R.id.me_fragmennt_iv_head)
    CircleImageView meFragmenntIvHead;
    @BindView(R.id.me_fragmennt_tv_name)
    TextView meFragmenntTvName;

    private HttpParams params = new HttpParams();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loDataMe();
    }

    private void loDataMe() {
        params.put("uid", SPUtils.getString(KeyUtils.USERID));
        NetworkReuset.getInstance().PostReuset(RequestUrls.USERDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                analysisData(s);
                LogUtils.d("----------------->");
            }
        });

    }

    private void analysisData(String s) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(s, UserBean.class);
        int type = userBean.getH().getS();
        if (type==1){
            if (!userBean.getC().getPurl().equals("")){
                Picasso.with(getContext()).load(userBean.getC().getPurl()).into(meFragmenntIvHead);
            }
            meFragmenntTvName.setText(userBean.getC().getNn());
            EventBus.getDefault().postSticky(new UserModel(userBean));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 下半部分跳转
    @OnClick({R.id.me_fragmennt_iv_head,R.id.me_fragmennt_stting, R.id.me_fragment_fair, R.id.me_fragment_guide, R.id.me_fragment_post, R.id.me_fragment_feedback, R.id.me_fragment_ar, R.id.me_fragment_we,R.id.me_fragment_shitu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_fragmennt_stting:
                getActivity().startActivity(new Intent(getContext(), StiingActivity.class));
                break;
            case R.id.me_fragment_fair:
                getActivity().startActivity(new Intent(getContext(), TheFairActivity.class));
                break;
            case R.id.me_fragment_guide:
                getActivity().startActivity(new Intent(getContext(), GardenerUniversityActivity.class));
                break;
            case R.id.me_fragment_post:
                getActivity().startActivity(new Intent(getActivity(),MePostListActivity.class));
                break;
            case R.id.me_fragment_feedback:
                getActivity().startActivity(new Intent(getContext(), BangActivity.class));
                break;
            case R.id.me_fragment_ar:
                getActivity().startActivity(new Intent(getContext(), LampStiingActivity.class));
                break;
            case R.id.me_fragment_we:
                break;
            case R.id.me_fragmennt_iv_head:
                getActivity().startActivity(new Intent(getContext(), StiingActivity.class));
                break;
            case R.id.me_fragment_shitu:
                getActivity().startActivity(new Intent(getContext(),ImageViewRecognitionActivity.class));
                break;
        }
    }
}
