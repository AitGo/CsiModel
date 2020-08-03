package com.liany.csiclient.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.SPUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

/**
 * @创建者 ly
 * @创建时间 2020/4/9
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SettingActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    TextView tvSettingIsUseDevicePeople;
    RelativeLayout rlSettingIsUseDevicePeople;
    TextView tvSettingIsUseDevice;
    RelativeLayout rlSettingIsUseDevice;

    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        tvSettingIsUseDevicePeople = findViewById(R.id.tv_setting_isUseDevicePeople);
        rlSettingIsUseDevicePeople = findViewById(R.id.rl_setting_isUseDevicePeople);
        tvSettingIsUseDevice = findViewById(R.id.tv_setting_isUseDevice);
        rlSettingIsUseDevice = findViewById(R.id.rl_setting_isUseDevice);
        ivTitleBack.setOnClickListener(this);
        rlSettingIsUseDevicePeople.setOnClickListener(this);
        rlSettingIsUseDevice.setOnClickListener(this);

        tvTitle.setText("设置");
        int useDevicePosition = (int) SPUtils.getParam(mContext, Constants.sp_UseDevice, 0);
        tvSettingIsUseDevice.setText(Constants.devices[useDevicePosition]);
        int usePeopleDevicePosition = (int) SPUtils.getParam(mContext, Constants.sp_UseDevicePeople, 0);
        tvSettingIsUseDevicePeople.setText(Constants.devicesPeople[usePeopleDevicePosition]);
    }

    @Override
    protected void initData() {
        mContext = this;
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.rl_setting_isUseDevicePeople) {
            showChooseDevicePeople();
        } else if (id == R.id.rl_setting_isUseDevice) {
            showChooseDevice();
        }
    }

    public void showChooseDevicePeople() {
        new XPopup.Builder(this)
                .asBottomList("选择事主指纹采集设备", Constants.devicesPeople,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                tvSettingIsUseDevicePeople.setText(text);
                                SPUtils.setParam(mContext, Constants.sp_UseDevicePeople,position);
                            }
                        })
                .show();
    }

    public void showChooseDevice() {
        new XPopup.Builder(this)
                .asBottomList("选择现场指纹采集设备", Constants.devices,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                tvSettingIsUseDevice.setText(text);
                                SPUtils.setParam(mContext, Constants.sp_UseDevice,position);
                            }
                        })
                .show();
    }
}
