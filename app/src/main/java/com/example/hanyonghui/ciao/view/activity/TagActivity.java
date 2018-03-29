package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.LibraryBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.TagModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.TagAdapter;
import com.example.hanyonghui.ciao.view.fragment.LibraryTabFragment;
import com.google.gson.Gson;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/5.
 */

public class TagActivity extends AutoLayoutActivity {

    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
    @BindView(R.id.tag_activity_location)
    FlowTagLayout tagActivityLocation;
    @BindView(R.id.tag_activity_purpose)
    FlowTagLayout tagActivityPurpose;
    @BindView(R.id.tag_activity_gift)
    FlowTagLayout tagActivityGift;
    @BindView(R.id.tag_activity_season)
    FlowTagLayout tagActivitySeason;
    @BindView(R.id.tag_activity_fengshui)
    FlowTagLayout tagActivityFengshui;
    @BindView(R.id.tag_activity_easy)
    FlowTagLayout tagActivityEasy;
    @BindView(R.id.tag_activity_popular)
    FlowTagLayout tagActivityPopular;

    private TagAdapter locationAdapter;
    private TagAdapter purposeAdapter;
    private TagAdapter gifAdapter;
    private TagAdapter seasonAdapter;
    private TagAdapter fengshuiAdapter;
    private TagAdapter easyAdapter;

    private HttpParams params = new HttpParams();
    private String id;
    private TagAdapter popularAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra(PlantDataAvtivity.PLANDID);
        APP.getApp().addActivity(this);
        initView();
        LogData();
    }

    private void LogData() {
        NetworkReuset.getInstance().
                GetReuset(RequestUrls.LIBRARYURL, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.d(s);
                        processData(s);
                    }
                });


    }

    // 拿到数据
    private void processData(String json) {
        List<LibraryBean.CBean> popular = new ArrayList<>();
        List<LibraryBean.CBean> locations = new ArrayList<>();
        List<LibraryBean.CBean> purposes = new ArrayList<>();
        List<LibraryBean.CBean> gifts = new ArrayList<>();
        List<LibraryBean.CBean> seasons = new ArrayList<>();
        List<LibraryBean.CBean> fengshuis = new ArrayList<>();
        List<LibraryBean.CBean> easys = new ArrayList<>();
        Gson gson = new Gson();
        LibraryBean libraryBean = gson.fromJson(json, LibraryBean.class);
        List<LibraryBean.CBean> list = libraryBean.getC();
        for (LibraryBean.CBean bean : list) {
            switch (bean.getName()) {
                case "通俗大类":
                    popular.add(bean);
                    break;
                case "摆放位置":
                    locations.add(bean);
                    break;
                case "功能用途":
                    purposes.add(bean);
                    break;
                case "礼品场合":
                    gifts.add(bean);
                    break;
                case "季节":
                    seasons.add(bean);
                    break;
                case "风水":
                    fengshuis.add(bean);
                    break;
                case "难易程度":
                    easys.add(bean);
                    break;
            }
        }

        popularAdapter.onlyAddAll(popular);
        locationAdapter.onlyAddAll(locations);
        purposeAdapter.onlyAddAll(purposes);
        gifAdapter.onlyAddAll(gifts);
        seasonAdapter.onlyAddAll(seasons);
        fengshuiAdapter.onlyAddAll(fengshuis);
        easyAdapter.onlyAddAll(easys);
    }


    private void initView() {
        // 设置标题

        baseActicityToolbarTv.setText(id != null ? "选择标签" : "筛选");
        /*
        初始化每个FlowTagLayout
         */
        //通俗大类
        popularAdapter = new TagAdapter(this);
        tagActivityPopular.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityPopular.setAdapter(popularAdapter);
        tagActivityPopular.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }

                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    LogUtils.e(substring);
                    params.put("populars", substring);
                }
            }
        });

        // 设置摆放位置
        locationAdapter = new TagAdapter(this);
        tagActivityLocation.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityLocation.setAdapter(locationAdapter);
        tagActivityLocation.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("places", substring);
                }

            }
        });
        // 功能用途
        purposeAdapter = new TagAdapter(this);
        tagActivityPurpose.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityPurpose.setAdapter(purposeAdapter);
        tagActivityPurpose.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("presents", substring);
                }
            }
        });
        // 礼品场合
        gifAdapter = new TagAdapter(this);
        tagActivityGift.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityGift.setAdapter(gifAdapter);
        tagActivityGift.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("presents", substring);
                }
            }
        });

        // 季节
        seasonAdapter = new TagAdapter(this);
        tagActivitySeason.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivitySeason.setAdapter(seasonAdapter);
        tagActivitySeason.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("presents", substring);
                }
            }
        });
        // 风水
        fengshuiAdapter = new TagAdapter(this);
        tagActivityFengshui.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityFengshui.setAdapter(fengshuiAdapter);
        tagActivityFengshui.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("presents", substring);
                }
            }
        });

        // 难易程度
        easyAdapter = new TagAdapter(this);
        tagActivityEasy.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagActivityEasy.setAdapter(easyAdapter);
        tagActivityEasy.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        LibraryBean.CBean item = (LibraryBean.CBean) parent.getAdapter().getItem(i);
                        sb.append(item.getId());
                        sb.append(",");
                    }
                    String substring = sb.toString().substring(0, sb.toString().length() - 1);
                    params.put("presents", substring);
                }
            }
        });
    }

    @OnClick(R.id.base_acticity_toolbar_iv_left)
    public void onViewClicked() {
        finish();
    }


    @OnClick({R.id.tag_activity_btn_reset, R.id.tag_activity_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tag_activity_btn_reset:
                tagActivityLocation.clearAllOption();
                tagActivityEasy.clearAllOption();
                tagActivityFengshui.clearAllOption();
                tagActivityGift.clearAllOption();
                tagActivityPurpose.clearAllOption();
                tagActivitySeason.clearAllOption();
                params.clear();
                break;
            case R.id.tag_activity_btn_confirm:
                if (id != null) {
                    params.put("id", id);
                    LogUtils.d("*********>TagActivity");
                    postTagData();
                } else {
                    LogUtils.e("------------>发送事件");
                    EventBus.getDefault().post(new TagModel(params, LibraryTabFragment.typeFrgamnt));
                }

                finish();
                break;
        }
    }

    private void postTagData() {
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTTYPE, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.d(s);
            }
        });
    }
}
