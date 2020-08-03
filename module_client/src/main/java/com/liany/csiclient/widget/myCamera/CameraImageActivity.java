package com.liany.csiclient.widget.myCamera;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.BaseEvent;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.BitmapUtils;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import uk.co.senab.photoview.PhotoView;

/**
 * @创建者 ly
 * @创建时间 2019/12/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CameraImageActivity extends BaseAcitivity implements View.OnClickListener {

    PhotoView ivPhoto;
    LinearLayout llRotate;
    LinearLayout llImage;
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;

    private String photoPath;
    private Bitmap newBitmap;
    private float rea_width;
    private float rea_height;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_google_image;
    }

    @Override
    protected void initView() {
        ivPhoto = findViewById(R.id.iv_photo);
        llRotate = findViewById(R.id.ll_rotate);
        llImage = findViewById(R.id.ll_image);
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        llImage.setOnClickListener(this);
        llRotate.setOnClickListener(this);
        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText("调整图片");

        if (StringUtils.checkString(photoPath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            if (bitmap != null) {
                newBitmap = BitmapUtils.get26Bitmap(bitmap, rea_width, rea_height);
                ivPhoto.setImageBitmap(newBitmap);
            }
        }
    }

    @Override
    protected void initData() {
        photoPath = getIntent().getStringExtra("path");
        //判断机型，获取真实拍摄的宽高数据
        rea_width = (float) SPUtils.getParam(this, Constants.sp_mobileType_Width,(float)7.5);
        rea_height = (float) SPUtils.getParam(this, Constants.sp_mobileType_Heigth,(float)10);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            //删除图片
            if(StringUtils.checkString(photoPath)) {
                FileUtils.deleteFile(photoPath);
            }
            finish();
        }
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_title_back) {//删除图片
            if (StringUtils.checkString(photoPath)) {
                FileUtils.deleteFile(photoPath);
            }
            finish();
        } else if (id == R.id.iv_title_confirm) {//保存处理过的截图图片，删除之前拍照的大图，处理为8bit灰度图
            Bitmap bitmap = BitmapUtils.imageScale(newBitmap, 512, 512);
//                bitmap = BitmapUtils.save640Bitmap(bitmap);
            bitmap = BitmapUtils.convertGray(bitmap);
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            BitmapUtils.save8BitBmp(bitmap, Constants.path_photoDir + File.separator + "Finger_" + timeStamp + ".bmp");
            BitmapUtils.saveBitmapAsPng(bitmap, new File(Constants.path_photoDir + File.separator + "Finger_" + timeStamp + ".jpg"));
            FileUtils.deleteFile(photoPath);
            BaseEvent.CommonEvent event = BaseEvent.CommonEvent.CAMERA_TAKEPHOTO_FINISH;
            event.setObject(Constants.path_photoDir + File.separator + "Finger_" + timeStamp + ".bmp");
            EventBus.getDefault().post(event);
            finish();
        } else if (id == R.id.ll_rotate) {
            if (newBitmap != null) {
                newBitmap = BitmapUtils.rotateBitmap(newBitmap, 90);
                ivPhoto.setImageBitmap(newBitmap);
            }
        } else if (id == R.id.ll_image) {
            if (newBitmap != null) {
                newBitmap = BitmapUtils.convertBitmap(newBitmap);
                ivPhoto.setImageBitmap(newBitmap);
            }
        }
    }
}
