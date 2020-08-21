package com.liany.clientmodel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.ToolEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/3/26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddToolAdapter extends BaseQuickAdapter<ToolEntity, BaseViewHolder> {
    public AddToolAdapter(int layoutResId, @Nullable List<ToolEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToolEntity tool) {
        helper.setText(R.id.tv_tool_name,tool.getName());
        helper.setText(R.id.tv_tool_category,tool.getCategory());
        helper.setText(R.id.tv_tool_source,tool.getSource());
    }
}
