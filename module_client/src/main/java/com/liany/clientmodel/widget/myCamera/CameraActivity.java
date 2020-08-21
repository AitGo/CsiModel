/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liany.clientmodel.widget.myCamera;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseEvent;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.widget.myCamera.manager.Controller;
import com.liany.clientmodel.widget.myCamera.module.CameraFragment;
import com.liany.clientmodel.widget.myCamera.module.SettingFragment;
import com.liany.clientmodel.widget.myCamera.utils.Permission;
import com.liany.clientmodel.widget.myCamera.utils.PermissionDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = Config.getTag(CameraActivity.class);
    private CameraFragment mCameraFragment;
    public static final String SETTING_ACTION = "com.smewise.camera2.setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setWindowFlag();
        setContentView(R.layout.activity_camera2);
    }

    private boolean isSettingShortcut() {
        return SETTING_ACTION.equals(getIntent().getAction());
    }

    private void setWindowFlag() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        window.setAttributes(params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Permission.checkPermission(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Permission.isPermissionGranted(this)) {
            initCameraFragment();
//            if (isSettingShortcut()) {
//                addSettingFragment();
//                getIntent().setAction(null);
//            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public Controller getController() {
        return mCameraFragment.getController();
    }

    private void initCameraFragment() {
        if (mCameraFragment == null) {
            mCameraFragment = new CameraFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.app_root, mCameraFragment);
            transaction.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Permission.REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    showPermissionDenyDialog();
                    return;
                }
            }
        }
    }

    private void showPermissionDenyDialog() {
        PermissionDialog dialog = new PermissionDialog();
        dialog.show(getFragmentManager(), "PermissionDeny");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void removeSettingFragment() {
        String tag = SettingFragment.class.getSimpleName();
        Fragment settingFragment = getFragmentManager().findFragmentByTag(tag);
        if (settingFragment == null) {
            return;
        }
        if (settingFragment.isAdded()) {
            getFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void addSettingFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.app_root, new SettingFragment(),
                SettingFragment.class.getSimpleName());
        transaction.addToBackStack(SettingFragment.class.getSimpleName());
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent.CommonEvent event) {
        if (event == BaseEvent.CommonEvent.CAMERA_TAKEPHOTO_FINISH) {
            Intent intent = getIntent();
            intent.putExtra(Constants.FingerPhoto, (String) event.getObject());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
