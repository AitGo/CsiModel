package com.liany.csiclient.view.subView.sceneStep;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.adapter.KctAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.contract.subView.sceneStep.StationCollectionContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiclient.diagnose.KctMultiItem;
import com.liany.csiclient.presenter.subView.sceneStep.StationCollectionPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.widget.MyDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class StationCollectionActivity extends BaseAcitivity implements StationCollectionContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvKct;

    private StationCollectionContract.Presenter presenter;
    private CrimeItem crimeItem;
    private List<KCTBASESTATIONDATABean> kctLists = new ArrayList<>();
    private List<KctMultiItem> kctMultiItems = new ArrayList<>();
    private MyDialog myDialog;
    private View emptyView;
    private KctAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_stationcollection;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvKct = findViewById(R.id.rv_kct);

        ivTitleBack.setOnClickListener(this);

        tvTitle.setText("基站采集");
        LinearLayoutManager positionLayoutManager = new LinearLayoutManager(this);
        positionLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvKct.setLayoutManager(positionLayoutManager);
        adapter = new KctAdapter(kctMultiItems);
        rvKct.setAdapter(adapter);

        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvKct.getParent(), false);
        adapter.setEmptyView(emptyView);
        presenter.getKctData(crimeItem);

    }

    @Override
    protected void initData() {
        presenter = new StationCollectionPresenter(this,this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        if (view.getId() == R.id.iv_title_back) {
            finish();
        }
    }

    @Override
    public void updateList(List<KCTBASESTATIONDATABean> data) {
        kctMultiItems.clear();
        kctMultiItems.addAll(StringUtils.selectKctMultiItems(data));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showStartCollectDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要采集基站数据")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.startCollect(crimeItem);
                        myDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

}
