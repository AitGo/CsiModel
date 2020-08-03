package com.liany.csiclient.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.Level0Item;
import com.liany.csiclient.diagnose.Level1Item;
import com.liany.csiclient.diagnose.Level2Item;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.utils.LogUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/4/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SelectExpandRadioAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public int selectPosition = -1;
    public static final int TYPE_LEVEL0 = 0;
    public static final int TYPE_LEVEL1 = 1;
    public static final int TYPE_LEVEL2 = 2;

    private sysDict dict;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SelectExpandRadioAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL0, R.layout.item_adapter_level0);
        addItemType(TYPE_LEVEL1, R.layout.item_adapter_level1);
        addItemType(TYPE_LEVEL2, R.layout.item_adapter_level2);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL0:
                final Level0Item level0Item = (Level0Item)item;
                helper.setText(R.id.tv_level0,level0Item.getLevel0Value())
                        .setImageResource(R.id.iv_level0_open, level0Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        LogUtils.d("Level 0 item pos radio: " + pos);
                        if(level0Item.isExpanded()) {
                            collapse(pos);
                        }else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL1:
                final Level1Item level1Item = (Level1Item)item;
                helper.setText(R.id.tv_level1,level1Item.getLevel1Value())
                        .setImageResource(R.id.iv_level1_open, level1Item.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        LogUtils.d("Level 1 item pos radio: " + pos);
                        if(level1Item.isExpanded()) {
                            collapse(pos);
                        }else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL2:
                final Level2Item level2Item = (Level2Item)item;
                helper.setText(R.id.tv_level2,level2Item.getLevel2Value().getValue());
                final ImageView imageView = helper.getView(R.id.iv_level2_radio);
                if(selectPosition == -1) {
                    if(level2Item.getLevel2Value().isCheck()) {
                        level2Item.getLevel2Value().setCheck(true);
                        imageView.setVisibility(View.VISIBLE);
                        dict = new sysDict();
                        dict.setDictValue(level2Item.getLevel2Value().getValue());
                        dict.setDictKey(level2Item.getLevel2Value().getValueKey());
                    }
                }else {
                    level2Item.getLevel2Value().setCheck(false);
                    imageView.setVisibility(View.INVISIBLE);
                    int pos = helper.getAdapterPosition();
                    if(selectPosition == pos) {
                        level2Item.getLevel2Value().setCheck(true);
                        imageView.setVisibility(View.VISIBLE);
                        dict = new sysDict();
                        dict.setDictValue(level2Item.getLevel2Value().getValue());
                        dict.setDictKey(level2Item.getLevel2Value().getValueKey());
                    }
                }
                break;

        }
    }

    public sysDict getChooseSysDict() {
        return dict;
    }
}
