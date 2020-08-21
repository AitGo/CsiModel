package com.liany.clientmodel.contract.subView;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface SceneListComparisonContactsContract {
    interface Model {
        void getCrimeList(callBack callBack);
    }

    interface View {

        void setCrimeList(List<CrimeItem> crimeList);

        void startActivity(Class<?> activity, CrimeItem crimeItem);
    }

    interface Presenter {
        void initData();

        void startActivity(CrimeItem crimeItem);
    }
}
