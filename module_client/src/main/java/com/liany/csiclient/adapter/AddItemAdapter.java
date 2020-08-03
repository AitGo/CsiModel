package com.liany.csiclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.ItemEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/3/25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddItemAdapter extends BaseQuickAdapter<ItemEntity, BaseViewHolder> {
    public AddItemAdapter(int layoutResId, @Nullable List<ItemEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemEntity item) {
        helper.setText(R.id.tv_item_name,item.getItemName());
        helper.setText(R.id.tv_item_amount,item.getAmount());
        helper.setText(R.id.tv_item_value,item.getValue());
        helper.setText(R.id.tv_brand_model,item.getBrandModel());
    }
}
