package com.liany.csiserverapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

/**
 * @创建者 ly
 * @创建时间 2020/4/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Compare {

    /**
     * 提取特征点
     * @param path
     * @return
     */
    public static String getFeature(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        byte[] probeImage =  BitmapUtils.Bitmap2Bytes(bitmap);
        FingerprintTemplate probe = new FingerprintTemplate(
                new FingerprintImage()
                        .dpi(500)
                        .decode(probeImage));
        return GsonUtils.gsonString(probe);
    }

    /**
     * 比对指纹
     * @param probeString
     * @param candidateString
     * @return
     */
    public static boolean CompareFinger(String probeString,String candidateString) {
        FingerprintTemplate probe = GsonUtils.gsonBean(probeString, FingerprintTemplate.class);
        FingerprintTemplate candidate = GsonUtils.gsonBean(candidateString, FingerprintTemplate.class);
        double score = new FingerprintMatcher()
                .index(probe)
                .match(candidate);
        Log.e("compare",score +"");
        double threshold = 40;
        return score >= threshold;
    }
}
