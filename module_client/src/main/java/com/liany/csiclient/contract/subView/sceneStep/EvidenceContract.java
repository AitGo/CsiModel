package com.liany.csiclient.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.Photo;

import java.io.File;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface EvidenceContract {
    interface Model {

        void deleteEvidenceFingerPic(EvidenceEntity evidenceEntity);
        void deleteEvidenceFootPic(EvidenceEntity evidenceEntity);
        void deleteEvidenceOtherPic(EvidenceEntity evidenceEntity);

        void deleteMonitoringPic(Photo monitoringPhoto);

        void deleteCameraPic(Photo cameraPhoto);

        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void uploadPics(List<File> file, List<String> photoIds, String state, String parentId, String crimeId, callBack callBack);

        void deletePic(Photo photo, callBack callBack);

        void startCompareEvidence(CrimeItem crimeItem, callBack callBack);

        void startCompareFootEvidence(CrimeItem crimeItem, callBack callBack);

        void deleteEvidence(String evidenceId, String crimeId, callBack callBack);

        void getCompareResult(String crimeId, String state, callBack callBack);

        void sendCompare(String evidenceId, String type, callBack callBack);

        void deleteEvidenceFacePic(EvidenceEntity evidenceEntity);
    }

    interface View {

        void takePhoto(int code);

        void addFingerEvidencePic(EvidenceEntity entity, int position);
        void addFootEvidencePic(EvidenceEntity entity, int position);
        void addOtherEvidencePic(EvidenceEntity entity, int position);

        void addMonitoringPic(Photo entity);

        void addMonitoringPics(List<Photo> entitys);

        void addCameraPic(Photo entity);

        void addCameraPics(List<Photo> entitys);

        void showEvidenceFingerDel(int position);
        void showEvidenceFootDel(int position);
        void showEvidenceFaceDel(final int position);
        void showEvidenceOtherDel(int position);

        void showMonitoringDel(int position);

        void showCameraDel(int position);

        void startFingerAddPage(EvidenceEntity entity, int position);
        void startFootAddPage(EvidenceEntity entity, int position);
        void startFaceAddPage(EvidenceEntity entity, int position);
        void startOtherAddPage(EvidenceEntity entity, int position);

        void saveEvidence();

        void updateEvidenceFingerList(EvidenceEntity entity);
        void updateEvidenceFootList(EvidenceEntity entity);
        void updateEvidenceOtherList(EvidenceEntity entity);
        void updateMonitoringList(Photo entity);
        void updateCameraList(Photo entity);

        void showCompareFingerDialog(List<EvidenceEntity> evidenceEntityList);

        void addDetail(Photo photo);
        void delDetail(String path);

        void updateFinger(List<ComparePhoto> data);

        void updateEvidenceFaceList(EvidenceEntity evidenceEntity);

        void addFaceEvidencePic(EvidenceEntity entity, int position);

        void startAlarm();

        void clearCache();
    }

    interface Presenter {

        void addPosition(int code);

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

        //        void onActivityResult(int requestCode, int resultCode, Intent data, CrimeItem crimeItem);
        void onActivityResult(int requestCode, int resultCode, Intent data, final File file, CrimeItem crimeItem);

        void deleteEvidenceFingerPic(EvidenceEntity evidenceEntityList);
        void deleteEvidenceFootPic(EvidenceEntity evidenceEntityList);
        void deleteEvidenceOtherPic(EvidenceEntity evidenceEntityList);

        void deleteMonitoringPic(Photo monitoringPhotoList);

        void deleteCameraPic(Photo cameraPhotoList);

        void compareFinger(CrimeItem crimeItem);

        void compareFoot(CrimeItem crimeItem);

        void deleteEvidence(EvidenceEntity evidenceEntity, int type);

        void getCompareResult(CrimeItem item);

        void sendCompare(EvidenceEntity evidenceEntity, int type);
    }
}
