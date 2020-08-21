package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Witness_AddContract;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.WitnessEntity;
import com.liany.clientmodel.presenter.subView.sceneStep.step_window.Witness_AddPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Witness_AddActivity extends BaseAcitivity implements Witness_AddContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    ClearableEditText nameEditView;
    RadioButton radioJzSexMan;
    RadioButton radioJzSexWomen;
    RadioGroup radioGroupJzPeopleSex;
    TextView birthdayDate;
    Button birthdayDateButton;
    ClearableEditText contactNumberEditView;
    ClearableEditText addressEditView;
    ImageView image;
    Button btn;
    ImageView ivSign;
    ImageView ivTitleCameraRight;

    private Witness_AddContract.Presenter addPresenter;
    private WitnessEntity entity;
    private int position;
    private String sex = "男";
    private String sexKey = "1";
    private TimePickerView pv_time;
    private String crimeId;
    private MyDialog myDialog;
    private Photo photo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_witness_add;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        nameEditView = findViewById(R.id.name_editView);
        radioJzSexMan = findViewById(R.id.radioJzSexMan);
        radioJzSexWomen = findViewById(R.id.radioJzSexWomen);
        radioGroupJzPeopleSex = findViewById(R.id.radioGroupJzPeopleSex);
        birthdayDate = findViewById(R.id.birthday_date);
        birthdayDateButton = findViewById(R.id.birthday_date_button);
        contactNumberEditView = findViewById(R.id.contact_number_editView);
        addressEditView = findViewById(R.id.address_editView);
        image = findViewById(R.id.image);
        btn = findViewById(R.id.btn);
        ivSign = findViewById(R.id.iv_sign);
        ivTitleCameraRight = findViewById(R.id.iv_title_camera_right);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        btn.setOnClickListener(this);
        ivTitleCameraRight.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        ivTitleCameraRight.setVisibility(View.VISIBLE);
        tvTitle.setText("添加见证人");
        contactNumberEditView.setKeyListener(DigitsKeyListener.getInstance("()-0123456789"));
        if (entity != null) {
            addPresenter.setValueToView(entity);
        }

        radioGroupJzPeopleSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radioJzSexMan) {
                    sex = "男";
                    sexKey = "1";
                } else if (checkedRadioButtonId == R.id.radioJzSexWomen) {
                    sex = "女";
                    sexKey = "2";
                }
            }
        });
    }

    @Override
    protected void initData() {
        addPresenter = new Witness_AddPresenter(this, this);
        entity = (WitnessEntity) getIntent().getSerializableExtra("witness");
        position = getIntent().getIntExtra("position", -1);
        crimeId = getIntent().getStringExtra("crimeId");
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_confirm) {
            addPresenter.saveWitness(entity, crimeId, true);
        } else if (id == R.id.birthday_date) {
            addPresenter.getBirthday();
        } else if (id == R.id.btn) {
            startActivityForResult(new Intent(Witness_AddActivity.this, Witness_HandWriteActivity.class), Constants.EVENT_PHOTO_TYPE_SIGN);
        } else if (id == R.id.iv_title_camera_right) {
            startIDCardOCR(Constants.REQUEST_WITNESS_ADD_IDCARD);
        }
    }

    private void startIDCardOCR(int code) {
        Intent intent = new Intent(Witness_AddActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtils.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addPresenter.onActivityResult(requestCode, resultCode, data, entity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void showTimerView() {
        closeEdit();
        Calendar selectedDate;
        String sDate = getBirthdayDate();
        if (sDate != null && !sDate.equals("")) {
            selectedDate = StringUtils.String2Calendar(sDate, "yyyy-MM-dd");
        } else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                setBirthdayDate(StringUtils.Date2String(date, "yyyy-MM-dd"));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void setBirthdayDate(String date) {
        birthdayDate.setText(date);
    }

    @Override
    public String getBirthdayDate() {
        return birthdayDate.getText().toString();
    }

    @Override
    public String getWitnessName() {
        return nameEditView.getText().toString();
    }

    @Override
    public String getWitnessNumber() {
        return contactNumberEditView.getText().toString();
    }

    @Override
    public String getWitnessAddress() {
        return addressEditView.getText().toString();
    }

    @Override
    public String getWitnessSex() {
        return sex;
    }

    @Override
    public String getWitnessSexKey() {
        return sexKey;
    }

    @Override
    public void setWitnessName(String witnessName) {
        nameEditView.setText(witnessName);
    }

    @Override
    public void setWitnessNumber(String witnessNumber) {
        contactNumberEditView.setText(witnessNumber);
    }

    @Override
    public void setWitnessAddress(String witnessAddress) {
        addressEditView.setText(witnessAddress);
    }

    @Override
    public void setWitnessSex(String sex) {
        if (sex.equals("男")) {
            radioJzSexMan.setChecked(true);
            radioJzSexWomen.setChecked(false);
            this.sex = "男";
            sexKey = "1";
        } else if (sex.equals("女")) {
            radioJzSexMan.setChecked(false);
            radioJzSexWomen.setChecked(true);
            this.sex = "女";
            sexKey = "2";
        }
    }

    @Override
    public void setSignImg(Photo photo) {
        this.photo = photo;
        entity.setPhoto(photo);
        Glide.with(this).load(photo.getServerPath())
                .dontAnimate()
                .into(ivSign);
    }

    public void setWitnessSexKey(String sexKey) {
        this.sexKey = sexKey;
    }

    @Override
    public void closeEdit() {
//        nameEditView.clearFocus();
//        contactNumberEditView.clearFocus();
//        addressEditView.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(nameEditView.getWindowToken(), 0);
    }

    @Override
    public boolean checkTimerShow() {
        return pv_time.isShowing();
    }

    @Override
    public void close(WitnessEntity entity) {
        Intent result = getIntent();
        entity.setPhoto(photo);
        result.putExtra("witness", entity);
        result.putExtra("position", position);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public void showMsgDialog(String s) {
        if (s.equals("")) {
            s = "请填写必填项信息";
        }
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(s)
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        addPresenter.saveWitness(entity,crimeId,false);
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
    public String getCrimeId() {
        return crimeId;
    }

//    @Override
//    public void readIDCardSuccess(IDCardResult result) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //更新entity
//                entity.setWitnessName(result.getName().toString());
//                String sex = result.getGender().toString();
//                entity.setWitnessSex(result.getGender().toString());
//                entity.setWitnessSexKey(sex.equals("男") ? "1" : "2");
//                long yyyyMMdd = StringUtils.String2long(result.getBirthday().toString(), "yyyyMMdd");
//                String sBirthday = StringUtils.long2String(yyyyMMdd, "yyyy-MM-dd");
//                entity.setWitnessBirthday(sBirthday);
//                entity.setWitnessAddress(result.getAddress().toString());
//
//                //更新界面
//                setWitnessName(result.getName().toString());
//                setWitnessSex(sex);
//                setBirthdayDate(sBirthday);
//                setWitnessAddress(result.getAddress().toString());
//            }
//        });
//    }

//    @Override
//    public void readIDCardError(OCRError error) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                ToastUtils.showLong(error.getMessage());
//            }
//        });
//    }

}
