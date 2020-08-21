package com.liany.clientmodel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.CrimeListEntity;
import com.liany.clientmodel.utils.StringUtils;

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
public class CrimeListAdapter extends BaseQuickAdapter<CrimeListEntity, BaseViewHolder> {

    private boolean isVisibility = false;

    public void setEditVisibility(boolean isVisibility) {
        this.isVisibility = isVisibility;
    }

    public CrimeListAdapter(int layoutResId, @Nullable List<CrimeListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CrimeListEntity item) {
        if(item.getCrimeItem().getGetLastData() == null) {
            item.getCrimeItem().setGetLastData(false);
        }
        helper.setText(R.id.tv_crime_time, StringUtils.long2String(item.getCrimeItem().getOccurred_start_time()));
        helper.setText(R.id.tv_crime_casetype,item.getCrimeItem().getCasetype());
        helper.setText(R.id.tv_crime_area,item.getCrimeItem().getArea());
        helper.setText(R.id.tv_crime_complete,item.getCrimeItem().getGetLastData() ? "完整" : "不完整");
        if(item.getCrimeItem().getIsUpload() != null && item.getCrimeItem().getIsUpload().equals("upload")) {
            helper.setText(R.id.tv_crime_complete,"已上传");
        }
        helper.setGone(R.id.item_check,isVisibility);
        if(item.isCheck()){
            helper.setBackgroundRes(R.id.item_check, R.mipmap.radiochoose);
        }else {
            helper.setBackgroundRes(R.id.item_check, R.mipmap.radiounchoose);
        }
    }
}
