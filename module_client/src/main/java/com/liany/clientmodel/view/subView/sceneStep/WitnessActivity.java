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
import com.liany.clientmodel.adapter.WitnessAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.WitnessContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.WitnessEntity;
import com.liany.clientmodel.presenter.subView.sceneStep.WitnessPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.Witness_AddActivity;
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
public class WitnessActivity extends BaseAcitivity implements WitnessContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    LinearLayout addWitnessButton;
    RecyclerView rvWitness;

    private WitnessContract.Presenter witnessPresenter;
    private CrimeItem crimeItem;
    private List<WitnessEntity> witnessList = new ArrayList<>();
    private WitnessAdapter adapter;
    private MyDialog delDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_witness;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        addWitnessButton = findViewById(R.id.add_witness_button);
        rvWitness = findViewById(R.id.rv_witness);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        addWitnessButton.setOnClickListener(this);

        tvTitle.setText(getString(R.string.title_activity_step7));
        rvWitness.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WitnessAdapter(R.layout.item_adapter_witness, witnessList);
        rvWitness.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startWitnessAdd(witnessList.get(position), position);
            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                witnessPresenter.showDelWitnessDialog(position);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        witnessPresenter = new WitnessPresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");

        if (crimeItem.getId() != null && crimeItem.getWitnessItem() != null) {
            witnessList.addAll(crimeItem.getWitnessItem());
        }
        if (crimeItem.getWitnessItem().size() == 0) {
            WitnessEntity entity = new WitnessEntity();
            entity.setId(StringUtils.getUUID());
            entity.setCrimeId(crimeItem.getId());
            startWitnessAdd(entity, -1);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveWitness();
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
            saveWitness();
        } else if (id == R.id.iv_title_confirm) {
            saveWitness();
        } else if (id == R.id.add_witness_button) {
            WitnessEntity entity = new WitnessEntity();
            entity.setId(StringUtils.getUUID());
            entity.setCrimeId(crimeItem.getId());
            startWitnessAdd(entity, -1);
        }
    }

    private void startWitnessAdd(WitnessEntity entity, int position) {
        Intent intent = new Intent(WitnessActivity.this, Witness_AddActivity.class);
        if (entity != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("witness", entity);
            intent.putExtras(bundle);
            intent.putExtra("position", position);
        }
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_WITNESS_ADD);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        witnessPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void addWitness(WitnessEntity entity, int position) {
        if (position != -1) {
            witnessList.remove(position);
            witnessList.add(position, entity);
        } else {
            witnessList.add(entity);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showWitnessDeleteDialog(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        delDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        witnessPresenter.deleteWitness(witnessList.get(position));
                        delDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delDialog.dismiss();
                    }
                })
                .create();
        delDialog.show();
    }

    @Override
    public void saveWitness() {
        Intent intent = getIntent();
        crimeItem.setWitnessItem(witnessList);
        intent.putExtra("crime",crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void updateWitnessList(WitnessEntity entity) {
        witnessList.remove(entity);
        adapter.notifyDataSetChanged();
    }

}
