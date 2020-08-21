package com.liany.clientmodel.contract.subView.sceneStep;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.KCTBASESTATIONDATABean;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/7
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface StationCollectionContract {
    interface Model {
        void startCollect(CrimeItem item, callBack callBack);

        void getKctData(CrimeItem item, callBack callBack);

        void stopCollect(callBack callBack);
    }

    interface View {
        void updateList(List<KCTBASESTATIONDATABean> data);

        void showStartCollectDialog();
    }

    interface Presenter {
        void getKctData(CrimeItem item);

        void startCollect(CrimeItem item);
    }
}
