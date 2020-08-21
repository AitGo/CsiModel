package com.liany.clientmodel.diagnose;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @创建者 ly
 * @创建时间 2019/4/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Level1Item extends AbstractExpandableItem<Level2Item> implements MultiItemEntity {

    private String level1Value;

    public String getLevel1Value() {
        return level1Value;
    }

    public void setLevel1Value(String level1Value) {
        this.level1Value = level1Value;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
