package com.liany.csiclient.contract.subView.sceneStep;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/7/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Comparison_FaceContract {
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
