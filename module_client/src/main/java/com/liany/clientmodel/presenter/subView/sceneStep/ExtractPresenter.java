package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.ExtractContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.model.subView.sceneStep.ExtractModel;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.Extract_AddActivity;

import java.util.Date;
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
public class ExtractPresenter implements ExtractContract.Presenter {
    private ExtractContract.View view;
    private ExtractContract.Model model;
    private Context mContext;

    public ExtractPresenter(Context mContext, ExtractContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new ExtractModel(mContext);
    }

    @Override
    public void addCommonExtract(CrimeItem item) {
        GoodEntity entity = new GoodEntity();
        entity.setRev1("0");
        entity.setCollectedMethod("原物提取");
        entity.setCollectedNum("1");
        entity.setCollectedDate(StringUtils.Date2String(new Date(),"yyyy-MM-dd"));
        entity.setCollectedName((String) SPUtils.getParam(mContext, Constants.sp_accessInspectors,""));
        entity.setCollectedIds((String) SPUtils.getParam(mContext, Constants.sp_accessInspectorsKey,""));
        entity.setRev2((String) SPUtils.getParam(mContext, Constants.sp_accessInspectorsId,""));
        view.startActivity(Extract_AddActivity.class,item,entity,-1);
    }

    @Override
    public void addLiveExtract(CrimeItem item) {
        GoodEntity entity = new GoodEntity();
        entity.setRev1("1");
        entity.setCollectedMethod("转移提取");
        entity.setCollectedNum("1");
        entity.setCollectedDate(StringUtils.Date2String(new Date(),"yyyy-MM-dd"));
        entity.setCollectedName((String) SPUtils.getParam(mContext, Constants.sp_accessInspectors,""));
        entity.setCollectedIds((String) SPUtils.getParam(mContext, Constants.sp_accessInspectorsKey,""));
        entity.setRev2((String) SPUtils.getParam(mContext, Constants.sp_accessInspectorsId,""));
        view.startActivity(Extract_AddActivity.class,item,entity,-1);
    }

    @Override
    public void initData(CrimeItem item, List<GoodEntity> commLists, List<GoodEntity> liveLists) {
        List<GoodEntity> goodEntities = item.getGoodEntities();
        for(GoodEntity entity : goodEntities) {
            if(StringUtils.checkString(entity.getRev1()) && entity.getRev1().equals("1")) {
                liveLists.add(entity);
            }else {
                commLists.add(entity);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == Constants.REQUEST_EXTRACT) {
                int position = data.getIntExtra("position", -1);
                GoodEntity entity = (GoodEntity) data.getSerializableExtra("extract");
                if(position == -1) {
                    //新增
                    view.addExtract(entity);
                }else {
                    //修改
                    view.updateExtract(entity,position);
                }
            }
        }
    }

    @Override
    public void deleteExtract(GoodEntity entity) {
        view.removeExtract(entity);
    }
}
