package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.StationCollectionContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.sceneStep.StationCollectionModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/7
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class StationCollectionPresenter implements StationCollectionContract.Presenter {
    private StationCollectionContract.View view;
    private StationCollectionContract.Model model;
    private Context mContext;

    public StationCollectionPresenter(Context mContext, StationCollectionContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new StationCollectionModel(mContext);
    }

    @Override
    public void getKctData(CrimeItem item) {
        ProgressUtils.showProgressDialog(mContext,"正在加载数据");
        model.getKctData(item, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<List<KCTBASESTATIONDATABean>> response = GsonUtils.fromJsonArray(date, KCTBASESTATIONDATABean.class);
                if(response.getCode() == 200) {
                    List<KCTBASESTATIONDATABean> data = response.getData();
                    if(data.size() > 0) {
                        //更新界面
                        view.updateList(response.getData());
                    }
//                    else {
//                        view.showStartCollectDialog();
//                    }
                }else {
                    ToastUtils.showLong("" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("获取基站数据错误:" + msg);
            }
        });
    }

    @Override
    public void startCollect(CrimeItem item) {
        model.startCollect(item, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showLong(response.getData());
                }else {
                    ToastUtils.showLong(response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("采集基站错误:" + msg);
            }
        });
    }
}
