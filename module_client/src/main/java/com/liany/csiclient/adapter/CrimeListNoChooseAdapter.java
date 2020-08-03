package com.liany.csiclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.utils.StringUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CrimeListNoChooseAdapter extends BaseQuickAdapter<CrimeItem, BaseViewHolder> {

    public CrimeListNoChooseAdapter(int layoutResId, @Nullable List<CrimeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CrimeItem item) {

        helper.setText(R.id.tv_crime_time, StringUtils.long2String(item.getOccurred_start_time()));
        helper.setText(R.id.tv_crime_casetype,item.getCasetype());
        helper.setText(R.id.tv_crime_area,item.getArea());
    }
}
