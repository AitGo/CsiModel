package com.liany.clientmodel.widget.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.liany.clientmodel.R;
import com.liany.clientmodel.utils.BitmapUtils;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.GestureCropImageView;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import androidx.appcompat.widget.Toolbar;

/**
 * @创建者 ly
 * @创建时间 2020/7/20
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class MyUcropActivity extends UCropActivity implements View.OnClickListener {

    private Uri inputUri,outputUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputUri = getIntent().getParcelableExtra(UCrop.EXTRA_INPUT_URI);
        outputUri = getIntent().getParcelableExtra(UCrop.EXTRA_OUTPUT_URI);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_myucrop,null,false);
        ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);  //item的宽高
        mp.setMargins((int) (100 * Resources.getSystem().getDisplayMetrics().density), 0, 0, 0);//分别是margin_top那四个属性
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mp);
        lp.addRule(RelativeLayout.LEFT_OF, R.id.toolbar_title);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        Toolbar viewGroup = findViewById(R.id.toolbar);
        viewGroup.addView(view,lp);
        view.findViewById(R.id.ll_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_image) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(inputUri));
                bitmap = BitmapUtils.convertBitmap(bitmap);
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                setScale(uri, outputUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反射设置uri
     */
    public void setScale(Uri uri,Uri outUri) {
        try {
            Field field = this.getClass().getSuperclass().getDeclaredField("mGestureCropImageView");
            field.setAccessible(true);
            GestureCropImageView gestureCropImageView = (GestureCropImageView) field.get(this);
            gestureCropImageView.setImageUri(uri,outUri);
            gestureCropImageView.setImageToWrapCropBounds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
