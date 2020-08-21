package com.liany.clientmodel.presenter.subView;

import android.content.Context;

import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.SceneListComparisonContactsContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.SceneListComparisonContactsModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.view.subView.sceneStep.Comparison_PeopleActivity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListComparisonContactsPresenter implements SceneListComparisonContactsContract.Presenter {
    private SceneListComparisonContactsContract.View view;
    private SceneListComparisonContactsContract.Model model;
    private Context mContext;

    public SceneListComparisonContactsPresenter(Context mContext, SceneListComparisonContactsContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new SceneListComparisonContactsModel(mContext);
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
        view.startActivity(Comparison_PeopleActivity.class,crimeItem);
    }
}
