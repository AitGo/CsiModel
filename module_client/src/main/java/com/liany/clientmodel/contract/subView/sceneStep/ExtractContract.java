package com.liany.clientmodel.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.GoodEntity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface ExtractContract {
    interface Model {
    }

    interface View {
        void startActivity(Class<?> activity, CrimeItem crimeItem, GoodEntity entity, int position);

        void updateExtract(GoodEntity entity, int position);

        void addExtract(GoodEntity entity);

        void removeExtract(GoodEntity entity);
    }

    interface Presenter {
        void addCommonExtract(CrimeItem item);

        void addLiveExtract(CrimeItem item);

        void initData(CrimeItem item, List<GoodEntity> commLists, List<GoodEntity> liveLists);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void deleteExtract(GoodEntity goodEntity);
    }
}
