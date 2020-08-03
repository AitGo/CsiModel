package com.liany.csiclient.diagnose;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @创建者 ly
 * @创建时间 2020/4/7
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class KctMultiItem implements MultiItemEntity {

    public static final int kct_title = 1;
    public static final int kct_other = 2;
    public static final int kct_cdma = 3;

    private int itemType;
    private String value;
    private KCTBASESTATIONDATABean kctbasestationdataBean;

    public KctMultiItem(int itemType) {
        this.itemType = itemType;
    }

    public KctMultiItem(int itemType, String value) {
        this.itemType = itemType;
        this.value = value;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public KCTBASESTATIONDATABean getKctbasestationdataBean() {
        return kctbasestationdataBean;
    }

    public void setKctbasestationdataBean(KCTBASESTATIONDATABean kctbasestationdataBean) {
        this.kctbasestationdataBean = kctbasestationdataBean;
    }
}
