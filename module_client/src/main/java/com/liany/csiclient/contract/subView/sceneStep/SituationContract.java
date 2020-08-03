package com.liany.csiclient.contract.subView.sceneStep;

import com.liany.csiclient.diagnose.CrimeItem;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface SituationContract {
    interface Model {
    }

    interface View {

        String getOverView();
        void setOverView(String value);

        void saveSituation(CrimeItem crimeItem);
    }

    interface Presenter {
        void saveSituation(CrimeItem crimeItem);

        void automatic(CrimeItem mItem);
        void automaticDraw(CrimeItem mItem);
    }
}
