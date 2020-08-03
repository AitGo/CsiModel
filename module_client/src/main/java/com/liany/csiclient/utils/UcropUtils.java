package com.liany.csiclient.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.liany.csiclient.R;
import com.liany.csiclient.widget.image.MyUcropActivity;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

import androidx.core.app.ActivityCompat;

/**
 * @创建者 ly
 * @创建时间 2019/11/8
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UcropUtils {

    /**
     * 启动裁剪
     * @param activity 上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode 比如：UCrop.REQUEST_CROP
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                             int requestCode) {
        File file = new File(sourceFilePath);
        Uri sourceUri = Uri.fromFile(file);
        File outDir = file.getParentFile();
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, file.getName());
        //裁剪后图片的绝对路径
        String cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.ALL, UCropActivity.ROTATE, UCropActivity.SCALE);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        options.setToolbarWidgetColor(ActivityCompat.getColor(activity, R.color.white));
        options.setToolbarTitle("图像处理");
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(false);
        options.setCompressionQuality(100);
        //是否显示裁剪框网格
        options.setShowCropGrid(false);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比
//        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
//        uCrop.start(activity, requestCode);
        //跳转到我们的activity，而不是用uCrop自带的跳转到UCropActivity
        Intent uCropIntent = uCrop.getIntent(activity);
        uCropIntent.setClass(activity, MyUcropActivity.class);
        activity.startActivityForResult(uCropIntent,requestCode);
        return cameraScalePath;
    }
}
