package com.example.hanyonghui.ciao.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PlanDataDescribeBean;
import com.example.hanyonghui.ciao.bean.bean.PlantDataBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.GetNumUtlis;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.RoundedUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.RatingBarView;
import com.example.hanyonghui.ciao.view.views.WeiboDialogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/7/25.
 */

public class PlantDataAvtivity extends AutoLayoutActivity {
    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
    @BindView(R.id.base_acticity_toolbar_tv_fight)
    TextView baseActicityToolbarTvFight;
    @BindView(R.id.plandata_gude_lin)
    View plandataGudeLin;
    @BindView(R.id.plandata_describe_lin)
    View plandataDescribeLin;
    @BindView(R.id.layout_plantdata_guide)
    LinearLayout layoutPlantdataGuide;
    @BindView(R.id.layout_plantdata_describe)
    LinearLayout layoutPlantdataDescribe;
    @BindView(R.id.ratinview_moisture)
    RatingBarView ratinviewMoisture;
    @BindView(R.id.ratinview_light)
    RatingBarView ratinviewLight;
    @BindView(R.id.ratinview_fertilizer)
    RatingBarView ratinviewFertilizer;
    @BindView(R.id.plandata_ed_temperature)
    EditText plandataEdTemperature;
    @BindView(R.id.plandata_ed_temperature_final)
    EditText plandataEdTemperatureFinal;
    @BindView(R.id.plandata_ed_fertilizer)
    EditText plandataEdFertilizer;
    @BindView(R.id.plandata_ed_pests)
    EditText plandataEdPests;
    @BindView(R.id.plandata_iv_head)
    CircleImageView plandataIvHead;
    @BindView(R.id.plandata_tv_name)
    TextView plandataTvName;
    @BindView(R.id.plandata_describe_ed_latin)
    EditText plandataDescribeEdLatin;
    @BindView(R.id.plandata_describe_ed_alis)
    EditText plandataDescribeEdAlis;
    @BindView(R.id.plandata_ed_genaer)
    EditText plandataEdGenaer;
    @BindView(R.id.plandata_ed_personality)
    EditText plandataEdPersonality;
    @BindView(R.id.plandata_ed_scientific)
    EditText plandataEdScientific;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.plandata_describe_btn)
    Button plandataDescribeBtn;
    @BindView(R.id.plandata_btn_develop)
    Button plandataBtnDevelop;


    // ******月份的CheckBox *********//
    @BindView(R.id.checkbox_yi)
    CheckBox checkboxYi;
    @BindView(R.id.checkbox_er)
    CheckBox checkboxEr;
    @BindView(R.id.checkbox_san)
    CheckBox checkboxSan;
    @BindView(R.id.checkbox_si)
    CheckBox checkboxSi;
    @BindView(R.id.checkbox_wu)
    CheckBox checkboxWu;
    @BindView(R.id.checkbox_liu)
    CheckBox checkboxLiu;
    @BindView(R.id.checkbox_qi)
    CheckBox checkboxQi;
    @BindView(R.id.checkbox_ba)
    CheckBox checkboxBa;
    @BindView(R.id.checkbox_jiu)
    CheckBox checkboxJiu;
    @BindView(R.id.checkbox_shi)
    CheckBox checkboxShi;
    @BindView(R.id.checkbox_shiyi)
    CheckBox checkboxShiyi;
    @BindView(R.id.checkbox_shier)
    CheckBox checkboxShier;
    @BindView(R.id.plandata_breed)
    EditText plandataBreed;
    @BindView(R.id.plandata_guide)
    TextView plandataGuide;
    @BindView(R.id.plandata_describe)
    TextView plandataDescribe;

    private List<String> months = new ArrayList<>();
    private List<CheckBox> checkBoxList;

    private int MOISTURE;// 记录水分
    private int LIGHT; // 记录光照
    private int FERTILIZER;//记录化肥

    private HttpParams params = new HttpParams();

    private HttpParams postParams = new HttpParams();

    private String ID;

    public static final String PLANDID = "id";

    public static final String ADDTAG = "tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plandata_activity);
        ButterKnife.bind(this);
        APP.getApp().addActivity(this);
        Intent intent = getIntent();
        ID = intent.getStringExtra(KeyUtils.PLANDATAID);
        initView();
        loDataInfo();
        loaDescription();
        administrator(SPUtils.getBoolean(KeyUtils.ADMINISTRATORS, false));
        LogUtils.d(SPUtils.getBoolean(KeyUtils.ADMINISTRATORS, false) + "--------------->");
    }

    // 初始化View
    private void initView() {

        baseActicityToolbarTv.setText("植物资库");
        // baseActicityToolbarTvFight.setText("市集");


        if (!SPUtils.getBoolean(KeyUtils.ADMINISTRATORS,false)){
            ratinviewMoisture.setClickable(false);
            ratinviewLight.setClickable(false);
            ratinviewFertilizer.setClickable(false);
        }

        // 水分
        ratinviewMoisture.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                String moisture = RoundedUtils.setMoisture(RatingScore);
                params.put("moisture", moisture);
            }
        });

        // 光照
        ratinviewLight.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                String light = RoundedUtils.setLight(RatingScore);
                params.put("illumination", light);
            }
        });

        //化肥
        ratinviewFertilizer.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                String fertilizer = RoundedUtils.setFertilizer(RatingScore);
                params.put("fertility", fertilizer);
            }
        });

        checkboxYi.setOnCheckedChangeListener(cb);
        checkboxEr.setOnCheckedChangeListener(cb);
        checkboxSan.setOnCheckedChangeListener(cb);
        checkboxSi.setOnCheckedChangeListener(cb);
        checkboxWu.setOnCheckedChangeListener(cb);
        checkboxLiu.setOnCheckedChangeListener(cb);
        checkboxQi.setOnCheckedChangeListener(cb);
        checkboxBa.setOnCheckedChangeListener(cb);
        checkboxJiu.setOnCheckedChangeListener(cb);
        checkboxShi.setOnCheckedChangeListener(cb);
        checkboxShiyi.setOnCheckedChangeListener(cb);
        checkboxShier.setOnCheckedChangeListener(cb);


        checkBoxList = new ArrayList<>();
        checkBoxList.add(checkboxYi);
        checkBoxList.add(checkboxEr);
        checkBoxList.add(checkboxSan);
        checkBoxList.add(checkboxSi);
        checkBoxList.add(checkboxWu);
        checkBoxList.add(checkboxLiu);
        checkBoxList.add(checkboxQi);
        checkBoxList.add(checkboxBa);
        checkBoxList.add(checkboxJiu);
        checkBoxList.add(checkboxShi);
        checkBoxList.add(checkboxShiyi);
        checkBoxList.add(checkboxShier);
    }

    // 初始化数据
    private void loDataInfo() {

        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(PlantDataAvtivity.this, "加载中...");
        params.put("id", ID);
        NetworkReuset.getInstance().PostReuset(RequestUrls.PLANDATADATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                processData(s);
                WeiboDialogUtils.closeDialog(loadingDialog);
            }
        });
    }

    // 加载植物描述
    private void loaDescription() {
        NetworkReuset.getInstance().PostReuset(RequestUrls.PLANTDESCRIPTION, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                description(s);
            }
        });
    }


    // 设置植物描述
    private void description(String s) {
        LogUtils.d(s);
        Gson gson = new Gson();
        PlanDataDescribeBean planDataDescribeBean = gson.fromJson(s, PlanDataDescribeBean.class);
        PlanDataDescribeBean.CBean c = planDataDescribeBean.getC();
        if (c.getImgurl() != "") {
            Picasso.with(this).load(c.getImgurl()).into(plandataIvHead);
        }
        plandataTvName.setText(c.getTypename());
        plandataEdScientific.setText(c.getTypename());
        plandataDescribeEdAlis.setText(c.getAlias());
        plandataEdGenaer.setText(c.getGenera());
        plandataEdPersonality.setText(c.getContent());
        plandataDescribeEdLatin.setText(c.getScientificname());

    }

    // 拿到数据 给控件设置数据
    private void processData(String json) {
        Gson gson = new Gson();
        PlantDataBean dataBean = gson.fromJson(json, PlantDataBean.class);
        PlantDataBean.CBean c = dataBean.getC();
        if (c.getFertility() == null) {
            return;
        }
        ratinviewFertilizer.setStar(RoundedUtils.getFertilizer(c.getFertility()), true);
        ratinviewLight.setStar(RoundedUtils.getLight(c.getIllumination()), true);
        ratinviewMoisture.setStar(RoundedUtils.getMoisture(c.getMoisture()), true);
        plandataEdTemperature.setText(c.getHumidity().substring(0, c.getHumidity().indexOf("*")));
        plandataEdTemperatureFinal.setText(c.getHumidity().substring(c.getHumidity().indexOf("*") + 1));
        plandataEdFertilizer.setText(c.getManure());
        plandataEdPests.setText(c.getInsectcontent());

        if (!c.getMonths().equals("")){
            int[] num = GetNumUtlis.getNum(c.getMonths());
            for (int i = 0; i < num.length; i++) {
                checkBoxList.get(num[i]==0?num[i]:num[i]-1).setChecked(true);

            }
        }

    }


    @OnClick({R.id.base_acticity_toolbar_iv_left, R.id.base_acticity_toolbar_tv_fight, R.id.plandata_guide, R.id.plandata_describe, R.id.plandata_btn_develop, R.id.button, R.id.plandata_describe_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_acticity_toolbar_iv_left:
                finish();
                break;
            case R.id.base_acticity_toolbar_tv_fight:
                break;
            //
            case R.id.plandata_guide: // 点击养护指南
                guide();
                break;
            case R.id.plandata_describe:
                descride();
                break;
            case R.id.plandata_btn_develop:
                startActivity(new Intent(PlantDataAvtivity.this, PlantDataGuideActivity.class).putExtra(PLANDID, ID));
                break;
            case R.id.button:
                postPlanData();
                postPlanDesribe();
                break;
            case R.id.plandata_describe_btn:
                startActivity(new Intent(PlantDataAvtivity.this, TagActivity.class)
                        .putExtra(ADDTAG, "1").putExtra(PLANDID, ID));
                break;

        }
    }

    // 提交植物描述
    private void postPlanDesribe() {
        postParams.clear();
        String latin = plandataDescribeEdLatin.getText().toString().trim();
        String alis = plandataDescribeEdAlis.getText().toString().trim();
        String genaer = plandataEdGenaer.getText().toString().trim();
        String sonlity = plandataEdPersonality.getText().toString().trim();
        String sicentific = plandataEdScientific.getText().toString().trim();
        postParams.put("id", ID);
        postParams.put("scientificname", latin);
        postParams.put("typename", sicentific);
        // TODO: 提交个性亮点
        postParams.put("content", sonlity);
        postParams.put("genera", genaer);
        postParams.put("alias", alis);
        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(PlantDataAvtivity.this, "加载中...");
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTTYPE, postParams, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                WeiboDialogUtils.closeDialog(loadingDialog);
            }
        });
    }


    // 提交养护修改信息
    private void postPlanData() {
        params.clear();
        String humidity = plandataEdTemperature.getText().toString().trim();
        String endhumidity = plandataEdTemperatureFinal.getText().toString().trim();
        String fertilizer = plandataEdFertilizer.getText().toString().trim();

        String pests = plandataEdPests.getText().toString().trim();
        String breed = plandataBreed.getText().toString().trim();
        params.put("typeNo", ID);
        params.put("humidity", humidity + "*" + endhumidity);
        params.put("manure", fertilizer);
        params.put("insectcontent", pests);
        params.put("months", getMonths());
        params.put("breed", breed);
        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(PlantDataAvtivity.this, "加载中...");
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTPLANDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                WeiboDialogUtils.closeDialog(loadingDialog);
            }
        });
    }


    // 获取选中的月份
    private String getMonths() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < months.size(); i++) {
            sb.append(months.get(i));
            sb.append(",");
        }
        String substring = sb.toString().substring(0, sb.toString().length()==0?sb.toString().length()-0:sb.toString().length()-1);
        LogUtils.d(substring);
        return substring;
    }

    private void descride() {
        plandataGuide.setTextColor(Color.GRAY);
        plandataDescribe.setTextColor(Color.BLACK);
        plandataGudeLin.setBackgroundColor(Color.GRAY);
        plandataDescribeLin.setBackgroundColor(Color.BLACK);

        layoutPlantdataGuide.setVisibility(View.GONE);
        layoutPlantdataDescribe.setVisibility(View.VISIBLE);
    }

    private void guide() {
        plandataGuide.setTextColor(Color.BLACK);
        plandataDescribe.setTextColor(Color.GRAY);
        plandataGudeLin.setBackgroundColor(Color.BLACK);
        plandataDescribeLin.setBackgroundColor(Color.GRAY);
        layoutPlantdataGuide.setVisibility(View.VISIBLE);
        layoutPlantdataDescribe.setVisibility(View.GONE);
    }

    private void administrator(boolean is) {
        if (!is) {
            button.setVisibility(View.GONE);
            plandataEdTemperature.setEnabled(false);
            plandataEdTemperatureFinal.setEnabled(false);
            plandataEdFertilizer.setEnabled(false);
            plandataEdPests.setEnabled(false);
            plandataDescribeEdLatin.setEnabled(false);
            plandataDescribeEdAlis.setEnabled(false);
            plandataEdGenaer.setEnabled(false);
            plandataEdPersonality.setEnabled(false);
            plandataEdScientific.setEnabled(false);
            plandataDescribeBtn.setVisibility(View.GONE);
            ratinviewMoisture.setClickable(false);
            ratinviewLight.setClickable(false);
            ratinviewFertilizer.setClickable(false);

        }
    }

    private CompoundButton.OnCheckedChangeListener cb = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                String string = buttonView.getText().toString();;
                months.add(string);
            } else {
                String string = buttonView.getText().toString();
                removeData(string);
                LogUtils.e("删除" + string);
            }
        }

    };


    private void removeData(String week) {
        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).equals(week)) {
                months.remove(i);
            }
        }
    }
}
