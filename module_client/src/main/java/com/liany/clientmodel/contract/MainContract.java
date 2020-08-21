package com.liany.clientmodel.contract;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface MainContract {
    interface Model {

        void getPresentScene(callBack callBack);

        void setPresentScene(String crimeId, callBack callBack);

        void getSceneList(String type, callBack callBack);

        String getUserName();

        String getLoginName();

        String getAccessInspectors();

        String getAccessInspectorsKey();

        String getUnitName();

        String selectOrganId();

        String selectSysTechId();

        void saveMobileTypeSetting(float width, float height);

        String getUnitCode();

    }

    interface View {
        void showCreateScene();

        void showSceneList();

        void showComparisonContacts();

        void showComparisonEvidence();

        void showBaseInfo(CrimeItem crimeItem);

        void setSceneName(String name);

        void showSelectScene(List<CrimeItem> crimeItems);

        void showProspection(CrimeItem item);

        void showSetting();

    }
    interface Presenter {

        void showBaseInfo();
        void initPresentScene();

        void showSelectScene();

        void updatePresentScene(CrimeItem item);

        void showProspection();

        void initMobileTypeSetting(String manufacturer, String model);
    }
}
