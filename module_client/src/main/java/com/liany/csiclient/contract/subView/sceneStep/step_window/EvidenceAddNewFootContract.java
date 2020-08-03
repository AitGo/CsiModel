package com.liany.csiclient.contract.subView.sceneStep.step_window;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;

import java.io.File;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface EvidenceAddNewFootContract {
    interface Model {
        void selectHandEvidence(callBack callBack);
        void selectFootEvidence(callBack callBack);
        void selectToolEvidence(callBack callBack);
        void selectHandMethod(callBack callBack);
        void selectFootMethod(callBack callBack);
        void selectToolMethod(callBack callBack);
        void selectInfer(callBack callBack);
        void selectGetPeople(String organId, callBack callBack);

        String getOrganId();

        int getChooseDevice();

        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void deletePic(Photo photo, callBack callBack);

        void uploadEvidence(EvidenceEntity evidenceEntity, callBack callBack);
    }

    interface View {

        void takePhoto();

        void setPhoto(Photo photo);

        void setEvidenceTextLabel(String label);

        String getEvidenceTextLabel();

        String getEvidenceTime();

        void setEvidenceTime(String date);

        String getEvidenceType();
        void setEvidence(String evidence);
        void setEvidence(sysDict dict);
        String getOtherEvidence();
        void setOtherEvidence(String evidence);
        String getEvidence();
        void setEvidenceName(String evidenceName);
        String getEvidenceName();
        void setLegacySite(String legacySite);
        String getLegacySite();
        void setBasiceFeature(String basiceFeature);
        String getBasiceFeature();
        void setInfer(String infer);
        void setInfer(sysDict dict);
        String getInfer();
        void setMethod(String method);
        void setMethod(sysDict dict);
        String getMethod();
        void setTime(String time);
        String getTime();
        void setPeople(String people);
        void setPeople(List<selectUser> users);
        String getPeople();

        String getPhotoFile();

        void closeEdit();

        void close(EvidenceEntity entity);

        void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);

        void startSelectUserView(int selectType, String title, List<selectUser> users, String value);

        void showMsgDialog(String s);

        void startFlashAir();

        void goPhotoAlbum();
    }

    interface Presenter {

        void initValue(EvidenceEntity entity, int position);

        void checkPermission();

        void onActivityResult(int requestCode, int resultCode, Intent data, File file, String crimeId, EvidenceEntity entity);

        void getEvidenceTime();

        void deleteEvidencePhoto(EvidenceEntity entity, String photoPath);

        void saveEvidence(EvidenceEntity entity, String crimeId, boolean isCheck);

        void evidence(String type);
        void method(String type);
        void infer();
        void getPeople();

        void copyPhoto(String flashAirPath, String crimeId, EvidenceEntity evidenceEntity);
    }
}
