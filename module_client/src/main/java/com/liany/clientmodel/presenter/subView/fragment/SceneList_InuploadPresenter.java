package com.liany.clientmodel.presenter.subView.fragment;

import android.content.Context;
import android.widget.Toast;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.fragment.SceneList_InuploadContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.CrimeListEntity;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.fragment.SceneList_InuploadModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneList_InuploadPresenter implements SceneList_InuploadContract.Presenter {
    private SceneList_InuploadContract.View view;
    private SceneList_InuploadContract.Model model;
    private Context mContext;

    public SceneList_InuploadPresenter(Context mContext, SceneList_InuploadContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new SceneList_InuploadModel(mContext);
    }

    @Override
    public void initData(int offset,boolean isRefresh,boolean isShowProgress) {
        if(isShowProgress) {
            view.showProgressDialog("正在加载中");
        }
        String userName = (String) SPUtils.getParam(mContext, Constants.sp_userName,"");
        model.getPageList(userName, "0", offset, new callBack() {
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
    public void clickItem(boolean isEdit, boolean isUpload, CrimeListEntity crimeListEntity) {
        if(isEdit || isUpload) {
            //修改
            if(crimeListEntity.getCrimeItem().getIsUpload() == null || !crimeListEntity.getCrimeItem().getIsUpload().equals("upload")) {
                view.setCrimeCheck(crimeListEntity);
            }else {
                Toast.makeText(mContext,"该数据已经上传，不能操作",Toast.LENGTH_SHORT).show();
            }
        }else {
            view.showCreateScene(crimeListEntity.getCrimeItem());
        }
    }

    @Override
    public void deleteScene(List<CrimeListEntity> crimeList) {
        ProgressUtils.showProgressDialog(mContext,"正在删除现场");
        List<CrimeItem> crimeItems = new ArrayList<>();
        for(CrimeListEntity entity : crimeList) {
            if(entity.isCheck()) {
                crimeItems.add(entity.getCrimeItem());
            }
        }
        model.deleteScene(crimeItems, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showShort("删除成功");
                    //修改界面
                    view.deleteCrime(crimeItems);
                }else {
                    ToastUtils.showLong("删除失败:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("删除失败:" + msg);
            }
        });
    }

    @Override
    public void uploadScene(List<CrimeListEntity> crimeList) {
        List<CrimeItem> crimeItems = new ArrayList<>();
        for(CrimeListEntity entity : crimeList) {
            crimeItems.add(entity.getCrimeItem());
        }
        model.uploadScene(crimeItems, new callBack() {
            @Override
            public void onSuccess(String date) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void checkCrime(List<CrimeListEntity> crimeList) {
        List<CrimeItem> crimeItems = new ArrayList<>();
        List<CrimeItem> errorDatas = new ArrayList<>();
        for(CrimeListEntity entity : crimeList) {
            if(entity.isCheck()) {
                if(entity.getCrimeItem().getGetLastData()) {
                    crimeItems.add(entity.getCrimeItem());
                }else {
                    errorDatas.add(entity.getCrimeItem());
                }
            }
        }
        if(crimeItems.size() <= 0) {
            //判断是否完整
            Toast.makeText(mContext,"没有选中数据或数据不完整，请检查后重新上传",Toast.LENGTH_SHORT).show();
            return;
        }
        if(errorDatas.size() > 0) {
            Toast.makeText(mContext,"有数据项不完整，请检查后重新上传",Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressUtils.showProgressDialog(mContext,"");
        model.uploadScene(crimeItems, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showShort("数据校验成功，正在上传");
                }else {
                    ToastUtils.showLong("上传错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("上传错误:" + msg);
            }
        });
    }

}
