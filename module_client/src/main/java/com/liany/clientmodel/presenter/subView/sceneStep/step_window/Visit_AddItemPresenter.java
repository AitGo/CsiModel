package com.liany.clientmodel.presenter.subView.sceneStep.step_window;

import android.content.Context;

import com.liany.clientmodel.contract.subView.sceneStep.step_window.Visit_AddItemContract;
import com.liany.clientmodel.diagnose.ItemEntity;
import com.liany.clientmodel.model.subView.sceneStep.step_window.Visit_AddItemModel;
import com.liany.clientmodel.utils.StringUtils;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Visit_AddItemPresenter implements Visit_AddItemContract.Presenter {
    private Context mContext;
    private Visit_AddItemContract.View addItemView;
    private Visit_AddItemContract.Model addItemModel;

    public Visit_AddItemPresenter(Context mContext , Visit_AddItemContract.View addItemView) {
        this.mContext = mContext;
        this.addItemView = addItemView;
        this.addItemModel = new Visit_AddItemModel(mContext);
    }

    @Override
    public void initViewDate(ItemEntity itemEntity) {
        if(itemEntity != null) {
            addItemView.setItemName(itemEntity.getItemName());
            addItemView.setBrandModel(itemEntity.getBrandModel());
            addItemView.setItemAmount(itemEntity.getAmount());
            addItemView.setItemValue(itemEntity.getValue());
            addItemView.setFeatureDescription(itemEntity.getFeatureDescription());
        }
    }

    @Override
    public void saveItem(ItemEntity entity, String crimeId, boolean isCheck) {
        if(isCheck) {
            if(!StringUtils.checkString(addItemView.getItemName())) {
                addItemView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addItemView.getBrandModel())) {
                addItemView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addItemView.getItemValue())) {
                addItemView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addItemView.getItemAmount())) {
                addItemView.showMsgDialog("");
                return;
            }
        }
        if(entity == null) {
            entity = new ItemEntity();
            entity.setId(StringUtils.getUUID());
            entity.setCrimeId(crimeId);
        }
        entity.setItemName(addItemView.getItemName());
        entity.setBrandModel(addItemView.getBrandModel());
        entity.setAmount(addItemView.getItemAmount());
        entity.setValue(addItemView.getItemValue());
        entity.setFeatureDescription(addItemView.getFeatureDescription());
        addItemView.close(entity);
    }
}
