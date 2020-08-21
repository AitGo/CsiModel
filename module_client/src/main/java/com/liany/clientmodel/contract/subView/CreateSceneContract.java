package com.liany.clientmodel.contract.subView;

import android.content.Intent;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.diagnose.KCTBASESTATIONDATABean;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.SceneWifiInfo;
import com.liany.clientmodel.diagnose.WitnessEntity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface CreateSceneContract {
    interface Model {
        void saveCrime(String crimeItem, callBack callBack);

        void deleteExtract(GoodEntity item);

        void deleteWitness(WitnessEntity item);

        void deleteCamera(Photo item);

        void deleteMonitoring(Photo item);

        void deleteEvidence(EvidenceEntity item);

        void deleteImportant(Photo item);

        void deletelike(Photo item);

        void deletePositionPic(Photo item);

        void deleteFlat(Photo item);

        void deletePositionPhoto(Photo item);

        void getSceneInfo(String crimeId, callBack callBack);

    }
    interface View {
        void setTitle(String title);

        void startActivity(Class<?> activity, CrimeItem crimeItem, int requestCode);

        void startProspectingActivity(Class<?> activity, CrimeItem crimeItem, int requestCode);

        void updateCrime(CrimeItem crimeItem);

        CrimeItem getCrime();
        void showBackDialog(CrimeItem crimeItem);

        void showSaveDialog();
        void showBaseInfoBadge(String value);
        void showProspectingBadge(String value);
        void showVisitBadge(String value);
        void showFigureBadge(String value);
        void showPhotoBadge(String value);
        void showEvidenceBadge(String value);
        void showSituationBadge(String value);
        void showOpinionBadge(String value);
        void showWitnessBadge(String value);
        void showWifiBadge(String value);
        void showExtractBadge(String value);

        void showCollectionBadge(String value);
        void closeProspectingBadge();
        void closeVisitBadge();
        void closeFigureBadge();
        void closePhotoBadge();
        void closeEvidenceBadge();
        void closeSituationBadge();
        void closeOpinionBadge();

        void closeWitnessBadge();
        void showLoading();

        void closeLoading();

        void finishActivity();

        void setCrimeItem(CrimeItem item);

        void startCollectWifi();

        void startVideo();

    }

    interface Presenter {
        void setTitle(CrimeItem crimeItem);
        void initBadge();
        String getBackMsg(String msg);
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
        void setKCTBaseStationList(List<KCTBASESTATIONDATABean> beans);

        void sceneStep_baseInfo(CrimeItem item);
        void sceneStep_prospecting(CrimeItem crimeItem);
        void sceneStep_visit(CrimeItem crimeItem);
        void sceneStep_figure(CrimeItem crimeItem);
        void sceneStep_photo(CrimeItem crimeItem);
        void sceneStep_evidence(CrimeItem crimeItem);
        void sceneStep_situation(CrimeItem crimeItem);
        void sceneStep_opinion(CrimeItem crimeItem);
        void sceneStep_witness(CrimeItem crimeItem);
        void sceneStep_wifi(CrimeItem crimeItem);
        void sceneStep_extract(CrimeItem crimeItem);

        void sceneStep_stationCollection(CrimeItem crimeItem);

        void saveCrime(CrimeItem crimeItem);

        void initCrimeItem(CrimeItem crimeItem);

        void deletePic();

        void setWIfiList(List<SceneWifiInfo> scanResultsCopy);

        void sceneStep_video();

    }
}
