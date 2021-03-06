package com.liany.clientmodel.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.utils.StringUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/7/9
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CompareFootAdapter extends BaseQuickAdapter<ComparePhoto, BaseViewHolder> {

    public CompareFootAdapter(@Nullable List<ComparePhoto> data) {
        super(R.layout.item_adapter_comparison_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComparePhoto item) {
        Glide.with(mContext).load(item.getServicePath())
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_photo));
        if(StringUtils.checkString(item.getCreateDate())) {
            helper.setText(R.id.tv_time,item.getCreateDate() + "");
        }
        if(StringUtils.checkString(item.getRev2())) {
            if(item.getRev2().contains("@@")) {
                String[] split = item.getRev2().split("@@");
                String caseType = split[0];
                String area = split[1];
                helper.setText(R.id.tv_caseType,caseType);
                helper.setText(R.id.tv_area,area);
            }
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
                        msg = rev1;
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
