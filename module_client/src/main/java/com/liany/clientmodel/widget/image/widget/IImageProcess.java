package com.liany.clientmodel.widget.image.widget;

public interface IImageProcess {

    /**
     * 图像旋转90度
     */
    void rotateCW(float degrees);
    void rotateCW();

    /**
     * 图像水平翻转
     */
    void reversalHorizontal();

    /**
     * 图像垂直翻转
     */
    void reverslVertical();
}
