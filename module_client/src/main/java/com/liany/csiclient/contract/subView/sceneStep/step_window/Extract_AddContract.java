package com.liany.csiclient.contract.subView.sceneStep.step_window;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.GoodEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.selectUser;

import java.io.File;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Extract_AddContract {
    interface Model {
        void selectGetPeople(String organId, callBack callBack);

        String getOrganId();

        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void deletePhoto(Photo photo, callBack callBack);

        void deletePhotoList(List<Photo> photos, String crimeId, callBack callBack);
    }

    interface View {
        String getCollectedName();
        String getCollectedId();

        void setCollectedName(String user);
        void setCollectedName(List<selectUser> users);

        String getMaterialName();
        void setMaterialName(String name);
        String getCollectedPosition();
        void setCollectedPosition(String position);
        String getCollectedMethod();
        void setCollectedMethod(String method);
        String getCollectedDate();
        void setCollectedDate(String date);
        String getCollectedNum();
        void setCollectedNum(String num);
        String getRemark();
        void setRemark(String remark);
        void setPhoto(Photo photo);
        void setScan(String result);
        String getScan();
        void showTimerView();

        void startSelectUserView(int selectType, String title, List<selectUser> users, String value);

        void showMsgDialog(String msg);

        void showDeleteDialog(int position);

        void updateExtractList(Photo photo);

        void takePhoto();

        void saveExtract(GoodEntity entity);

    }

    interface Presenter {
        void onActivityResult(int requestCode, int resultCode, Intent data, File file, String crimeId, String goodId);

        void saveExtract(CrimeItem crimeItem, GoodEntity entity, boolean isCheck);

        void getPeople();

        void deleteExtractPic(Photo photo, String crimeId);

        void deleteExtractPicList(List<Photo> photos, String crimeId);
    }
}
