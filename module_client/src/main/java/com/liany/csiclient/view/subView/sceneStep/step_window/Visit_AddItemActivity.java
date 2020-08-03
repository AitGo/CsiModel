package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.contract.subView.sceneStep.step_window.Visit_AddItemContract;
import com.liany.csiclient.diagnose.ItemEntity;
import com.liany.csiclient.presenter.subView.sceneStep.step_window.Visit_AddItemPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.widget.ClearableEditText;
import com.liany.csiclient.widget.MyDialog;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Visit_AddItemActivity extends BaseAcitivity implements Visit_AddItemContract.View, View.OnClickListener {
    ClearableEditText etItemName;
    ClearableEditText etBrandModel;
    ClearableEditText etAmount;
    ClearableEditText etValue;
    ClearableEditText etFeatureDescription;
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    ImageView ivTitleSetting;

    private Visit_AddItemContract.Presenter addItemPresenter;
    private ItemEntity itemEntity;
    private int position = -1;
    private String crimeId;
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_view_visit_additem;
    }

    @Override
    protected void initView() {
        etItemName = findViewById(R.id.et_item_name);
        etBrandModel = findViewById(R.id.et_brand_model);
        etAmount = findViewById(R.id.et_amount);
        etValue = findViewById(R.id.et_value);
        etFeatureDescription = findViewById(R.id.et_feature_description);
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        ivTitleSetting = findViewById(R.id.iv_title_setting);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.add_item));
        //设置键盘输入为数字
        etAmount.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        etValue.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

        addItemPresenter.initViewDate(itemEntity);
    }

    @Override
    protected void initData() {
        addItemPresenter = new Visit_AddItemPresenter(this, this);
        itemEntity = (ItemEntity) getIntent().getSerializableExtra("itemEntity");
        position = getIntent().getIntExtra("position",-1);
        crimeId = getIntent().getStringExtra("crimeId");
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
            addItemPresenter.saveItem(itemEntity, crimeId, true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public String getItemName() {
        return etItemName.getText().toString() == null ? "" : etItemName.getText().toString();
    }

    @Override
    public String getBrandModel() {
        return etBrandModel.getText().toString() == null ? "" : etBrandModel.getText().toString();
    }

    @Override
    public String getItemAmount() {
        return etAmount.getText().toString() == null ? "" : etAmount.getText().toString();
    }

    @Override
    public String getItemValue() {
        return etValue.getText().toString() == null ? "" : etValue.getText().toString();
    }

    @Override
    public String getFeatureDescription() {
        return etFeatureDescription.getText().toString() == null ? "" : etFeatureDescription.getText().toString();
    }

    @Override
    public void setItemName(String itemName) {
        etItemName.setText(itemName);
    }

    @Override
    public void setBrandModel(String brandModel) {
        etBrandModel.setText(brandModel);
    }

    @Override
    public void setItemAmount(String itemAmount) {
        etAmount.setText(itemAmount);
    }

    @Override
    public void setItemValue(String itemValue) {
        etValue.setText(itemValue);
    }

    @Override
    public void setFeatureDescription(String description) {
        etFeatureDescription.setText(description);
    }

    @Override
    public void close(ItemEntity entity) {
        Intent intent = getIntent();
        intent.putExtra("itemEntity",entity);
        intent.putExtra("position",position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void showMsgDialog(String s) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        addItemPresenter.saveItem(itemEntity,crimeId,false);
                        myDialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("补全信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

}
