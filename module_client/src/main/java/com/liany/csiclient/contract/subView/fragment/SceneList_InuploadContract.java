package com.liany.csiclient.contract.subView.fragment;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.CrimeListEntity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface SceneList_InuploadContract {
    interface Model {
        void getPageList(String userName, String type, int offset, callBack callBack);

        void deleteScene(List<CrimeItem> crimeItems, callBack callBack);

        void uploadScene(List<CrimeItem> crimeItems, callBack callBack);
    }

    interface View {
        void edit();

        void upload();

        void done(boolean isRemove);

        void selectAll();

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

        void setCrimeCheck(CrimeListEntity crimeListEntity);

        void deleteCrime(List<CrimeItem> crimeList);
    }

    interface Presenter {
        void initData(int offset, boolean isRefresh, boolean isShowProgress);

        void clickItem(boolean isEdit, boolean isUpload, CrimeListEntity crimeListEntity);

        void deleteScene(List<CrimeListEntity> crimeList);

        void uploadScene(List<CrimeListEntity> crimeList);

        void checkCrime(List<CrimeListEntity> crimeList);
    }
}
