package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Opinion_AddToolContract;
import com.liany.clientmodel.diagnose.ToolEntity;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.presenter.subView.sceneStep.step_window.Opinion_AddToolPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.view.subView.sceneStep.Select_RadioList_DictActivity;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Opinion_AddToolActivity extends BaseAcitivity implements Opinion_AddToolContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    ImageView ivTitleConfirm;
    ClearableEditText etToolName;
    TextView tvToolCategory;
    TextView tvToolSource;

    private Opinion_AddToolContract.Presenter addToolPresenter;
    private ToolEntity toolEntity;
    private int position = -1;
    private String crimeId;
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_opinion_addtool;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        etToolName = findViewById(R.id.et_tool_name);
        tvToolCategory = findViewById(R.id.tv_tool_category);
        tvToolSource = findViewById(R.id.tv_tool_source);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        tvToolCategory.setOnClickListener(this);
        tvToolSource.setOnClickListener(this);


        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.add_tools));
    }

    @Override
    protected void initData() {
        addToolPresenter = new Opinion_AddToolPresenter(this, this);
        toolEntity = (ToolEntity) getIntent().getSerializableExtra("toolEntity");
        position = getIntent().getIntExtra("position",-1);
        crimeId = getIntent().getStringExtra("crimeId");

        addToolPresenter.initViewDate(toolEntity);
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_confirm) {
            addToolPresenter.saveTool(toolEntity, crimeId, true);
        } else if (id == R.id.tv_tool_category) {
            addToolPresenter.toolType();
        } else if (id == R.id.tv_tool_source) {
            addToolPresenter.toolSource();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addToolPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void setToolName(String toolName) {
        etToolName.setText(toolName);
    }

    @Override
    public void setToolCategory(String toolCategory) {
        tvToolCategory.setText(toolCategory);
    }

    @Override
    public void setToolCategory(sysDict dict) {
        if(dict != null) {
            tvToolCategory.setText(dict.getDictValue());
            if(toolEntity == null) {
                toolEntity = new ToolEntity();
            }
            toolEntity.setCategory(dict.getDictValue());
            toolEntity.setCategoryKey(dict.getDictKey());
        }
    }

    @Override
    public void setToolSource(String toolSource) {
        tvToolSource.setText(toolSource);
    }

    @Override
    public void setToolSource(sysDict dict) {
        if(dict != null) {
            tvToolSource.setText(dict.getDictValue());
            if(toolEntity == null) {
                toolEntity = new ToolEntity();
            }
            toolEntity.setSource(dict.getDictValue());
            toolEntity.setSourceKey(dict.getDictKey());
        }
    }

    @Override
    public String getToolName() {
        return etToolName.getText().toString() == null ? "" : etToolName.getText().toString();
    }

    @Override
    public String getToolCategory() {
        return tvToolCategory.getText().toString() == null ? "" : tvToolCategory.getText().toString();
    }

    @Override
    public String getToolSource() {
        return tvToolSource.getText().toString() == null ? "" : tvToolSource.getText().toString();
    }

    @Override
    public void close(ToolEntity entity) {
        Intent intent = getIntent();
        intent.putExtra("toolEntity",entity);
        intent.putExtra("position",position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(Opinion_AddToolActivity.this, Select_RadioList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE,title);
        intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent,selectType);
    }

    @Override
    public void showMsgDialog(String s) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("tuic", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        addToolPresenter.saveTool(toolEntity,crimeId,false);
                        myDialog.dismiss();
                        finish();
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

}
