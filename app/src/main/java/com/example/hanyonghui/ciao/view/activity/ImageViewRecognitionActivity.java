package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.Recognition;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.PictureSelectorConfig;
import com.example.hanyonghui.ciao.view.adapter.ImageViewRecognitionAdapter;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/30.
 * Holle Android
 */

public class ImageViewRecognitionActivity extends AutoLayoutActivity {

    //设置APPID/AK/SK
    public static final String APP_ID = "10177683";
    public static final String API_KEY = "p01ejEnXQPphatqihPk0jU87";
    public static final String SECRET_KEY = "tR30Km4iwKsliRGt4CKnvlKFLCBWtB04";
    @BindView(R.id.image_recognition)
    CircleImageView imageRecognition;
    @BindView(R.id.map_recyclerview)
    RecyclerView mapRecyclerview;

    private AipImageClassify classify;
    private String compressPath;

    private List<Recognition.ResultBean> mlist;

    private ImageViewRecognitionAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mlist = (List<Recognition.ResultBean>) msg.obj;
                    adapter.setData(mlist);
                    break;
            }

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview_recognition);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        adapter = new ImageViewRecognitionAdapter(this);
        LinearLayoutManager mMabager = new LinearLayoutManager(this);
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        mapRecyclerview.setLayoutManager(mMabager);
        mapRecyclerview.setAdapter(adapter);
    }

    private void initData() {
        // 初始化一个AipImageClassifyClient
        classify = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        classify.setConnectionTimeoutInMillis(2000);
        classify.setSocketTimeoutInMillis(60000);
    }

    @OnClick(R.id.recognition_btn)
    public void onViewClicked() {
        selectPic(1);
    }

    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    break;
            }
        }
    }

    private void refreshAdapter(List<LocalMedia> picList) {

        for (int i = 0; i < picList.size(); i++) {
            compressPath = picList.get(0).getCompressPath();
            LogUtils.e(compressPath);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(compressPath);
        imageRecognition.setImageBitmap(bitmap);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 传入可选参数调用接口
                HashMap<String, String> options = new HashMap<String, String>();
                // 参数为本地图片路径
                JSONObject res = classify.plantDetect(compressPath, options);
                LogUtils.e(res.toString());

                Gson gson = new Gson();
                Recognition recognition = gson.fromJson(res.toString(), Recognition.class);
                List<Recognition.ResultBean> result = recognition.getResult();
                Message age = new Message();
                age.obj = result;
                age.what = 0;
                handler.sendMessage(age);

            }
        }).start();

    }


}
