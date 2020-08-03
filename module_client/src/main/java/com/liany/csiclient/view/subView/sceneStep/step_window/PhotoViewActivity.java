package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.widget.ClearableEditText;

import uk.co.senab.photoview.PhotoView;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class PhotoViewActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    PhotoView ivPic;
    ImageView ivBack;
    ClearableEditText etPhotoDes;

    private String des;
    private String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_photo_view;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivPic = findViewById(R.id.iv_pic);
        ivBack = findViewById(R.id.iv_back);
        etPhotoDes = findViewById(R.id.et_photo_des);

        ivTitleBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        etPhotoDes.setText(des);
        if(StringUtils.checkString(path)) {
            Glide.with(this).load(path)
                    .dontAnimate()
                    .into(ivPic);
        }

        tvTitle.setText("图片");
    }

    @Override
    protected void initData() {
        path = getIntent().getStringExtra("filePath");
        des = getIntent().getStringExtra("des");
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_back) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

}
