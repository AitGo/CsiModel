package com.liany.clientmodel.contract.subView.sceneStep;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.diagnose.CrimeItem;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/8
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Comparison_FigureContract {
    interface Model {
        void getComparisonData(CrimeItem crimeItem, String state, callBack callBack);

        void getAllComparisonData(String state, callBack callBack);
    }

    interface View {
        void updateEvidence(List<ComparePhoto> evidencePhotoByUpload);
    }

    interface Presenter {
        void getComparisonResult(CrimeItem crimeItem);

        void getCompareData();
    }
}
