package com.liany.clientmodel.widget.image.widget;

import android.graphics.Bitmap;

public interface IImageAdjust {
    /**
     * 调节亮度
     */
    void adjustBrightness(float value);

    /**
     * 调节对比度
     */
    void adjustConstrast(float value);

    /**
     * 直方图调节
     */
    void adjustHistogram(float value);

    /**
     * 保存调节(亮度、对比度)结果
     */
    void saveBC();

    Bitmap getBitmap();

    /**
     * 取消调节(亮度、对比度)操作
     */
    void cancelBC();

    /**
     * 保存(直方增强)调节结果
     */
    void saveHistogram(float value);

    /**
     * 取消(直方增强)调节操作
     */
    void cancelHistogram();
}
