package com.liany.csiclient.presenter.subView;

import android.content.Context;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.SceneListComparisonEvidenceFootContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.SceneListComparisonEvidenceFootModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.Comparison_FootActivity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListComparisonEvidenceFootPresenter implements SceneListComparisonEvidenceFootContract.Presenter {
    private SceneListComparisonEvidenceFootContract.View view;
    private SceneListComparisonEvidenceFootContract.Model model;
    private Context mContext;

    public SceneListComparisonEvidenceFootPresenter(Context mContext, SceneListComparisonEvidenceFootContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new SceneListComparisonEvidenceFootModel(mContext);
    }

    @Override
    public void initData() {
        ProgressUtils.showProgressDialog(mContext,"正在加载中");
        model.getCrimeList(new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(date, CrimeItem.class);
                if(response.getCode() == 200) {
                    view.setCrimeList(response.getData());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("获取列表错误：" + msg);
            }
        });
    }

    @Override
    public void startActivity(CrimeItem crimeItem) {
        view.startActivity(Comparison_FootActivity.class,crimeItem);
    }
}
