package com.example.hanyonghui.ciao.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.hanyonghui.ciao.bean.bean.AddplantBean;
import com.example.hanyonghui.ciao.bean.bean.ImageBean;
import com.example.hanyonghui.ciao.bean.bean.PlantsIdBean;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.ImageCompress;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.RoundedUtils;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.ImageEditing;
import com.example.hanyonghui.ciao.view.views.RatingBarView;
import com.example.hanyonghui.ciao.view.views.WeiboDialogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;
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

public class AddPlantsDatavtivity extends AutoLayoutActivity {
    @BindView(R.id.base_acticity_toolbar_tv)
    TextView baseActicityToolbarTv;
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
    @BindView(R.id.base_acticity_toolbar_tv_fight)
    TextView baseActicityToolbarTvFight;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.plandata_btn_develop)
    Button plandataBtnDevelop;
    @BindView(R.id.plandata_breed)
    EditText plandataBreed;

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

    private HttpParams params = new HttpParams();
    private HttpParams postParams = new HttpParams();

    private String ID; // 植物ID
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 3;
    private String imagUrl;
    private Dialog loadingDialog;
    private Uri uriImageData;
    private String id;

    private List<String> months = new ArrayList<>();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plandata_activity);
        ButterKnife.bind(this);
        initView();
        loData();
        APP.getApp().addActivity(this);
    }

    private void loData() {
        NetworkReuset.getInstance().GetReuset(RequestUrls.ADDPLANTSID, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                PlantsIdBean plantsIdBean = gson.fromJson(s, PlantsIdBean.class);
                ID = plantsIdBean.getC().getTypeNo();
                postParams.put("id", ID);
            }
        });
    }


    // 初始化View
    private void initView() {
        baseActicityToolbarTv.setText("植物资库");
        baseActicityToolbarTvFight.setText("确认添加");
        button.setVisibility(View.GONE);
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

        // 给CheckBox 设置监听
        checkboxYi.setOnCheckedChangeListener(cb);
        checkboxEr.setOnCheckedChangeListener(cb);
        checkboxSan.setOnCheckedChangeListener(cb);
        checkboxSi.setOnCheckedChangeListener(cb);
        checkboxWu.setOnCheckedChangeListener(cb);
        checkboxLiu.setOnCheckedChangeListener(cb);
        checkboxQi.setOnCheckedChangeListener(cb);
        checkboxBa.setOnCheckedChangeListener(cb);
        checkboxShi.setOnCheckedChangeListener(cb);
        checkboxShiyi.setOnCheckedChangeListener(cb);
        checkboxShier.setOnCheckedChangeListener(cb);

    }

    @OnClick({R.id.base_acticity_toolbar_iv_left, R.id.base_acticity_toolbar_tv_fight, R.id.plandata_guide, R.id.plandata_describe, R.id.button, R.id.plandata_describe_btn, R.id.plandata_iv_head, R.id.plandata_btn_develop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_acticity_toolbar_iv_left:
                finish();
                break;
            case R.id.base_acticity_toolbar_tv_fight:
                // 确认添加植物
                loadingDialog = WeiboDialogUtils.createLoadingDialog(AddPlantsDatavtivity.this, "加载中...");
                postPlanDesribe();
                break;
            case R.id.plandata_guide:
                guide();
                break;
            case R.id.plandata_describe:
                descride();
                break;
            case R.id.plandata_describe_btn: // 添加标签
                startActivity(new Intent(AddPlantsDatavtivity.this, TagActivity.class).putExtra("id", id));
                break;
            case R.id.plandata_iv_head:
                showChoosePicDialog();
                break;
            case R.id.plandata_btn_develop:
                startActivity(new Intent(AddPlantsDatavtivity.this, GardenerCultivateActivity.class).putExtra(KeyUtils.PLANDATAID, id));
                break;
        }
    }

    // 提交植物描述
    private void postPlanDesribe() {
        String latin = plandataDescribeEdLatin.getText().toString().trim();
        String alis = plandataDescribeEdAlis.getText().toString().trim();
        String genaer = plandataEdGenaer.getText().toString().trim();
        String sonlity = plandataEdPersonality.getText().toString().trim();
        String sicentific = plandataEdScientific.getText().toString().trim();
        postParams.put("imgUrl", imagUrl);
        postParams.put("scientificname", latin);
        postParams.put("typename", sicentific);
        postParams.put("content", sonlity);
        postParams.put("genera", genaer);
        postParams.put("alias", alis);

        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTTYPE, postParams, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                AddplantBean addplantBean = gson.fromJson(s, AddplantBean.class);
                id = addplantBean.getC().getId();
                postPlanData(id);
            }
        });
    }

    // 提交养植信息
    private void postPlanData(final String id) {
        String humidity = plandataEdTemperature.getText().toString().trim();
        String endhumidity = plandataEdTemperatureFinal.getText().toString().trim();
        String fertilizer = plandataEdFertilizer.getText().toString().trim();
        String pests = plandataEdPests.getText().toString().trim();
        String breed = plandataBreed.getText().toString().trim();
        params.put("typeNo", id);
        params.put("humidity", humidity + "*" + endhumidity);
        params.put("manure", fertilizer);
        params.put("insectcontent", pests);
        params.put("breed", breed); // 繁殖方式
        params.put("months",getMonths());
        NetworkReuset.getInstance().PostReuset(RequestUrls.POSTPLANDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.d(s);
                WeiboDialogUtils.closeDialog(loadingDialog);
                MyToast.show(AddPlantsDatavtivity.this, "添加成功");
            }
        });
    }

    // 获取选中的月份
    private String getMonths(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < months.size(); i++) {
            sb.append(months.get(i));
            sb.append(",");
        }
        String substring = sb.toString().substring(0, sb.toString().length() - 1);
        LogUtils.d(substring);
        return substring;
    }

    private CompoundButton.OnCheckedChangeListener cb = new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                String string = buttonView.getText().toString();
                LogUtils.e("选中"+string);
                months.add(string);
            }else {
                String string = buttonView.getText().toString();
                removeData(string);
                LogUtils.e("删除"+string);
            }
        }

    };

    private void removeData(String week) {
        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).equals(week)){
                months.remove(i);
            }
        }
    }

    private void descride() {
        plandataGudeLin.setBackgroundColor(Color.GRAY);
        plandataDescribeLin.setBackgroundColor(Color.BLACK);
        layoutPlantdataGuide.setVisibility(View.GONE);
        layoutPlantdataDescribe.setVisibility(View.VISIBLE);
    }


    private void guide() {
        plandataGudeLin.setBackgroundColor(Color.BLACK);
        plandataDescribeLin.setBackgroundColor(Color.GRAY);
        layoutPlantdataGuide.setVisibility(View.VISIBLE);
        layoutPlantdataDescribe.setVisibility(View.GONE);
    }

    /* ==================== 选择照片 ================== */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加图片");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        choosePhoto();
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePhoto();
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            /**
             * 拍照的请求标志
             */
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        /**
                         * 该uri就是照片文件夹对应的uri
                         */
                        Bundle bundle = data.getExtras();
                        // 获取相机返回的数据，并转换为Bitmap图片格式，这是缩略图
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        if (data.getData() != null) {
                            uriImageData = data.getData();
                        } else {
                            uriImageData = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                        }
                        headUpload(getconpres(uriImageData));
                    } catch (Exception e) {
                    }
                }
                break;
            /**
             * 从相册中选取图片的请求标志
             */
            case REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        Bitmap bitmap = getconpres(uri);
                        headUpload(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    private void headUpload(Bitmap bitmap) {
        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(AddPlantsDatavtivity.this, "加载中...");
        if (bitmap != null) {
            final Bitmap bit = ImageEditing.toRoundBitmap(bitmap);
            File file = ImageEditing.saveBitmapFile(bit);
            plandataIvHead.setImageBitmap(bit);
            params.put("fstream", file);
            params.put("uid", SPUtils.getString(KeyUtils.USERID));
            NetworkReuset.getInstance().PostReuset(RequestUrls.UPLOADIMAGE, params, new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    Gson gson = new Gson();
                    ImageBean imageBean = gson.fromJson(s, ImageBean.class);
                    if (!imageBean.getC().equals("")) {
                        imagUrl = RequestUrls.HTTP + imageBean.getC();
                        LogUtils.d(imagUrl);
                        WeiboDialogUtils.closeDialog(loadingDialog);
                    }
                    // 拿到iamgUrl地址上传到服务器
                }
            });
        }
    }

    private void takePhoto() {
        /**
         * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
         */
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, CROP_PHOTO);
    }


    private void choosePhoto() {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    private Bitmap getconpres(Uri uri) {
        ImageCompress compress = new ImageCompress();
        ImageCompress.CompressOptions options = new ImageCompress.CompressOptions();
        options.uri = uri;
        Bitmap bitmap = compress.compressFromUri(AddPlantsDatavtivity.this, options);
        return bitmap;
    }


}
