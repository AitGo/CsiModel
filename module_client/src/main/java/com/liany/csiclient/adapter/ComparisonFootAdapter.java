package com.liany.csiclient.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.utils.StringUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/3/22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ComparisonFootAdapter extends BaseQuickAdapter<ComparePhoto, BaseViewHolder> {
    public ComparisonFootAdapter(int layoutResId, @Nullable List<ComparePhoto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComparePhoto item) {
        Glide.with(mContext).load(item.getServicePath())
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_photo));
        if(StringUtils.checkString(item.getEvidenceName())) {
            helper.setText(R.id.tv_name,item.getEvidenceName() + "");
        }
        if(StringUtils.checkString(item.getStatus())) {
            switch (item.getStatus()) {
                case "0":
                    helper.setText(R.id.tv_info,"比对中");
                    break;
                case "-1":
                    helper.setText(R.id.tv_info,"删除");
                    break;
                case "-2":
                    helper.setText(R.id.tv_info,"未提交比对");
                    break;
                case "3":
                    String msg = "";
                    String rev1 = item.getRev1();
                    if(StringUtils.checkString(rev1)) {
                        msg = "认定比中，编号：" + rev1;
                    }else {
                        msg = "认定比中";
                    }
                    helper.setText(R.id.tv_info,msg);
                    break;
                case "9":
                    helper.setText(R.id.tv_info,"未比中");
                    break;
            }
        }else {
            helper.setText(R.id.tv_info,"比对中");
        }
    }

}
