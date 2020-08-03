package com.liany.csiclient.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;

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
public class SelectRadioAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private ImageView imageView;
    private int selectPosition = -1;

    public void setSelectPosition(int position) {
        this.selectPosition = position;
    }

    public SelectRadioAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_select_radio,item);

        imageView = helper.getView(R.id.iv_select_radio);
        imageView.setVisibility(View.INVISIBLE);

        if(selectPosition == helper.getPosition()) {
            imageView.setVisibility(View.VISIBLE);
        }

    }

}
