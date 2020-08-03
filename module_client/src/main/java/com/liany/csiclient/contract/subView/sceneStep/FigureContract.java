package com.liany.csiclient.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Photo;

import java.io.File;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface FigureContract {
    interface Model {
        void deletePositionPic(Photo positionPhoto, callBack callBack);

        void deleteFlatPic(Photo flatPhoto, callBack callBack);

        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack);
        void uploadPic(File file, String photoId, String state, String parentId, String crimeId, String photoInfo, String rev2, callBack callBack);

        void deletePic(Photo photo, callBack callBack);

    }

    interface View {
        void showPositionDelDialog(int position);

        void showFlatDelDialog(int position);

        void setPositionListNotify(Photo positionEntity);

        void setFlatListNotify(Photo flatEntity);

        void saveFigure(CrimeItem crimeItem);

        void updateFlat(Photo flatPhoto);
        void updatePosition(Photo positionPhoto);

        void addDwg(Photo dwg);
        void reomveDwg(Photo dwg);

        void reomveFlat(int positon);

        String getPhotoId();

    }

    interface Presenter {
        void setUpdatePosition(int position);

        void onActivityResult(int requestCode, int resultCode, Intent data, CrimeItem crimeItem);

        void saveFigure(CrimeItem crimeItem);

        void deletePositionPic(Photo positionPhotoList);

        void deleteFlatPic(Photo flatPhotoList);

        void deleteDwgPic(Photo photo);

        void startDraw(CrimeItem item, String photoId);
    }
}
