package com.liany.clientmodel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.ContactsEntity;

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
public class ComparisonContactsAdapter extends BaseQuickAdapter<ContactsEntity, BaseViewHolder> {
    public ComparisonContactsAdapter(int layoutResId, @Nullable List<ContactsEntity> data) {
        super(layoutResId, data);
    }
    private int comparisonState = 0;


    @Override
    protected void convert(BaseViewHolder helper, ContactsEntity item) {
        if(item.getType().equals("报案人")) {
            helper.setText(R.id.tv_contacts_type,"报案人:");
        }else {
            helper.setText(R.id.tv_contacts_type,"受害人:");
        }
//        Glide.with(mContext).load(item.getPhotoPath())
//                .dontAnimate()
//                .into((ImageView) helper.getView(R.id.iv_contacts));
        helper.setText(R.id.tv_contacts_name,item.getName())
                .setText(R.id.tv_contacts_sex,item.getSex())
                .setText(R.id.tv_contacts_tel,item.getTel());
//        switch (comparisonState) {
//            case 0:
//                helper.setText(R.id.tv_contacts_result,"未比对");
//                break;
//            case 1:
//                helper.setText(R.id.tv_contacts_result,"比对中");
//                break;
//            case 2:
//                helper.setText(R.id.tv_contacts_result,"比对完成");
//                break;
//        }

    }

    public void setComparisonState(int comparisonState) {
        this.comparisonState = comparisonState;
    }
}
