package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.SelectCheck_UserAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.diagnose.selectUser;
import com.liany.clientmodel.utils.ClickUtils;

import java.io.Serializable;
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
public class Select_Check_UserActivity extends BaseAcitivity implements View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    LinearLayout llList;
    RecyclerView recyclerView;

    private SelectCheck_UserAdapter checkAdapter;
    private String title = "";
    private List<selectUser> itemList = new ArrayList<>();
    private List<selectUser> chooseList = new ArrayList<>();

    private String getPeople = "提取人";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_select_tree;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        llList = findViewById(R.id.ll_list);
        recyclerView = findViewById(R.id.rv_list);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);


        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText(title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkAdapter = new SelectCheck_UserAdapter(R.layout.item_adapter_select_check,itemList);
        recyclerView.setAdapter(checkAdapter);

        checkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView imageView = view.findViewById(R.id.iv_select_check);
                if(itemList.get(position).isCheck()) {
                    imageView.setVisibility(View.INVISIBLE);
                    chooseList.remove(itemList.get(position));
                    itemList.get(position).setCheck(false);
                }else {
                    imageView.setVisibility(View.VISIBLE);
                    chooseList.add(itemList.get(position));
                    itemList.get(position).setCheck(true);
                }
                checkAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra(Constants.SELECT_TITLE);
        itemList.clear();
        itemList.addAll((List<selectUser>) getIntent().getSerializableExtra(Constants.SELECT_CHECK_USER));
        String value = getIntent().getStringExtra(Constants.SELECT_POSITION);
        if(value != null && !value.equals("")) {
            String[] values = value.split(",");
            for(selectUser user : itemList) {
                for(String userId : values) {
                    if(user.getUserId().equals(userId)) {
                        chooseList.add(user);
                        user.setCheck(true);
                    }
                }
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
            if (title.equals("勘验检查人员") && chooseList.size() < 2) {
                Toast.makeText(this, "勘验检查人员必须2人以上", Toast.LENGTH_LONG).show();
                return;
            }
            if (title.equals(getPeople) && chooseList.size() < 2) {
                Toast.makeText(this, getPeople + "必须2人以上", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = getIntent();
            intent.putExtra(Constants.RESULT_CHECK_USER, (Serializable) chooseList);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

}
