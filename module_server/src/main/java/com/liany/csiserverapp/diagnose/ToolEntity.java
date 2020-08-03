package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/3/26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "tool")
public class ToolEntity implements Serializable {
    private static final long serialVersionUID = 3106254312629224979L;
    @Id
    private String id;
    private String crimeId;

    private String name;//工具名称
    private String category;//工具类目
    private String categoryKey;//工具类目
    private String source;//工具来源
    private String sourceKey;//工具来源
    @Generated(hash = 1229674174)
    public ToolEntity(String id, String crimeId, String name, String category,
            String categoryKey, String source, String sourceKey) {
        this.id = id;
        this.crimeId = crimeId;
        this.name = name;
        this.category = category;
        this.categoryKey = categoryKey;
        this.source = source;
        this.sourceKey = sourceKey;
    }
    @Generated(hash = 663594910)
    public ToolEntity() {
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategoryKey() {
        return this.categoryKey;
    }
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getSourceKey() {
        return this.sourceKey;
    }
    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

}
