package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.Comparison_PeopleContract;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.sceneStep.Comparison_PeopleModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/8
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_PeoplePresenter implements Comparison_PeopleContract.Presenter {
    private Comparison_PeopleContract.View view;
    private Comparison_PeopleContract.Model model;
    private Context mContext;

    public Comparison_PeoplePresenter(Context mContext, Comparison_PeopleContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new Comparison_PeopleModel(mContext);
    }

    @Override
    public void getComparisonResult(CrimeItem crimeItem) {
        ProgressUtils.showProgressDialog(mContext,"正在获取比对结果");
        model.startComparisonData(crimeItem,"2", new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(date, ComparePhoto.class);
                if(response.getCode() == 200) {
                    ProgressUtils.dismissProgressDialog();
                    view.updateEvidence(response.getData());
                }else {
                    try{
                        Thread.sleep(3000);
                        model.getComparisonData(crimeItem,"2", new callBack() {
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
                    }catch (Exception e ){

                    }
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
