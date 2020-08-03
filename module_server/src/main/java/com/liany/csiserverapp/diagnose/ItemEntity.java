package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/3/25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "item")
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1137121809727516343L;
    @Id
    private String id;
    private String crimeId;

    private String itemName;//品名
    private String brandModel;//厂牌型号
    private String amount;//数量
    private String value;//价值
    private String featureDescription;//特征描述
    @Generated(hash = 1932757921)
    public ItemEntity(String id, String crimeId, String itemName, String brandModel,
            String amount, String value, String featureDescription) {
        this.id = id;
        this.crimeId = crimeId;
        this.itemName = itemName;
        this.brandModel = brandModel;
        this.amount = amount;
        this.value = value;
        this.featureDescription = featureDescription;
    }
    @Generated(hash = 365170573)
    public ItemEntity() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCrimeId() {
        return this.crimeId;
    }
    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getBrandModel() {
        return this.brandModel;
    }
    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getFeatureDescription() {
        return this.featureDescription;
    }
    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

}
