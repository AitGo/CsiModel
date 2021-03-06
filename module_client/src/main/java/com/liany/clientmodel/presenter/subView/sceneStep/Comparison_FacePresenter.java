package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.Comparison_FaceContract;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.sceneStep.Comparison_FaceModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/7/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_FacePresenter implements Comparison_FaceContract.Presenter {
    private Comparison_FaceContract.View view;
    private Comparison_FaceContract.Model model;
    private Context mContext;

    public Comparison_FacePresenter(Context mContext, Comparison_FaceContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new Comparison_FaceModel(mContext);
    }

    @Override
    public void getComparisonResult(CrimeItem crimeItem) {
        ProgressUtils.showProgressDialog(mContext,"正在获取比对结果");
        model.getComparisonData(crimeItem,"4", new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(date, ComparePhoto.class);
                if(response.getCode() == 200) {
                    view.updateEvidence(response.getData());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("获取比对结果错误:" + msg);
            }
        });
    }

    @Override
    public void getCompareData() {
        ProgressUtils.showProgressDialog(mContext,"正在获取比对结果");
        model.getAllComparisonData("4", new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(date, ComparePhoto.class);
                if(response.getCode() == 200) {
                    view.updateEvidence(response.getData());
                }else {
                    ToastUtils.showLong("服务器获取比对结果错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("服务器获取比对结果错误:" + msg);
            }
        });
    }
}
