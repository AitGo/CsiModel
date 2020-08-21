package com.liany.clientmodel.contract.subView.sceneStep.step_window;

import android.content.Intent;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.WitnessEntity;

import java.io.File;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Witness_AddContract {
    interface Model {
        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void deletePhoto(Photo photo, callBack callBack);
    }

    interface View {

        void showTimerView();

        void setBirthdayDate(String date);

        String getBirthdayDate();

        String getWitnessName();

        String getWitnessNumber();

        String getWitnessAddress();

        String getWitnessSex();
        String getWitnessSexKey();

        void setWitnessSexKey(String sexKey);

        void setWitnessName(String witnessName);

        void setWitnessNumber(String witnessNumber);

        void setWitnessAddress(String witnessAddress);

        void setWitnessSex(String sex);

        void setSignImg(Photo photo);

        void closeEdit();

        boolean checkTimerShow();

        void close(WitnessEntity entity);

        void showMsgDialog(String s);

        String getCrimeId();

//        void readIDCardSuccess(IDCardResult result);

//        void readIDCardError(OCRError error);
    }

    interface Presenter {

        void getBirthday();

        void onActivityResult(int requestCode, int resultCode, Intent data, WitnessEntity entity);

        void saveWitness(WitnessEntity entity, String crimeId, boolean isCheck);

        void setValueToView(WitnessEntity entity);

    }
}
