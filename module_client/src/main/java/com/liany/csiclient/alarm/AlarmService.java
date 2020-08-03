package com.liany.csiclient.alarm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.content.Intent;

import com.liany.csiclient.utils.LogUtils;

import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2019/5/13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AlarmService extends IntentService {

    // 从其他地方通过Intent传递过来的提醒时间
    private String alarmDateTime;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        alarmDateTime = intent.getStringExtra("alarm_time");
        // taskId用于区分不同的任务
        int taskId = intent.getIntExtra("task_id", 0);

        LogUtils.e("AlarmService " + "executed at " + new Date().toString()
                + " @Thread id：" + Thread.currentThread().getId());

        long alarmDateTimeMillis = DateTimeUtil.stringToMillis(alarmDateTime);

        AlarmManagerUtil.sendRepeatAlarmBroadcast(this, taskId,
                AlarmManager.RTC_WAKEUP, alarmDateTimeMillis, 10 * 1000,
                AlarmReceiver.class);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e( "Destroy "+ "Alarm Service Destroy");
    }
}
