package com.liany.clientmodel.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.widget.ClearableEditText;

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
public class EvidencePhotoDesAdapter extends BaseQuickAdapter<EvidenceEntity, ImageDesAdapterHolder> {

    private int desVisibility = View.VISIBLE;

    public EvidencePhotoDesAdapter(int layoutResId, @Nullable List<EvidenceEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ImageDesAdapterHolder helper, EvidenceEntity item) {

//        ImageView position = helper.getView(R.id.iv_position);
        ClearableEditText et_des = helper.getView(R.id.et_photo_des);
        et_des.setVisibility(desVisibility);
        if(item.getPhoto() != null) {
            Glide.with(mContext).load(item.getPhoto().getServerPath())
                    .dontAnimate()
                    .into((ImageView) helper.getView(R.id.iv_position));
            et_des.setText(item.getPhoto().getPhotoInfo());
        }
//        helper.setImageBitmap(R.id.iv_position,bitmap);
//        helper.setText(R.id.et_photo_des,item.getDescribe() == null ? "" : item.getDescribe());
    }

    public void setDesVisibility(int desVisibility) {
        this.desVisibility = desVisibility;
    }

}
