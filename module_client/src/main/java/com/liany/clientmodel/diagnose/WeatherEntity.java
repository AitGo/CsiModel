package com.liany.clientmodel.diagnose;

/**
 * @创建者 ly
 * @创建时间 2020/4/14
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class WeatherEntity {
    private String amapValue;//高德地图天气情况
    private String dictValue;//现勘
    private String dictKey;

    public String getAmapValue() {
        return amapValue;
    }

    public void setAmapValue(String amapValue) {
        this.amapValue = amapValue;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }
}
