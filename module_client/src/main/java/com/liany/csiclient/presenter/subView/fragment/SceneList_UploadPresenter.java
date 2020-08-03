package com.liany.csiclient.presenter.subView.fragment;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.fragment.SceneList_UploadContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.CrimeListEntity;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.model.subView.fragment.SceneList_UploadModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneList_UploadPresenter implements SceneList_UploadContract.Presenter {
    private SceneList_UploadContract.View view;
    private SceneList_UploadContract.Model model;
    private Context mContext;

    public SceneList_UploadPresenter(Context mContext, SceneList_UploadContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new SceneList_UploadModel(mContext);
    }

    @Override
    public void initData(int offset,boolean isRefresh,boolean isShowProgress) {
        if(isShowProgress) {
            view.showProgressDialog("正在加载中");
        }
        String userName = (String) SPUtils.getParam(mContext, Constants.sp_userName,"");
        model.getPageList(userName, "1", offset, new callBack() {
            @Override
            public void onSuccess(String date) {
                view.dismissProgressDialog();
                Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(date, CrimeItem.class);
                if(response.getCode() == 200) {
                    if(response.getData().size() < Constants.value_pageSize) {
                        //最后一页
                        view.setOffset(offset + 1);
                        view.setCrimeList(response.getData());
                        if(isRefresh) {
                            view.setRefreshComplete();
                        }else {
                            view.setListLoadMoreEnd();
                        }
                    }else {
                        //设置界面,offset++
                        view.setOffset(offset + 1);
                        view.setCrimeList(response.getData());
                        if(isRefresh) {
                            view.setRefreshComplete();
                        }else {
                            view.setListLoadMoreComplete();
                        }
                    }
                }else {
                    if(isRefresh) {
                        view.setRefreshFail();
                    }else {
                        view.setListLoadMoreFail();
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                view.dismissProgressDialog();
                ToastUtils.showLong("获取列表错误：" + msg);
                if(isRefresh) {
                    view.setRefreshFail();
                }else {
                    view.setListLoadMoreFail();
                }
            }
        });
    }

    @Override
    public void clickItem(CrimeListEntity crimeListEntity) {
        model.getSceneInfo(crimeListEntity.getCrimeItem().getId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<CrimeItem> response = GsonUtils.fromJsonObject(date, CrimeItem.class);
                if(response.getCode() == 200) {
                    view.showCreateScene(response.getData());
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
