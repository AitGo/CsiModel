package com.liany.csiclient.contract.subView.sceneStep.step_window;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.ContactsEntity;
import com.liany.csiclient.diagnose.Photo;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Visit_AddContactsContract {
    interface Model {
        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void deletePhotoFile(Photo delPhoto, callBack callBack);

        void deletePhotoFileList(List<Photo> delPhotos, callBack callBack);

        int getChooseContactsDevice();
    }

    interface View {
        void setType(String type);
        void setName(String name);
        void setSex(String sex);
        void setId(String id);
        void setTel(String tel);
        void setAddress(String address);

        String getType();
        String getName();
        String getSex();
        String getSexKey();
        String getId();
        String getTel();
        String getAddress();

        void startFigerPrintDevice(Map<Integer, String> result, String filePath);

        void addFigerPhoto(Photo photo);
        void removeFigerPhoto(int position);

        void close(ContactsEntity entity);

        void showMsgDialog(String msg);

        void showProgress(String msg);

        void dismissProgress();

        void setPhotos(List<Photo> photos);
    }

    interface Presenter {
        void initViewDate(ContactsEntity contactsEntity);

        void saveContract(ContactsEntity contactsEntity, String crimeId, boolean isCheck, List<Photo> photos);

        void startFigerPrint(ContactsEntity contactsEntity);

        void onActivityResult(int requestCode, int resultCode, Intent data, ContactsEntity contactsEntity);

        void deleteFingerPhoto(List<Photo> photos, int i, String contactsId);
    }
}
