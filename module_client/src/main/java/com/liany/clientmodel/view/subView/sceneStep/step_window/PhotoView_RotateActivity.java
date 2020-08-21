package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.utils.BitmapUtils;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2019/10/31
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class PhotoView_RotateActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivImg;
    ImageButton btnRotate;
    ImageView ivTitleConfirm;

    private byte[] imageBytes;
    private Bitmap bitmap;
    private int imageSize = 640;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_photo_rotate;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivImg = findViewById(R.id.iv_img);
        btnRotate = findViewById(R.id.btn_rotate);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        btnRotate.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText("调整指纹图片");
        ivImg.setImageBitmap(bitmap);
    }

    @Override
    protected void initData() {
        imageBytes = getIntent().getByteArrayExtra("image_bytes");
        boolean type = getIntent().getBooleanExtra("type", Constants.TYPE_YHCAMERA_PEOPLE);
        imageSize = type ? 512 : 640;
        // convert to bitmap
        Mat mat = new Mat(imageSize, imageSize, CvType.CV_8UC1);
        mat.put(0, 0, imageBytes);
        bitmap = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, bitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_confirm) {
            String fileName = "Finger_" + StringUtils.long2FileName(new Date());
            //保存png
//                BitmapUtils.saveBitmapAsPng(bitmap,new File(Constants.path_photoDir + File.separator + fileName + ".png"));
            //保存bmp
            BitmapUtils.save8BitBmp(bitmap, Constants.path_photoDir + File.separator + fileName + ".bmp");
            //返回图片路径
            Intent intent = getIntent();
            intent.putExtra("filePath", fileName);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else if (id == R.id.btn_rotate) {//旋转图片
            bitmap = BitmapUtils.rotateBitmap(bitmap, 90);
            ivImg.setImageBitmap(bitmap);
        }
    }

}
