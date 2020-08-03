package com.liany.csiclient.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.WitnessEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/3/29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WitnessAdapter extends BaseQuickAdapter<WitnessEntity, BaseViewHolder> {
    public WitnessAdapter(int layoutResId, @Nullable List<WitnessEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WitnessEntity item) {
        helper.setText(R.id.tv_witness_name,item.getWitnessName());
        helper.setText(R.id.tv_witness_sax,item.getWitnessSex());
        helper.setText(R.id.tv_witness_tel,item.getWitnessNumber());
        if(item.getPhoto() != null) {
            Glide.with(mContext).load(item.getPhoto().getServerPath())
                    .dontAnimate()
                    .into((ImageView) helper.getView(R.id.iv_sign));
        }
        helper.setText(R.id.tv_witness_address,item.getWitnessAddress());
    }
}
