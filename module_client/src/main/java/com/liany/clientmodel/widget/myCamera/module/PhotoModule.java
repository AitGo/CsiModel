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

package com.liany.clientmodel.widget.myCamera.module;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liany.clientmodel.R;
import com.liany.clientmodel.widget.myCamera.Config;
import com.liany.clientmodel.widget.myCamera.callback.CameraUiEvent;
import com.liany.clientmodel.widget.myCamera.callback.MenuInfo;
import com.liany.clientmodel.widget.myCamera.callback.RequestCallback;
import com.liany.clientmodel.widget.myCamera.manager.CameraSession;
import com.liany.clientmodel.widget.myCamera.manager.CameraSettings;
import com.liany.clientmodel.widget.myCamera.manager.Controller;
import com.liany.clientmodel.widget.myCamera.manager.DeviceManager;
import com.liany.clientmodel.widget.myCamera.manager.FocusOverlayManager;
import com.liany.clientmodel.widget.myCamera.manager.Session;
import com.liany.clientmodel.widget.myCamera.manager.SingleDeviceManager;
import com.liany.clientmodel.widget.myCamera.ui.CameraBaseMenu;
import com.liany.clientmodel.widget.myCamera.ui.CameraMenu;
import com.liany.clientmodel.widget.myCamera.ui.PhotoUI;
import com.liany.clientmodel.widget.myCamera.utils.FileSaver;
import com.liany.clientmodel.widget.myCamera.utils.MediaFunc;

/**
 * @创建者 ly
 * @创建时间 2019/12/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class PhotoModule extends CameraModule implements FileSaver.FileListener,
        CameraBaseMenu.OnMenuClickListener {

    private SurfaceTexture mSurfaceTexture;
    private PhotoUI mUI;
    private CameraSession mSession;
    private SingleDeviceManager mDeviceMgr;
    private FocusOverlayManager mFocusManager;
    private CameraMenu mCameraMenu;

    private static final String TAG = Config.getTag(PhotoModule.class);

    @Override
    protected void init() {
        mUI = new PhotoUI(appContext, mainHandler, mCameraUiEvent);
        mUI.setCoverView(getCoverView());
        mDeviceMgr = new SingleDeviceManager(appContext, getExecutor(), mCameraEvent);
        mFocusManager = new FocusOverlayManager(getBaseUI().getFocusView(), mainHandler.getLooper());
        mFocusManager.setListener(mCameraUiEvent);
        mCameraMenu = new CameraMenu(appContext, R.xml.menu_preference, mMenuInfo);
        mCameraMenu.setOnMenuClickListener(this);
        mSession = new CameraSession(appContext, mainHandler, getSettings());
    }

    @Override
    public void start() {
        String cameraId = getSettings().getGlobalPref(
                CameraSettings.KEY_CAMERA_ID, mDeviceMgr.getCameraIdList()[0]);
        mDeviceMgr.setCameraId(cameraId);
        mDeviceMgr.openCamera(mainHandler);
        // when module changed , need update listener
        fileSaver.setFileListener(this);
        getBaseUI().setCameraUiEvent(mCameraUiEvent);
        getBaseUI().setMenuView(mCameraMenu.getView());
        addModuleView(mUI.getRootView());
        Log.d(TAG, "start module");
    }

    private DeviceManager.CameraEvent mCameraEvent = new DeviceManager.CameraEvent() {
        @Override
        public void onDeviceOpened(CameraDevice device) {
            super.onDeviceOpened(device);
            Log.d(TAG, "camera opened");
            mSession.applyRequest(Session.RQ_SET_DEVICE, device);
            enableState(Controller.CAMERA_STATE_OPENED);
            if (stateEnabled(Controller.CAMERA_STATE_UI_READY)) {
                mSession.applyRequest(Session.RQ_START_PREVIEW, mSurfaceTexture, mRequestCallback);
            }
        }

        @Override
        public void onDeviceClosed() {
            super.onDeviceClosed();
            disableState(Controller.CAMERA_STATE_OPENED);
            if (mUI != null) {
                mUI.resetFrameCount();
            }
            Log.d(TAG, "camera closed");
        }
    };

    private RequestCallback mRequestCallback = new RequestCallback() {
        @Override
        public void onDataBack(byte[] data, int width, int height) {
            super.onDataBack(data, width, height);
            saveFile(data, width, height, mDeviceMgr.getCameraId(),
                    CameraSettings.KEY_PICTURE_FORMAT, "CAMERA");
            mSession.applyRequest(Session.RQ_RESTART_PREVIEW);
        }

        @Override
        public void onViewChange(int width, int height) {
            super.onViewChange(width, height);
            getBaseUI().updateUiSize(width, height);
            mFocusManager.onPreviewChanged(width, height, mDeviceMgr.getCharacteristics());
        }

        @Override
        public void onAFStateChanged(int state) {
            super.onAFStateChanged(state);
            updateAFState(state, mFocusManager);
        }
    };

    @Override
    public void stop() {
        mCameraMenu.close();
        getBaseUI().setCameraUiEvent(null);
        getCoverView().showCover();
        getBaseUI().removeMenuView();
        mFocusManager.removeDelayMessage();
        mFocusManager.hideFocusUI();
        mSession.release();
        mDeviceMgr.releaseCamera();
        Log.d(TAG, "stop module");
    }

    private void takePicture() {
        mUI.setUIClickable(false);
        getBaseUI().setUIClickable(false);
        mSession.applyRequest(Session.RQ_TAKE_PICTURE, getToolKit().getOrientation());
    }

    /**
     * FileSaver.FileListener
     * @param uri image file uri
     * @param path image file path
     * @param thumbnail image thumbnail
     */
    @Override
    public void onFileSaved(Uri uri, String path, Bitmap thumbnail) {
        MediaFunc.setCurrentUri(uri);
        mUI.setUIClickable(true);
        getBaseUI().setUIClickable(true);
        getBaseUI().setThumbnail(thumbnail);
        Log.d(TAG, "uri:" + uri.toString());
        super.mTakenPhotoListener.taken(uri,path,thumbnail);
    }

    /**
     * callback for file save error
     * @param msg error msg
     */
    @Override
    public void onFileSaveError(String msg) {
        Toast.makeText(appContext,msg, Toast.LENGTH_LONG).show();
        mUI.setUIClickable(true);
        getBaseUI().setUIClickable(true);
    }

    private CameraUiEvent mCameraUiEvent = new CameraUiEvent() {

        @Override
        public void onPreviewUiReady(SurfaceTexture mainSurface, SurfaceTexture auxSurface) {
            Log.d(TAG, "onSurfaceTextureAvailable");
            mSurfaceTexture = mainSurface;
            enableState(Controller.CAMERA_STATE_UI_READY);
            if (stateEnabled(Controller.CAMERA_STATE_OPENED)) {
                mSession.applyRequest(Session.RQ_START_PREVIEW, mSurfaceTexture, mRequestCallback);
            }
        }

        @Override
        public void onPreviewUiDestroy() {
            disableState(Controller.CAMERA_STATE_UI_READY);
            Log.d(TAG, "onSurfaceTextureDestroyed");
        }

        @Override
        public void onTouchToFocus(float x, float y) {
            // close all menu when touch to focus
            mCameraMenu.close();
            mFocusManager.startFocus(x, y);
            MeteringRectangle focusRect = mFocusManager.getFocusArea(x, y, true);
            MeteringRectangle meterRect = mFocusManager.getFocusArea(x, y, false);
            mSession.applyRequest(Session.RQ_AF_AE_REGIONS, focusRect, meterRect);
        }

        @Override
        public void resetTouchToFocus() {
            if (stateEnabled(Controller.CAMERA_MODULE_RUNNING)) {
                mSession.applyRequest(Session.RQ_FOCUS_MODE,
                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            }
        }

        @Override
        public <T> void onSettingChange(CaptureRequest.Key<T> key, T value) {
            if (key == CaptureRequest.LENS_FOCUS_DISTANCE) {
                mSession.applyRequest(Session.RQ_FOCUS_DISTANCE, value);
            }
        }

        @Override
        public <T> void onAction(String type, T value) {
            // close all menu when ui click
            mCameraMenu.close();
            switch (type) {
                case CameraUiEvent.ACTION_CLICK:
                    handleClick((View) value);
                    break;
                case CameraUiEvent.ACTION_CHANGE_MODULE:
                    setNewModule((Integer) value);
                    break;
                case CameraUiEvent.ACTION_SWITCH_CAMERA:
                    break;
                case CameraUiEvent.ACTION_PREVIEW_READY:
                    getCoverView().hideCoverWithAnimation();
                    break;
                default:
                    break;
            }
        }
    };

    private MenuInfo mMenuInfo = new MenuInfo() {
        @Override
        public String[] getCameraIdList() {
            return mDeviceMgr.getCameraIdList();
        }

        @Override
        public String getCurrentCameraId() {
            return getSettings().getGlobalPref(CameraSettings.KEY_CAMERA_ID);
        }

        @Override
        public String getCurrentValue(String key) {
            return getSettings().getGlobalPref(key);
        }
    };

    private void handleClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_shutter) {
            takePicture();
        } else if (id == R.id.btn_setting) {
            showSetting();
        } else if (id == R.id.thumbnail) {
            MediaFunc.goToGallery(appContext);
        }
    }

    /**
     * CameraBaseMenu.OnMenuClickListener
     * @param key clicked menu key
     * @param value clicked menu value
     */
    @Override
    public void onMenuClick(String key, String value) {
        switch (key) {
            case CameraSettings.KEY_SWITCH_CAMERA:
                switchCamera();
                break;
            case CameraSettings.KEY_FLASH_MODE:
                getSettings().setPrefValueById(mDeviceMgr.getCameraId(), key, value);
                mSession.applyRequest(Session.RQ_FLASH_MODE, value);
                break;
            default:
                break;
        }
    }

    private void switchCamera() {
        int currentId = Integer.parseInt(mDeviceMgr.getCameraId());
        int cameraCount = mDeviceMgr.getCameraIdList().length;
        currentId++;
        if (cameraCount < 2) {
            // only one camera, just return
            return;
        } else if (currentId >= cameraCount) {
            currentId = 0;
        }
        String switchId = String.valueOf(currentId);
        mDeviceMgr.setCameraId(switchId);
        boolean ret = getSettings().setGlobalPref(CameraSettings.KEY_CAMERA_ID, switchId);
        if (ret) {
            stopModule();
            startModule();
        } else {
            Log.e(TAG, "set camera id pref fail");
        }
    }
}
