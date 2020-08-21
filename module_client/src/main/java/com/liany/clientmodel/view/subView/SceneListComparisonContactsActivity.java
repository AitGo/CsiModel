package com.liany.clientmodel.view.subView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.CrimeListNoChooseAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.contract.subView.SceneListComparisonContactsContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.presenter.subView.SceneListComparisonContactsPresenter;
import com.liany.clientmodel.utils.ClickUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListComparisonContactsActivity extends BaseAcitivity implements SceneListComparisonContactsContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvScenelist;

    private SceneListComparisonContactsContract.Presenter presenter;
    private List<CrimeItem> crimeItemList = new ArrayList<>();
    private CrimeListNoChooseAdapter adapter;
    private View emptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_comparison_finger_list;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvScenelist = findViewById(R.id.rv_scenelist);
        ivTitleBack.setOnClickListener(this);

        tvTitle.setText("事主排查列表");
        rvScenelist.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CrimeListNoChooseAdapter(R.layout.item_adapter_compair_crimelist, crimeItemList);
        rvScenelist.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.startActivity(crimeItemList.get(position));
            }
        });
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvScenelist.getParent(), false);
        adapter.setEmptyView(emptyView);
        crimeItemList.clear();
        presenter.initData();
    }

    @Override
    protected void initData() {
        presenter = new SceneListComparisonContactsPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View v) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        finish();
    }

    @Override
    public void setCrimeList(List<CrimeItem> crimeList) {
        this.crimeItemList.clear();
        this.crimeItemList.addAll(crimeList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startActivity(Class<?> activity, CrimeItem crimeItem) {
        Intent intent = new Intent(SceneListComparisonContactsActivity.this, activity);
        //修改现场
        Bundle bundle = new Bundle();
        bundle.putSerializable("crime", crimeItem);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
