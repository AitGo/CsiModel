package com.liany.clientmodel.utils;

import android.widget.Toast;

import com.liany.model.common.base.BaseApplication;


/**
 * @创建者 ly
 * @创建时间 2019/7/9
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ToastUtils {

    public static void showLong(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    public static void showShort(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
