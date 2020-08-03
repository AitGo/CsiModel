package com.liany.csiclient.view.subView.sceneStep;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.CameraPhotoDesAdapter;
import com.liany.csiclient.adapter.EvidenceFingerDesAdapter;
import com.liany.csiclient.adapter.EvidencePhotoDesAdapter;
import com.liany.csiclient.adapter.MonitoringPhotoDesAdapter;
import com.liany.csiclient.alarm.AlarmService;
import com.liany.csiclient.alarm.DateTimeUtil;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.EvidenceContract;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.presenter.subView.sceneStep.EvidencePresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.GlideEngine;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.SysCameraUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.step_window.Evidence_Add_FaceActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.Evidence_Add_FingerActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.Evidence_Add_FootActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.Evidence_Add_OtherActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.PhotoViewActivity;
import com.liany.csiclient.widget.MyDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
public class EvidenceActivity extends BaseAcitivity implements EvidenceContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RelativeLayout llAddMonitoring;
    RecyclerView rvMonitoringPhoto;
    RelativeLayout llAddCamera;
    RecyclerView rvCameraPhoto;
    ImageView ivMonitoringState;
    ImageButton ivMonitoring;
    ImageView ivCameraState;
    ImageButton ivCamera;
    TextView tvMonitoringNum;
    TextView tvCameraNum;
    ImageView ivEvidenceStateFinger;
    TextView tvEvidenceNumFinger;
    ImageButton ivEvidenceFinger;
    RelativeLayout llAddEvidenceFinger;
    RecyclerView rvEvidenceFinger;
    ImageView ivEvidenceStateFoot;
    TextView tvEvidenceNumFoot;
    ImageButton ivEvidenceFoot;
    RelativeLayout llAddEvidenceFoot;
    RecyclerView rvEvidenceFoot;
    ImageView ivEvidenceStateOther;
    TextView tvEvidenceNumOther;
    ImageButton ivEvidenceOther;
    RelativeLayout llAddEvidenceOther;
    RecyclerView rvEvidenceOther;
    ImageButton ibEvidenceCompare;
    ImageButton ivMonitoringImg;
    ImageButton ivCameraImg;
    ImageButton ibEvidenceCompareFoot;
    TextView tvEvidenceNumFace;
    ImageButton ivEvidenceFace;
    RelativeLayout llAddEvidenceFace;
    RecyclerView rvEvidenceFace;
    ImageView ivEvidenceStateFace;

    private CrimeItem crimeItem;
    private EvidenceContract.Presenter evidencePresenter;
    private EvidencePhotoDesAdapter evidenceFootAdapter, evidenceFaceAdapter, evidenceOtherAdapter;
    private EvidenceFingerDesAdapter evidenceFingerAdapter;
    private MonitoringPhotoDesAdapter monitoringAdapter;
    private CameraPhotoDesAdapter cameraAdapter;

    private List<EvidenceEntity> evidenceList = new ArrayList<>();
    private List<EvidenceEntity> evidenceFingerList = new ArrayList<>();
    private List<EvidenceEntity> evidenceFootList = new ArrayList<>();
    private List<EvidenceEntity> evidenceFaceList = new ArrayList<>();
    private List<EvidenceEntity> evidenceOtherList = new ArrayList<>();
    private List<Photo> monitoringList = new ArrayList<>();
    private List<Photo> detailList = new ArrayList<>();
    private List<Photo> cameraList = new ArrayList<>();

    private MyDialog evidenceDel, monitoringDel, cameraDel, compareDialog;
    private File outputImagepath;
    private Animation mShowAction;
    private Animation mHiddenAction;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_evidence;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        llAddMonitoring = findViewById(R.id.ll_add_monitoring);
        rvMonitoringPhoto = findViewById(R.id.rv_monitoring_photo);
        llAddCamera = findViewById(R.id.ll_add_camera);
        rvCameraPhoto = findViewById(R.id.rv_camera_photo);
        ivMonitoringState = findViewById(R.id.iv_monitoring_state);
        ivMonitoring = findViewById(R.id.iv_monitoring);
        ivCameraState = findViewById(R.id.iv_camera_state);
        ivCamera = findViewById(R.id.iv_camera);
        tvMonitoringNum = findViewById(R.id.tv_monitoring_num);
        tvCameraNum = findViewById(R.id.tv_camera_num);
        ivEvidenceStateFinger = findViewById(R.id.iv_evidence_state_finger);
        tvEvidenceNumFinger = findViewById(R.id.tv_evidence_num_finger);
        ivEvidenceFinger = findViewById(R.id.iv_evidence_finger);
        llAddEvidenceFinger = findViewById(R.id.ll_add_evidence_finger);
        rvEvidenceFinger = findViewById(R.id.rv_evidence_finger);
        ivEvidenceStateFoot = findViewById(R.id.iv_evidence_state_foot);
        tvEvidenceNumFoot = findViewById(R.id.tv_evidence_num_foot);
        ivEvidenceFoot = findViewById(R.id.iv_evidence_foot);
        llAddEvidenceFoot = findViewById(R.id.ll_add_evidence_foot);
        rvEvidenceFoot = findViewById(R.id.rv_evidence_foot);
        ivEvidenceStateOther = findViewById(R.id.iv_evidence_state_other);
        tvEvidenceNumOther = findViewById(R.id.tv_evidence_num_other);
        ivEvidenceOther = findViewById(R.id.iv_evidence_other);
        llAddEvidenceOther = findViewById(R.id.ll_add_evidence_other);
        rvEvidenceOther = findViewById(R.id.rv_evidence_other);
        ibEvidenceCompare = findViewById(R.id.ib_evidence_compare);
        ivMonitoringImg = findViewById(R.id.iv_monitoring_img);
        ivCameraImg = findViewById(R.id.iv_camera_img);
        ibEvidenceCompareFoot = findViewById(R.id.ib_evidence_compare_foot);
        tvEvidenceNumFace = findViewById(R.id.tv_evidence_num_face);
        ivEvidenceFace = findViewById(R.id.iv_evidence_face);
        llAddEvidenceFace = findViewById(R.id.ll_add_evidence_face);
        rvEvidenceFace = findViewById(R.id.rv_evidence_face);
        ivEvidenceStateFace = findViewById(R.id.iv_evidence_state_face);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        llAddEvidenceFinger.setOnClickListener(this);
        llAddEvidenceFoot.setOnClickListener(this);
        llAddEvidenceFace.setOnClickListener(this);
        llAddEvidenceOther.setOnClickListener(this);
        llAddMonitoring.setOnClickListener(this);
        llAddCamera.setOnClickListener(this);
        ivEvidenceFinger.setOnClickListener(this);
        ivEvidenceFoot.setOnClickListener(this);
        ivEvidenceFace.setOnClickListener(this);
        ivEvidenceOther.setOnClickListener(this);
        ivMonitoring.setOnClickListener(this);
        ivCamera.setOnClickListener(this);
        ibEvidenceCompare.setOnClickListener(this);
        ivMonitoringImg.setOnClickListener(this);
        ivCameraImg.setOnClickListener(this);
        ibEvidenceCompareFoot.setOnClickListener(this);

        tvTitle.setText(getString(R.string.title_activity_step4));
        ibEvidenceCompareFoot.setVisibility(View.GONE);
        ibEvidenceCompare.setVisibility(View.GONE);
        LinearLayoutManager evidenceFingerLayoutManager = new LinearLayoutManager(this);
        evidenceFingerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvidenceFinger.setLayoutManager(evidenceFingerLayoutManager);
        LinearLayoutManager evidenceFootLayoutManager = new LinearLayoutManager(this);
        evidenceFootLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvidenceFoot.setLayoutManager(evidenceFootLayoutManager);
        LinearLayoutManager evidenceFaceLayoutManager = new LinearLayoutManager(this);
        evidenceFaceLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvidenceFace.setLayoutManager(evidenceFaceLayoutManager);
        LinearLayoutManager evidenceOtherLayoutManager = new LinearLayoutManager(this);
        evidenceOtherLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvidenceOther.setLayoutManager(evidenceOtherLayoutManager);
        LinearLayoutManager evidenceLayoutManager = new LinearLayoutManager(this);
        evidenceLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvEvidenceFinger.setLayoutManager(evidenceLayoutManager);
        LinearLayoutManager monitoringLayoutManager = new LinearLayoutManager(this);
        monitoringLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMonitoringPhoto.setLayoutManager(monitoringLayoutManager);
        LinearLayoutManager photoLayoutManager = new LinearLayoutManager(this);
        photoLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCameraPhoto.setLayoutManager(photoLayoutManager);

        evidenceFingerAdapter = new EvidenceFingerDesAdapter(evidenceFingerList);
        tvEvidenceNumFinger.setText("(" + evidenceFingerList.size() + ")");
        rvEvidenceFinger.setAdapter(evidenceFingerAdapter);
        evidenceFootAdapter = new EvidencePhotoDesAdapter(R.layout.item_adapter_position, evidenceFootList);
        evidenceFootAdapter.setDesVisibility(View.GONE);
        tvEvidenceNumFoot.setText("(" + evidenceFootList.size() + ")");
        rvEvidenceFoot.setAdapter(evidenceFootAdapter);
        evidenceFaceAdapter = new EvidencePhotoDesAdapter(R.layout.item_adapter_position, evidenceFaceList);
        evidenceFaceAdapter.setDesVisibility(View.GONE);
        tvEvidenceNumFace.setText("(" + evidenceFaceList.size() + ")");
        rvEvidenceFace.setAdapter(evidenceFaceAdapter);
        evidenceOtherAdapter = new EvidencePhotoDesAdapter(R.layout.item_adapter_position, evidenceOtherList);
        evidenceOtherAdapter.setDesVisibility(View.GONE);
        tvEvidenceNumOther.setText("(" + evidenceOtherList.size() + ")");
        rvEvidenceOther.setAdapter(evidenceOtherAdapter);
        monitoringAdapter = new MonitoringPhotoDesAdapter(R.layout.item_adapter_position, monitoringList);
        tvMonitoringNum.setText("(" + monitoringList.size() + ")");
        rvMonitoringPhoto.setAdapter(monitoringAdapter);
        cameraAdapter = new CameraPhotoDesAdapter(R.layout.item_adapter_position, cameraList);
        tvCameraNum.setText("(" + cameraList.size() + ")");
        rvCameraPhoto.setAdapter(cameraAdapter);

        evidenceFingerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFingerAddPage(evidenceFingerList.get(position), position);
            }
        });
        evidenceFingerAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showEvidenceFingerDel(position);
                return true;
            }
        });
        evidenceFootAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFootAddPage(evidenceFootList.get(position), position);
            }
        });
        evidenceFootAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showEvidenceFootDel(position);
                return true;
            }
        });
        evidenceFaceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFaceAddPage(evidenceFaceList.get(position), position);
            }
        });
        evidenceFaceAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showEvidenceFaceDel(position);
                return true;
            }
        });
        evidenceOtherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startOtherAddPage(evidenceOtherList.get(position), position);
            }
        });
        evidenceOtherAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showEvidenceOtherDel(position);
                return true;
            }
        });

        monitoringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = monitoringList.get(position);
                Intent intent = new Intent(EvidenceActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        monitoringAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showMonitoringDel(position);
                return true;
            }
        });

        cameraAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = cameraList.get(position);
                Intent intent = new Intent(EvidenceActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        cameraAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showCameraDel(position);
                return true;
            }
        });

        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
    }

    @Override
    protected void initData() {
        evidencePresenter = new EvidencePresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");

        if (crimeItem.getId() != null && crimeItem.getEvidenceItem() != null) {
            evidenceList.addAll(crimeItem.getEvidenceItem());
            for (EvidenceEntity entity : evidenceList) {
                if (!StringUtils.checkString(entity.getMethodKey()) || (entity.getMethodKey() != null && entity.getMethodKey().equals("C01"))) {
                    if(StringUtils.checkString(entity.getEvidenceCategory()) && entity.getEvidenceCategory().equals("人像")) {
                        evidenceFaceList.add(entity);
                    }else {
                        //其他,工痕
                        evidenceOtherList.add(entity);
                    }
                } else if (entity.getMethodKey() != null && entity.getMethodKey().equals("A01")) {
                    //手印
                    evidenceFingerList.add(entity);
                } else if (entity.getMethodKey() != null && entity.getMethodKey().equals("B01")) {
                    //足迹
                    evidenceFootList.add(entity);
                }
            }
        }
        if (crimeItem.getId() != null && crimeItem.getMonitoringPhotoItem() != null) {
            monitoringList.addAll(crimeItem.getMonitoringPhotoItem());
        }
        if (crimeItem.getId() != null && crimeItem.getCameraPhotoItem() != null) {
            cameraList.addAll(crimeItem.getCameraPhotoItem());
        }
        if (crimeItem.getId() != null && crimeItem.getDetailPhotoItem() != null) {
            detailList.addAll(crimeItem.getDetailPhotoItem());
        }
        //当有手印痕迹时，获取事主比对结果
        if (evidenceFingerList.size() > 0) {
            evidencePresenter.getCompareResult(crimeItem);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveEvidence();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            saveEvidence();
        } else if (id == R.id.iv_title_confirm) {
            saveEvidence();
        } else if (id == R.id.ll_add_evidence_finger) {
            if (rvEvidenceFinger.isShown()) {
                ivEvidenceStateFinger.setBackgroundResource(R.mipmap.expand_close);
                rvEvidenceFinger.startAnimation(mHiddenAction);
                rvEvidenceFinger.setVisibility(View.GONE);
            } else {
                ivEvidenceStateFinger.setBackgroundResource(R.mipmap.expand_open);
                rvEvidenceFinger.startAnimation(mShowAction);
                rvEvidenceFinger.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.ll_add_evidence_foot) {
            if (rvEvidenceFoot.isShown()) {
                ivEvidenceStateFoot.setBackgroundResource(R.mipmap.expand_close);
                rvEvidenceFoot.startAnimation(mHiddenAction);
                rvEvidenceFoot.setVisibility(View.GONE);
            } else {
                ivEvidenceStateFoot.setBackgroundResource(R.mipmap.expand_open);
                rvEvidenceFoot.startAnimation(mShowAction);
                rvEvidenceFoot.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.ll_add_evidence_face) {
            if (rvEvidenceFace.isShown()) {
                ivEvidenceStateFace.setBackgroundResource(R.mipmap.expand_close);
                rvEvidenceFace.startAnimation(mHiddenAction);
                rvEvidenceFace.setVisibility(View.GONE);
            } else {
                ivEvidenceStateFace.setBackgroundResource(R.mipmap.expand_open);
                rvEvidenceFace.startAnimation(mShowAction);
                rvEvidenceFace.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.ll_add_evidence_other) {
            if (rvEvidenceOther.isShown()) {
                ivEvidenceStateOther.setBackgroundResource(R.mipmap.expand_close);
                rvEvidenceOther.startAnimation(mHiddenAction);
                rvEvidenceOther.setVisibility(View.GONE);
            } else {
                ivEvidenceStateOther.setBackgroundResource(R.mipmap.expand_open);
                rvEvidenceOther.startAnimation(mShowAction);
                rvEvidenceOther.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_evidence_finger) {
            EvidenceEntity entity = new EvidenceEntity();
            entity.setId(StringUtils.getUUID());
            entity.setCrimeId(crimeItem.getId());
            startFingerAddPage(entity, -1);
        } else if (id == R.id.iv_evidence_foot) {
            EvidenceEntity entityfoot = new EvidenceEntity();
            entityfoot.setId(StringUtils.getUUID());
            entityfoot.setCrimeId(crimeItem.getId());
            startFootAddPage(entityfoot, -1);
        } else if (id == R.id.iv_evidence_face) {
            EvidenceEntity entityface = new EvidenceEntity();
            entityface.setId(StringUtils.getUUID());
            entityface.setCrimeId(crimeItem.getId());
            startFaceAddPage(entityface, -1);
        } else if (id == R.id.iv_evidence_other) {
            EvidenceEntity entityother = new EvidenceEntity();
            entityother.setId(StringUtils.getUUID());
            entityother.setCrimeId(crimeItem.getId());
            startOtherAddPage(entityother, -1);
        } else if (id == R.id.ll_add_monitoring) {
            if (rvMonitoringPhoto.isShown()) {
                ivMonitoringState.setBackgroundResource(R.mipmap.expand_close);
                rvMonitoringPhoto.startAnimation(mHiddenAction);
                rvMonitoringPhoto.setVisibility(View.GONE);
            } else {
                ivMonitoringState.setBackgroundResource(R.mipmap.expand_open);
                rvMonitoringPhoto.startAnimation(mShowAction);
                rvMonitoringPhoto.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_monitoring_img) {//                goPhotoAlbum(Constants.REQUEST_EVIDENCE_MONITORING_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .forResult(Constants.REQUEST_EVIDENCE_MONITORING_img_more);
        } else if (id == R.id.iv_monitoring) {
            evidencePresenter.addPosition(Constants.REQUEST_EVIDENCE_MONITORING);
        } else if (id == R.id.ll_add_camera) {
            if (rvCameraPhoto.isShown()) {
                ivCameraState.setBackgroundResource(R.mipmap.expand_close);
                rvCameraPhoto.startAnimation(mHiddenAction);
                rvCameraPhoto.setVisibility(View.GONE);
            } else {
                ivCameraState.setBackgroundResource(R.mipmap.expand_open);
                rvCameraPhoto.startAnimation(mShowAction);
                rvCameraPhoto.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_camera_img) {//                goPhotoAlbum(Constants.REQUEST_EVIDENCE_CAMERA_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .forResult(Constants.REQUEST_EVIDENCE_CAMERA_img_more);
        } else if (id == R.id.iv_camera) {
            evidencePresenter.addPosition(Constants.REQUEST_EVIDENCE_CAMERA);
        } else if (id == R.id.ib_evidence_compare) {//指纹比对
            showCompareFingerDialog(evidenceFingerList);
        } else if (id == R.id.ib_evidence_compare_foot) {//足迹比对
            showCompareFootDialog(evidenceFootList);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        evidencePresenter.onActivityResult(requestCode, resultCode, data, crimeItem);
        evidencePresenter.onActivityResult(requestCode, resultCode, data, outputImagepath, crimeItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    /**
     * 打开系统相机
     */
    public void takePhoto(int code) {
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir, "evidence", code);
    }

    @Override
    public void addFingerEvidencePic(EvidenceEntity entity, int position) {
        if (position != -1) {
            evidenceFingerList.remove(position);
            evidenceFingerList.add(position, entity);
        } else {
            evidenceFingerList.add(entity);
        }
        tvEvidenceNumFinger.setText("(" + evidenceFingerList.size() + ")");
        evidenceFingerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFootEvidencePic(EvidenceEntity entity, int position) {
        if (position != -1) {
            evidenceFootList.remove(position);
            evidenceFootList.add(position, entity);
        } else {
            evidenceFootList.add(entity);
        }
        tvEvidenceNumFoot.setText("(" + evidenceFootList.size() + ")");
        evidenceFootAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFaceEvidencePic(EvidenceEntity entity, int position) {
        if (position != -1) {
            evidenceFaceList.remove(position);
            evidenceFaceList.add(position, entity);
        } else {
            evidenceFaceList.add(entity);
        }
        tvEvidenceNumFace.setText("(" + evidenceFaceList.size() + ")");
        evidenceFaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void startAlarm() {
        if (Constants.isAlarm) {
            LogUtils.e("Start polling service...");
            Intent i = new Intent(this, AlarmService.class);
//                // 获取20秒之后的日期时间字符串
            i.putExtra("alarm_time",
                    DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 2));
            i.putExtra("task_id", Constants.mTaskId);
            startService(i);
        }
    }

    @Override
    public void addOtherEvidencePic(EvidenceEntity entity, int position) {
        if (position != -1) {
            evidenceOtherList.remove(position);
            evidenceOtherList.add(position, entity);
        } else {
            evidenceOtherList.add(entity);
        }
        tvEvidenceNumOther.setText("(" + evidenceOtherList.size() + ")");
        evidenceOtherAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMonitoringPic(Photo entity) {
        monitoringList.add(entity);
        tvMonitoringNum.setText("(" + monitoringList.size() + ")");
        monitoringAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMonitoringPics(List<Photo> entitys) {
        monitoringList.addAll(entitys);
        tvMonitoringNum.setText("(" + monitoringList.size() + ")");
        monitoringAdapter.notifyDataSetChanged();
    }

    @Override
    public void addCameraPic(Photo entity) {
        cameraList.add(entity);
        tvCameraNum.setText("(" + cameraList.size() + ")");
        cameraAdapter.notifyDataSetChanged();
    }

    @Override
    public void addCameraPics(List<Photo> entitys) {
        cameraList.addAll(entitys);
        tvCameraNum.setText("(" + cameraList.size() + ")");
        cameraAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEvidenceFingerDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        evidenceDel = builder.setTitle(getString(R.string.prompt))
                .setMsg("请选择操作")
                .setEditButtonShow(true)
                .setCompareButtonShow(true)
                .setCompareButton("事主排查", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        evidencePresenter.sendCompare(evidenceFingerList.get(position), 2);
                        evidenceDel.dismiss();
                    }
                })
                .setEditButton("指纹比对", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidencePresenter.sendCompare(evidenceFingerList.get(position), 1);
                        evidenceDel.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        evidencePresenter.deleteEvidenceFingerPic(evidenceFingerList.get(position));
                        evidencePresenter.deleteEvidence(evidenceFingerList.get(position), 1);
                        evidenceDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidenceDel.dismiss();
                    }
                })
                .create();
        evidenceDel.show();
    }

    @Override
    public void showEvidenceFootDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        evidenceDel = builder.setTitle(getString(R.string.prompt))
                .setMsg("请选择操作")
                .setEditButtonShow(true)
                .setEditButton("比对", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidencePresenter.sendCompare(evidenceFootList.get(position), 3);
                        evidenceDel.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        evidencePresenter.deleteEvidenceFootPic(evidenceFootList.get(position));
                        evidencePresenter.deleteEvidence(evidenceFootList.get(position), 2);
                        evidenceDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidenceDel.dismiss();
                    }
                })
                .create();
        evidenceDel.show();
    }

    @Override
    public void showEvidenceFaceDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        evidenceDel = builder.setTitle(getString(R.string.prompt))
                .setMsg("请选择操作")
                .setEditButtonShow(true)
                .setEditButton("比对", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidencePresenter.sendCompare(evidenceFaceList.get(position), 4);
                        evidenceDel.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        evidencePresenter.deleteEvidenceFootPic(evidenceFootList.get(position));
                        evidencePresenter.deleteEvidence(evidenceFaceList.get(position), 4);
                        evidenceDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidenceDel.dismiss();
                    }
                })
                .create();
        evidenceDel.show();
    }

    @Override
    public void showEvidenceOtherDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        evidenceDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        evidencePresenter.deleteEvidenceOtherPic(evidenceOtherList.get(position));
                        evidencePresenter.deleteEvidence(evidenceOtherList.get(position), 3);
                        evidenceDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evidenceDel.dismiss();
                    }
                })
                .create();
        evidenceDel.show();
    }

    @Override
    public void showMonitoringDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        monitoringDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        evidencePresenter.deleteMonitoringPic(monitoringList.get(position));
                        monitoringDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        monitoringDel.dismiss();
                    }
                })
                .create();
        monitoringDel.show();
    }

    @Override
    public void showCameraDel(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        cameraDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        evidencePresenter.deleteCameraPic(cameraList.get(position));
                        cameraDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cameraDel.dismiss();
                    }
                })
                .create();
        cameraDel.show();
    }

    @Override
    public void startFingerAddPage(EvidenceEntity entity, int position) {
        Intent intent = new Intent(EvidenceActivity.this, Evidence_Add_FingerActivity.class);
        intent.putExtra("evidenceEntity", entity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_ADD_FINGER);
    }

    @Override
    public void startFootAddPage(EvidenceEntity entity, int position) {
        Intent intent = new Intent(EvidenceActivity.this, Evidence_Add_FootActivity.class);
        intent.putExtra("evidenceEntity", entity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_ADD_FOOT);
    }

    @Override
    public void startFaceAddPage(EvidenceEntity entity, int position) {
        Intent intent = new Intent(EvidenceActivity.this, Evidence_Add_FaceActivity.class);
        intent.putExtra("evidenceEntity", entity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_ADD_FACE);
    }

    @Override
    public void startOtherAddPage(EvidenceEntity entity, int position) {
        Intent intent = new Intent(EvidenceActivity.this, Evidence_Add_OtherActivity.class);
        intent.putExtra("evidenceEntity", entity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_ADD_OTHER);
    }

    @Override
    public void saveEvidence() {
        evidenceList.clear();
        evidenceList.addAll(evidenceFingerList);
        evidenceList.addAll(evidenceFootList);
        evidenceList.addAll(evidenceOtherList);
        Intent intent = getIntent();
        crimeItem.setEvidenceItem(evidenceList);
        crimeItem.setMonitoringPhotoItem(monitoringList);
        crimeItem.setCameraPhotoItem(cameraList);
        crimeItem.setDetailPhotoItem(detailList);
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void updateEvidenceFingerList(EvidenceEntity entity) {
        evidenceFingerList.remove(entity);
        tvEvidenceNumFinger.setText("(" + evidenceFingerList.size() + ")");
        evidenceFingerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateEvidenceFootList(EvidenceEntity entity) {
        evidenceFootList.remove(entity);
        tvEvidenceNumFoot.setText("(" + evidenceFootList.size() + ")");
        evidenceFootAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateEvidenceFaceList(EvidenceEntity entity) {
        evidenceFaceList.remove(entity);
        tvEvidenceNumFace.setText("(" + evidenceFaceList.size() + ")");
        evidenceFaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateEvidenceOtherList(EvidenceEntity entity) {
        evidenceOtherList.remove(entity);
        tvEvidenceNumOther.setText("(" + evidenceOtherList.size() + ")");
        evidenceOtherAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateMonitoringList(Photo entity) {
        monitoringList.remove(entity);
        tvMonitoringNum.setText("(" + monitoringList.size() + ")");
        monitoringAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateCameraList(Photo entity) {
        cameraList.remove(entity);
        tvCameraNum.setText("(" + cameraList.size() + ")");
        cameraAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCompareFingerDialog(List<EvidenceEntity> evidenceEntityList) {
        if (evidenceEntityList.size() > 0) {
            MyDialog.Builder builder = new MyDialog.Builder(this);
            compareDialog = builder.setTitle(getString(R.string.prompt))
                    .setMsg("是否要提交现场指纹进行比对")
                    .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                        @Override
                        public void onClick() {
                            //打包bmp文件并上传
                            evidenceList.clear();
                            evidenceList.addAll(evidenceFingerList);
                            evidenceList.addAll(evidenceFootList);
                            evidenceList.addAll(evidenceOtherList);
                            crimeItem.setEvidenceItem(evidenceList);
                            evidencePresenter.compareFinger(crimeItem);
                            compareDialog.dismiss();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            compareDialog.dismiss();
                        }
                    })
                    .create();
            compareDialog.show();
        } else {
            ToastUtils.showLong("暂无指纹可以比对，请提交后重试");
        }
    }

    public void showCompareFootDialog(List<EvidenceEntity> evidenceEntityList) {
        if (evidenceEntityList.size() > 0) {
            MyDialog.Builder builder = new MyDialog.Builder(this);
            compareDialog = builder.setTitle(getString(R.string.prompt))
                    .setMsg("是否要提交现场足迹进行比对")
                    .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                        @Override
                        public void onClick() {
                            //打包bmp文件并上传
                            evidenceList.clear();
                            evidenceList.addAll(evidenceFingerList);
                            evidenceList.addAll(evidenceFootList);
                            evidenceList.addAll(evidenceOtherList);
                            crimeItem.setEvidenceItem(evidenceList);
                            evidencePresenter.compareFoot(crimeItem);
                            compareDialog.dismiss();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            compareDialog.dismiss();
                        }
                    })
                    .create();
            compareDialog.show();
        } else {
            ToastUtils.showLong("暂无足迹可以比对，请提交后重试");
        }
    }

    @Override
    public void addDetail(Photo photo) {
        detailList.add(photo);
    }

    @Override
    public void delDetail(String path) {
        Iterator<Photo> it = detailList.iterator();
        while (it.hasNext()) {
            // index and number
            Photo photo = it.next();
            if (photo.getPath().equals(path)) {
                it.remove();
            }
        }
    }

    @Override
    public void updateFinger(List<ComparePhoto> data) {
        for (ComparePhoto photo : data) {
            for (EvidenceEntity entity : evidenceFingerList) {
                if (photo.getPhotoId().equals(entity.getPhoto().getId())) {
                    if (photo.getStatus().equals("8")) {
                        entity.setRev1("事主排查未比中");
                    } else if (photo.getStatus().equals("107")) {
                        entity.setRev1("比中事主");
                    } else if (photo.getStatus().equals("1")) {
                        entity.setRev1("事主比对中");
                    }
                }
            }
        }
        evidenceFingerAdapter.notifyDataSetChanged();
    }

    public void goPhotoAlbum(int code) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, code);
    }

    /**
     * 清空缓存包括裁剪、压缩、AndroidQToPath所生成的文件，注意调用时机必须是处理完本身的业务逻辑后调用；非强制性
     */
    public void clearCache() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PictureFileUtils.deleteCacheDirFile(this, PictureMimeType.ofImage());
//            PictureFileUtils.deleteAllCacheDirFile(this);
        } else {
            PermissionChecker.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
        }
    }

}
