package com.liany.csiclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.GoodEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/8/16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ExtractListAdapter extends BaseQuickAdapter<GoodEntity, BaseViewHolder> {
    public ExtractListAdapter(int layoutResId, @Nullable List<GoodEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodEntity item) {
        helper.setText(R.id.tv_material_name,item.getMaterialName())
                .setText(R.id.tv_collected_name,item.getCollectedName())
                .setText(R.id.tv_collected_position,item.getCollectedPosition())
                .setText(R.id.tv_collected_method,item.getCollectedMethod())
                .setText(R.id.tv_collected_date,item.getCollectedDate());
    }
}
