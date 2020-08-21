package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.ExtractAddPhotoAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Extract_AddContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.selectUser;
import com.liany.clientmodel.presenter.subView.sceneStep.step_window.Extract_AddPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.SysCameraUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.view.subView.sceneStep.Select_Check_UserActivity;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Extract_AddActivity extends BaseAcitivity implements Extract_AddContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    RecyclerView rvCollectedPhoto;
    ClearableEditText materialName;
    ClearableEditText collectedPosition;
    TextView tvCollectedMethod;
    TextView collectedName;
    ClearableEditText collectedNum;
    TextView tvCollectedDate;
    ClearableEditText remark;
    ImageView ivTitleCamera;
    ClearableEditText etCollectedMethod;
    TextView tvCode;
    ImageButton ivScan;
    ImageView ivTitleConfirm;

    private Extract_AddContract.Presenter presenter;
    private CrimeItem crimeItem;
    private File outputImagepath;
    private GoodEntity entity;
    private int position = -1;
    private List<Photo> photos = new ArrayList<>();
    private ExtractAddPhotoAdapter adapter;
    private TimePickerView pv_time;
    private MyDialog myDialog;
    private MyDialog delDialog;
    private View emptyView;
    private String type = "0";
    private List<Photo> takePhotoList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_extract_add;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        rvCollectedPhoto = findViewById(R.id.rv_collected_photo);
        materialName = findViewById(R.id.material_name);
        collectedPosition = findViewById(R.id.collected_position);
        tvCollectedMethod = findViewById(R.id.tv_collected_method);
        collectedName = findViewById(R.id.collected_name);
        collectedNum = findViewById(R.id.collected_num);
        tvCollectedDate = findViewById(R.id.tv_collected_date);
        remark = findViewById(R.id.remark);
        ivTitleCamera = findViewById(R.id.iv_title_camera_right);
        etCollectedMethod = findViewById(R.id.et_collected_method);
        tvCode = findViewById(R.id.tv_code);
        ivScan = findViewById(R.id.iv_scan);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        ivTitleCamera.setOnClickListener(this);
        tvCollectedMethod.setOnClickListener(this);
        collectedName.setOnClickListener(this);
        tvCollectedDate.setOnClickListener(this);
        ivScan.setOnClickListener(this);

        collectedNum.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        ivTitleCamera.setVisibility(View.VISIBLE);
        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText("添加物品");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCollectedPhoto.setLayoutManager(layoutManager);
        adapter = new ExtractAddPhotoAdapter(R.layout.item_adapter_extract_photo, photos);
        rvCollectedPhoto.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(StringUtils.checkString(photos.get(position).getServerPath())) {
                    Intent photo = new Intent(Extract_AddActivity.this, PhotoViewActivity.class);
                    photo.putExtra("filePath", photos.get(position).getServerPath());
                    startActivity(photo);
                }else {
                    ToastUtils.showShort("暂无图片");
                }
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //删除
                showDeleteDialog(position);
                return false;
            }
        });

        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvCollectedPhoto.getParent(), false);
        adapter.setEmptyView(emptyView);
    }

    @Override
    protected void initData() {
        presenter = new Extract_AddPresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        entity = (GoodEntity) getIntent().getSerializableExtra("extract");
        position = getIntent().getIntExtra("position",-1);
        type = entity.getRev1();
        if(position == -1) {
            entity.setId(StringUtils.getUUID());
        } else {
            tvCode.setText(entity.getCode());
            materialName.setText(entity.getMaterialName());
            collectedPosition.setText(entity.getCollectedPosition());
            remark.setText(entity.getRemark());
            photos.clear();
            if(entity.getPhotos() != null) {
                photos.addAll(entity.getPhotos());
            }
        }
        etCollectedMethod.setText(entity.getCollectedMethod());
        collectedName.setText(entity.getCollectedName());
        collectedNum.setText(entity.getCollectedNum());
        tvCollectedDate.setText(entity.getCollectedDate());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //退出时删除已经拍照但没有保存的照片
            presenter.deleteExtractPicList(takePhotoList, crimeItem.getId());
            finish();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            presenter.deleteExtractPicList(takePhotoList, crimeItem.getId());
            finish();
        } else if (id == R.id.iv_title_confirm) {//保存并退出
            presenter.saveExtract(crimeItem, entity, true);
        } else if (id == R.id.iv_title_camera_right) {
            takePhoto();
        } else if (id == R.id.iv_scan) {
            startScan();
        } else if (id == R.id.tv_collected_method) {
        } else if (id == R.id.collected_name) {
            presenter.getPeople();
        } else if (id == R.id.tv_collected_date) {
            showTimerView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data, outputImagepath, crimeItem.getId(), entity.getId());
    }

    @Override
    public String getCollectedName() {
        return collectedName.getText().toString();
    }

    @Override
    public String getCollectedId() {
        return entity.getRev2();
    }

    @Override
    public void setCollectedName(String name) {
        collectedName.setText(name);
    }

    public void setCollectedName(List<selectUser> users) {
        collectedName.setText(StringUtils.selectUserValue2String(users));
        entity.setCollectedName(StringUtils.selectUserValue2String(users));
        entity.setCollectedIds(StringUtils.selectUserKey2String(users));
        entity.setRev2(StringUtils.selectUserId2String(users));
    }

    @Override
    public String getMaterialName() {
        return materialName.getText().toString();
    }

    @Override
    public void setMaterialName(String name) {
        materialName.setText(name);
    }

    @Override
    public String getCollectedPosition() {
        return collectedPosition.getText().toString();
    }

    @Override
    public void setCollectedPosition(String position) {
        collectedPosition.setText(position);
    }

    @Override
    public String getCollectedMethod() {
        return etCollectedMethod.getText().toString();
    }

    @Override
    public void setCollectedMethod(String method) {
        etCollectedMethod.setText(method);
    }

    @Override
    public String getCollectedDate() {
        return tvCollectedDate.getText().toString();
    }

    @Override
    public void setCollectedDate(String date) {
        tvCollectedDate.setText(date);
    }

    @Override
    public String getCollectedNum() {
        return collectedNum.getText().toString();
    }

    @Override
    public void setCollectedNum(String num) {
        collectedNum.setText(num);
    }

    @Override
    public String getRemark() {
        return remark.getText().toString();
    }

    @Override
    public void setRemark(String remark) {
        this.remark.setText(remark);
    }

    @Override
    public void setPhoto(Photo photo) {
        photos.add(photo);
        takePhotoList.add(photo);
        adapter.notifyDataSetChanged();
        entity.setPhotos(photos);
    }

    @Override
    public void setScan(String result) {
        entity.setCode(result);
        tvCode.setText(result);
    }

    @Override
    public String getScan() {
        return tvCode.getText().toString();
    }

    @Override
    public void showTimerView() {
        hideInput();
        Calendar selectedDate;
        String sDate = getCollectedDate();
        if (sDate != null && !sDate.equals("")) {
            selectedDate = StringUtils.String2Calendar(sDate,"yyyy-MM-dd");
        } else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                setCollectedDate(StringUtils.Date2String(date,"yyyy-MM-dd"));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void startSelectUserView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(Extract_AddActivity.this, Select_Check_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    public void startScan() {
        Intent intent = new Intent(Extract_AddActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.SCAN_REQUEST_CODE);
    }

    /**
     * 打开系统相机
     */
    public void takePhoto() {
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir,"extract", Constants.REQUEST_EVIDENCE_EVIDENCE);
    }

    @Override
    public void showMsgDialog(String msg) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.deleteExtractPicList(photos, crimeItem.getId());
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
    public void showDeleteDialog(int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        delDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要删除该图片")
                .setPositiveButton("确定", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.deleteExtractPic(photos.get(position), crimeItem.getId());
                        //删除服务器图片
                        delDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delDialog.dismiss();
                    }
                })
                .create();
        delDialog.show();
    }

    @Override
    public void updateExtractList(Photo photo) {
        photos.remove(photo);
        adapter.notifyDataSetChanged();
        entity.setPhotos(photos);
    }

    @Override
    public void saveExtract(GoodEntity entity) {
        Intent intent = getIntent();
        intent.putExtra("extract", (Serializable) entity);
        intent.putExtra("position",position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
