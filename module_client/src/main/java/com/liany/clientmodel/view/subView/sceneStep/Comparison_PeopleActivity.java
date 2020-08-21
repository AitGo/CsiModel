package com.liany.clientmodel.view.subView.sceneStep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.ComparisonContactsAdapter;
import com.liany.clientmodel.adapter.ComparisonEvidenceAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.contract.subView.sceneStep.Comparison_PeopleContract;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.presenter.subView.sceneStep.Comparison_PeoplePresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/4/8
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_PeopleActivity extends BaseAcitivity implements Comparison_PeopleContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvEvidence;
    ImageView ivTitleSearchWifi;
    RecyclerView rvContacts;

    private Comparison_PeopleContract.Presenter presenter;
    private CrimeItem crimeItem;
    private ComparisonEvidenceAdapter evidenceAdapter;
    private ComparisonContactsAdapter contactsAdapter;
    private View evidenceEmptyView,contactsEmptyView;
    private List<ComparePhoto> evidenceEntities = new ArrayList<>();
    private List<ContactsEntity> contactsEntities = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_comparison_people;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvEvidence = findViewById(R.id.rv_evidence);
        ivTitleSearchWifi = findViewById(R.id.iv_title_search_wifi);
        rvContacts = findViewById(R.id.rv_contacts);

        ivTitleBack.setOnClickListener(this);
        ivTitleSearchWifi.setOnClickListener(this);

        ivTitleSearchWifi.setVisibility(View.VISIBLE);
        tvTitle.setText("事主排查");

        rvEvidence.setLayoutManager(new LinearLayoutManager(this));
        evidenceAdapter = new ComparisonEvidenceAdapter(R.layout.item_adapter_comparison_evidence, evidenceEntities);
        rvEvidence.setAdapter(evidenceAdapter);
        evidenceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if(StringUtils.checkString(evidenceEntities.get(position).getPhotoPath())) {
                            Intent photo = new Intent(Comparison_PeopleActivity.this, PhotoViewActivity.class);
                            photo.putExtra("filePath", evidenceEntities.get(position).getServicePath());
                            startActivity(photo);
                        }else {
                            ToastUtils.showShort("暂无图片");
                        }
                    }
                });
            }
        });
        evidenceEmptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvEvidence.getParent(), false);
        evidenceAdapter.setEmptyView(evidenceEmptyView);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ComparisonContactsAdapter(R.layout.item_adapter_comparison_contacts,contactsEntities);
        rvContacts.setAdapter(contactsAdapter);
        contactsEmptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvContacts.getParent(), false);
        contactsAdapter.setEmptyView(contactsEmptyView);
        presenter.getComparisonResult(crimeItem);
    }

    @Override
    protected void initData() {
        presenter = new Comparison_PeoplePresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        contactsEntities.addAll(crimeItem.getReleatedPeopleItem());
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_search_wifi) {//刷新
            presenter.getComparisonResult(crimeItem);
        }
    }

    @Override
    public void updateEvidence(List<ComparePhoto> entitys) {
        evidenceEntities.clear();
        evidenceEntities.addAll(entitys);
        evidenceAdapter.notifyDataSetChanged();
    }
}
