package com.liany.csiclient.utils;

/**
 * @创建者 ly
 * @创建时间 2020/4/21
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ClickUtils {
    private static final int MIN_DELAY_TIME = 500;  // 两次点击间隔
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
