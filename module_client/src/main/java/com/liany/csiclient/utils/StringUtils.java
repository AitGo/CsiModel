package com.liany.csiclient.utils;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.liany.csiclient.debug.ClientApplication;
import com.liany.csiclient.diagnose.IsCheckEntity;
import com.liany.csiclient.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiclient.diagnose.KctMultiItem;
import com.liany.csiclient.diagnose.Level0Item;
import com.liany.csiclient.diagnose.Level1Item;
import com.liany.csiclient.diagnose.Level2Item;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.WeatherEntity;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;

import java.io.File;
import java.io.FileInputStream;
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

    public static String Date2String(Date date,String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }

    public static String long2SceneDateSecond(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        return format.format(date);
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

    public static Calendar String2Calendar(String strdate,String type) {
        SimpleDateFormat sdf= new SimpleDateFormat(type);
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

    public static long String2long(String dateString,String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
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

    public static List<MultiItemEntity> sysDict2MultiItem(List<sysDict> values) {
        List<MultiItemEntity> value = new ArrayList<>();
        List<sysDict> sysDicts = sysDict2OtherItem(values);

        for(sysDict dict : values) {
            if(dict.getRootKey().equals(dict.getParentKey())) {
                boolean isOther = false;
                for(sysDict otherItem : sysDicts) {
                    isOther = dict.getDictValue().equals(otherItem.getDictValue());
                    if(isOther) {
                        break;
                    }
                }
                if(isOther) {
//                if(dict.getDictValue().equals("其他作案手段") ||
//                        dict.getDictValue().equals("利用计算机手段") ||
//                        dict.getDictValue().equals("其他时机") ||
//                        dict.getDictValue().equals("其他") ||
//                        dict.getDictValue().equals("其它作案特点") ||
//                        dict.getDictValue().equals("和平进入现场") ||
//                        dict.getDictValue().equals("其他侵入手段") ) {
                    Level2Item level2Item = new Level2Item();
                    IsCheckEntity entity = new IsCheckEntity();
                    if(dict.getRemark().equals("true")) {
                        entity.setCheck(true);
                    }
                    entity.setValue(dict.getDictValue());
                    entity.setValueKey(dict.getDictKey());
                    level2Item.setLevel2Value(entity);
                    value.add(level2Item);
                }else {
                    Level1Item level1Item = new Level1Item();
                    level1Item.setLevel1Value(dict.getDictValue());

                    for(sysDict dict1 : values) {
                        if(!dict1.getRootKey().equals(dict1.getParentKey())) {
                            if(dict1.getParentKey().equals(dict.getDictKey())) {
                                Level2Item level2Item = new Level2Item();
                                IsCheckEntity entity = new IsCheckEntity();
                                entity.setValue(dict1.getDictValue());
                                entity.setValueKey(dict1.getDictKey());
                                if(dict1.getRemark().equals("true")) {
                                    entity.setCheck(true);
                                }
                                level2Item.setLevel2Value(entity);
                                level1Item.addSubItem(level2Item);
                            }
                        }
                    }
                    value.add(level1Item);
                }

            }
        }
        return value;
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

    public static String selectUserValue2String(List<selectUser> users) {
        String value = "";
        StringBuilder valueBuilder = new StringBuilder();
        for(selectUser user : users) {
            valueBuilder.append(value);
            valueBuilder.append(user.getTrueName());
            valueBuilder.append("、");
        }
        value = valueBuilder.toString();
        int index = 0;
        if(valueBuilder.length() != 0) {
            index = valueBuilder.length()-1;
        }
        return value.substring(0,index);
    }

    public static String selectUserKey2String(List<selectUser> users) {
        String value = "";
        StringBuilder valueBuilder = new StringBuilder();
        for(selectUser user : users) {
            valueBuilder.append(value);
            valueBuilder.append(user.getTechId());
            valueBuilder.append(",");
        }
        value = valueBuilder.toString();
        int index = 0;
        if(valueBuilder.length() != 0) {
            index = valueBuilder.length()-1;
        }
        return value.substring(0,index);
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

    public static List<MultiItemEntity> sysDict2MultiItem3(List<sysDict> values) {
        List<MultiItemEntity> value = new ArrayList<>();
        for(sysDict dict : values) {
            if(dict.getDictLevel().equals("1")) {
                if(selectOtherItem(values, dict)) {
                    LogUtils.e("value:" + dict.getDictValue() + " level:" + dict.getDictLevel());
                    Level2Item item2 = new Level2Item();
                    IsCheckEntity entity = new IsCheckEntity();
                    entity.setValue(dict.getDictValue());
                    entity.setValueKey(dict.getDictKey());
                    if(dict.getRemark().equals("true")) {
                        entity.setCheck(true);
                    }
                    item2.setLevel2Value(entity);
                    value.add(item2);
                }else {
                    Level0Item item0 = new Level0Item();
                    item0.setLevel0Value(dict.getDictValue());
                    for(sysDict dict1 : values) {
                        if(dict1.getParentKey().equals(dict.getDictKey())) {
                            if(dict1.getDictLevel().equals("2")) {
                                Level1Item item1 = new Level1Item();
                                if(!selectOtherItem(values, dict1)) {
                                    item1.setLevel1Value(dict1.getDictValue());
                                    for(sysDict dict2 : values) {
                                        if(dict2.getParentKey().equals(dict1.getDictKey())) {
                                            if(dict2.getDictLevel().equals("3")) {
                                                Level2Item item2 = new Level2Item();
                                                IsCheckEntity entity = new IsCheckEntity();
                                                entity.setValue(dict2.getDictValue());
                                                entity.setValueKey(dict2.getDictKey());
                                                if(dict2.getRemark().equals("true")) {
                                                    entity.setCheck(true);
                                                }
                                                item2.setLevel2Value(entity);
                                                item1.addSubItem(item2);
                                            }
                                        }
                                    }
                                    item0.addSubItem(item1);
                                }else {
                                    LogUtils.e("value:" + dict1.getDictValue() + " level:" + dict1.getDictLevel());
                                    Level2Item item2 = new Level2Item();
                                    IsCheckEntity entity = new IsCheckEntity();
                                    entity.setValue(dict1.getDictValue());
                                    entity.setValueKey(dict1.getDictKey());
                                    if(dict1.getRemark().equals("true")) {
                                        entity.setCheck(true);
                                    }
                                    item1.setLevel1Value(dict1.getDictValue());
                                    item2.setLevel2Value(entity);
                                    item1.addSubItem(item2);
                                    item0.addSubItem(item1);
                                }
                            }
                        }
                    }
                    value.add(item0);
                }
            }
        }
        return value;
    }

    public static boolean selectOtherItem(List<sysDict> dicts, sysDict dict) {
        boolean isOther = false;
        for(com.liany.csiclient.diagnose.sysDict sysDict : dicts) {
            if(sysDict.getDictLevel().equals(dict.getDictLevel()) && sysDict.getDictKey().equals(dict.getDictKey())) {
                List<com.liany.csiclient.diagnose.sysDict> selectValue = new ArrayList<>();
                for(com.liany.csiclient.diagnose.sysDict dict1 : dicts) {
                    if(dict1.getParentKey().equals(sysDict.getDictKey())) {
                        selectValue.add(dict1);
                    }
                }
                if(selectValue.size() <= 0) {
                    isOther = true;
                }
            }
        }
        return isOther;
    }

    public static List<KctMultiItem> selectKctMultiItems(List<KCTBASESTATIONDATABean> data) {
        List<KctMultiItem> kctMultiItems = new ArrayList<>();
        List<KctMultiItem> cdmas = new ArrayList<>();
        List<KctMultiItem> cmccGsms = new ArrayList<>();
        List<KctMultiItem> cmccLtes = new ArrayList<>();
        List<KctMultiItem> tdScdmas = new ArrayList<>();
        List<KctMultiItem> cuGsms = new ArrayList<>();
        List<KctMultiItem> wcdmas = new ArrayList<>();
        List<KctMultiItem> cuLtes = new ArrayList<>();
        List<KctMultiItem> ctccLtes = new ArrayList<>();

        for(KCTBASESTATIONDATABean kct : data) {
            if(kct.getBS_TYPE().equals("CDMA")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_cdma);
                kctMultiItem.setKctbasestationdataBean(kct);
                cdmas.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("CMCC_GSM")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                cmccGsms.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("CMCC_LTE")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                cmccLtes.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("TD_SCDMA")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                tdScdmas.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("CU_GSM")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                cuGsms.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("WCDMA")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                wcdmas.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("CU_LTE")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                cuLtes.add(kctMultiItem);
            }
            if(kct.getBS_TYPE().equals("CTCC_LTE")) {
                KctMultiItem kctMultiItem = new KctMultiItem(KctMultiItem.kct_other);
                kctMultiItem.setKctbasestationdataBean(kct);
                ctccLtes.add(kctMultiItem);
            }
        }
        KctMultiItem kctMultiItemCdma = new KctMultiItem(KctMultiItem.kct_cdma);
        KctMultiItem kctMultiItemOther = new KctMultiItem(KctMultiItem.kct_other);
        KCTBASESTATIONDATABean bean = new KCTBASESTATIONDATABean();
        bean.setSID("SID");
        bean.setNID("NID");
        bean.setBASE_ID("BID");
        bean.setPN("PN");
        bean.setSTRENGTH("RX");
        bean.setLAC("LAC");
        bean.setCELL_ID("CELLID");
        bean.setRSSI("RSSI");
        kctMultiItemCdma.setKctbasestationdataBean(bean);
        kctMultiItemOther.setKctbasestationdataBean(bean);

        if(cmccGsms.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"移动GSM"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(cmccGsms);
        }
        if(tdScdmas.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"移动3G"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(tdScdmas);
        }
        if(cmccLtes.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"移动4GLTE"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(cmccLtes);
        }
        if(cuGsms.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"联通GSM"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(cuGsms);
        }
        if(wcdmas.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"联通WCDMA"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(wcdmas);
        }
        if(cuLtes.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"联通4GLTE"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(cuLtes);
        }
        if(ctccLtes.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"电信4GLTE"));
            kctMultiItems.add(kctMultiItemOther);
            kctMultiItems.addAll(ctccLtes);
        }
        if(cdmas.size() > 0) {
            kctMultiItems.add(new KctMultiItem(KctMultiItem.kct_title,"电信CDMA"));
            kctMultiItems.add(kctMultiItemCdma);
            kctMultiItems.addAll(cdmas);
        }
        return kctMultiItems;
    }

    public static WeatherEntity selectAmapWeatherCondition(String weather, String fileName) {
        String json = FileUtils.ReadAssetsFile(ClientApplication.getContext(), fileName);
        Response<List<WeatherEntity>> response = GsonUtils.fromJsonArray(json, WeatherEntity.class);
        for(WeatherEntity entity : response.getData()) {
            if(weather.equals(entity.getAmapValue())) {
                return entity;
            }
        }
        return null;
    }
}
