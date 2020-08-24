package com.liany.csiclient.contract;

import android.content.Intent;

import com.liany.clientmodel.callback.callBack;

/**
 * @创建者 ly
 * @创建时间 2020/8/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Zz_SplashContract {
    interface Model {
        void checkIsFirst(callBack callBack);

        void saveUrl(String result);
    }

    interface View {
        void showScanView();

        void showLoginView(final boolean isShowUpdate);

        void closeActivity();
    }

    interface Presenter {
        void checkIsFirst();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }
}
