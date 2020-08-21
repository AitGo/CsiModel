package com.liany.clientmodel.diagnose;

/**
 * @创建者 ly
 * @创建时间 2019/12/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class MobileType {

    private String manufacturer;//品牌
    private String model;//型号
    private float width;//实际拍摄的宽度
    private float height;//实际拍摄的长度
    private String remark;//备注，机型

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
