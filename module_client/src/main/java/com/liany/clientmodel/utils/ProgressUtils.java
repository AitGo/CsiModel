package com.liany.clientmodel.utils;

import android.content.Context;

import com.liany.clientmodel.widget.CsiProgressDialog;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ProgressUtils {

    private static CsiProgressDialog mProgressDialog;
    private static boolean isShow = false;

    public static void showProgressDialog(Context mContext, String msg) {
//        if(mProgressDialog == null) {
            mProgressDialog = new CsiProgressDialog(mContext, msg);
//        }
//        else {
//            mProgressDialog.setMessage(msg);
//        }
//        if(!isShow) {
            mProgressDialog.show();
//            isShow = true;
//        }
    }

    public static void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
//            isShow = false;
        }
    }
}
