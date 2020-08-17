package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.kc.criminaiinvest.bean.Entrances;
import com.kc.criminaiinvest.bean.ExecuteDraw;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.FigureContract;
import com.liany.csiclient.diagnose.ContactsEntity;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.sceneStep.FigureModel;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.FigureActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.Figure_LegendActivity;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class FigurePresenter implements FigureContract.Presenter {
    private Context mContext;
    private FigureContract.View figureView;
    private FigureContract.Model figureModel;
    private double lat;
    private double lon;
    private int updatePosition = -1;

    public FigurePresenter(Context mContext, FigureContract.View figureView) {
        this.mContext = mContext;
        this.figureView = figureView;
        this.figureModel = new FigureModel(mContext);
    }

    @Override
    public void setUpdatePosition(int position) {
        updatePosition = position;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, CrimeItem crimeItem) {
        String caseType = crimeItem.getCasetype() == null ? "" : crimeItem.getCasetype();
        String location = crimeItem.getLocation() == null ? "" : crimeItem.getLocation();
        if(StringUtils.checkString(location) && location.contains("(") && location.contains(")")) {
            int startIndex = location.indexOf("(");
            int endIndex = location.indexOf(")");
            String subStart = location.substring(0, startIndex);
            String subEnd = location.substring(endIndex + 1);
            location = subStart + subEnd;
        }
//        if(resultCode == RESULT_OK) {
            if(requestCode == Constants.RESULT_CODE_POSITION) {
                if(null != data) {
                    String fileName = data.getStringExtra(Constants.positionFilePathKey);
                    lat = data.getDoubleExtra("lat",0.0);
                    lon = data.getDoubleExtra("lon",0.0);
                    if(null != fileName) {
                        if(StringUtils.checkString(Constants.path_photoDir + File.separator + fileName)
                                && FileUtils.checkFileExists(Constants.path_photoDir + File.separator + fileName)) {
                            Photo positionEntity= new Photo();
                            positionEntity.setId(StringUtils.getUUID());
                            positionEntity.setPath(Constants.path_photoDir + File.separator + fileName);
                            positionEntity.setPhotoInfo("“" + StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd") + "” " + location + caseType + "现场方位示意图");
                            positionEntity.setUUID(StringUtils.md5HashCode32(positionEntity.getPath()));
                            File file = new File(positionEntity.getPath());
                            positionEntity.setFileName(file.getName());
                            positionEntity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                            positionEntity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                            positionEntity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                            positionEntity.setCrimeId(crimeItem.getId());
                            positionEntity.setParentId(crimeItem.getId());
                            positionEntity.setState(Constants.state_positionPic);

                            Intent intent = new Intent(mContext, Figure_LegendActivity.class);
                            intent.putExtra("entity",positionEntity);
                            intent.putExtra("crimeItem",crimeItem);
                            intent.putExtra("type",0);
                            ((FigureActivity)mContext).startActivityForResult(intent, Constants.REQUEST_LEGEND);
                        }else {
                            ToastUtils.showShort("图片保存错误：文件未找到");
                        }
                    }
                }
            } else if(requestCode == Constants.REQUEST_FLAT) {
                int isSaved=data.getIntExtra("isSaved",2);
                if(isSaved==1) {
                    // 新增記事資料到資料庫
                    Photo flatItem = (Photo) data.getSerializableExtra("com.android.csiapp.Databases.PhotoItem");
                    flatItem.setPhotoInfo("“" + StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd") + "” " + location + caseType + "现场平面示意图");
                    flatItem.setId(StringUtils.getUUID());
                    flatItem.setUUID(StringUtils.md5HashCode32(flatItem.getPath()));
                    File file = new File(flatItem.getPath());
                    flatItem.setFileName(file.getName());
                    flatItem.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                    flatItem.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                    flatItem.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                    flatItem.setCrimeId(crimeItem.getId());
                    flatItem.setParentId(crimeItem.getId());
                    flatItem.setState(Constants.state_flat);

                    Photo dwg = new Photo();
                    dwg.setId(StringUtils.getUUID());
                    dwg.setPath(flatItem.getPath().replace(".png",".dwg"));
                    dwg.setCrimeId(crimeItem.getId());
                    dwg.setParentId(crimeItem.getId());
                    dwg.setState(Constants.state_flat_dwg);

                    Intent intent = new Intent(mContext, Figure_LegendActivity.class);
                    intent.putExtra("entity",flatItem);
                    intent.putExtra("crimeItem",crimeItem);
                    intent.putExtra("type",1);
                    intent.putExtra("dwg",dwg);
                    ((FigureActivity)mContext).startActivityForResult(intent, Constants.REQUEST_LEGEND);

    //                figureView.setFlatListNotify(flatItem);
    //                //上传图片
    //                uploadFlatPic(flatItem);
                }
            } else if(requestCode == Constants.EVENT_EDIT_FLAT){
                //修改
                figureView.setFlatListNotify(null);
                int isSaved=data.getIntExtra("isSaved",2);
                if(isSaved==1) {
                    // 新增記事資料到資料庫
                    Photo flatItem = (Photo) data.getSerializableExtra("com.android.csiapp.Databases.PhotoItem");
                    File file = new File(flatItem.getPath());
                    //查找修改的第几项
    //                figureView.removeFlat(updatePosition);
                    flatItem.setPhotoInfo("“" + StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd") + "” " + location + caseType + "现场平面示意图");
                    flatItem.setId(StringUtils.getUUID());
                    flatItem.setUUID(StringUtils.md5HashCode32(flatItem.getPath()));
                    flatItem.setFileName(file.getName());
                    flatItem.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                    flatItem.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                    flatItem.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                    flatItem.setCrimeId(crimeItem.getId());
                    flatItem.setParentId(crimeItem.getId());
                    flatItem.setState(Constants.state_flat);

                    Photo dwg = new Photo();
                    dwg.setId(StringUtils.getUUID());
                    dwg.setPath(flatItem.getPath().replace(".png",".dwg"));
                    dwg.setCrimeId(crimeItem.getId());
                    dwg.setParentId(crimeItem.getId());
                    dwg.setState(Constants.state_flat_dwg);

                    Intent intent = new Intent(mContext, Figure_LegendActivity.class);
                    intent.putExtra("entity", flatItem);
                    intent.putExtra("crimeItem", crimeItem);
                    intent.putExtra("dwg",dwg);
                    intent.putExtra("type", 2);
                    ((FigureActivity) mContext).startActivityForResult(intent, Constants.REQUEST_LEGEND);
                }
            } else if(requestCode == ExecuteDraw.drawRequestCode) {
                //笔录内容
                String path = ExecuteDraw.getPlaneDiagramPath(figureView.getPhotoId());
                LogUtils.e("houseId path:" + path);
                if(StringUtils.checkString(path)) {
                    Photo flatItem = new Photo();
                    String record = ExecuteDraw.getDrawRecord(figureView.getPhotoId());
                    if(StringUtils.checkString(record) && !"笔录内容为空".equals(record)) {
//                        figureView.setOverView(record);
                        flatItem.setRev2(record);
                        LogUtils.e("houseId record:" + record);
                    }else {

                    }
                    flatItem.setId(figureView.getPhotoId());
                    flatItem.setPhotoInfo("“" + StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd") + "” " + location + caseType + "现场平面示意图");
                    flatItem.setPath(path);
                    flatItem.setUUID(StringUtils.md5HashCode32(flatItem.getPath()));
                    File file = new File(flatItem.getPath());
                    flatItem.setFileName(file.getName());
                    flatItem.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                    flatItem.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                    flatItem.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                    flatItem.setCrimeId(crimeItem.getId());
                    flatItem.setParentId(crimeItem.getId());
                    flatItem.setState(Constants.state_flat);
                    figureModel.uploadPic(new File(flatItem.getPath()),
                            flatItem.getId(),
                            flatItem.getState(),
                            flatItem.getParentId(),
                            flatItem.getCrimeId(),
                            flatItem.getPhotoInfo(),
                            flatItem.getRev2(),
                            new callBack() {
                                @Override
                                public void onSuccess(String date) {
                                    ProgressUtils.dismissProgressDialog();
                                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                    if(response.getCode() == 200) {
                                        flatItem.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                        figureView.setFlatListNotify(flatItem);
                                    }else {
                                        ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                    }
                                }

                                @Override
                                public void onFail(String msg) {
                                    ProgressUtils.dismissProgressDialog();
                                }
                            });
                }else {
                    ToastUtils.showShort("平面图路径为空");
                }
            } else if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_LEGEND) {
                int type = data.getIntExtra("type",0);
                if(type == 0) {
                    ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                    Photo positionEntity = (Photo) data.getSerializableExtra("entity");
                    figureModel.uploadPic(new File(positionEntity.getPath()),
                            positionEntity.getId(),positionEntity.getState(),
                            positionEntity.getCrimeId(),
                            positionEntity.getCrimeId(),
                            positionEntity.getPhotoInfo(),
                            "",
                            new callBack() {
                        @Override
                        public void onSuccess(String date) {
                            ProgressUtils.dismissProgressDialog();
                            Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                            if(response.getCode() == 200) {
                                positionEntity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                figureView.setPositionListNotify(positionEntity);
                            }else {
                                ToastUtils.showLong("上传图片错误:" + response.getMsg());
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            ProgressUtils.dismissProgressDialog();
                            ToastUtils.showLong("上传图片错误:" + msg);
                        }
                    });
                }else if(type == 1) {
                    ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                    Photo flatItem = (Photo) data.getSerializableExtra("entity");
                    Photo dwg = (Photo) data.getSerializableExtra("dwg");
                    figureModel.uploadPic(new File(flatItem.getPath()),
                            flatItem.getId(),flatItem.getState(),
                            flatItem.getCrimeId(),
                            flatItem.getCrimeId(),
                            new callBack() {
                        @Override
                        public void onSuccess(String date) {
                            ProgressUtils.dismissProgressDialog();
                            Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                            if(response.getCode() == 200) {
                                flatItem.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                figureView.setFlatListNotify(flatItem);
                            }else {
                                ToastUtils.showLong("上传图片错误:" + response.getMsg());
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            ProgressUtils.dismissProgressDialog();
                        }
                    });
                    figureModel.uploadPic(new File(dwg.getPath()),
                            dwg.getId(),
                            dwg.getState(),
                            dwg.getCrimeId(),
                            dwg.getCrimeId(),
                            new callBack() {
                        @Override
                        public void onSuccess(String date) {
                            ProgressUtils.dismissProgressDialog();
                            Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                            if(response.getCode() == 200) {
                                dwg.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                figureView.addDwg(dwg);
                            }else {
                                ToastUtils.showLong("上传图片错误:" + response.getMsg());
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            ProgressUtils.dismissProgressDialog();
                        }
                    });
                }else if(type == 2) {
                    //删除修改的position
                    ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                    Photo flatItem = (Photo) data.getSerializableExtra("entity");
                    Photo dwg = (Photo) data.getSerializableExtra("dwg");
                    figureModel.uploadPic(new File(flatItem.getPath()),
                            flatItem.getId(),
                            flatItem.getState(),
                            flatItem.getCrimeId(),
                            flatItem.getCrimeId(),
                            new callBack() {
                        @Override
                        public void onSuccess(String date) {
                            ProgressUtils.dismissProgressDialog();
                            Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                            if(response.getCode() == 200) {
                                figureView.reomveFlat(updatePosition);
                                flatItem.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                figureView.setFlatListNotify(flatItem);
                            }else {
                                ToastUtils.showLong("上传图片错误:" + response.getMsg());
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            ProgressUtils.dismissProgressDialog();
                        }
                    });
                    figureModel.uploadPic(new File(dwg.getPath()),
                            dwg.getId(),
                            dwg.getState(),
                            dwg.getCrimeId(),
                            dwg.getCrimeId(),
                            new callBack() {
                        @Override
                        public void onSuccess(String date) {
                            ProgressUtils.dismissProgressDialog();
                            Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                            if(response.getCode() == 200) {
                                figureView.reomveDwg(dwg);
                                dwg.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                figureView.addDwg(dwg);
                            }else {
                                ToastUtils.showLong("上传图片错误:" + response.getMsg());
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            ProgressUtils.dismissProgressDialog();
                        }
                    });
                }
        }

//        }
    }

    @Override
    public void saveFigure(CrimeItem crimeItem) {
        if(crimeItem.getGpsLon() == 0) {
            crimeItem.setGpsLon(lon);
        }
        if(crimeItem.getGpsLat() == 0) {
            crimeItem.setGpsLat(lat);
        }
        figureView.saveFigure(crimeItem);
    }

    @Override
    public void deletePositionPic(Photo positionPhoto) {
        figureModel.deletePic(positionPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    figureView.updatePosition(positionPhoto);
                }else {
                    ToastUtils.showLong("删除图片错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("删除图片错误:" + msg);
            }
        });
    }

    @Override
    public void deleteFlatPic(Photo flatPhoto) {
        figureModel.deletePic(flatPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    figureView.updateFlat(flatPhoto);
                }else {
                    ToastUtils.showLong("删除图片错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("删除图片错误:" + msg);
            }
        });
    }

    @Override
    public void deleteDwgPic(Photo photo) {
        figureModel.deletePic(photo, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                }else {
                    ToastUtils.showLong("删除图片错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("删除图片错误:" + msg);
            }
        });
    }

    @Override
    public void startDraw(CrimeItem crimeItem, String photoId) {
        String caseType = crimeItem.getCasetype() == null ? "" : crimeItem.getCasetype();
        String location = crimeItem.getLocation() == null ? "" : crimeItem.getLocation();
        if(StringUtils.checkString(location) && location.contains("(") && location.contains(")")) {
            int startIndex = location.indexOf("(");
            int endIndex = location.indexOf(")");
            String subStart = location.substring(0, startIndex);
            String subEnd = location.substring(endIndex + 1);
            location = subStart + subEnd;
        }
        String caseVictims = "";
        List<ContactsEntity> releatedPeopleItem = crimeItem.getReleatedPeopleItem();
        for(ContactsEntity entity : releatedPeopleItem) {
            if(entity.getType().equals("受害人")) {
                caseVictims = entity.getName();
            }
        }
        Entrances entrances = new Entrances();
        entrances.setHosueId(photoId);
        entrances.setCaseAddress(location);
        entrances.setCaseName(caseType);
        entrances.setCaseTime(StringUtils.long2String(crimeItem.getOccurred_start_time()));
        entrances.setCaseVictims(caseVictims);
        entrances.setDrawTime(StringUtils.long2String(System.currentTimeMillis()));
        entrances.setDrawUser((String) SPUtils.getParam(mContext, Constants.sp_userName,""));
        entrances.setDrawUnit((String) SPUtils.getParam(mContext, Constants.sp_unitName,""));
        entrances.setJpgTitle("“" + StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd") + "” " + location + caseType + "现场平面示意图");
//        entrances.setCaseVictimsIdentity("123456");
//        entrances.setAccount((String) SPUtils.getParam(mContext,Constants.sp_loginName,""));
        ExecuteDraw.entranceDraw(((FigureActivity)mContext),entrances);
    }
}
