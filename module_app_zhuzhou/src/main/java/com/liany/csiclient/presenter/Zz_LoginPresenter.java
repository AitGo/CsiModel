package com.liany.csiclient.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.aj.frame.ydjwpt.common.beans.UserExt;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.LoginUserResult;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.csiclient.Zz_LoginActivity;
import com.liany.csiclient.aidl.service.JwtptServiceManager;
import com.liany.csiclient.base.Zz_BaseEvent;
import com.liany.csiclient.contract.Zz_LoginContract;
import com.liany.csiclient.model.Zz_LoginModel;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @创建者 ly
 * @创建时间 2020/8/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Zz_LoginPresenter implements Zz_LoginContract.Presenter {
    private Zz_LoginContract.View view;
    private Zz_LoginContract.Model model;
    private Context mContext;

    public Zz_LoginPresenter(Context mContext, Zz_LoginContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new Zz_LoginModel(mContext);
    }

    @Override
    public void updateDB() {
        ProgressUtils.showProgressDialog(mContext,"正在同步数据");
        String url = (String) SPUtils.getParam(mContext, Constants.sp_url, "");
        model.updateDB(url,new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showShort(response.getData());
                    SPUtils.setParam(mContext,Constants.sp_accessInspectors,"");
                    SPUtils.setParam(mContext,Constants.sp_accessInspectorsKey,"");
                }else {
                    ToastUtils.showShort("同步错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showShort("同步失败:" + msg);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.code_scan) {
            //处理扫描结果
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //处理登录
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    LogUtils.e(result);
                    model.saveUrl(result);
                    //弹出dialog，选择是否要同步数据
                    view.showUpdateDialog();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    LogUtils.e("scan fail");
                }
            }
        }else if(requestCode == Constants.code_scan_ip) {
            //处理扫描结果
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //处理登录
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    LogUtils.e(result);
                    Constants.ipAddress = result;
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    LogUtils.e("scan fail");
                }
            }
        }
    }

    @Override
    public void login() {
        String account = view.getAccount();
        String password = view.getPassword();
        if(StringUtils.checkString(account) && StringUtils.checkString(password)) {
            ProgressUtils.showProgressDialog(mContext,"正在登陆");
            model.login(account, password, new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<LoginUserResult> response = GsonUtils.fromJsonObject(date, LoginUserResult.class);
                    if(response.getCode() == 200) {
                        SPUtils.setParam(mContext,Constants.sp_loginName,account);
                        SPUtils.setParam(mContext,Constants.sp_organId,response.getData().getOrganId());
                        SPUtils.setParam(mContext,Constants.sp_techId,response.getData().getTechId());
                        SPUtils.setParam(mContext,Constants.sp_userName,response.getData().getUserName());
                        SPUtils.setParam(mContext,Constants.sp_unitName,response.getData().getUnitName());
                        SPUtils.setParam(mContext,Constants.sp_unitCode,response.getData().getUnitCode());
                        SPUtils.setParam(mContext,Constants.sp_userId,response.getData().getUserId());
//                        SPUtils.setParam(mContext,Constants.sp_deviceId,response.getData().getDeviceId());
//                        CrashReport.putUserData(mContext, "deviceId", response.getData().getDeviceId());
//                        view.showMainActivity();
                        view.checkVersion();
                    }else {
                        ToastUtils.showLong(response.getMsg());
                    }
                }

                @Override
                public void onFail(String msg) {
                    ProgressUtils.dismissProgressDialog();
                    ToastUtils.showLong("登录错误:" + msg);
                }
            });
        }else {
            ToastUtils.showLong("用户名或密码不能为空！");
        }
    }

    @Override
    public void initData() {
        String account = (String) SPUtils.getParam(mContext, Constants.sp_loginName, "");
        if(StringUtils.checkString(account)) {
            view.setAccount(account);
        }
    }

    @Override
    public void checkIsFirst() {
        model.checkIsFirst(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date,String.class);
                if(response.getCode() == 200) {
                    //第一次登陆，进入扫描页面同步数据
                    if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)) {
                        view.showScanView();
                    }else {
                        //提示用户开户拍照权限
                        String[] perms = {Manifest.permission.CAMERA};
                        ActivityCompat.requestPermissions((Zz_LoginActivity)mContext, perms, Constants.code_permissions_camera);
                    }
                }else {
                    getCurrentUser();
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong(msg);
            }
        });
    }

    @Override
    public void autoLogin(String policeId) {
        if(!StringUtils.checkString(policeId)) {
            ToastUtils.showLong("警号为空");
            return;
        }
        ProgressUtils.showProgressDialog(mContext,"正在自动登录");
        model.autoLogin(policeId, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<LoginUserResult> response = GsonUtils.fromJsonObject(date, LoginUserResult.class);
                if(response.getCode() == 200) {
                    SPUtils.setParam(mContext,Constants.sp_loginName,policeId);
                    SPUtils.setParam(mContext,Constants.sp_organId,response.getData().getOrganId());
                    SPUtils.setParam(mContext,Constants.sp_techId,response.getData().getTechId());
                    SPUtils.setParam(mContext,Constants.sp_userName,response.getData().getUserName());
                    SPUtils.setParam(mContext,Constants.sp_unitName,response.getData().getUnitName());
                    SPUtils.setParam(mContext,Constants.sp_unitCode,response.getData().getUnitCode());
                    SPUtils.setParam(mContext,Constants.sp_userId,response.getData().getUserId());
//                        SPUtils.setParam(mContext,Constants.sp_deviceId,response.getData().getDeviceId());
//                    view.showMainActivity();
                    view.checkVersion();
                }else {
                    ToastUtils.showLong(response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong(msg);
                ProgressUtils.dismissProgressDialog();
            }
        });
    }

    @Override
    public void getCurrentUser() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    JwtptServiceManager manager = new JwtptServiceManager(((Zz_LoginActivity)mContext).getApplication());
                    LogUtils.e("jwtptService.getCurrentUser");
                    UserExt user = manager.getCurrentUser();
//                    UserExt user = new UserExt();
                    Zz_BaseEvent.CommonEvent event = Zz_BaseEvent.CommonEvent.JWTPT_GETCURRENTUSER_SUCCESS;
                    event.setObject(user);
                    EventBus.getDefault().post(event);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Zz_BaseEvent.CommonEvent event = Zz_BaseEvent.CommonEvent.JWTPT_GETCURRENTUSER_ERROR;
                    event.setObject(e);
                    EventBus.getDefault().post(event);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void checkVersion(int versionCode, String versionName) {
        model.checkVersion(versionCode + "", versionName, new callBack() {
            @Override
            public void onSuccess(String date) {
                view.showMainActivity();
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong(msg);
                view.showMainActivity();
            }
        });
    }
}
