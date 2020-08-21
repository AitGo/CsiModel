package com.liany.clientmodel.presenter.subView.sceneStep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.liany.clientmodel.adapter.AddToolAdapter;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.OpinionContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.diagnose.ToolEntity;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.model.subView.sceneStep.OpinionModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.util.ArrayList;
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
public class OpinionPresenter implements OpinionContract.Presenter {
    private Context mContext;
    private OpinionContract.View opinionView;
    private OpinionContract.Model opinionModel;

    private String peopleNumber = "作案人数";
    private String crimeMeans = "作案手段";
    private String crimeCharacter = "案件性质";
    private String crimeEntrance = "作案入口";
    private String crimeTiming = "作案时机";
    private String selectObject = "选择对象";
    private String crimeExport = "作案出口";
    private String crimeFeature = "作案特点";
    private String intrusiveMethod = "侵入方式";
    private String selectLocation = "选择处所";
    private String crimePurpose = "作案动机";

    public OpinionPresenter(Context mContext, OpinionContract.View opinionView) {
        this.mContext = mContext;
        this.opinionView = opinionView;
        this.opinionModel = new OpinionModel(mContext);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_RADIO_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(peopleNumber)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    opinionView.setPeopleNumber(dict);
                }
            } else if (requestCode == Constants.REQUEST_CHECK_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeEntrance)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
                    opinionView.setCrimeEntrance(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeExport)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
                    opinionView.setCrimeExport(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeTiming)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
                    opinionView.setCrimeTiming(dicts);
                }

            } else if (requestCode == Constants.REQUEST_EXPAND_RADIO_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeCharacter)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_EXPAND_RADIO_DICT);
                    opinionView.setCrimeCharacter(dict);
                }

            } else if (requestCode == Constants.REQUEST_EXPAND_CHECK_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimePurpose)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setCrimePurpose(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeMeans)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setCrimeMeans(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(selectObject)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setSelectObject(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeFeature)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setCrimeFeature(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(intrusiveMethod)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setIntrusiveMethod(dicts);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(selectLocation)) {
                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                    opinionView.setSelectLocation(dicts);
                }
            } else if(requestCode == Constants.REQUEST_VISIT_ADD_TOOL) {
                ToolEntity toolEntity = (ToolEntity) data.getSerializableExtra("toolEntity");
                int toolPosition = data.getIntExtra("position",-1);
                opinionView.addTool(toolEntity,toolPosition);
            }
        }
    }

    @Override
    public void peopleNumber() {
        String value = opinionView.getPeopleNumber();
        opinionModel.selectPeopleNumber(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(value != null && !value.equals("")) {
                        for(sysDict dict : response.getData()) {
                            if(dict.getDictValue().equals(value)) {
                                selectList.add(response.getData().indexOf(dict));
                            }
                        }
                    }
                    opinionView.startRadioSelectDictView(Constants.REQUEST_RADIO_DICT,peopleNumber,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeMeans() {
        String value = opinionView.getCrimeMeans();
        String[] values = value.split(",");
        opinionModel.selectCrimeMeans(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
//                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,crimeMeans,response.getData(),selectList);
                    opinionView.startExpand3CheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,crimeMeans,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeCharacter(CrimeItem crimeItem) {
        String value = opinionView.getCrimeCharacter();
        String[] values = value.split(",");
        opinionModel.selectCrimeCharacter(crimeItem,new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandRadioSelectDictView(Constants.REQUEST_EXPAND_RADIO_DICT,crimeCharacter,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeEntrance() {
        String value = opinionView.getCrimeEntrance();
        opinionModel.selectCrimeEntrance(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(value != null && !value.equals("")) {
                        String[] values = value.split(",");
                        for(sysDict dict : response.getData()) {
                            for(int i = 0; i < values.length; i++) {
                                if(dict.getDictValue().equals(values[i])) {
                                    dict.setRemark("true");
                                }
                            }
                        }
                    }
                    opinionView.startCheckSelectDictView(Constants.REQUEST_CHECK_DICT,crimeEntrance,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeTiming() {
        String value = opinionView.getCrimeTiming();
        String[] values = value.split(",");
        opinionModel.selectCrimeTiming(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startCheckSelectDictView(Constants.REQUEST_CHECK_DICT,crimeTiming,response.getData(),selectList);
//                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,crimeTiming,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void selectObject() {
        String value = opinionView.getSelectObject();
        String[] values = value.split(",");
        opinionModel.selectObject(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,selectObject,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeExport() {
        String value = opinionView.getCrimeExport();
        opinionModel.selectCrimeEntrance(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(value != null && !value.equals("")) {
                        String[] values = value.split(",");
                        for(sysDict dict : response.getData()) {
                            for(int i = 0; i < values.length; i++) {
                                if(dict.getDictValue().equals(values[i])) {
                                    dict.setRemark("true");
                                }
                            }
                        }
                    }
                    opinionView.startCheckSelectDictView(Constants.REQUEST_CHECK_DICT,crimeExport,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimeFeature() {
        String value = opinionView.getCrimeFeature();
        String[] values = value.split(",");
        opinionModel.selectCrimeFeature(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,crimeFeature,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void intrusiveMethod() {
        String value = opinionView.getIntrusiveMethod();
        String[] values = value.split(",");
        opinionModel.selectIntrusiveMethod(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,intrusiveMethod,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void selectLocation(CrimeItem crimeItem) {
        String value = opinionView.getSelectLocation();
        String[] values = value.split(",");
        opinionModel.selectLocation(crimeItem,new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,selectLocation,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void crimePurpose() {
        String value = opinionView.getCrimePurpose();
        String[] values = value.split(",");
        opinionModel.selectCrimePurpose(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    for(sysDict dict : response.getData()) {
                        for(int i = 0; i < values.length; i++) {
                            if(dict.getDictValue().equals(values[i])) {
                                dict.setRemark("true");
                            }
                        }
                    }
                    opinionView.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,crimePurpose,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void initValue(CrimeItem crimeItem) {

        if(crimeItem != null) {
            opinionView.setPeopleNumber(crimeItem.getCrimePeopleNumber());
            opinionView.setCrimeMeans(crimeItem.getCrimeMeans());
            opinionView.setCrimeCharacter(crimeItem.getCrimeCharacter());
            opinionView.setCrimeEntrance(crimeItem.getCrimeEntrance());
            opinionView.setCrimeTiming(crimeItem.getCrimeTiming());
            opinionView.setSelectObject(crimeItem.getSelectObject());
            opinionView.setCrimeExport(crimeItem.getCrimeExport());
            opinionView.setPeopleFeature(crimeItem.getCrimePeopleFeature());
            opinionView.setCrimeFeature(crimeItem.getCrimeFeature());
            opinionView.setIntrusiveMethod(crimeItem.getIntrusiveMethod());
            opinionView.setSelectLocation(crimeItem.getSelectLocation());
            opinionView.setCrimePurpose(crimeItem.getCrimePurpose());
        }
    }

    @Override
    public void saveOpinion(CrimeItem crimeItem, List<ToolEntity> toolEntities) {
        if(crimeItem == null) {
            crimeItem = new CrimeItem();
        }
        crimeItem.setCrimePeopleNumber(opinionView.getPeopleNumber());
        crimeItem.setCrimeMeans(opinionView.getCrimeMeans());
        crimeItem.setCrimeCharacter(opinionView.getCrimeCharacter());
        crimeItem.setCrimeEntrance(opinionView.getCrimeEntrance());
        crimeItem.setCrimeTiming(opinionView.getCrimeTiming());
        crimeItem.setSelectObject(opinionView.getSelectObject());
        crimeItem.setCrimeExport(opinionView.getCrimeExport());
        crimeItem.setCrimePeopleFeature(opinionView.getPeopleFeature());
        crimeItem.setCrimeFeature(opinionView.getCrimeFeature());
        crimeItem.setIntrusiveMethod(opinionView.getIntrusiveMethod());
        crimeItem.setSelectLocation(opinionView.getSelectLocation());
        crimeItem.setCrimePurpose(opinionView.getCrimePurpose());
        crimeItem.setCrimeToolItem(toolEntities);
        opinionView.saveOpinion(crimeItem);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getOpinionSearchResult(CrimeItem crimeItem) {
        if(!StringUtils.checkString(crimeItem.getCrimeMeansKey())) {
            ToastUtils.showLong("作案手段不能为空");
            return;
        }
        if(!StringUtils.checkString(crimeItem.getCrimeCharacterKey())) {
            ToastUtils.showLong("案件性质不能为空");
            return;
        }
//        //访问接口，获取数据
//        opinionView.showProgress("正在加载中");
//        Item2Scene item2Scene = new Item2Scene(mContext);
//        CrimeScene.SCENEANALYSISSUGGESTIONBean analysisSuggestion = item2Scene.getAnalysisSuggestion(crimeItem);
//        String gsonString = GsonUtils.gsonString(analysisSuggestion);
//        NetWorkUtils.getSameCaseInfo(mContext, Constants.GetSameCaseInfo, gsonString, new NetWorkUtils.Callback() {
//            @Override
//            public void onNext(String result) {
//                Response<String> response = GsonUtils.fromJsonObject(result,String.class);
//                if(response.getCode() == 0) {
//                    opinionView.showMsgDialog(response.getData());
//                }else {
//                    ToastUtils.showLong("查询同类案件错误: " + response.getMsg());
//                }
//                opinionView.dismissProgress();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                ToastUtils.showLong("查询同类案件错误: " + e.getMessage());
//                opinionView.dismissProgress();
//            }
//        });
//        opinionView.showMsgDialog();
    }

    @Override
    public void onAddToolItemClick(List<ToolEntity> toolEntityList, int position) {
        opinionView.startAddToolActivity(toolEntityList.get(position),position);
    }

    @Override
    public void onAddToolItemLongClick(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter) {
        opinionView.showToolDeleteDialog(toolEntityList,position,addToolAdapter);
    }

    @Override
    public void deleteTool(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter) {
        toolEntityList.remove(position);
        addToolAdapter.notifyDataSetChanged();
    }
}
