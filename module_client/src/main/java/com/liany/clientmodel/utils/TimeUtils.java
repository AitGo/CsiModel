package com.liany.clientmodel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2019/4/12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TimeUtils {

    public static String getTime(String time,int type ,int addTime) {
        Calendar c = null;
        if (time == null) {
            c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
        }else {
            c = String2Calendar(time);
        }
        switch (type) {
            case Calendar.YEAR:
                c.add(Calendar.YEAR,addTime);
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH,addTime);
                break;
            case Calendar.DATE:
                c.add(Calendar.DATE,addTime);
                break;
            case Calendar.HOUR_OF_DAY:
                c.add(Calendar.HOUR_OF_DAY,addTime);
                break;
            case Calendar.MINUTE:
                c.add(Calendar.MINUTE,addTime);
                break;
            default:

                break;
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        return year + "年" + month + "月" + date + "日" + hour + "时" + min + "分";
    }

    public static Calendar String2Calendar(String strdate) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = null;
        try {
            date = sdf.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
