package ytga.liany.csiclient.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import ytga.liany.csiclient.Ks_SplashActivity;
import ytga.liany.csiclient.contract.SplashContract;
import ytga.liany.csiclient.model.SplashModel;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SplashPresenter implements SplashContract.Presenter {

    private Context mContext;
    private SplashContract.View view;
    private SplashContract.Model model;

    public SplashPresenter(Context mContext, SplashContract.View splashView) {
        this.mContext = mContext;
        this.view = splashView;
        this.model = new SplashModel(mContext);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == Constants.code_permissions_camera) {
            boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//            if (cameraAccepted) {
//                view.showScanView();
//            } else {
//                //用户授权拒绝
//                LogUtils.e("get premissions fail");
//            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.code_scan) {
                //处理扫描结果
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {

                        //得到扫描结果，保存url
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        LogUtils.e(result);
                        model.saveUrl(result);
                        //跳转到登陆页面同步数据
                        view.showLoginView(true);

                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        LogUtils.e("scan fail");
                    }
                }
            }
        }else {
            view.closeActivity();
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
                        ActivityCompat.requestPermissions((Ks_SplashActivity)mContext, perms, Constants.code_permissions_camera);
                    }
                }else {
                    //进入登陆页
                    view.showLoginView(false);
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong(msg);
                //进入登陆页
                view.showLoginView(false);
            }
        });
    }
}
