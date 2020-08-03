package com.liany.csiclient.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.widget.ClearableEditText;

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
public class PositionPicDesAdapter extends BaseQuickAdapter<Photo,ImageDesAdapterHolder> {

    private int desVisibility = View.VISIBLE;

    public PositionPicDesAdapter(int layoutResId, @Nullable List<Photo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ImageDesAdapterHolder helper, Photo item) {

//        ImageView position = helper.getView(R.id.iv_position);
        ClearableEditText et_des = helper.getView(R.id.et_photo_des);
        et_des.setVisibility(desVisibility);
//        position.setImageBitmap(PhotoUtils.path2Bitmap(item.getPhotoPath()));
        LogUtils.e("servicepaht " + item.getServerPath());
        Glide.with(mContext).load(item.getServerPath())
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_position));
        et_des.setText(item.getPhotoInfo());

        et_des.addTextChangedListener(new MyTextWatcher(item));
//        helper.setImageBitmap(R.id.iv_position,bitmap);
//        helper.setText(R.id.et_photo_des,item.getDescribe() == null ? "" : item.getDescribe());
    }

    public void setDesVisibility(int desVisibility) {
        this.desVisibility = desVisibility;
    }

    class MyTextWatcher implements TextWatcher {

        private Photo positionPic;

        public MyTextWatcher(Photo positionPic) {
            this.positionPic = positionPic;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            positionPic.setPhotoInfo(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
