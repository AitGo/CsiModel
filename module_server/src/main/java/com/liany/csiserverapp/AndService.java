package com.liany.csiserverapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.bt.BtBase;
import com.liany.csiserverapp.bt.BtServer;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


/**
 * @创建者 ly
 * @创建时间 2020/3/5
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
@Route(path = "/service/andService")
public class AndService extends Service implements BtBase.Listener{

    private BtServer mServer;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("onCreate: MyService");
        new Thread() {
            @Override
            public void run() {
                super.run();
                ServerManager serverManager = new ServerManager(getApplicationContext());
                serverManager.startServer();
            }
        }.start();

        startForeground(2,getNotification());
        //开启蓝牙监听
        mServer = new BtServer(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification getNotification() {
        String id = "channel_id";
        String name="channel_name";
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        Intent intent = new Intent(this, Server_MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            //是否允许震动
            mChannel.enableVibration(true);
            //获取系统通知响铃声音的配置
            mChannel.getAudioAttributes();
//			Toast.makeText(context, mChannel.toString(), Toast.LENGTH_SHORT).show();
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("快勘服务")
                    .setContentText("服务已启动")
                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE)
                    .setSound(null)
                    .setVibrate(new long[]{0})
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("快勘服务")
                    .setContentText("服务已启动")
                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE)
                    .setSound(null)
                    .setVibrate(new long[]{0})
                    .setAutoCancel(true)
                    .setOngoing(true);
//					.setChannel(id);//无效
            notificationBuilder.setContentIntent(pendingIntent);
            notification = notificationBuilder.build();
        }
        return notification;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void socketNotify(int state, Object obj) {
        String msg = null;
        switch (state) {
            case BtBase.Listener.CONNECTED:
                BluetoothDevice dev = (BluetoothDevice) obj;
                msg = String.format("与%s(%s)连接成功", dev.getName(), dev.getAddress());
//                mTips.setText(msg);
                break;
            case BtBase.Listener.DISCONNECTED:
                mServer.listen();
                msg = "连接断开,正在重新监听...";
//                mTips.setText(msg);
                break;
            case BtBase.Listener.MSG:
                msg = String.format("\n%s", obj);
//                mLogs.append(msg);
                break;
        }
        ServerApplication.toast(msg, 0);
    }
}
