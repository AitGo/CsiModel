package com.liany.csiserverapp.utils;

import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.cookie.Cookie;

/**
 * @创建者 ly
 * @创建时间 2020/3/20
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class RequestUtils {

    public static String getUser(HttpRequest request) {
        Cookie account = request.getCookie("account");
        String value = account.getValue();
        String[] split = value.split("=");
        if(split.length > 0) {
            return split[0];
        }
        return "";
    }

    public static String getPassword(HttpRequest request) {
        Cookie account = request.getCookie("account");
        String value = account.getValue();
        String[] split = value.split("=");
        if(split.length > 0) {
            return split[split.length-1];
        }
        return "";
    }
}
