package com.liany.csiclient.contract.subView;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface SceneListComparisonEvidenceFootContract {
    interface Model {
        void getCrimeList(callBack callBack);
        void getCompareData(callBack callBack);
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
