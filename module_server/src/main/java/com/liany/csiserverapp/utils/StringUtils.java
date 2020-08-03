package com.liany.csiserverapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.sysDict;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.RequiresApi;

/**
 * @创建者 ly
 * @创建时间 2019/3/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class StringUtils {
    public static int string2int(String str) {
        return string2int(str, 0);
    }

    public static int string2int(String str, int def) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
        }
        return def;
    }

    public static boolean checkString(String str) {
        if(str != null && !str.equals("")) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPassword(String password) {
        if (password.length() < 6) {
            return false;
        } else {
            return true;
        }

    }

    public static String long2FileName(Date date) {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String filename = timeStampFormat.format(date);
        return filename;
    }

    public static String Date2String(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return simpleDateFormat.format(date);
    }

    public static Calendar Date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date Calendar2Date(Calendar calendar) {
        return calendar.getTime();
    }

    public static String Calendar2String(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return sdf.format(calendar.getTime());
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

    public static String long2String(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return format.format(date);
    }

    public static String long2String(long milSecond,String type) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    public static long String2long(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String copyToInternalPath(Activity activity, String OldPath){
        String NewPath = "";
        File mediaStorageDir = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Report");
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return NewPath;
            }
        }
        String[] filename = OldPath.split("/");
        NewPath = new File(mediaStorageDir.getPath() + File.separator + filename[filename.length-1]).toString();
        Log.d("Anita", "new path = "+NewPath);
//        BackupRestore.copyFile(OldPath, NewPath);
//        BackupRestore.deleteFiles(new File(OldPath));
        return NewPath;
    }

    public static List<sysDict> sysDict2OtherItem(List<sysDict> dicts) {
        List<sysDict> value = new ArrayList<>();
        for(sysDict dict : dicts) {
            if(dict.getDictLevel().equals("1")) {
                List<sysDict> selectValue = new ArrayList<>();
                for(sysDict dict1 : dicts) {
                    if(dict1.getParentKey().equals(dict.getDictKey())) {
                        selectValue.add(dict1);
                    }
                }
                if(selectValue.size() <= 0) {
                    value.add(dict);
                }
            }
        }
        return value;
    }

    public static String sysDictValue2String(List<sysDict> dicts) {
        String value = "";
        StringBuilder valueBuilder = new StringBuilder();
        for(sysDict dict : dicts) {
            valueBuilder.append(value);
            valueBuilder.append(dict.getDictValue());
            valueBuilder.append(",");
        }
        value = valueBuilder.toString();
        int index = 0;
        if(valueBuilder.length() != 0) {
            index = valueBuilder.length()-1;
        }
        return value.substring(0,index);
    }

    public static String sysDictKey2String(List<sysDict> dicts) {
        String key = "";
        StringBuilder keyBuilder = new StringBuilder();
        for(sysDict dict : dicts) {
            keyBuilder.append(key);
            keyBuilder.append(dict.getDictKey());
            keyBuilder.append(",");
        }
        key = keyBuilder.toString();
        int index = 0;
        if(keyBuilder.length() != 0) {
            index = keyBuilder.length()-1;
        }
        return key.substring(0,index);
    }

    /**
     * java计算文件32位md5值
     * @param filePath 文件路径
     * @return
     */
    public static String md5HashCode32(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");

            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();

            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes  = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;//解释参见最下方
                if (val < 16) {
                    /**
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getRamdom(int count) {
        String strRand="" ;
        for(int i=0;i<count;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    /**
     * 压缩包23位名字，除开后缀19位
     * @param createDate
     * @return
     */
    public static String getCompareZipFileName(long createDate) {
        return "A" + createDate + getRamdom(5) + ".zip";
    }

    public static String StringDateFormat(String strdate) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取手机序列号
     *
     * @return 手机序列号
     */
    @SuppressLint({"NewApi", "MissingPermission"})
    public static String getSerialNumber() {
        String serial = "";
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("e", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String externalSDCardPath() {
        String path = "";
        try {
            StorageManager storageManager = (StorageManager) ServerApplication.getContext().getSystemService(Context.STORAGE_SERVICE);
            // 7.0才有的方法
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            Class<?> volumeClass = Class.forName("android.os.storage.StorageVolume");
            Method getPath = volumeClass.getDeclaredMethod("getPath");
            Method isRemovable = volumeClass.getDeclaredMethod("isRemovable");
            getPath.setAccessible(true);
            isRemovable.setAccessible(true);
            for (int i = 0; i < storageVolumes.size(); i++) {
                StorageVolume storageVolume = storageVolumes.get(i);
                String mPath = (String) getPath.invoke(storageVolume);
                Boolean isRemove = (Boolean) isRemovable.invoke(storageVolume);
                Log.d("tag2", "mPath is === " + mPath + "isRemoveble == " + isRemove);
                if(i == (storageVolumes.size() - 1)) {
                    if(isRemove) {
                        path = mPath;
                    }
                }
            }
        }catch (Exception e){
            Log.d("tag2","e == "+e.getMessage());
        }
        return path;
    }
}
