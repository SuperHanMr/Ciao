package com.example.hanyonghui.ciao.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.ImageBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.PostDatailsModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MainConstant;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.PictureSelectorConfig;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.GridViewAdapter;
import com.example.hanyonghui.ciao.view.views.GridViewInScroll;
import com.example.hanyonghui.ciao.view.views.ImageEditing;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 程序员：韩绍辉
 * 创建日期：on 2017/9/22.
 * Holle Android
 */

public class PostingActivity extends AutoLayoutActivity {


    @BindView(R.id.posting_ed_content)
    EditText postingEdContent;
    @BindView(R.id.posting_tv)
    TextView postingTv;
    @BindView(R.id.posting_gridview)
    GridViewInScroll postingGridview;



    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private ArrayList<String> mPicList = new ArrayList<>();

    private HttpParams params = new HttpParams();
    private StringBuilder sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        postingEdContent.addTextChangedListener(new EditChangedListener());
        mGridViewAddImgAdapter = new GridViewAdapter(this, mPicList);
        postingGridview.setAdapter(mGridViewAddImgAdapter);
        postingGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加3张图片
                        viewPluImg(position);
                    } else {
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(this, PlusImageActivity.class);
        intent.putStringArrayListExtra(MainConstant.IMG_LIST, mPicList);
        intent.putExtra(MainConstant.POSITION, position);
        startActivityForResult(intent, MainConstant.REQUEST_CODE_MAIN);
    }

    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    // 处理选择的照片的地址 拿到图片地址就可以转成Bitmap 创建一个Bitmap集合 上传服务器


    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                LogUtils.e(compressPath);
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }



        //遍历mPicList 获取图片的URL地址 上传 服务器
        final List<File> files = new ArrayList<>();
        sb = new StringBuilder();
        for (int i = 0; i < mPicList.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPicList.get(i));
            File file = ImageEditing.saveBitmapFile(bitmap);
            files.add(file);
        }



        // 创建一个子线程 循环上传图片 因为主线程不能做耗时操作
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < files.size(); i++) {
                    pictureUpload(files.get(i));
                }
            }
        }.start();

    }


   private void pictureUpload(File file){
       params.put("uid", SPUtils.getString(KeyUtils.USERID));
       params.put("fstream",file);

     NetworkReuset.getInstance().PostReuset(RequestUrls.UPLOADIMAGE, params, new StringCallback() {
        @Override
        public void onSuccess(String s, Call call, Response response) {
            Gson gson = new Gson();
            ImageBean imageBean = gson.fromJson(s, ImageBean.class);
            String iamgeUrl = RequestUrls.HTTP+imageBean.getC(); // 图片地址
            LogUtils.e(iamgeUrl);
            sb.append(iamgeUrl);
            sb.append(",");
        }
    });
   }


    @OnClick({R.id.posting_back, R.id.posting_tv_send, R.id.pictures_seleter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.posting_back:
                finish();
                break;
            case R.id.posting_tv_send:
                // 把发帖内容上传服务器
                postingUpload();
                break;
            case R.id.pictures_seleter:
                // 点击选择照片 获取File文件 上传服务器 得到URL
                if (mPicList.size()<3){
                    selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                }else {
                    MyToast.show(this,"最多三张照片");
                }

                break;
        }
    }



    private void postingUpload() {
        params.clear();
        if (sb!=null){
            String substring = sb.toString().substring(0, sb.toString().length() - 1);
            params.put("imgs",substring);
        }

        String mContent = postingEdContent.getText().toString().trim();
        params.put("uid",SPUtils.getString(KeyUtils.USERID));
        params.put("body",mContent);

        NetworkReuset.getInstance().PostReuset(RequestUrls.ADDPOSTING, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                EventBus.getDefault().post(new PostDatailsModel(1));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == MainConstant.REQUEST_CODE_MAIN && resultCode == MainConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(MainConstant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
    }

    class EditChangedListener implements TextWatcher{

        private final int charMaxNum = 100;
        private CharSequence temp; // 监听当前的文字


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 在这里做监听
            postingTv.setText("还可以输入"+(charMaxNum-s.length())+"字");

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length()>charMaxNum){
                MyToast.show(getApplicationContext(),"已经超出100字");
                postingEdContent.setFocusable(false);
            }
        }
    }

}
