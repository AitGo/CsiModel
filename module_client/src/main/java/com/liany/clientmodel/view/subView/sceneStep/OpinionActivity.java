package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.AddToolAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.OpinionContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.diagnose.ToolEntity;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.presenter.subView.sceneStep.OpinionPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.Opinion_AddToolActivity;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;

import java.io.Serializable;
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
public class OpinionActivity extends BaseAcitivity implements OpinionContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    TextView peopleNumber;
    TextView crimeMeans;
    TextView crimeCharacter;
    TextView crimeEntrance;
    TextView crimeTiming;
    TextView selectObject;
    TextView crimeExport;
    ClearableEditText peopleFeature;
    TextView crimeFeature;
    TextView intrusiveMethod;
    TextView selectLocation;
    TextView crimePurpose;
    ImageView ivTitleOpinionsearch;
    LinearLayout addTool;
    RecyclerView toolListView;

    private List<ToolEntity> toolEntityList = new ArrayList<>();
    private AddToolAdapter addToolAdapter;
    private OpinionContract.Presenter opinionPresenter;
    private CrimeItem crimeItem;
    private MyDialog myDialog,toolDelDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_opinion;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        peopleNumber = findViewById(R.id.peopleNumber);
        crimeMeans = findViewById(R.id.crimeMeans);
        crimeCharacter = findViewById(R.id.crimeCharacter);
        crimeEntrance = findViewById(R.id.crimeEntrance);
        crimeTiming = findViewById(R.id.crimeTiming);
        selectObject = findViewById(R.id.selectObject);
        crimeExport = findViewById(R.id.crimeExport);
        peopleFeature = findViewById(R.id.peopleFeature);
        crimeFeature = findViewById(R.id.crimeFeature);
        intrusiveMethod = findViewById(R.id.intrusiveMethod);
        selectLocation = findViewById(R.id.selectLocation);
        crimePurpose = findViewById(R.id.crimePurpose);
        ivTitleOpinionsearch = findViewById(R.id.iv_title_opinionsearch);
        addTool = findViewById(R.id.add_tool);
        toolListView = findViewById(R.id.tool_listView);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        peopleNumber.setOnClickListener(this);
        crimeMeans.setOnClickListener(this);
        crimeCharacter.setOnClickListener(this);
        crimeEntrance.setOnClickListener(this);
        crimeTiming.setOnClickListener(this);
        selectObject.setOnClickListener(this);
        crimeExport.setOnClickListener(this);
        crimeFeature.setOnClickListener(this);
        intrusiveMethod.setOnClickListener(this);
        selectLocation.setOnClickListener(this);
        crimePurpose.setOnClickListener(this);
        ivTitleOpinionsearch.setOnClickListener(this);
        addTool.setOnClickListener(this);


        ivTitleOpinionsearch.setVisibility(Constants.state_isShowOpinionQueryBtn ? View.VISIBLE : View.INVISIBLE);
        tvTitle.setText(getString(R.string.title_activity_step6));
        opinionPresenter.initValue(crimeItem);

        toolListView.setLayoutManager(new LinearLayoutManager(this));
        addToolAdapter = new AddToolAdapter(R.layout.item__adapter_tool, toolEntityList);
        toolListView.setAdapter(addToolAdapter);
        addToolAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                opinionPresenter.onAddToolItemClick(toolEntityList,position);
            }
        });

        addToolAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                opinionPresenter.onAddToolItemLongClick(toolEntityList,position,addToolAdapter);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        opinionPresenter = new OpinionPresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        if(crimeItem.getId() != null && crimeItem.getCrimeToolItem() != null) {
            toolEntityList.addAll(crimeItem.getCrimeToolItem());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            opinionPresenter.saveOpinion(crimeItem,toolEntityList);
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
            opinionPresenter.saveOpinion(crimeItem, toolEntityList);
        } else if (id == R.id.iv_title_confirm) {
            opinionPresenter.saveOpinion(crimeItem, toolEntityList);
        } else if (id == R.id.peopleNumber) {//作案人数
            opinionPresenter.peopleNumber();
        } else if (id == R.id.crimeMeans) {//作案手段
            opinionPresenter.crimeMeans();
        } else if (id == R.id.crimeCharacter) {//案件性质
            opinionPresenter.crimeCharacter(crimeItem);
        } else if (id == R.id.crimeEntrance) {//作案入口
            opinionPresenter.crimeEntrance();
        } else if (id == R.id.crimeTiming) {//作案时机
            opinionPresenter.crimeTiming();
        } else if (id == R.id.selectObject) {//选择对象
            opinionPresenter.selectObject();
        } else if (id == R.id.crimeExport) {//作案出口
            opinionPresenter.crimeExport();
        } else if (id == R.id.crimeFeature) {//作案特点
            opinionPresenter.crimeFeature();
        } else if (id == R.id.intrusiveMethod) {//侵入方式
            opinionPresenter.intrusiveMethod();
        } else if (id == R.id.selectLocation) {//选择处所
            opinionPresenter.selectLocation(crimeItem);
        } else if (id == R.id.crimePurpose) {//作案动机目的
            opinionPresenter.crimePurpose();
        } else if (id == R.id.iv_title_opinionsearch) {//同类案件查询，根据作案手段和案件性质，查询最近一段时间同类案件
            opinionPresenter.getOpinionSearchResult(crimeItem);
        } else if (id == R.id.add_tool) {//添加作案工具
            Intent addTool = new Intent(OpinionActivity.this, Opinion_AddToolActivity.class);
            addTool.putExtra("crimeId", crimeItem.getId());
            startActivityForResult(addTool, Constants.REQUEST_VISIT_ADD_TOOL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        opinionPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public String getPeopleNumber() {
        return peopleNumber.getText().toString();
    }

    @Override
    public void setPeopleNumber(String number) {
        peopleNumber.setText(number);
    }

    @Override
    public void setPeopleNumber(sysDict dict) {
        if(dict != null) {
            peopleNumber.setText(dict.getDictValue());
            crimeItem.setCrimePeopleNumber(dict.getDictValue());
            crimeItem.setCrimePeopleNumberKey(dict.getDictKey());
        }
    }

    @Override
    public String getCrimeMeans() {
        return crimeMeans.getText().toString();
    }

    @Override
    public void setCrimeMeans(String means) {
        crimeMeans.setText(means);
    }

    @Override
    public void setCrimeMeans(List<sysDict> dicts) {
        crimeMeans.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeMeans(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeMeansKey(StringUtils.sysDictKey2String(dicts));
        for (sysDict dict : dicts) {
            //判断是否是已配置案件
            String json = FileUtils.ReadAssetsFile(this, "crimeMeans.json");
            Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(json, CrimeItem.class);
            for (CrimeItem item : response.getData()) {
                if (item.getCrimeMeansKey().equals(dict.getDictKey()) && crimeItem.getCrimeCharacterKey().equals(item.getCrimeCharacterKey())) {
                    //撬门/锁、撞门、踢门类案件
                    crimeItem.setCrimeEntrance(item.getCrimeEntrance());
                    crimeItem.setCrimeEntranceKey(item.getCrimeEntranceKey());
                    setCrimeEntrance(item.getCrimeEntrance());
                    crimeItem.setCrimeExport(item.getCrimeExport());
                    crimeItem.setCrimeExportKey(item.getCrimeExportKey());
                    setCrimeExport(item.getCrimeExport());
//                    crimeItem.setCrimeMeans(item.getCrimeMeans());
//                    crimeItem.setCrimeMeansKey(item.getCrimeMeansKey());
//                    setCrimeMeans(item.getCrimeMeans());
                    crimeItem.setIntrusiveMethod(item.getIntrusiveMethod());
                    crimeItem.setIntrusiveMethodKey(item.getIntrusiveMethodKey());
                    setIntrusiveMethod(item.getIntrusiveMethod());
                }
            }
        }
    }

    @Override
    public String getCrimeCharacter() {
        return crimeCharacter.getText().toString();
    }

    @Override
    public void setCrimeCharacter(String character) {
        crimeCharacter.setText(character);
    }

    @Override
    public void setCrimeCharacter(List<sysDict> dicts) {
        crimeCharacter.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeCharacter(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeCharacterKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public void setCrimeCharacter(sysDict dict) {
        if(dict != null) {
            crimeCharacter.setText(dict.getDictValue());
            crimeItem.setCrimeCharacter(dict.getDictValue());
            crimeItem.setCrimeCharacterKey(dict.getDictKey());
            //判断是否是已配置案件
            String json = FileUtils.ReadAssetsFile(this, "crimeCharacter.json");
            Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(json, CrimeItem.class);
            for (CrimeItem item : response.getData()) {
                if (item.getCrimeCharacterKey().equals(dict.getDictKey())) {
//                if(!StringUtils.checkString(crimeItem.getCrimeEntrance()) && !StringUtils.checkString(crimeItem.getCrimeEntranceKey())) {
                    crimeItem.setCrimeEntrance(item.getCrimeEntrance());
                    crimeItem.setCrimeEntranceKey(item.getCrimeEntranceKey());
                    setCrimeEntrance(item.getCrimeEntrance());
//                }
//                if(!StringUtils.checkString(crimeItem.getCrimeExport()) && !StringUtils.checkString(crimeItem.getCrimeExportKey())) {
                    crimeItem.setCrimeExport(item.getCrimeExport());
                    crimeItem.setCrimeExportKey(item.getCrimeExportKey());
                    setCrimeExport(item.getCrimeExport());
//                }
//                if(!StringUtils.checkString(crimeItem.getCrimeMeans()) && !StringUtils.checkString(crimeItem.getCrimeMeansKey())) {
                    crimeItem.setCrimeMeans(item.getCrimeMeans());
                    crimeItem.setCrimeMeansKey(item.getCrimeMeansKey());
                    setCrimeMeans(item.getCrimeMeans());
//                }
//                if(!StringUtils.checkString(crimeItem.getIntrusiveMethod()) && !StringUtils.checkString(crimeItem.getIntrusiveMethodKey())) {
                    crimeItem.setIntrusiveMethod(item.getIntrusiveMethod());
                    crimeItem.setIntrusiveMethodKey(item.getIntrusiveMethodKey());
                    setIntrusiveMethod(item.getIntrusiveMethod());
//                }
                }
            }
        }
    }

    @Override
    public String getCrimeEntrance() {
        return crimeEntrance.getText().toString();
    }

    @Override
    public void setCrimeEntrance(String entrance) {
        crimeEntrance.setText(entrance);
    }

    @Override
    public void setCrimeEntrance(List<sysDict> dicts) {
        crimeEntrance.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeEntrance(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeEntranceKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getCrimeTiming() {
        return crimeTiming.getText().toString();
    }

    @Override
    public void setCrimeTiming(String timing) {
        crimeTiming.setText(timing);
    }

    @Override
    public void setCrimeTiming(List<sysDict> dict) {
        crimeTiming.setText(StringUtils.sysDictValue2String(dict));
        crimeItem.setCrimeTiming(StringUtils.sysDictValue2String(dict));
        crimeItem.setCrimeTimingKey(StringUtils.sysDictKey2String(dict));
    }

    @Override
    public String getSelectObject() {
        return selectObject.getText().toString();
    }

    @Override
    public void setSelectObject(String object) {
        selectObject.setText(object);
    }

    @Override
    public void setSelectObject(List<sysDict> dicts) {
        selectObject.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectObject(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectObjectKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getCrimeExport() {
        return crimeExport.getText().toString();
    }

    @Override
    public void setCrimeExport(String export) {
        crimeExport.setText(export);
    }

    @Override
    public void setCrimeExport(List<sysDict> dicts) {
        crimeExport.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeExport(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeExportKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getPeopleFeature() {
        return peopleFeature.getText().toString();
    }

    @Override
    public void setPeopleFeature(String peopleFeature) {
        this.peopleFeature.setText(peopleFeature);
    }

    @Override
    public String getCrimeFeature() {
        return crimeFeature.getText().toString();
    }

    @Override
    public void setCrimeFeature(String crimeFeature) {
        this.crimeFeature.setText(crimeFeature);
    }

    @Override
    public void setCrimeFeature(List<sysDict> dicts) {
        this.crimeFeature.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeFeature(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimeFeatureKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getIntrusiveMethod() {
        return intrusiveMethod.getText().toString();
    }

    @Override
    public void setIntrusiveMethod(String method) {
        intrusiveMethod.setText(method);
    }

    @Override
    public void setIntrusiveMethod(List<sysDict> dicts) {
        intrusiveMethod.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setIntrusiveMethod(StringUtils.sysDictValue2String(dicts));
        crimeItem.setIntrusiveMethodKey(StringUtils.sysDictKey2String(dicts));
        for (sysDict dict : dicts) {
            //判断是否是已配置案件
            String json = FileUtils.ReadAssetsFile(this, "intrusiveMethod.json");
            Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(json, CrimeItem.class);
            for (CrimeItem item : response.getData()) {
                if (item.getIntrusiveMethodKey().equals(dict.getDictKey()) && crimeItem.getCrimeCharacterKey().equals(item.getCrimeCharacterKey())) {
                    //撬门/锁、撞门、踢门类案件
                    crimeItem.setCrimeEntrance(item.getCrimeEntrance());
                    crimeItem.setCrimeEntranceKey(item.getCrimeEntranceKey());
                    setCrimeEntrance(item.getCrimeEntrance());
                    crimeItem.setCrimeExport(item.getCrimeExport());
                    crimeItem.setCrimeExportKey(item.getCrimeExportKey());
                    setCrimeExport(item.getCrimeExport());
                    crimeItem.setCrimeMeans(item.getCrimeMeans());
                    crimeItem.setCrimeMeansKey(item.getCrimeMeansKey());
                    setCrimeMeans(item.getCrimeMeans());
//                    crimeItem.setIntrusiveMethod(item.getIntrusiveMethod());
//                    crimeItem.setIntrusiveMethodKey(item.getIntrusiveMethodKey());
//                    setIntrusiveMethod(item.getIntrusiveMethod());
                }
            }
        }
    }

    @Override
    public String getSelectLocation() {
        return selectLocation.getText().toString();
    }

    @Override
    public void setSelectLocation(String location) {
        selectLocation.setText(location);
    }

    @Override
    public void setSelectLocation(List<sysDict> dicts) {
        selectLocation.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectLocation(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectLocationKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getCrimePurpose() {
        return crimePurpose.getText().toString();
    }

    @Override
    public void setCrimePurpose(String purpose) {
        crimePurpose.setText(purpose);
    }

    @Override
    public void setCrimePurpose(List<sysDict> dicts) {
        crimePurpose.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimePurpose(StringUtils.sysDictValue2String(dicts));
        crimeItem.setCrimePurposeKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public void saveOpinion(CrimeItem crimeItem) {
        Intent intent = getIntent();
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void startRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(OpinionActivity.this, Select_RadioList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(OpinionActivity.this, Select_CheckList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startExpandCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(OpinionActivity.this, Select_Expand_CheckActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_EXPAND_CHECK_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startExpandRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(OpinionActivity.this, Select_Expand_RadioActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_EXPAND_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void showMsgDialog(String msg) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(msg)
                .setPositiveButton("确定", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        myDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

    @Override
    public void startExpand3CheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(OpinionActivity.this, Select_Expand3_CheckActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_EXPAND_CHECK_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void addTool(ToolEntity entity, int position) {
        if(position != -1) {
            toolEntityList.remove(position);
            toolEntityList.add(position,entity);
        }else {
            toolEntityList.add(entity);
        }
        addToolAdapter.notifyDataSetChanged();
    }

    @Override
    public void startAddToolActivity(ToolEntity toolEntity, int position) {
        Intent intent = new Intent(OpinionActivity.this,Opinion_AddToolActivity.class);
        intent.putExtra("toolEntity",toolEntity);
        intent.putExtra("position",position);
        intent.putExtra("crimeId",crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_VISIT_ADD_TOOL);
    }

    @Override
    public void showToolDeleteDialog(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        toolDelDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        opinionPresenter.deleteTool(toolEntityList,position,addToolAdapter);
                        toolDelDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toolDelDialog.dismiss();
                    }
                })
                .create();
        toolDelDialog.show();
    }
}
