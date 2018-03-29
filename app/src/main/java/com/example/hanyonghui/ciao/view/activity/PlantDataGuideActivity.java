package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PlantsGuideBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.MusicProgressBar;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/1.
 */

public class PlantDataGuideActivity extends AutoLayoutActivity {

    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
    @BindView(R.id.musicProgressBar)
    MusicProgressBar musicProgressBar;
    @BindView(R.id.guide_tv_planname)
    TextView guideTvPlanname;
    @BindView(R.id.guide_ed_cycle)
    TextView guideEdCycle;
    @BindView(R.id.guide_tv_water)
    TextView guideTvWater;
    @BindView(R.id.guide_tv_spray)
    TextView guideTvSpray;
    @BindView(R.id.base_acticity_toolbar_tv_fight)
    TextView baseActicityToolbarTvFight;
    @BindView(R.id.bouttn_lin)
    View bouttnLin;
    @BindView(R.id.login_iv_weixi)
    TextView loginIvWeixi;
    private String id;
    private HttpParams httpParams = new HttpParams();
    private List<PlantsGuideBean.CBean.GuBean> gu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantdata_guide_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        id = getIntent().getStringExtra(PlantDataAvtivity.PLANDID);
        httpParams.put("typeId", id);
        initView();
        loData();
    }

    // 初始化UI
    private void initView() {
        baseActicityToolbarTv.setText("园丁养成");
        if (SPUtils.getBoolean(KeyUtils.ADMINISTRATORS, false)) {
            baseActicityToolbarTvFight.setText("修改");
        }

        musicProgressBar.setChangeListener(new MusicProgressBar.OnProgressChangeListener() {
            @Override
            public void onProgressChange(int duration, int progress) {
                LogUtils.e(progress + "");
            }

            @Override
            public void onProgressChangeEnd(int duration, int progress) {
                LogUtils.e(progress + "");
                int size = gu.size();
                int[] arr = new int[gu.size()]; // 数组存的就是 每个点的位置
                for (int i = 0; i < gu.size(); i++) {
                    arr[i] = gu.get(i).getDay() + arr[i == 0 ? 0 : i - 1];
                    LogUtils.e(arr[i] + "----");
                }


                for (int i = 0; i < arr.length; i++) {
                    int i1 = arr[i];


                    LogUtils.d(i1 + "至" + "----->" + progress);
                    //(i1+5)>progress&&progress>(i1-5)
                    if ((i1 + 5) > progress && progress > (i1 - 5)) {
                        guideTvPlanname.setText(gu.get(i).getTitle());
                        guideEdCycle.setText(gu.get(i).getContent());
                        guideTvWater.setText(gu.get(i).getWateringName());
                        guideTvSpray.setText(gu.get(i).getSprayName());
                        break;
                    }

                }

            }
        });
    }




    // 联网加载数据
    private void loData() {

        NetworkReuset.getInstance().PostReuset(RequestUrls.PLANTDEVELOP, httpParams, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                setData(s);
            }
        });
    }

    private List<Integer> list = new ArrayList<>();

    // 设置数据
    private void setData(String s) {
        Gson gson = new Gson();
        PlantsGuideBean bean = gson.fromJson(s, PlantsGuideBean.class);
        gu = bean.getC().getGu();
        if (gu != null) {
            for (int i = 0; i < gu.size(); i++) {
                int day = gu.get(i).getDay();
                list.add(day);
            }
            musicProgressBar.setDot(list);
            guideTvPlanname.setText(gu.get(0).getTitle());
            guideEdCycle.setText(gu.get(0).getContent());
            guideTvWater.setText(gu.get(0).getWateringName());
            guideTvSpray.setText(gu.get(0).getSprayName());
        }


    }


    @OnClick({R.id.base_acticity_toolbar_iv_left, R.id.base_acticity_toolbar_tv_fight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_acticity_toolbar_iv_left:
                finish();
                break;
            case R.id.base_acticity_toolbar_tv_fight:

                startActivity(new Intent(PlantDataGuideActivity.this,GardenerCultivateActivity.class).putExtra(KeyUtils.PLANDATAID,id));

                finish();
                break;
        }
    }
}
