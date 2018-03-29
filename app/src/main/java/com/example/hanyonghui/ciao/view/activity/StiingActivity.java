package com.example.hanyonghui.ciao.view.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.ImageBean;
import com.example.hanyonghui.ciao.bean.bean.UserBean;
import com.example.hanyonghui.ciao.bean.eventbusmodel.UserModel;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.ImageCompress;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;

import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.views.ImageEditdingView;
import com.example.hanyonghui.ciao.view.views.ImageEditing;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hanyonghui on 2017/8/23.
 */

public class StiingActivity extends AutoLayoutActivity {

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    @BindView(R.id.me_head)
    CircleImageView meHead;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_phone)
    EditText userPhone;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE = 3;
    @BindView(R.id.stting_code_rl)
    RelativeLayout sttingCodeRl;
    private HttpParams params = new HttpParams();
    private String url;
    private String imagUrl;
    private Uri uriImageData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stiing_activity);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        APP.getApp().addActivity(this);

    }


    @OnClick({R.id.stting_back, R.id.stting_commtion, R.id.me_head,R.id.tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stting_back:
                finish();
                break;
            case R.id.stting_commtion:
                // 上传信息
                upLoadUserData();
                break;
            case R.id.me_head:
                showChoosePicDialog();
                break;
            case R.id.tuichu:
                SPUtils.putString(KeyUtils.USERID,null);
                startActivity(new Intent(StiingActivity.this,LoginActivity.class));
                SPUtils.clear();
                APP.getApp().exit();
                break;

        }
    }


    private void upLoadUserData() {
        params.clear();
        String name = userName.getText().toString().trim();
        String phone = userPhone.getText().toString().trim();
        params.put("uid",SPUtils.getString(KeyUtils.USERID));
        params.put("pn",phone);
        params.put("purl",imagUrl);
        LogUtils.d(imagUrl);
        params.put("nn",name);
        NetworkReuset.getInstance().PostReuset(RequestUrls.SETUSERDATA, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                finish();
            }
        });
    }

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
                        if (data.getData()!=null){
                            uriImageData= data.getData();
                        }else {
                           uriImageData  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
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
        if (bitmap != null) {
            final Bitmap bit = ImageEditing.toRoundBitmap(bitmap);
            File file = ImageEditing.saveBitmapFile(bit);
            meHead.setImageBitmap(bit);
            params.put("fstream", file);
            params.put("uid", SPUtils.getString(KeyUtils.USERID));
            NetworkReuset.getInstance().PostReuset(RequestUrls.UPLOADIMAGE, params, new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    Gson gson = new Gson();
                    ImageBean imageBean = gson.fromJson(s, ImageBean.class);
                    if (!imageBean.getC().equals("")){
                        imagUrl = RequestUrls.HTTP+imageBean.getC();
                        LogUtils.d(imagUrl);
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



    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBusBean(UserModel userBean) {
        LogUtils.d(".......");
        UserBean bean = userBean.getUserBean();
        //设置头像
        if (!bean.getC().getPurl().equals("")) {
            Picasso.with(this).load(bean.getC().getPurl()).into(meHead);
        }

        // 设置名字
        userName.setText(bean.getC().getNn());

        // 检测是否绑定手机号 没有的话 让用户绑定
        if (bean.getC().getPn() != null) {
            userPhone.setText(bean.getC().getPn());
            userPhone.setEnabled(false);
        } else {
            sttingCodeRl.setVisibility(View.VISIBLE);
        }

    }


    private Bitmap getconpres(Uri uri){
        ImageCompress compress = new ImageCompress();
        ImageCompress.CompressOptions options = new ImageCompress.CompressOptions();
        options.uri = uri;
        Bitmap bitmap = compress.compressFromUri(StiingActivity.this, options);
        return bitmap;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }




}
