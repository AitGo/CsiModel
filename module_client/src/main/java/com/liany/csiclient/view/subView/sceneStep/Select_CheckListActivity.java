package com.liany.csiclient.view.subView.sceneStep;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.SelectCheckAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.ClickUtils;

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
public class Select_CheckListActivity extends BaseAcitivity implements View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RecyclerView rvSelectCheck;

    private String title;
    private SelectCheckAdapter checkAdapter;
    private List<String> itemList = new ArrayList<>();
    private List<String> chooseList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_select_list;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        rvSelectCheck = findViewById(R.id.rv_select_radio);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(title);

        rvSelectCheck.setLayoutManager(new LinearLayoutManager(this));
        checkAdapter = new SelectCheckAdapter(R.layout.item_adapter_select_check,itemList);
        rvSelectCheck.setAdapter(checkAdapter);

        checkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView imageView = view.findViewById(R.id.iv_select_check);
                if(imageView.isShown()) {
                    imageView.setVisibility(View.INVISIBLE);
                    chooseList.remove(itemList.get(position));
                }else {
                    imageView.setVisibility(View.VISIBLE);
                    chooseList.add(itemList.get(position));
                }
            }
        });
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra(Constants.SELECT_TITLE);
        itemList.add("11111");
        itemList.add("22222");
        itemList.add("33333");
        itemList.add("44444");
        itemList.add("55555");
        itemList.add("11111");
        itemList.add("22222");
        itemList.add("33333");
        itemList.add("44444");
        itemList.add("55555");
        itemList.add("11111");
        itemList.add("22222");
        itemList.add("33333");
        itemList.add("44444");
        itemList.add("55555");
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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

}
