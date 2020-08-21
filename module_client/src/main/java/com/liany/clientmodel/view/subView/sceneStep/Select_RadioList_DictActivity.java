package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.SelectRadio_DictAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.utils.ClickUtils;

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
public class Select_RadioList_DictActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RecyclerView rvSelectRadio;

    private String title;
    private SelectRadio_DictAdapter radioAdapter;
    private List<sysDict> itemList = new ArrayList<>();
    private List<Integer> selectList = new ArrayList<>();
    private sysDict chooseItem;

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
        radioAdapter = new SelectRadio_DictAdapter(R.layout.item_adapter_select_radio,itemList);
        rvSelectRadio.setAdapter(radioAdapter);

        radioAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                radioAdapter.setSelectPosition(position);
                chooseItem = itemList.get(position);
                radioAdapter.notifyDataSetChanged();
            }
        });
        for(int i : selectList) {
            radioAdapter.setSelectPosition(i);
            radioAdapter.notifyDataSetChanged();
        }

//        radioAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//            }
//        },rvSelectRadio);
//        radioAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra(Constants.SELECT_TITLE);
        itemList.clear();
        itemList.addAll((List<sysDict>) getIntent().getSerializableExtra(Constants.SELECT_RADIO_DICT));
        selectList = (List<Integer>) getIntent().getSerializableExtra(Constants.SELECT_POSITION);
        if(selectList.size() > 0) {
            chooseItem = itemList.get(selectList.get(0));
        }
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
            intent.putExtra(Constants.RESULT_RADIO_DICT, chooseItem);
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
