package com.liany.csiclient.view.subView.sceneStep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.BaseInfoContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.presenter.subView.sceneStep.BaseInfoPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.view.subView.CreateSceneActivity;
import com.liany.csiclient.widget.ClearableEditText;
import com.liany.csiclient.widget.MyDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class BaseInfoActivity extends BaseAcitivity implements BaseInfoContract.View, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    TextView accessInspectors;
    TextView weatherCondition;
    TextView windDirection;
    ClearableEditText temperature;
    ClearableEditText humidity;
    TextView area;
    ClearableEditText unitsAssigned;
    ClearableEditText accessPolicemen;
    TextView illuminationCondition;
    ClearableEditText productPeopleName;
    ClearableEditText productPeopleUnit;
    ClearableEditText productPeopleDuties;
    ClearableEditText safeguard;
    TextView sceneConductor;
    RadioButton radioSceneCondition1;
    RadioButton radioSceneCondition2;
    RadioGroup radioGroupSceneCondition;
    CheckBox InformantCkBx;
    CheckBox VictimCkBx;
    CheckBox OtherCkBx;
    ClearableEditText changeReason;
    LinearLayout changeReasonLinear;
    ImageView ivTitleConfirm;

    private CrimeItem crimeItem;
    private BaseInfoContract.Presenter presenter;
    private String sceneCondition = "原始现场";
    private String sceneConditionKey = "1";
    private List<String> changeOptionList = new ArrayList<>();
    private List<String> changeOptionKeyList = new ArrayList<>();
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_baseinfo;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        accessInspectors = findViewById(R.id.accessInspectors);
        weatherCondition = findViewById(R.id.weatherCondition);
        windDirection = findViewById(R.id.windDirection);
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        area = findViewById(R.id.area);
        unitsAssigned = findViewById(R.id.unitsAssigned);
        accessPolicemen = findViewById(R.id.accessPolicemen);
        illuminationCondition = findViewById(R.id.illuminationCondition);
        productPeopleName = findViewById(R.id.productPeople_name);
        productPeopleUnit = findViewById(R.id.productPeople_unit);
        productPeopleDuties = findViewById(R.id.productPeople_duties);
        safeguard = findViewById(R.id.safeguard);
        sceneConductor = findViewById(R.id.sceneConductor);
        radioSceneCondition1 = findViewById(R.id.radioSceneCondition1);
        radioSceneCondition2 = findViewById(R.id.radioSceneCondition2);
        radioGroupSceneCondition = findViewById(R.id.radioGroupSceneCondition);
        InformantCkBx = findViewById(R.id.InformantCkBx);
        VictimCkBx = findViewById(R.id.VictimCkBx);
        OtherCkBx = findViewById(R.id.OtherCkBx);
        changeReason = findViewById(R.id.change_reason);
        changeReasonLinear = findViewById(R.id.change_reason_linear);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        ivTitleBack.setOnClickListener(this);
        accessInspectors.setOnClickListener(this);
        weatherCondition.setOnClickListener(this);
        windDirection.setOnClickListener(this);
        area.setOnClickListener(this);
        illuminationCondition.setOnClickListener(this);
        sceneConductor.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        InformantCkBx.setOnCheckedChangeListener(this);
        VictimCkBx.setOnCheckedChangeListener(this);
        OtherCkBx.setOnCheckedChangeListener(this);

        tvTitle.setText("基本信息录入");
        ivTitleConfirm.setVisibility(View.GONE);
        presenter.initView(crimeItem);
        if (crimeItem.getSceneConditionKey().equals("1")) {
            crimeItem.setSceneConditionKey("1");
            InformantCkBx.setVisibility(View.GONE);
            VictimCkBx.setVisibility(View.GONE);
            OtherCkBx.setVisibility(View.GONE);
            changeReason.setVisibility(View.GONE);
            radioSceneCondition1.setChecked(true);
            radioSceneCondition2.setChecked(false);
        } else if (crimeItem.getSceneConditionKey().equals("2")) {
            crimeItem.setSceneConditionKey("2");
            InformantCkBx.setVisibility(View.VISIBLE);
            VictimCkBx.setVisibility(View.VISIBLE);
            OtherCkBx.setVisibility(View.VISIBLE);
            changeReason.setVisibility(View.VISIBLE);
            radioSceneCondition1.setChecked(false);
            radioSceneCondition2.setChecked(true);
        }

        radioGroupSceneCondition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radioSceneCondition1) {//原始现场
                    setSceneChangeShow(View.GONE);
                    sceneCondition = "原始现场";
                    sceneConditionKey = "1";
                    crimeItem.setSceneCondition("原始现场");
                    crimeItem.setSceneConditionKey("1");
                } else if (checkedRadioButtonId == R.id.radioSceneCondition2) {//变动现场
                    setSceneChangeShow(View.VISIBLE);
                    sceneCondition = "变动现场";
                    sceneConditionKey = "2";
                    crimeItem.setSceneCondition("变动现场");
                    crimeItem.setSceneConditionKey("2");
                }
            }
        });
    }

    @Override
    protected void initData() {
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        presenter = new BaseInfoPresenter(this, this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            CrimeItem crimeItem = presenter.saveBaseInfo(this.crimeItem);
            setResultForCreate(crimeItem);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.InformantCkBx) {
            if (isChecked) {
                changeOptionList.add("报案人进入");
                changeOptionKeyList.add("1002");
            } else {
                changeOptionList.remove("报案人进入");
                changeOptionKeyList.remove("1002");
            }
        } else if (id == R.id.VictimCkBx) {
            if (isChecked) {
                changeOptionList.add("事主进入");
                changeOptionKeyList.add("1001");
            } else {
                changeOptionList.remove("事主进入");
                changeOptionKeyList.remove("1001");
            }
        } else if (id == R.id.OtherCkBx) {
            if (isChecked) {
                changeOptionList.add("其他");
                changeOptionKeyList.add("1003");
            } else {
                changeOptionList.remove("其他");
                changeOptionKeyList.remove("1003");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            CrimeItem crimeItem = presenter.saveBaseInfo(this.crimeItem);
            setResultForCreate(crimeItem);
        } else if (id == R.id.accessInspectors) {
            presenter.accessInspectors();
        } else if (id == R.id.weatherCondition) {
            presenter.weatherCondition();
        } else if (id == R.id.windDirection) {
            presenter.windDirection();
        } else if (id == R.id.area) {
            presenter.area();
        } else if (id == R.id.illuminationCondition) {
            presenter.illuminationCondition();
        } else if (id == R.id.sceneConductor) {
            presenter.sceneConductor();
        } else if (id == R.id.iv_title_confirm) {//                showSaveDialog();
        }
    }

    public void setSceneChangeShow(int visit) {
        InformantCkBx.setVisibility(visit);
        VictimCkBx.setVisibility(visit);
        OtherCkBx.setVisibility(visit);
        changeReason.setVisibility(visit);
    }

    @Override
    public void setAccessInspectors(String accessInspectors) {
        this.accessInspectors.setText(accessInspectors);
    }

    @Override
    public void setAccessInspectors(List<selectUser> users) {
        this.accessInspectors.setText(StringUtils.selectUserValue2String(users));
        crimeItem.setAccessInspectors(StringUtils.selectUserValue2String(users));
        crimeItem.setAccessInspectorsKey(StringUtils.selectUserKey2String(users));
        crimeItem.setRev1(StringUtils.selectUserId2String(users));
    }

    @Override
    public String getAccessInspectors() {
        return this.accessInspectors.getText().toString();
    }

    @Override
    public String getAccessInspectorsId() {
        return crimeItem.getRev1();
    }

    @Override
    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition.setText(weatherCondition);
    }

    @Override
    public void setWeatherCondition(sysDict dict) {
        if(dict != null) {
            this.weatherCondition.setText(dict.getDictValue());
            crimeItem.setWeatherCondition(dict.getDictValue());
            crimeItem.setWeatherConditionKey(dict.getDictKey());
        }
    }

    @Override
    public String getWeatherCondition() {
        return weatherCondition.getText().toString();
    }

    @Override
    public void setWindDirection(String windDirection) {
        this.windDirection.setText(windDirection);
    }

    @Override
    public void setWindDirection(sysDict dict) {
        if(dict != null) {
            this.windDirection.setText(dict.getDictValue());
            crimeItem.setWindDirection(dict.getDictValue());
            crimeItem.setWindDirectionKey(dict.getDictKey());
        }
    }

    @Override
    public String getWindDirection() {
        return this.windDirection.getText().toString();
    }

    @Override
    public void setTemperature(String temperature) {
        this.temperature.setText(temperature);
    }

    @Override
    public String getTemperature() {
        return this.temperature.getText().toString();
    }

    @Override
    public void setHumidity(String humidity) {
        this.humidity.setText(humidity);
    }

    @Override
    public String getHumidity() {
        return this.humidity.getText().toString();
    }

    @Override
    public void setArea(String area) {
        this.area.setText(area);
    }

    @Override
    public void setArea(selectUser area) {
        if(area != null) {
            this.area.setText(area.getShortName());
            crimeItem.setArea(area.getShortName());
            crimeItem.setAreaKey(area.getOrganId());
        }
    }

    @Override
    public String getArea() {
        return area.getText().toString();
    }

    @Override
    public void setUnitsAssigned(String unitsAssigned) {
        this.unitsAssigned.setText(unitsAssigned);
    }

    @Override
    public String getUnitsAssigned() {
        return this.unitsAssigned.getText().toString();
    }

    @Override
    public void setAccessPolicemen(String accessPolicemen) {
        this.accessPolicemen.setText(accessPolicemen);
    }

    @Override
    public String getAccessPolicemen() {
        return this.accessPolicemen.getText().toString();
    }

    @Override
    public void setIlluminationCondition(String illuminationCondition) {
        this.illuminationCondition.setText(illuminationCondition);
    }

    @Override
    public void setIlluminationCondition(sysDict dict) {
        if(dict != null) {
            this.illuminationCondition.setText(dict.getDictValue());
            crimeItem.setIlluminationCondition(dict.getDictValue());
            crimeItem.setIlluminationConditionKey(dict.getDictKey());
        }
    }

    @Override
    public String getIlluminationCondition() {
        return this.illuminationCondition.getText().toString();
    }

    @Override
    public void setProductPeopleName(String productPeopleName) {
        this.productPeopleName.setText(productPeopleName);
    }

    @Override
    public String getProductPeopleName() {
        return this.productPeopleName.getText().toString();
    }

    @Override
    public void setProductPeopleUnit(String productPeopleUnit) {
        this.productPeopleUnit.setText(productPeopleUnit);
    }

    @Override
    public String getProductPeopleUnit() {
        return this.productPeopleUnit.getText().toString();
    }

    @Override
    public void setProductPeopleDuties(String productPeopleDuties) {
        this.productPeopleDuties.setText(productPeopleDuties);
    }

    @Override
    public String getProductPeopleDuties() {
        return this.productPeopleDuties.getText().toString();
    }

    @Override
    public void setSafeguard(String safeguard) {
        this.safeguard.setText(safeguard);
    }

    @Override
    public String getSafeguard() {
        return this.safeguard.getText().toString();
    }

    @Override
    public void setSceneConductor(String sceneConductor) {
        this.sceneConductor.setText(sceneConductor);
    }

    @Override
    public void setSceneConductor(selectUser user) {
        if(user != null) {
            this.sceneConductor.setText(user.getTrueName());
            crimeItem.setSceneConductor(user.getTrueName());
            crimeItem.setSceneConductorKey(user.getTechId());
        }
    }

    @Override
    public String getSceneConductor() {
        return this.sceneConductor.getText().toString();
    }

    @Override
    public void setSceneCondition(String sceneConditionKey) {
        if (sceneConditionKey == null || sceneConditionKey.equals("")) {
            sceneConditionKey = "1";
        }
        if (sceneConditionKey.equals("1")) {
            crimeItem.setSceneCondition("原始现场");
            crimeItem.setSceneConditionKey("1");
            sceneCondition = "原始现场";
            this.sceneConditionKey = "1";
            radioSceneCondition1.setChecked(true);
            radioSceneCondition2.setChecked(false);
            InformantCkBx.setVisibility(View.GONE);
            VictimCkBx.setVisibility(View.GONE);
            OtherCkBx.setVisibility(View.GONE);
            changeReason.setVisibility(View.GONE);
        } else if (sceneConditionKey.equals("2")) {
            crimeItem.setSceneCondition("变动现场");
            crimeItem.setSceneConditionKey("2");
            sceneCondition = "变动现场";
            this.sceneConditionKey = "2";
            radioSceneCondition1.setChecked(false);
            radioSceneCondition2.setChecked(true);
            InformantCkBx.setVisibility(View.VISIBLE);
            VictimCkBx.setVisibility(View.VISIBLE);
            OtherCkBx.setVisibility(View.VISIBLE);
            changeReason.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setChangeOption(String changeOptionKey) {
        if (changeOptionKey != null && !changeOptionKey.equals("")) {
            String[] optionKeys = changeOptionKey.split(",");
            for (int i = 0; i < optionKeys.length; i++) {
                if (optionKeys[i].equals("1002")) {
                    InformantCkBx.setChecked(true);
                } else if (optionKeys[i].equals("1001")) {
                    VictimCkBx.setChecked(true);
                } else if (optionKeys[i].equals("1003")) {
                    OtherCkBx.setChecked(true);
                }
            }
        }
    }

    @Override
    public String getChangeOption() {
        String changeOptions = "";
        if (changeOptionList.size() > 0) {
            for (int i = 0; i < changeOptionList.size(); i++) {
                if (i != changeOptionList.size() - 1) {
                    changeOptions = changeOptions + changeOptionList.get(i) + ",";
                } else {
                    changeOptions = changeOptions + changeOptionList.get(i);
                }

            }
        }
        return changeOptions;
    }

    @Override
    public String getChangeOptionKey() {
        String changeOptionKeys = "";
        if (changeOptionKeyList.size() > 0) {
            for (int i = 0; i < changeOptionKeyList.size(); i++) {
                if (i != changeOptionKeyList.size() - 1) {
                    changeOptionKeys = changeOptionKeys + changeOptionKeyList.get(i) + ",";
                } else {
                    changeOptionKeys = changeOptionKeys + changeOptionKeyList.get(i);
                }

            }
        }
        return changeOptionKeys;
    }

    @Override
    public void setChangeReason(String changeReason) {
        this.changeReason.setText(changeReason);
    }

    @Override
    public String getChagenReason() {
        return changeReason.getText().toString();
    }

    @Override
    public void setRadioSceneCondition1(String text) {
        radioSceneCondition1.setText(text);
    }

    @Override
    public String getRadioSceneCondition1() {
        return radioSceneCondition1.getText().toString();
    }

    @Override
    public void setRadioSceneCondition2(String text) {
        radioSceneCondition2.setText(text);
    }

    @Override
    public String getRadioSceneCondition2() {
        return radioSceneCondition2.getText().toString();
    }

    @Override
    public String getSceneCondition() {
        return sceneCondition;
    }

    @Override
    public String getSceneConditionKey() {
        return sceneConditionKey;
    }

    @Override
    public void startCreateSceneView(CrimeItem crimeItem) {
        Intent intent = new Intent(this, CreateSceneActivity.class);
        intent.putExtra("crime",crimeItem);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(BaseInfoActivity.this, Select_RadioList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUserView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(BaseInfoActivity.this, Select_Check_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUserRadioView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(BaseInfoActivity.this, Select_Radio_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUnitRadioView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(BaseInfoActivity.this, Select_Radio_UnitActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    public void showSaveDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要创建现场")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.saveCrimeItem(crimeItem);
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

    public void setResultForCreate(CrimeItem crimeItem) {
        Intent intent = getIntent();
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
