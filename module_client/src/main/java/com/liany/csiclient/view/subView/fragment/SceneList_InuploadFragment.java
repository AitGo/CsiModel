package com.liany.csiclient.view.subView.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.CrimeListAdapter;
import com.liany.csiclient.base.BaseFragment;
import com.liany.csiclient.contract.subView.fragment.SceneList_InuploadContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.CrimeListEntity;
import com.liany.csiclient.presenter.subView.fragment.SceneList_InuploadPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.view.subView.CreateSceneActivity;
import com.liany.csiclient.view.subView.SceneListFragmentActivity;
import com.liany.csiclient.widget.CsiProgressDialog;
import com.liany.csiclient.widget.MyDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneList_InuploadFragment extends BaseFragment implements SceneList_InuploadContract.View, View.OnClickListener {

    RecyclerView rvScenelist;
    Button listDelete;
    Button listUpload;
    SmartRefreshLayout infoRefreshLayout;

    private SceneList_InuploadPresenter presenter;
    private List<CrimeListEntity> crimeList = new ArrayList<>();
    private int offset = 0;
    private CrimeListAdapter adapter;
    private View emptyView;
    private boolean isEdit = false;
    private boolean isUpload = false;
    private CsiProgressDialog mProgressDialog;
    private MyDialog deleteDialog,uploadDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inupload;
    }

    @Override
    protected void initView(View inflate) {
        rvScenelist = inflate.findViewById(R.id.rv_scenelist);
        listDelete = inflate.findViewById(R.id.list_delete);
        listUpload = inflate.findViewById(R.id.list_upload);
        infoRefreshLayout = inflate.findViewById(R.id.info_refreshLayout);

        listDelete.setOnClickListener(this);
        listUpload.setOnClickListener(this);

        rvScenelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CrimeListAdapter(R.layout.item_adapter_crimelist, crimeList);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.clickItem(isEdit,isUpload,crimeList.get(position));
            }
        });

        infoRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                offset = 0;
                crimeList.clear();
                presenter.initData(offset,true,false);
            }
        });

        infoRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.initData(offset,false,false);
            }
        });
        infoRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        rvScenelist.setAdapter(adapter);
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvScenelist.getParent(), false);
        adapter.setEmptyView(emptyView);
        crimeList.clear();
        presenter.initData(offset,false,true);
    }

    @Override
    protected void initData() {
        presenter = new SceneList_InuploadPresenter(this.getContext(), this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) { // 不在最前端显示 相当于调用了onPause();
            //更新数据
//            presenter.initData(offset,false,true);
        }
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.list_delete) {
            showDeleteDialog();
        } else if (id == R.id.list_upload) {
            showUploadDialog();
        }
    }

    @Override
    public void edit() {
        ((SceneListFragmentActivity) getActivity()).edit();
        listDelete.setVisibility(View.VISIBLE);

        isEdit = true;
        adapter.setEditVisibility(isEdit);
        for (CrimeListEntity entity : crimeList) {
            entity.setCheck(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void upload() {
        ((SceneListFragmentActivity) getActivity()).edit();
        listUpload.setVisibility(View.VISIBLE);

        isUpload = true;
        adapter.setEditVisibility(isUpload);
        for (CrimeListEntity entity : crimeList) {
            entity.setCheck(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void done(boolean isRemove) {
        ((SceneListFragmentActivity) getActivity()).done();
        listDelete.setVisibility(View.GONE);
        listUpload.setVisibility(View.GONE);
        isEdit = false;
        isUpload = false;
        adapter.setEditVisibility(isEdit);
        if (isRemove) {
            offset = 0;
            presenter.initData(offset,false,true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectAll() {
        boolean isCkeck = true;
        for (CrimeListEntity entity : crimeList) {
            if (entity.getCrimeItem().getIsUpload() == null || !entity.getCrimeItem().getIsUpload().equals("upload")) {
                if (!entity.isCheck()) {
                    isCkeck = true;
                    break;
                } else {
                    isCkeck = false;
                }
            }
        }
        for (CrimeListEntity entity : crimeList) {
            if (entity.getCrimeItem().getIsUpload() == null || !entity.getCrimeItem().getIsUpload().equals("upload")) {
                entity.setCheck(isCkeck);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void setCrimeList(List<CrimeItem> crimeItemList) {
        List<CrimeListEntity> listEntityList = new ArrayList<>();
        for (int i = 0; i < crimeItemList.size(); i++) {
            CrimeListEntity entity = new CrimeListEntity();
            entity.setCrimeItem(crimeItemList.get(i));
            listEntityList.add(entity);
        }
        this.crimeList.addAll(listEntityList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setListLoadMoreEnd() {
        infoRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void setListLoadMoreComplete() {
        infoRefreshLayout.finishLoadMore(true);
    }

    @Override
    public void setListLoadMoreFail() {
        infoRefreshLayout.finishLoadMore(false);
    }

    @Override
    public void setRefreshComplete() {
        infoRefreshLayout.finishRefresh();
    }

    @Override
    public void setRefreshFail() {
        infoRefreshLayout.finishRefresh(false);
    }

    @Override
    public void showProgressDialog(String msg) {
        if(mProgressDialog == null) {
            mProgressDialog = new CsiProgressDialog(this.getContext(), msg);
        }else {
            mProgressDialog.setMessage(msg);
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showCreateScene(CrimeItem data) {
        Intent intent = new Intent(this.getContext(), CreateSceneActivity.class);
        intent.putExtra("crime",data);
        intent.putExtra("isCreate","0");
        startActivity(intent);
    }

    @Override
    public void setCrimeCheck(CrimeListEntity entity) {
        entity.setCheck(!entity.isCheck());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCrime(List<CrimeItem> crimeList) {
        for(CrimeItem crimeItem : crimeList) {
            Iterator<CrimeListEntity> iterator = this.crimeList.iterator();
            while (iterator.hasNext()) {
                CrimeListEntity entity = iterator.next();
                if (entity.getCrimeItem().getId().equals(crimeItem.getId())) {
                    iterator.remove();
                }
            }
            adapter.notifyDataSetChanged();
        }
        done(false);
    }

    public void showDeleteDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(getActivity());
        deleteDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要删除数据？")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.deleteScene(crimeList);
                        deleteDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDialog.dismiss();
                    }
                })
                .create();
        deleteDialog.show();
    }

    public void showUploadDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(getActivity());
        uploadDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要上传数据？")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.checkCrime(crimeList);
                        uploadDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uploadDialog.dismiss();
                    }
                })
                .create();
        uploadDialog.show();
    }
}
