package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.Comparison_FootContract;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.sceneStep.Comparison_FootModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/28
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_FootPresenter implements Comparison_FootContract.Presenter {
    private Comparison_FootContract.View view;
    private Comparison_FootContract.Model model;
    private Context mContext;

    public Comparison_FootPresenter(Context mContext, Comparison_FootContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new Comparison_FootModel(mContext);
    }

    @Override
    public void getComparisonResult(CrimeItem crimeItem) {
        ProgressUtils.showProgressDialog(mContext,"正在获取比对结果");
        model.getComparisonData(crimeItem,"3", new callBack() {
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
        model.getAllComparisonData("3", new callBack() {
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
