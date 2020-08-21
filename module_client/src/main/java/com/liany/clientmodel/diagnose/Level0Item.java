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
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {

    private String level0Value;

    public String getLevel0Value() {
        return level0Value;
    }

    public void setLevel0Value(String level0Value) {
        this.level0Value = level0Value;
    }


    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
