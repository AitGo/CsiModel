package com.liany.csiclient.view.subView.sceneStep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.CompareFaceAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.Comparison_FaceContract;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.presenter.subView.sceneStep.Comparison_FacePresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.step_window.Compare_Face_addActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/7/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_FaceActivity extends BaseAcitivity implements Comparison_FaceContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvFace;
    ImageView ivTitleSearchWifi;
    ImageView ivTitleAdd1;

    private CrimeItem crimeItem;
    private CompareFaceAdapter adapter;
    private List<ComparePhoto> comparePhotos = new ArrayList<>();
    private View emptyView;
    private Comparison_FaceContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_comparison_face;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvFace = findViewById(R.id.rv_face);
        ivTitleSearchWifi = findViewById(R.id.iv_title_search_wifi);
        ivTitleAdd1 = findViewById(R.id.iv_title_add1);

        ivTitleBack.setOnClickListener(this);
        ivTitleSearchWifi.setOnClickListener(this);
        ivTitleAdd1.setOnClickListener(this);

        tvTitle.setText("人脸比对");
        ivTitleSearchWifi.setVisibility(View.VISIBLE);
        ivTitleAdd1.setVisibility(View.VISIBLE);
        adapter = new CompareFaceAdapter(comparePhotos);
        rvFace.setLayoutManager(new LinearLayoutManager(this));
        rvFace.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtils.checkString(comparePhotos.get(position).getServicePath())) {
                    Intent photo = new Intent(Comparison_FaceActivity.this, PhotoViewActivity.class);
                    photo.putExtra("filePath", comparePhotos.get(position).getServicePath());
                    startActivity(photo);
                } else {
                    ToastUtils.showShort("暂无图片");
                }
            }
        });
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvFace.getParent(), false);
        adapter.setEmptyView(emptyView);
        presenter.getCompareData();
    }

    @Override
    protected void initData() {
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        presenter = new Comparison_FacePresenter(this, this);
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
        } else if (id == R.id.iv_title_search_wifi) {
            presenter.getCompareData();
        } else if (id == R.id.iv_title_add1) {
            AddFaceCompare();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == Constants.REQUEST_COMPARE_FACE_ADD) {
                ComparePhoto comparePhoto = (ComparePhoto) data.getSerializableExtra("comparePhoto");
                comparePhotos.add(0,comparePhoto);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void AddFaceCompare() {
        Intent intent = new Intent(Comparison_FaceActivity.this, Compare_Face_addActivity.class);
        startActivityForResult(intent, Constants.REQUEST_COMPARE_FACE_ADD);
    }

    @Override
    public void updateEvidence(List<ComparePhoto> evidencePhotoByUpload) {
        comparePhotos.clear();
        comparePhotos.addAll(evidencePhotoByUpload);
        adapter.notifyDataSetChanged();
    }
}
