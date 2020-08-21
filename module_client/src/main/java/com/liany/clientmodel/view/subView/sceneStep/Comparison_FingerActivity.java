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
import com.liany.clientmodel.adapter.CompareFingerAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.Comparison_FigureContract;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.presenter.subView.sceneStep.Comparison_FigurePresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.Compare_Finger_addActivity;
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
public class Comparison_FingerActivity extends BaseAcitivity implements Comparison_FigureContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvFigure;
    ImageView ivTitleSearchWifi;
    ImageView ivTitleAdd1;

    private CrimeItem crimeItem;
    //    private ComparisonFigureAdapter adapter;
    private CompareFingerAdapter adapter;
    private List<ComparePhoto> comparePhotos = new ArrayList<>();
    private View emptyView;
    private Comparison_FigureContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_comparison_finger;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvFigure = findViewById(R.id.rv_figure);
        ivTitleSearchWifi = findViewById(R.id.iv_title_search_wifi);
        ivTitleAdd1 = findViewById(R.id.iv_title_add1);

        ivTitleBack.setOnClickListener(this);
        ivTitleSearchWifi.setOnClickListener(this);
        ivTitleAdd1.setOnClickListener(this);

        tvTitle.setText("指纹比对");
        ivTitleSearchWifi.setVisibility(View.VISIBLE);
        ivTitleAdd1.setVisibility(View.VISIBLE);
        adapter = new CompareFingerAdapter(comparePhotos);
        rvFigure.setLayoutManager(new LinearLayoutManager(this));
        rvFigure.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtils.checkString(comparePhotos.get(position).getServicePath())) {
                    Intent photo = new Intent(Comparison_FingerActivity.this, PhotoViewActivity.class);
                    photo.putExtra("filePath", comparePhotos.get(position).getServicePath());
                    startActivity(photo);
                } else {
                    ToastUtils.showShort("暂无图片");
                }
            }
        });
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvFigure.getParent(), false);
        adapter.setEmptyView(emptyView);
//        presenter.getComparisonResult(crimeItem);
        presenter.getCompareData();
    }

    @Override
    protected void initData() {
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        presenter = new Comparison_FigurePresenter(this, this);
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
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_search_wifi) {//                presenter.getComparisonResult(crimeItem);
            presenter.getCompareData();
        } else if (id == R.id.iv_title_add1) {
            AddFingerCompare();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == Constants.REQUEST_COMPARE_FINGER_ADD) {
                ComparePhoto comparePhoto = (ComparePhoto) data.getSerializableExtra("comparePhoto");
                comparePhotos.add(0,comparePhoto);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void AddFingerCompare() {
        Intent intent = new Intent(Comparison_FingerActivity.this, Compare_Finger_addActivity.class);
        startActivityForResult(intent, Constants.REQUEST_COMPARE_FINGER_ADD);
    }

    @Override
    public void updateEvidence(List<ComparePhoto> evidencePhotoByUpload) {
        comparePhotos.clear();
        comparePhotos.addAll(evidencePhotoByUpload);
        adapter.notifyDataSetChanged();
    }
}
