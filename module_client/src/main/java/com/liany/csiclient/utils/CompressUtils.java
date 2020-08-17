package com.liany.csiclient.utils;

import android.content.Context;
import android.text.TextUtils;

import com.liany.csiclient.base.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * @创建者 ly
 * @创建时间 2019/11/28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CompressUtils {

    public interface Callback{
        void onStart();
        void onSuccess(File file);
        void onError(Throwable e);
    }

    public static void compressPic(Context mContext, File file, String rename, Callback callback) {
        //压缩图片
        File finalFile = file;
        if(!StringUtils.checkString(file.getAbsolutePath())) {
            callback.onError(new FileNotFoundException("图片路径为空"));
            return;
        }
        if(file.length() == 0) {
            callback.onError(new FileNotFoundException("图片大小为0"));
            return;
        }
        if(file.length()/1024 < 300) {
            callback.onSuccess(file);
            return;
        }
        Luban.with(mContext)
                .load(file)
                .ignoreBy(100)
                .setTargetDir(Constants.path_photoDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return rename + StringUtils.long2FileName(new Date()) + finalFile.getName().substring(finalFile.getName().lastIndexOf("."));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        callback.onStart();
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        callback.onSuccess(file);
                    }
                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        callback.onError(e);
                    }
                }).launch();
    }
}
