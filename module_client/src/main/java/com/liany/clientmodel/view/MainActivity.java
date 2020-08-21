package com.liany.clientmodel.view;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.GlideImageLoader;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.contract.MainContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.presenter.MainPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.view.subView.CreateSceneActivity;
import com.liany.clientmodel.view.subView.SceneListComparisonContactsActivity;
import com.liany.clientmodel.view.subView.SceneListComparisonEvidenceActivity;
import com.liany.clientmodel.view.subView.SceneListFragmentActivity;
import com.liany.clientmodel.view.subView.sceneStep.BaseInfoActivity;
import com.liany.clientmodel.view.subView.sceneStep.Comparison_FaceActivity;
import com.liany.clientmodel.view.subView.sceneStep.Comparison_FingerActivity;
import com.liany.clientmodel.view.subView.sceneStep.Comparison_FootActivity;
import com.liany.clientmodel.view.subView.sceneStep.ProspectingActivity;
import com.liany.clientmodel.widget.MyDialog;
import com.liany.clientmodel.widget.SelectCrimeDialog;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/csi/main")
public class MainActivity extends BaseAcitivity implements MainContract.View, View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    Banner banner;
    LinearLayout llMainPreviewScene;
    LinearLayout llMainCreateScene;
    LinearLayout llMainSceneList;
    LinearLayout llMainContacts;
    LinearLayout llMainComparison;
    TextView tvSceneName;
    Button btnSelectScene;
    LinearLayout llMainComparisonFinger;
    LinearLayout llMainComparisonFoot;
    LinearLayout llMainComparisonFace;

    private MainContract.Presenter mainPresenter;
    private List<Integer> images = new ArrayList<>();
    private SelectCrimeDialog selectDialog;
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_model;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        banner = findViewById(R.id.bannerview);
        llMainPreviewScene = findViewById(R.id.ll_main_preview_scene);
        llMainCreateScene = findViewById(R.id.ll_main_create_scene);
        llMainSceneList = findViewById(R.id.ll_main_scene_list);
        llMainContacts = findViewById(R.id.ll_main_contacts);
        llMainComparison = findViewById(R.id.ll_main_comparison);
        btnSelectScene = findViewById(R.id.btn_selectScene);
        tvSceneName = findViewById(R.id.tv_sceneName);
        llMainComparisonFinger = findViewById(R.id.ll_main_comparison_finger);
        llMainComparisonFoot = findViewById(R.id.ll_main_comparison_foot);
        llMainComparisonFace = findViewById(R.id.ll_main_comparison_face);

        ivTitleSetting.setOnClickListener(this);
        llMainPreviewScene.setOnClickListener(this);
        llMainCreateScene.setOnClickListener(this);
        llMainSceneList.setOnClickListener(this);
        llMainContacts.setOnClickListener(this);
        llMainComparison.setOnClickListener(this);
        btnSelectScene.setOnClickListener(this);
        llMainComparisonFinger.setOnClickListener(this);
        llMainComparisonFoot.setOnClickListener(this);
        llMainComparisonFace.setOnClickListener(this);

//        llMainPreviewScene.setVisibility(Constants.state_isShowPreView ? View.VISIBLE : View.INVISIBLE);
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        images.add(R.mipmap.banner1);
        images.add(R.mipmap.banner);
        banner.setImages(images);
        banner.isAutoPlay(true);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //banner设置方法全部调用完毕时最后调用
        banner.setDelayTime(5000);
        banner.start();
    }

    @Override
    protected void initData() {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.initPresentScene();
        mainPresenter.initMobileTypeSetting(Build.MANUFACTURER, Build.MODEL);
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_setting) {
            showSetting();
        } else if (id == R.id.ll_main_preview_scene) {
        } else if (id == R.id.ll_main_create_scene) {//                mainPresenter.showBaseInfo();
            mainPresenter.showProspection();
//                showCreateScene();
        } else if (id == R.id.ll_main_scene_list) {
            showSceneList();
        } else if (id == R.id.ll_main_contacts) {
            showComparisonContacts();
        } else if (id == R.id.ll_main_comparison) {
            showComparisonEvidence();
        } else if (id == R.id.ll_main_comparison_finger) {
            showComparisonFinger();
        } else if (id == R.id.ll_main_comparison_foot) {
            showComparisonFoot();
        } else if (id == R.id.ll_main_comparison_face) {
            showComparisonFace();
        } else if (id == R.id.btn_selectScene) {//判断是否有现场
            mainPresenter.showSelectScene();
        }
    }

    private void showComparisonFace() {
        Intent face = new Intent(this, Comparison_FaceActivity.class);
        startActivity(face);
    }

    private void showComparisonFoot() {
        Intent foot = new Intent(this, Comparison_FootActivity.class);
        startActivity(foot);
    }

    private void showComparisonFinger() {
        Intent finger = new Intent(this, Comparison_FingerActivity.class);
        startActivity(finger);
    }

    @Override
    public void showCreateScene() {
        Intent intent = new Intent(this, CreateSceneActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSceneList() {
        Intent intent = new Intent(this, SceneListFragmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void showComparisonContacts() {
        Intent intent = new Intent(this, SceneListComparisonContactsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showComparisonEvidence() {
        Intent intent = new Intent(this, SceneListComparisonEvidenceActivity.class);
        startActivity(intent);
    }

    @Override
    public void showBaseInfo(CrimeItem crimeItem) {
        Intent intent = new Intent(this, BaseInfoActivity.class);
        intent.putExtra("crime", crimeItem);
        startActivity(intent);
    }

    @Override
    public void setSceneName(String name) {
        tvSceneName.setText(name);
    }

    @Override
    public void showSelectScene(List<CrimeItem> crimeItems) {
        //显示弹窗
        SelectCrimeDialog.Builder builder = new SelectCrimeDialog.Builder(this, crimeItems);
        selectDialog = builder.itemClick(new SelectCrimeDialog.ItemListener() {
            @Override
            public void onClick(CrimeItem crimeItem) {
                mainPresenter.updatePresentScene(crimeItem);
                selectDialog.dismiss();
            }

            @Override
            public void finish() {
                selectDialog.dismiss();
            }
        }).create();
        selectDialog.show();
    }

    @Override
    public void showProspection(CrimeItem item) {
        Intent intent = new Intent(this, ProspectingActivity.class);
        intent.putExtra("isCreate", "1");
        intent.putExtra("crime", item);
        startActivity(intent);
    }

    @Override
    public void showSetting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

}
