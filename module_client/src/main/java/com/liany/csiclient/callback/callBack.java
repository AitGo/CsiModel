package com.liany.csiclient.callback;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface callBack {
    //成功返回
    void onSuccess(String date);
    //失败返回
    void onFail(String msg);
}
