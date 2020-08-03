package com.liany.csiclient.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.utils.StringUtils;

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
public class EvidenceFingerDesAdapter extends BaseQuickAdapter<EvidenceEntity,ImageDesAdapterHolder> {


    public EvidenceFingerDesAdapter(@Nullable List<EvidenceEntity> data) {
        super(R.layout.item_adapter_finger, data);
    }

    @Override
    protected void convert(ImageDesAdapterHolder helper, EvidenceEntity item) {

        TextView tv_des = helper.getView(R.id.tv_photo_des);

        if(item.getPhoto() != null) {
            Glide.with(mContext).load(item.getPhoto().getServerPath())
                    .dontAnimate()
                    .into((ImageView) helper.getView(R.id.iv_position));
        }
        if(StringUtils.checkString(item.getRev1())) {
            tv_des.setVisibility(View.VISIBLE);
            tv_des.setText(item.getRev1());
        }else {
            tv_des.setVisibility(View.GONE);
        }
    }

}
