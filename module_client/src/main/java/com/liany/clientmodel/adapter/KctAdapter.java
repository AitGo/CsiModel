package com.liany.clientmodel.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.clientmodel.R;
import com.liany.clientmodel.diagnose.KctMultiItem;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @创建者 ly
 * @创建时间 2020/4/7
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class KctAdapter extends BaseMultiItemQuickAdapter<KctMultiItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public KctAdapter(List<KctMultiItem> data) {
        super(data);
        addItemType(KctMultiItem.kct_other, R.layout.item_adapter_kct_other);
        addItemType(KctMultiItem.kct_title, R.layout.item_adapter_kct_title);
        addItemType(KctMultiItem.kct_cdma, R.layout.item_adapter_kct_cdma);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KctMultiItem item) {
        switch (helper.getItemViewType()) {
            case KctMultiItem.kct_title:
                helper.setText(R.id.tv_kct_title,item.getValue());
                break;
            case KctMultiItem.kct_other:
                helper.setText(R.id.tv_kct_other1,item.getKctbasestationdataBean().getLAC())
                        .setText(R.id.tv_kct_other2,item.getKctbasestationdataBean().getCELL_ID())
                        .setText(R.id.tv_kct_other3,item.getKctbasestationdataBean().getRSSI());
                break;
            case KctMultiItem.kct_cdma:
                helper.setText(R.id.tv_kct_cdma1,item.getKctbasestationdataBean().getSID())
                        .setText(R.id.tv_kct_cdma2,item.getKctbasestationdataBean().getNID())
                        .setText(R.id.tv_kct_cdma3,item.getKctbasestationdataBean().getBASE_ID())
                        .setText(R.id.tv_kct_cdma4,item.getKctbasestationdataBean().getPN())
                        .setText(R.id.tv_kct_cdma5,item.getKctbasestationdataBean().getSTRENGTH());
                break;
        }
    }
}
