package com.liany.clientmodel.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Photo;

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
public interface PhotoContract {
    interface Model {

        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);

        void uploadPics(List<File> file, List<String> photoIds, String state, String parentId, String crimeId, callBack callBack);

        void deletePic(Photo pic, callBack callBack);

        void deleteLikePic(Photo overViewPhoto);

        void deleteImportantPic(Photo importantPhoto);

        void deleteDetailPic(Photo photo);
    }

    interface View {
        void takePhoto(int requestCode);

        void addPositionPic(Photo position);

        void addPositionPics(List<Photo> positions);

        void addLikePic(Photo like);

        void addLikePics(List<Photo> likes);

        void addImportantPic(Photo important);

        void addImportantPics(List<Photo> importants);

        void addDetail(Photo photo);

        void addDetails(List<Photo> photos);

        void showPositionDelDialog(int position);

        void showLikeDelDialog(int position);

        void showImportantDelDialog(int position);

        void showDetailDelDialog(int position);

        void savePhoto();

        List<Photo> savePositionPicDes(List<Photo> positionPicList);
        void updatePositionList(Photo positionPic);
        void updateLikeList(Photo overViewPhoto);

        void updateImportantList(Photo importantPhoto);

        void updateDetailList(Photo photo);

        void showOrientationDialog(int code);

        void updatePositionListOnDes(Photo positionPic);

        void updateOverviewListOnDes(Photo uploadOverViewPhoto);

        void deleteDetail(String photoPath);

        void clearCache();
    }

    interface Presenter {

        void addPosition(int requestCode);

        //        void onActivityResult(int requestCode, int resultCode, Intent data,CrimeItem crimeItem);
        void onActivityResult(int requestCode, int resultCode, Intent data, final File outputImagepath, CrimeItem crimeItem);

        void deletePositionPic(Photo positionPicList);

        void deleteLikePic(Photo overViewPhotoList);

        void deleteImportantPic(Photo importantPhotoList);

        void deleteDetailPic(Photo photo);

        void setPositionDes(String message);

        void setLikeDes(String message);
    }
}
