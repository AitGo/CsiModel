package com.liany.clientmodel.contract.subView.fragment;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.CrimeListEntity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface SceneList_UploadContract {
    interface Model {
        void getPageList(String userName, String s, int offset, callBack callBack);

        void getSceneInfo(String crimeId, callBack callBack);
    }

    interface View {
        void setOffset(int offset);

        void setCrimeList(List<CrimeItem> crimeItemList);

        void setListLoadMoreEnd();

        void setListLoadMoreComplete();

        void setListLoadMoreFail();

        void setRefreshComplete();

        void setRefreshFail();

        void showProgressDialog(String message);

        void dismissProgressDialog();

        void showCreateScene(CrimeItem data);
    }

    interface Presenter {
        void initData(int offset, boolean isRefresh, boolean isShowProgress);

        void clickItem(CrimeListEntity crimeListEntity);
    }
}
