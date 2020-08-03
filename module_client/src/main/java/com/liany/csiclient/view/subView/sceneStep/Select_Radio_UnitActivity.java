package com.liany.csiclient.view.subView.sceneStep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.SelectRadio_UnitAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.utils.ClickUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2019/4/24
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Select_Radio_UnitActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RecyclerView recyclerView;

    private SelectRadio_UnitAdapter radioAdapter;
    private String title = "";
    private List<selectUser> itemList = new ArrayList<>();
    private selectUser chooseItem;
    private String checkUser;
    private int selectPosition = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_select_tree;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        recyclerView = findViewById(R.id.rv_list);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        radioAdapter = new SelectRadio_UnitAdapter(R.layout.item_adapter_select_check,itemList);
        recyclerView.setAdapter(radioAdapter);

        radioAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                radioAdapter.setSelectPosition(position);
                chooseItem = itemList.get(position);
                radioAdapter.notifyDataSetChanged();
            }
        });

        radioAdapter.setSelectPosition(selectPosition);
        radioAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra(Constants.SELECT_TITLE);
        itemList.clear();
        itemList.addAll((List<selectUser>) getIntent().getSerializableExtra(Constants.SELECT_RADIO_USER));
        checkUser = (String) getIntent().getStringExtra(Constants.SELECT_POSITION);
        for(selectUser user : itemList) {
            if(user.getShortName().equals(checkUser)) {
                selectPosition = itemList.indexOf(user);
                chooseItem = itemList.get(itemList.indexOf(user));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
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
            intent.putExtra(Constants.RESULT_RADIO_USER, chooseItem);
            intent.putExtra(Constants.SELECT_TITLE, title);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
