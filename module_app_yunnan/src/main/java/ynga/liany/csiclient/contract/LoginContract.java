package ynga.liany.csiclient.contract;

import android.content.Intent;

import com.liany.clientmodel.callback.callBack;


/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface LoginContract {
    interface Model {
        void updateDB(String url, callBack callBack);

        void saveUrl(String result);

        void login(String account, String password, callBack callBack);

        void checkIsFirst(callBack callBack);

        void autoLogin(String account, callBack callBack);

        void checkVersion(String versionCode, String versionName, callBack callBack);
    }

    interface View {
        void showUpdateDialog();

        void showScanView(int code);

        String getAccount();

        String getPassword();

        void showMainActivity();

        void setAccount(String account);

        void showScanView();

        void initToken();

        void checkVersion();
    }

    interface Presenter {
        void updateDB();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void login();

        void initData();

        void checkIsFirst();

        void autoLogin(String loginId);

        void checkVersion(int versionCode, String appVersionName);
    }
}
