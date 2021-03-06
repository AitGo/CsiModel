package com.liany.clientmodel.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.Photo;

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
public class AddContactsFingerAdapter extends BaseQuickAdapter<Photo, BaseViewHolder> {
    public AddContactsFingerAdapter(int layoutResId, @Nullable List<Photo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Photo item) {
        Glide.with(mContext).load(item.getServerPath())
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_finger));
    }
}
