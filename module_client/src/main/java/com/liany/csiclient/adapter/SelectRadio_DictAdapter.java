package com.liany.csiclient.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.sysDict;

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
public class SelectRadio_DictAdapter extends BaseQuickAdapter<sysDict, BaseViewHolder> {

    private ImageView imageView;
    private int selectPosition = -1;

    public void setSelectPosition(int position) {
        this.selectPosition = position;
    }

    public SelectRadio_DictAdapter(int layoutResId, @Nullable List<sysDict> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, sysDict item) {
        helper.setText(R.id.tv_select_radio,item.getDictValue());

        imageView = helper.getView(R.id.iv_select_radio);
        imageView.setVisibility(View.INVISIBLE);

        if(selectPosition == helper.getPosition()) {
            imageView.setVisibility(View.VISIBLE);
        }
    }

}
