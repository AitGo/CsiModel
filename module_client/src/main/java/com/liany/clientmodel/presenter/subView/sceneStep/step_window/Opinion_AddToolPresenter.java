package com.liany.clientmodel.presenter.subView.sceneStep.step_window;

import android.content.Context;
import android.content.Intent;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Opinion_AddToolContract;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.diagnose.ToolEntity;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.model.subView.sceneStep.step_window.Opinion_AddToolModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/4/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Opinion_AddToolPresenter implements Opinion_AddToolContract.Presenter {
    private Context mContext;
    private Opinion_AddToolContract.View addToolView;
    private Opinion_AddToolContract.Model addToolModel;

    private String type = "工具类目";
    private String source = "工具来源";

    public Opinion_AddToolPresenter(Context mContext, Opinion_AddToolContract.View addToolView) {
        this.mContext = mContext;
        this.addToolView = addToolView;
        this.addToolModel = new Opinion_AddToolModel(mContext);
    }

    @Override
    public void toolType() {
        String value = addToolView.getToolCategory();
        addToolModel.selectToolType(new callBack() {
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
                    addToolView.startSelectDictView(Constants.REQUEST_RADIO_DICT,type,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_RADIO_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(type)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    addToolView.setToolCategory(dict);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(source)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    addToolView.setToolSource(dict);
                }
            }
        }
    }

    @Override
    public void toolSource() {
        String value = addToolView.getToolSource();
        addToolModel.selectToolSource(new callBack() {
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
                    addToolView.startSelectDictView(Constants.REQUEST_RADIO_DICT,source,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void initViewDate(ToolEntity toolEntity) {
        if(toolEntity != null) {
            addToolView.setToolName(toolEntity.getName());
            addToolView.setToolCategory(toolEntity.getCategory());
            addToolView.setToolSource(toolEntity.getSource());
        }
    }

    @Override
    public void saveTool(ToolEntity entity,String crimeId,boolean isCheck) {
        if(isCheck) {
            if(!StringUtils.checkString(addToolView.getToolName())) {
                addToolView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addToolView.getToolCategory())) {
                addToolView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addToolView.getToolSource())) {
                addToolView.showMsgDialog("");
                return;
            }

        }
        if(entity == null) {
            entity = new ToolEntity();
        }
        if(!StringUtils.checkString(entity.getCrimeId())) {
            entity.setCrimeId(crimeId);
        }
        if(!StringUtils.checkString(entity.getId())) {
            entity.setId(StringUtils.getUUID());
        }
        entity.setName(addToolView.getToolName());
        entity.setCategory(addToolView.getToolCategory());
        entity.setSource(addToolView.getToolSource());
        addToolView.close(entity);
    }
}
