package com.liany.clientmodel.diagnose;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @创建者 ly
 * @创建时间 2019/4/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Level2Item implements MultiItemEntity {

    private IsCheckEntity level2Value;

    public IsCheckEntity getLevel2Value() {
        return level2Value;
    }

    public void setLevel2Value(IsCheckEntity level2Value) {
        this.level2Value = level2Value;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
