package com.liany.clientmodel.view.subView.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.CrimeListAdapter;
import com.liany.clientmodel.base.BaseFragment;
import com.liany.clientmodel.contract.subView.fragment.SceneList_UploadContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.CrimeListEntity;
import com.liany.clientmodel.presenter.subView.fragment.SceneList_UploadPresenter;
import com.liany.clientmodel.view.subView.CreateSceneActivity;
import com.liany.clientmodel.widget.CsiProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
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
public class SceneList_UploadFragment extends BaseFragment implements SceneList_UploadContract.View {
    RecyclerView rvScenelist;
    SmartRefreshLayout infoRefreshLayout;

    private SceneList_UploadContract.Presenter presenter;
    private CrimeListAdapter adapter;
    private List<CrimeListEntity> crimeList = new ArrayList<>();
    private View emptyView;
    private int offset = 0;
    private CsiProgressDialog mProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_upload;
    }

    @Override
    protected void initView(View inflate) {
        rvScenelist = inflate.findViewById(R.id.rv_scenelist);
        infoRefreshLayout = inflate.findViewById(R.id.info_refreshLayout);

        rvScenelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CrimeListAdapter(R.layout.item_adapter_crimelist, crimeList);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.clickItem(crimeList.get(position));
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
        rvScenelist.setAdapter(adapter);
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvScenelist.getParent(), false);
        adapter.setEmptyView(emptyView);
        crimeList.clear();
        presenter.initData(offset,false,true);
    }

    @Override
    protected void initData() {
        presenter = new SceneList_UploadPresenter(this.getContext(), this);
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
}
