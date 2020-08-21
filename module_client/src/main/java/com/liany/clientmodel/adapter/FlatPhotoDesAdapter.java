package com.liany.clientmodel.adapter;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.Photo;

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
public class FlatPhotoDesAdapter extends BaseQuickAdapter<Photo, ImageDesAdapterHolder> {

    private int desVisibility = View.VISIBLE;

    public FlatPhotoDesAdapter(int layoutResId, @Nullable List<Photo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ImageDesAdapterHolder helper, Photo item) {

//        ImageView position = helper.getView(R.id.iv_position);
        EditText et_des = helper.getView(R.id.et_photo_des);
        //设置EditText的显示方式为多行文本输入
        et_des.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //改变默认的单行模式
        et_des.setSingleLine(false);
        et_des.setVisibility(desVisibility);
        Glide.with(mContext).load(item.getServerPath())
                .dontAnimate()
                .into((ImageView) helper.getView(R.id.iv_position));
        et_des.setText(item.getPhotoInfo());
//        et_des.addTextChangedListener(new MyTextWatcher(item));
//        helper.setImageBitmap(R.id.iv_position,bitmap);
//        helper.setText(R.id.et_photo_des,item.getDescribe() == null ? "" : item.getDescribe());
    }

    public void setDesVisibility(int desVisibility) {
        this.desVisibility = desVisibility;
    }

    class MyTextWatcher implements TextWatcher {

        private Photo entity;

        public MyTextWatcher(Photo entity) {
            this.entity = entity;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            entity.setPhotoInfo(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
