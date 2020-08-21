package com.liany.clientmodel.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.liany.clientmodel.view.MainActivity;

/**
 * @创建者 ly
 * @创建时间 2019/10/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MyNotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //跳转activity
        Intent mIntent = new Intent(context, MainActivity.class);
//        mIntent.setFlags(
//                Intent.FLAG_ACTIVITY_NEW_TASK
//                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
//        );
        context.startActivity(mIntent);
    }
}
