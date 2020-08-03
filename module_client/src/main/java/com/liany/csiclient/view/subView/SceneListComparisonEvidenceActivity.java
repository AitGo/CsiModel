package com.liany.csiclient.view.subView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.utils.ClickUtils;

/**
 * @创建者 ly
 * @创建时间 2020/4/28
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListComparisonEvidenceActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    Button btnFinger;
    Button btnFoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_comparison;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        btnFinger = findViewById(R.id.btn_finger);
        btnFoot = findViewById(R.id.btn_foot);
        ivTitleBack.setOnClickListener(this);
        btnFinger.setOnClickListener(this);
        btnFoot.setOnClickListener(this);
        tvTitle.setText("现场比对");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.btn_finger) {
            Intent finger = new Intent(this, SceneListComparisonEvidenceFingerActivity.class);
            startActivity(finger);
        } else if (id == R.id.btn_foot) {
            Intent foot = new Intent(this, SceneListComparisonEvidenceFootActivity.class);
            startActivity(foot);
        }
    }
}
