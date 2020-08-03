package com.liany.csiclient.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.ContactsEntity;

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
public class AddContactsAdapter extends BaseQuickAdapter<ContactsEntity, BaseViewHolder> {
    public AddContactsAdapter(int layoutResId, @Nullable List<ContactsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactsEntity item) {
        if(item.getType().equals("报案人")) {
            helper.getView(R.id.tv_contacts_name1).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_contacts_name2).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.tv_contacts_name1).setVisibility(View.GONE);
            helper.getView(R.id.tv_contacts_name2).setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_contacts_name,item.getName());
        helper.setText(R.id.tv_contacts_tel,item.getTel());
        if(item.getPhotos() != null && item.getPhotos().size() > 0) {
            helper.setText(R.id.tv_collect,"已录入");
        }else {
            helper.setText(R.id.tv_collect,"未录入");
        }
        helper.setText(R.id.tv_address,item.getAddress());

    }

}
