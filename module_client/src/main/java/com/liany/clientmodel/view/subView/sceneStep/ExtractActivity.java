package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.ExtractListAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.ExtractContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.presenter.subView.sceneStep.ExtractPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.Extract_AddActivity;
import com.liany.clientmodel.widget.MyDialog;

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
public class ExtractActivity extends BaseAcitivity implements ExtractContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    ImageView ivTitleAdd;
    TextView tvTitle;
    ImageView ivExpandCommonState;
    TextView tvExpandCommonTitle;
    TextView tvExpandCommon;
    ImageButton ivExpandCommon;
    RelativeLayout llExpandCommon;
    RecyclerView rvExpandCommon;
    ImageView ivExpandLiveState;
    TextView tvExpandLiveTitle;
    TextView tvExpandLiveNum;
    ImageButton ivExpandLive;
    RelativeLayout llExpandLive;
    RecyclerView rvExpandLive;

    private ExtractContract.Presenter presenter;
    private CrimeItem crimeItem;
    private Animation mShowAction;
    private Animation mHiddenAction;
    private View emptyView;
    private ExtractListAdapter commAdapter,liveAdapter;
    private List<GoodEntity> commLists = new ArrayList<>();
    private List<GoodEntity> liveLists = new ArrayList<>();
    private MyDialog myDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_extract;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        ivTitleAdd = findViewById(R.id.iv_title_add);
        tvTitle = findViewById(R.id.tv_title);
        ivExpandCommonState = findViewById(R.id.iv_expand_common_state);
        tvExpandCommonTitle = findViewById(R.id.tv_expand_common_title);
        tvExpandCommon = findViewById(R.id.tv_expand_common);
        ivExpandCommon = findViewById(R.id.iv_expand_common);
        llExpandCommon = findViewById(R.id.ll_expand_common);
        rvExpandCommon = findViewById(R.id.rv_expand_common);
        ivExpandLiveState = findViewById(R.id.iv_expand_live_state);
        tvExpandLiveTitle = findViewById(R.id.tv_expand_live_title);
        tvExpandLiveNum = findViewById(R.id.tv_expand_live_num);
        ivExpandLive = findViewById(R.id.iv_expand_live);
        llExpandLive = findViewById(R.id.ll_expand_live);
        rvExpandLive = findViewById(R.id.rv_expand_live);

        ivTitleBack.setOnClickListener(this);
        ivTitleAdd.setOnClickListener(this);
        ivExpandCommon.setOnClickListener(this);
        llExpandCommon.setOnClickListener(this);
        ivExpandLive.setOnClickListener(this);
        llExpandLive.setOnClickListener(this);

        tvTitle.setText("提取物品");
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);

        rvExpandCommon.setLayoutManager(new LinearLayoutManager(this));
        commAdapter = new ExtractListAdapter(R.layout.item_adapter_extract_list,commLists);
        rvExpandCommon.setAdapter(commAdapter);
        commAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(Extract_AddActivity.class,crimeItem,commLists.get(position),position);
            }
        });
        commAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDelDialog(commLists,position);
                return true;
            }
        });

        rvExpandLive.setLayoutManager(new LinearLayoutManager(this));
        liveAdapter = new ExtractListAdapter(R.layout.item_adapter_extract_list,liveLists);
        rvExpandLive.setAdapter(liveAdapter);
        liveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(Extract_AddActivity.class,crimeItem,liveLists.get(position),position);
            }
        });
        liveAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDelDialog(liveLists,position);
                return true;
            }
        });
        tvExpandCommon.setText("(" + commLists.size() + ")");
        tvExpandLiveNum.setText("(" + liveLists.size() + ")");
        // 没有数据的时候默认显示该布局
//        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvExpandCommon.getParent(), false);
//        commAdapter.setEmptyView(emptyView);
//        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvExpandLive.getParent(), false);
//        liveAdapter.setEmptyView(emptyView);
    }

    @Override
    protected void initData() {
        presenter = new ExtractPresenter(this,this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        presenter.initData(crimeItem,commLists,liveLists);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveExtract();
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
            saveExtract();
        } else if (id == R.id.iv_expand_common) {
            presenter.addCommonExtract(crimeItem);
        } else if (id == R.id.ll_expand_common) {
            if (rvExpandCommon.isShown()) {
                ivExpandCommonState.setBackgroundResource(R.mipmap.expand_close);
                rvExpandCommon.startAnimation(mHiddenAction);
                rvExpandCommon.setVisibility(View.GONE);
            } else {
                ivExpandCommonState.setBackgroundResource(R.mipmap.expand_open);
                rvExpandCommon.startAnimation(mShowAction);
                rvExpandCommon.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_expand_live) {
            presenter.addLiveExtract(crimeItem);
        } else if (id == R.id.ll_expand_live) {
            if (rvExpandLive.isShown()) {
                ivExpandLiveState.setBackgroundResource(R.mipmap.expand_close);
                rvExpandLive.startAnimation(mHiddenAction);
                rvExpandLive.setVisibility(View.GONE);
            } else {
                ivExpandLiveState.setBackgroundResource(R.mipmap.expand_open);
                rvExpandLive.startAnimation(mShowAction);
                rvExpandLive.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivity(Class<?> activity, CrimeItem crimeItem, GoodEntity goodEntity, int position) {
        Intent intent = new Intent(ExtractActivity.this, activity);
        //修改现场
        Bundle bundle = new Bundle();
        bundle.putSerializable("crime", crimeItem);
        bundle.putSerializable("extract", goodEntity);
        bundle.putInt("position",position);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constants.REQUEST_EXTRACT);
    }

    @Override
    public void updateExtract(GoodEntity entity, int position) {
        if(entity.getRev1().equals("0")) {
            commLists.set(position,entity);
            commAdapter.notifyDataSetChanged();
            tvExpandCommon.setText("(" + commLists.size() + ")");
        }else {
            liveLists.set(position,entity);
            liveAdapter.notifyDataSetChanged();
            tvExpandLiveNum.setText("(" + liveLists.size() + ")");
        }
        crimeItem.getGoodEntities().clear();
        crimeItem.getGoodEntities().addAll(commLists);
        crimeItem.getGoodEntities().addAll(liveLists);
    }

    @Override
    public void addExtract(GoodEntity entity) {
        if(entity.getRev1().equals("0")) {
            commLists.add(entity);
            commAdapter.notifyDataSetChanged();
            tvExpandCommon.setText("(" + commLists.size() + ")");
        }else {
            liveLists.add(entity);
            liveAdapter.notifyDataSetChanged();
            tvExpandLiveNum.setText("(" + liveLists.size() + ")");
        }
        crimeItem.getGoodEntities().add(entity);
    }

    @Override
    public void removeExtract(GoodEntity entity) {
        if(entity.getRev1().equals("0")) {
            commLists.remove(entity);
            commAdapter.notifyDataSetChanged();
            tvExpandCommon.setText("(" + commLists.size() + ")");
        }else {
            liveLists.remove(entity);
            liveAdapter.notifyDataSetChanged();
            tvExpandLiveNum.setText("(" + liveLists.size() + ")");
        }
        crimeItem.getGoodEntities().remove(entity);
    }

    private void saveExtract() {
        List<GoodEntity> goodEntities = new ArrayList<>();
        goodEntities.addAll(commLists);
        goodEntities.addAll(liveLists);
        Intent intent = getIntent();
        crimeItem.setGoodEntities(goodEntities);
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void showDelDialog(List<GoodEntity> entities, int positoin) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否删除该数据")
                .setPositiveButton("确定", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        //删除
                        presenter.deleteExtract(entities.get(positoin));
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

}
