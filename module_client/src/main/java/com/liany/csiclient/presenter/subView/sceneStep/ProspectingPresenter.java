package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.ProspectingContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.model.subView.sceneStep.ProspectingModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ProspectingPresenter implements ProspectingContract.Presenter {
    private ProspectingContract.View view;
    private ProspectingContract.Model model;
    private Context mContext;
    private String casetype = "案件类别";
    private String selectObject = "选择对象";

    public ProspectingPresenter(Context mContext, ProspectingContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new ProspectingModel(mContext);
    }

    @Override
    public void initValue(CrimeItem item) {
        if(item != null) {
            view.setCaseType(item.getCasetype());
            view.setQhdx(item.getSelectObject());
            view.setLocation(item.getLocation());
            view.setAccessLocation(item.getAccessLocation());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_RADIO_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(casetype)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    view.setCaseType(dict);
                }
            } else if(requestCode == Constants.REQUEST_CHECK_DICT) {
//                if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeEntrance)) {
//                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
//                    view.setCrimeEntrance(dicts);
//                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeExport)) {
//                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
//                    view.setCrimeExport(dicts);
//                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(crimeMotivation)) {
//                    List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_CHECK_DICT);
//                    view.setCrimeMotivation(dicts);
//                }
            } else if(requestCode == Constants.REQUEST_CHECK_USER) {

            } else if(requestCode == Constants.REQUEST_RADIO_USER) {

            } else if (requestCode == Constants.REQUEST_EXPAND_CHECK_DICT) {
                List<sysDict> dicts = (List<sysDict>) data.getSerializableExtra(Constants.RESULT_EXPAND_CHECK_DICT);
                view.setQhdx(dicts);
            }
        }
    }

    @Override
    public void createCrime(CrimeItem item) {
        ProgressUtils.showProgressDialog(mContext,"正在创建现场");
        model.saveCrime(item, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    view.showCreateView();
                }else {
                    ToastUtils.showLong("创建现场错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("创建现场错误:" + msg);
            }
        });
    }

    @Override
    public void checkDate(CrimeItem item) {
        if(!StringUtils.checkString(item.getCasetype())) {
            view.showMsgDialog();
            return;
        }
//        if(!StringUtils.checkString(item.getSelectObject())) {
//            view.showMsgDialog();
//            return;
//        }
        if(!StringUtils.checkString(view.getAccessLocation())) {
            view.showMsgDialog();
            return;
        }
        if(!StringUtils.checkString(view.getLocation())) {
            view.showMsgDialog();
            return;
        }
        item.setLocation(view.getLocation());
        item.setAccessLocation(view.getAccessLocation());
        view.showCreateDialog();
    }

    @Override
    public void casetype() {
        String type = view.getCaseType();
        model.getCaseType(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(type != null && !type.equals("")) {
                        for(sysDict dict : response.getData()) {
                            if(dict.getDictValue().equals(type)) {
                                selectList.add(response.getData().indexOf(dict));
                            }
                        }
                    }
                    view.startRadioSelectDictView(Constants.REQUEST_RADIO_DICT,casetype,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void qhdx() {
        String value = view.getQhdx();
        String[] values = value.split(",");
        model.selectObject(new callBack() {
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
                    view.startExpandCheckSelectDictView(Constants.REQUEST_EXPAND_CHECK_DICT,selectObject,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
