package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.step_window.EvidenceAddNewFingerContract;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.presenter.subView.sceneStep.step_window.EvidenceAddNewFingerPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.SysCameraUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.Select_Check_UserActivity;
import com.liany.csiclient.view.subView.sceneStep.Select_RadioList_DictActivity;
import com.liany.csiclient.widget.ClearableEditText;
import com.liany.csiclient.widget.MyDialog;
import com.liany.csiclient.widget.image.ImageProcessActivity;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Evidence_Add_FingerActivity extends BaseAcitivity implements EvidenceAddNewFingerContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleCameraRight;
    ImageView ivTitleConfirm;
    ImageView ivEvidencePic;
    TextView evidenceTextLabel;
    TextView evidence;
    ClearableEditText otherEvidence;
    ClearableEditText evidenceName;
    ClearableEditText legacySite;
    ClearableEditText basiceFeature;
    TextView infer;
    LinearLayout inferLL;
    TextView method;
    ClearableEditText otherMethod;
    TextView time;
    Button timeButton;
    TextView getPeople;
    ImageView methodQuestion;
    ImageView ivTitleImgRight;

    private EvidenceAddNewFingerContract.Presenter addPresenter;
    private File outputImagepath;
    private String type = "手印";
    private String photoPath = null;
    private int position = -1;
    private EvidenceEntity entity;
    private String crimeId;
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_evidence_add_finger;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleCameraRight = findViewById(R.id.iv_title_camera_right);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        ivEvidencePic = findViewById(R.id.iv_evidence_pic);
        evidenceTextLabel = findViewById(R.id.evidenceTextLabel);
        evidence = findViewById(R.id.evidence);
        otherEvidence = findViewById(R.id.other_evidence);
        evidenceName = findViewById(R.id.evidence_name);
        legacySite = findViewById(R.id.legacy_site);
        basiceFeature = findViewById(R.id.basice_feature);
        infer = findViewById(R.id.infer);
        inferLL = findViewById(R.id.inferLL);
        method = findViewById(R.id.method);
        otherMethod = findViewById(R.id.other_method);
        time = findViewById(R.id.time);
        timeButton = findViewById(R.id.time_button);
        getPeople = findViewById(R.id.getPeople);
        methodQuestion = findViewById(R.id.method_question);
        ivTitleImgRight = findViewById(R.id.iv_title_img_right);

        ivTitleBack.setOnClickListener(this);
        ivTitleCameraRight.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        time.setOnClickListener(this);
        evidence.setOnClickListener(this);
        otherEvidence.setOnClickListener(this);
        infer.setOnClickListener(this);
        method.setOnClickListener(this);
        getPeople.setOnClickListener(this);
        methodQuestion.setOnClickListener(this);
        ivEvidencePic.setOnClickListener(this);
        ivTitleImgRight.setOnClickListener(this);

        tvTitle.setText("指纹");
        ivTitleCameraRight.setVisibility(View.VISIBLE);
        ivTitleConfirm.setVisibility(View.VISIBLE);
        ivTitleImgRight.setVisibility(View.VISIBLE);
        if (position == -1) {
            entity.setPeople((String) SPUtils.getParam(this, Constants.sp_accessInspectors, ""));
            entity.setPeopleKey((String) SPUtils.getParam(this, Constants.sp_accessInspectorsKey, ""));
            entity.setRev2((String) SPUtils.getParam(this, Constants.sp_accessInspectorsId, ""));
            setPeople((String) SPUtils.getParam(this, Constants.sp_accessInspectors, ""));
            entity.setEvidence("指纹");
            entity.setEvidenceKey("1101");
            setEvidence("指纹");
//            addPresenter.checkPermission();
        }
        setEvidenceTime(StringUtils.Calendar2String(Calendar.getInstance()));

        if (entity == null) {
            entity = new EvidenceEntity();
            entity.setId(StringUtils.getUUID());
        }

        method.setText("直接照相");
        if (entity.getEvidenceCategory() != null) {
            if (entity.getEvidenceCategory().equals("手印")) {
                entity.setMethodKey("A01");
            } else if (entity.getEvidenceCategory().equals("足迹")) {
                entity.setMethodKey("B01");
            } else if (entity.getEvidenceCategory().equals("工痕")) {
                entity.setMethodKey("C01");
            }
        } else {
            entity.setMethodKey("A01");
        }
        evidenceTextLabel.setText("手印痕迹");
        evidence.setVisibility(View.VISIBLE);
        otherEvidence.setVisibility(View.GONE);
        evidenceName.setText("");
        inferLL.setVisibility(View.GONE);
        otherMethod.setVisibility(View.GONE);
        method.setVisibility(View.VISIBLE);
        method.setText("直接照相");
        entity.setMethodKey("A01");
        type = "手印";
        addPresenter.initValue(entity, position);
    }

    @Override
    protected void initData() {
        addPresenter = new EvidenceAddNewFingerPresenter(this, this);
        entity = (EvidenceEntity) getIntent().getSerializableExtra("evidenceEntity");

        position = getIntent().getIntExtra("position", -1);
        crimeId = getIntent().getStringExtra("crimeId");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //退出时删除已经拍照但没有保存的照片
//            addPresenter.deleteEvidencePhoto(entity, photoPath);
            finish();
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        addPresenter.copyPhoto(Constants.path_flashAirDir,crimeId,entity);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        addPresenter.copyPhoto(flashAirPath,crimeId,entity);
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();//其他痕迹
        if (id == R.id.iv_title_back) {//                addPresenter.deleteEvidencePhoto(entity, photoPath);
            finish();
        } else if (id == R.id.iv_title_img_right) {
            goPhotoAlbum();
        } else if (id == R.id.iv_title_camera_right) {//                addPresenter.checkPermission();
            takePhoto();
        } else if (id == R.id.iv_title_confirm) {
            addPresenter.saveEvidence(entity, crimeId, true);
        } else if (id == R.id.evidence) {//痕迹
            addPresenter.evidence(type);
        } else if (id == R.id.other_evidence) {
        } else if (id == R.id.infer) {//工具推断
            addPresenter.infer();
        } else if (id == R.id.method) {//提取方法
            addPresenter.method(type);
        } else if (id == R.id.time) {
            addPresenter.getEvidenceTime();
            closeEdit();
        } else if (id == R.id.getPeople) {//提取人
            addPresenter.getPeople();
        } else if (id == R.id.method_question) {//提取方法问答
//                Intent intent = new Intent(Evidence_Add_FingerActivity.this,QuestionActivity.class);
//                startActivity(intent);
        } else if (id == R.id.iv_evidence_pic) {
            if (entity.getPhoto() != null && StringUtils.checkString(entity.getPhoto().getServerPath())) {
                Intent photo = new Intent(Evidence_Add_FingerActivity.this, PhotoViewActivity.class);
                photo.putExtra("filePath", entity.getPhoto().getServerPath());
                startActivity(photo);
            } else {
                ToastUtils.showShort("暂无图片");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addPresenter.onActivityResult(requestCode, resultCode, data, outputImagepath, crimeId, entity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    /**
     * 打开系统相机
     */
    public void takePhoto() {
//        Intent intent = new Intent(Evidence_Add_FingerActivity.this, com.liany.csiclient.widget.myCamera.CameraActivity.class);
//        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_EVIDENCE_GOOGLE);
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir + File.separator,"finger", Constants.REQUEST_EVIDENCE_EVIDENCE_GOOGLE);
    }

    @Override
    public void startFlashAir() {
        startActivity(getPackageManager().getLaunchIntentForPackage("jp.co.toshiba.android.FlashAir"));
    }

    @Override
    public void setPhoto(Photo photo) {
        this.photoPath = photo.getPath();
        if (photo != null) {
            Glide.with(this).load(photo.getServerPath())
                    .dontAnimate()
                    .into(ivEvidencePic);
        }
        entity.setPhoto(photo);
    }

    @Override
    public void setEvidenceTextLabel(String label) {
        evidenceTextLabel.setText(label);
    }

    @Override
    public String getEvidenceTextLabel() {
        return evidenceTextLabel.getText().toString();
    }

    @Override
    public String getEvidenceTime() {
        return time.getText().toString();
    }

    @Override
    public void setEvidenceTime(String date) {
        time.setText(date);
    }

    @Override
    public String getEvidenceType() {
        return type;
    }

    @Override
    public void setEvidence(String evidence) {
        this.evidence.setText(evidence);
    }

    @Override
    public void setEvidence(sysDict dict) {
        if (dict != null) {
            this.evidence.setText(dict.getDictValue());
            if (entity == null) {
                entity = new EvidenceEntity();
            }
            entity.setEvidence(dict.getDictValue());
            entity.setEvidenceKey(dict.getDictKey());
        }
    }

    @Override
    public String getEvidence() {
        return this.evidence.getText().toString();
    }

    public String getOtherEvidence() {
        return this.otherEvidence.getText().toString();
    }

    @Override
    public void setOtherEvidence(String evidence) {
        this.otherEvidence.setText(evidence);
    }

    @Override
    public void setEvidenceName(String evidenceName) {
        this.evidenceName.setText(evidenceName);
    }

    @Override
    public String getEvidenceName() {
        return this.evidenceName.getText().toString();
    }

    @Override
    public void setLegacySite(String legacySite) {
        this.legacySite.setText(legacySite);
    }

    @Override
    public String getLegacySite() {
        return this.legacySite.getText().toString();
    }

    @Override
    public void setBasiceFeature(String basiceFeature) {
        this.basiceFeature.setText(basiceFeature);
    }

    @Override
    public String getBasiceFeature() {
        return this.basiceFeature.getText().toString();
    }

    @Override
    public void setInfer(String infer) {
        this.infer.setText(infer);
    }

    @Override
    public void setInfer(sysDict dict) {
        if (dict != null) {
            this.infer.setText(dict.getDictValue());
            if (entity == null) {
                entity = new EvidenceEntity();
            }
            entity.setInfer(dict.getDictValue());
            entity.setInferKey(dict.getDictKey());
        }
    }

    @Override
    public String getInfer() {
        return this.infer.getText().toString();
    }

    @Override
    public void setMethod(String method) {
        this.method.setText(method);
    }

    @Override
    public void setMethod(sysDict dict) {
        if (dict != null) {
            this.method.setText(dict.getDictValue());
            if (entity == null) {
                entity = new EvidenceEntity();
            }
            entity.setMethod(dict.getDictValue());
            entity.setMethodKey(dict.getDictKey());
        }
    }


    @Override
    public String getMethod() {
        return this.method.getText().toString();
    }

    @Override
    public void setTime(String time) {
        this.time.setText(time);
    }

    @Override
    public String getTime() {
        return this.time.getText().toString();
    }

    @Override
    public void setPeople(String people) {
        this.getPeople.setText(people);
    }

    @Override
    public void setPeople(List<selectUser> users) {
        this.getPeople.setText(StringUtils.selectUserValue2String(users));
        if (entity == null) {
            entity = new EvidenceEntity();
        }
        entity.setPeople(StringUtils.selectUserValue2String(users));
        entity.setPeopleKey(StringUtils.selectUserKey2String(users));
        entity.setRev2(StringUtils.selectUserId2String(users));
    }

    @Override
    public String getPeople() {
        return this.getPeople.getText().toString();
    }

    @Override
    public String getPeopleId() {
        return entity.getRev2();
    }

    @Override
    public String getPhotoFile() {
        return photoPath;
    }

    @Override
    public void closeEdit() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(otherEvidence.getWindowToken(), 0);
    }

    @Override
    public void close(EvidenceEntity entity) {
        Intent intent = getIntent();
        intent.putExtra("evidenceEntity", entity);
        intent.putExtra("position", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(Evidence_Add_FingerActivity.this, Select_RadioList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUserView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(Evidence_Add_FingerActivity.this, Select_Check_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void showMsgDialog(String s) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        addPresenter.saveEvidence(entity,crimeId,false);
                        finish();
                        myDialog.dismiss();
                    }
                })
                .setNegativeButton("补全信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

    @Override
    public void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_EVIDENCE_ALBUM);
    }

    @Override
    public void startLocalCrop(String path) {
        Intent intent = new Intent(Evidence_Add_FingerActivity.this, ImageProcessActivity.class);
        intent.putExtra("image_filePath",path);
        startActivityForResult(intent, Constants.REQUEST_LOCAL_CROP);
    }

}
