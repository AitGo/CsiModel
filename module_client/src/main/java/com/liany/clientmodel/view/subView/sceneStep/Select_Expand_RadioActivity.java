package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.SelectExpandRadioAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2019/4/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Select_Expand_RadioActivity extends BaseAcitivity implements View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RecyclerView rvSelectRadio;

    private String title;
    private SelectExpandRadioAdapter expandAdapter;
    private List<MultiItemEntity> value = new ArrayList<>();
    private List<sysDict> values = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_select_list;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        rvSelectRadio = findViewById(R.id.rv_select_radio);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(title);

        rvSelectRadio.setLayoutManager(new LinearLayoutManager(this));
        expandAdapter = new SelectExpandRadioAdapter(value);
        rvSelectRadio.setAdapter(expandAdapter);
        expandAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.rl_level2) {
                    expandAdapter.setSelectPosition(position);
                    expandAdapter.notifyDataSetChanged();
                }
            }
        });
        expandAdapter.expand(0);
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra(Constants.SELECT_TITLE);
        values = (List<sysDict>) getIntent().getSerializableExtra(Constants.SELECT_EXPAND_RADIO_DICT);
        value = StringUtils.sysDict2MultiItem(values);
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
            Intent intent = getIntent();
            intent.putExtra(Constants.RESULT_EXPAND_RADIO_DICT, (Serializable) expandAdapter.getChooseSysDict());
            intent.putExtra(Constants.SELECT_TITLE, title);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

}
