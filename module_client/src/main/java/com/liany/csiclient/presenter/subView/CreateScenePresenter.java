package com.liany.csiclient.presenter.subView;

import android.content.Context;
import android.content.Intent;

import com.liany.csiclient.R;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.CreateSceneContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.GoodEntity;
import com.liany.csiclient.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.SceneWifiInfo;
import com.liany.csiclient.diagnose.WitnessEntity;
import com.liany.csiclient.model.subView.CreateSceneModel;
import com.liany.csiclient.utils.CheckCrime;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.BaseInfoActivity;
import com.liany.csiclient.view.subView.sceneStep.EvidenceActivity;
import com.liany.csiclient.view.subView.sceneStep.ExtractActivity;
import com.liany.csiclient.view.subView.sceneStep.FigureActivity;
import com.liany.csiclient.view.subView.sceneStep.OpinionActivity;
import com.liany.csiclient.view.subView.sceneStep.PhotoActivity;
import com.liany.csiclient.view.subView.sceneStep.ProspectingActivity;
import com.liany.csiclient.view.subView.sceneStep.SituationActivity;
import com.liany.csiclient.view.subView.sceneStep.StationCollectionActivity;
import com.liany.csiclient.view.subView.sceneStep.VisitActivity;
import com.liany.csiclient.view.subView.sceneStep.WifiActivity;
import com.liany.csiclient.view.subView.sceneStep.WitnessActivity;

import java.util.List;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CreateScenePresenter implements CreateSceneContract.Presenter {
    private CreateSceneContract.View view;
    private CreateSceneContract.Model model;
    private Context mContext;

    private CrimeItem crimeItem;

    public CreateScenePresenter(Context mContext, CreateSceneContract.View loginView, CrimeItem crimeItem) {
        this.mContext = mContext;
        this.view = loginView;
        model = new CreateSceneModel(mContext);
        this.crimeItem = crimeItem;
    }

    @Override
    public void setTitle(CrimeItem crimeItem) {
        if(crimeItem == null && !crimeItem.getId().equals("")) {
            view.setTitle(mContext.getString(R.string.update_scene_title));
        }else {
            view.setTitle(mContext.getString(R.string.create_scene_title));
        }
    }

    @Override
    public void initBadge() {
        crimeItem = view.getCrime();
        if(crimeItem.getId() != null) {
            view.showBaseInfoBadge(CheckCrime.checkBaseInfo(crimeItem));
            view.showProspectingBadge(CheckCrime.checkProspecting(crimeItem));
            view.showVisitBadge(CheckCrime.checkVisit(crimeItem.getReleatedPeopleItem(),crimeItem.getLostItem()));
            view.showFigureBadge(CheckCrime.checkFigure(crimeItem.getPositionItem(),crimeItem.getFlatItem()));
            view.showPhotoBadge(CheckCrime.checkPhoto(crimeItem.getPositionPhotoItem(),crimeItem.getOverviewPhotoItem(),crimeItem.getImportantPhotoItem()));
            view.showEvidenceBadge(CheckCrime.checkEvidence(crimeItem.getEvidenceItem(),crimeItem.getMonitoringPhotoItem(),crimeItem.getCameraPhotoItem()));
            view.showSituationBadge(CheckCrime.checkSituation(crimeItem));
            view.showOpinionBadge(CheckCrime.checkOpinionOne(crimeItem));
            view.showWitnessBadge(CheckCrime.checkWitness(crimeItem.getWitnessItem()));
            view.showWifiBadge(CheckCrime.checkWifi(crimeItem.getWifiInfos()));
            view.showExtractBadge(CheckCrime.checkExtract(crimeItem.getGoodEntities()));
            view.showCollectionBadge(CheckCrime.checkKct(crimeItem.getKctbasestationdataBeans()));
        }else {
            view.showBaseInfoBadge(CheckCrime.checkBaseInfo(crimeItem));
            view.showProspectingBadge(CheckCrime.checkProspecting(crimeItem));
            view.showSituationBadge(CheckCrime.checkSituation(crimeItem));
            view.showOpinionBadge(CheckCrime.checkOpinionOne(crimeItem));
        }
    }

    @Override
    public String getBackMsg(String msg) {
        crimeItem = view.getCrime();
        return CheckCrime.needToCheckInformation(crimeItem,msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.REQUEST_CREATE_SCENE_BASEINFO:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showBaseInfoBadge(CheckCrime.checkBaseInfo(crimeItem));
                    break;
                case Constants.REQUEST_CREATE_SCENE_PROSPECTING:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showProspectingBadge(CheckCrime.checkProspecting(crimeItem));
                    break;
                case Constants.REQUEST_CREATE_SCENE_VISIT:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
                    break;
                case Constants.REQUEST_CREATE_SCENE_FIGURE:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showFigureBadge(CheckCrime.checkFigure(positionEntityList,flatEntityList));
                    break;
                case Constants.REQUEST_CREATE_SCENE_PHOTO:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
                    break;
                case Constants.REQUEST_CREATE_SCENE_EVIDENCE:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showEvidenceBadge(CheckCrime.checkEvidence(evidenceList,monitoringList,cameraList));
                    break;
                case Constants.REQUEST_CREATE_SCENE_SITUATION:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showSituationBadge(CheckCrime.checkSituation(crimeItem));
                    break;
                case Constants.REQUEST_CREATE_SCENE_OPINION:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showOpinionBadge(CheckCrime.checkOpinionOne(crimeItem));
                    break;
                case Constants.REQUEST_CREATE_SCENE_WITNESS:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showWitnessBadge(CheckCrime.checkWitness(witnessList));
                    break;
                case Constants.REQUEST_CREATE_SCENE_WIFI:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
                    break;
                case Constants.REQUEST_CREATE_SCENE_EXTRACT:
                    crimeItem = (CrimeItem) data.getSerializableExtra("crime");
                    view.updateCrime(crimeItem);
//                    view.showExtractBadge(CheckCrime.checkExtract(extractList));
                    break;
            }
        }
    }

    @Override
    public void setKCTBaseStationList(List<KCTBASESTATIONDATABean> beans) {
        crimeItem.setKctbasestationdataBeans(beans);
        view.updateCrime(crimeItem);
    }

    @Override
    public void sceneStep_baseInfo(CrimeItem item) {
        view.startActivity(BaseInfoActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_BASEINFO);
    }

    @Override
    public void sceneStep_prospecting(CrimeItem crimeItem) {
        view.startProspectingActivity( ProspectingActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_PROSPECTING);
    }

    @Override
    public void sceneStep_visit(CrimeItem crimeItem) {
        view.startActivity(VisitActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_VISIT);
    }

    @Override
    public void sceneStep_figure(CrimeItem crimeItem) {
        view.startActivity(FigureActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_FIGURE);
    }

    @Override
    public void sceneStep_photo(CrimeItem crimeItem) {
        view.startActivity(PhotoActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_PHOTO);
    }

    @Override
    public void sceneStep_evidence(CrimeItem crimeItem) {
//        view.startActivity(EvidenceActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_EVIDENCE);
        view.startActivity(EvidenceActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_EVIDENCE);
    }

    @Override
    public void sceneStep_situation(CrimeItem crimeItem) {
        view.startActivity(SituationActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_SITUATION);
    }

    @Override
    public void sceneStep_opinion(CrimeItem crimeItem) {
        view.startActivity(OpinionActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_OPINION);
    }

    @Override
    public void sceneStep_witness(CrimeItem crimeItem) {
        view.startActivity(WitnessActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_WITNESS);
    }

    @Override
    public void sceneStep_wifi(CrimeItem crimeItem) {
        view.startActivity(WifiActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_WIFI);
    }

    @Override
    public void sceneStep_extract(CrimeItem crimeItem) {
        view.startActivity(ExtractActivity.class,crimeItem,Constants.REQUEST_CREATE_SCENE_EXTRACT);
    }

    @Override
    public void sceneStep_stationCollection(CrimeItem crimeItem) {
        view.startActivity(StationCollectionActivity.class, crimeItem,Constants.REQUEST_CREATE_STATION);
    }

    @Override
    public void sceneStep_video() {
        view.startVideo();
    }

    @Override
    public void saveCrime(CrimeItem crimeItem) {
        crimeItem.setGetLastData(CheckCrime.checkInformation(crimeItem));
        crimeItem.setUpdateTime(System.currentTimeMillis());
        String s = GsonUtils.gsonString(crimeItem);
        LogUtils.e(s);
        ProgressUtils.showProgressDialog(mContext,"正在保存数据");
        model.saveCrime(GsonUtils.gsonString(crimeItem),new callBack() {
                    @Override
                    public void onSuccess(String date) {
                        ProgressUtils.dismissProgressDialog();
                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                        if(response.getCode() == 200) {
                            ToastUtils.showLong(response.getData());
                            view.finishActivity();
                        }else {
                            ToastUtils.showLong("保存现场错误:" + response.getMsg());
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        ProgressUtils.dismissProgressDialog();
                        ToastUtils.showLong("保存现场错误:" + msg);
                    }
                });
    }

    @Override
    public void initCrimeItem(CrimeItem crimeItem) {
        if(crimeItem.getId() != null) {
            ProgressUtils.showProgressDialog(mContext,"正在加载数据");
            model.getSceneInfo(crimeItem.getId(), new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<CrimeItem> response = GsonUtils.fromJsonObject(date, CrimeItem.class);
                    if(response.getCode() == 200) {
                        view.setCrimeItem(response.getData());
                        if(crimeItem.getWifiInfos() == null || crimeItem.getWifiInfos().size() == 0) {
                            view.startCollectWifi();
                        }
                    }
                }

                @Override
                public void onFail(String msg) {
                    ProgressUtils.dismissProgressDialog();
                    ToastUtils.showLong("加载详细信息错误：" + msg);
                }
            });
        }else {
            String id = StringUtils.getUUID();
            crimeItem.setId(id);
            view.setCrimeItem(crimeItem);
        }
    }

    @Override
    public void deletePic() {
//        deletePositionPhoto(positionEntityList);
//        deleteFlatPic(flatEntityList);
//        deletePositionPic(positionList);
//        deleteLikePic(likeList);
//        deleteImportantPic(importantList);
//        deleteEvidencePic(evidenceList);
//        deleteMonitoringPic(monitoringList);
//        deleteCameraPic(cameraList);
//        deleteWitnessPic(witnessList);
//        deleteExtractPic(extractList);
    }

    @Override
    public void setWIfiList(List<SceneWifiInfo> scanResultsCopy) {
        crimeItem.setWifiInfos(scanResultsCopy);
    }

    private void deleteExtractPic(List<GoodEntity> extractList) {
        for(GoodEntity item : extractList) {
            //删除本地文件
            model.deleteExtract(item);
        }
    }

    private void deleteWitnessPic(List<WitnessEntity> witnessList) {
        for(WitnessEntity item : witnessList) {
            //删除本地文件
            model.deleteWitness(item);
        }
    }

    private void deleteCameraPic(List<Photo> cameraList) {
        for(Photo item : cameraList) {
            //删除本地文件
            model.deleteCamera(item);
        }
    }

    private void deleteMonitoringPic(List<Photo> monitoringList) {
        for(Photo item : monitoringList) {
            //删除本地文件
            model.deleteMonitoring(item);
        }
    }

    private void deleteEvidencePic(List<EvidenceEntity> evidenceList) {
        for(EvidenceEntity item : evidenceList) {
            //删除本地文件
            model.deleteEvidence(item);
        }
    }

    private void deleteImportantPic(List<Photo> importantList) {
        for(Photo item : importantList) {
            //删除本地文件
            model.deleteImportant(item);
        }
    }

    private void deleteLikePic(List<Photo> likeList) {
        for(Photo item : likeList) {
            //删除本地文件
            model.deletelike(item);
        }
    }

    private void deletePositionPic(List<Photo> positionList) {
        for(Photo item : positionList) {
            //删除本地文件
            model.deletePositionPic(item);
        }
    }

    private void deleteFlatPic(List<Photo> flatEntityList) {
        for(Photo item : flatEntityList) {
            //删除本地文件
            model.deleteFlat(item);
        }
    }

    private void deletePositionPhoto(List<Photo> positionEntityList) {
        for(Photo item : positionEntityList) {
            //删除本地文件
            model.deletePositionPhoto(item);
        }
    }

}
