package com.liany.clientmodel.contract.subView.sceneStep.step_window;

import com.liany.clientmodel.diagnose.ItemEntity;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Visit_AddItemContract {
    interface Model {
    }

    interface View {
        String getItemName();
        String getBrandModel();
        String getItemAmount();
        String getItemValue();
        String getFeatureDescription();

        void setItemName(String itemName);
        void setBrandModel(String brandModel);
        void setItemAmount(String itemAmount);
        void setItemValue(String itemValue);
        void setFeatureDescription(String description);

        void close(ItemEntity entity);

        void showMsgDialog(String s);
    }

    interface Presenter {
        void initViewDate(ItemEntity itemEntity);

        void saveItem(ItemEntity entity, String crimeId, boolean isCheck);
    }
}
