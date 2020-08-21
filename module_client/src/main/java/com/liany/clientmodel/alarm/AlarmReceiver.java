package com.liany.clientmodel.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @创建者 ly
 * @创建时间 2019/5/13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AlarmReceiver extends BroadcastReceiver {

    private Context mContext;
    private Notification mNotification;
    private NotificationManager mManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        LogUtils.e("从服务启动广播111" );
        OkGo.<String>post( Constants.ipAddress + "/compare/getAllCompareFromService")
                .tag(this)
                .params("userId", (String) SPUtils.getParam(mContext, Constants.sp_userId,""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(response.body());
                        com.liany.clientmodel.diagnose.Response<List<ComparePhoto>> listResponse = GsonUtils.fromJsonArray(response.body(), ComparePhoto.class);
                        if(listResponse.getCode() == 200) {
                            showNotify(context, intent);
                            AlarmManagerUtil.cancelAlarmBroadcast(mContext, Constants.mTaskId, AlarmReceiver.class);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        com.liany.clientmodel.utils.LogUtils.e(response.getException().getMessage());
                    }
                });
    }

    private void showNotify(Context context, Intent intent) {
        String id = "channel_id";
        String name="channel_name";

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;

//        Intent intent1 = new Intent(context, MyNotificationBroadcastReceiver.class);
//        intent1.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            //是否允许震动
            mChannel.enableVibration(true);
            //获取系统通知响铃声音的配置
            mChannel.getAudioAttributes();
//			Toast.makeText(context, mChannel.toString(), Toast.LENGTH_SHORT).show();
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(context)
                    .setChannelId(id)
                    .setContentTitle("提示")
                    .setContentText("您有一条任务一更新")
                    .setSmallIcon(R.mipmap.ic_liany)
                    .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setContentTitle("提示")
                    .setContentText("您有一条任务一更新")
                    .setSmallIcon(R.mipmap.ic_liany)
                    .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                    .setAutoCancel(true)
                    .setOngoing(true);
//					.setChannel(id);//无效
            notificationBuilder.setContentIntent(pendingIntent);
            notification = notificationBuilder.build();
        }

        notificationManager.notify((int) System.currentTimeMillis(), notification);
    }

}
