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

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.SituationContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.presenter.subView.sceneStep.SituationPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;


/**
 * @创建者 ly
 * @创建时间 2020/3/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SituationActivity extends BaseAcitivity implements SituationContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    LinearLayout automaticGeneratedButton;
    ClearableEditText overview;

    private SituationContract.Presenter situationPresenter;
    private CrimeItem crimeItem;
    private MyDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_situation;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        automaticGeneratedButton = findViewById(R.id.automatic_generated_button);
        overview = findViewById(R.id.overview);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        automaticGeneratedButton.setOnClickListener(this);

        tvTitle.setText(getString(R.string.title_activity_step5));
        overview.setMaxLines(30);
    }

    @Override
    protected void initData() {
        situationPresenter = new SituationPresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        if(crimeItem.getOverview() != null && !crimeItem.getOverview().equals("")){
            setOverView(crimeItem.getOverview());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            situationPresenter.saveSituation(crimeItem);
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
            situationPresenter.saveSituation(crimeItem);
        } else if (id == R.id.iv_title_confirm) {
            situationPresenter.saveSituation(crimeItem);
        } else if (id == R.id.automatic_generated_button) {
            if (Constants.isUseDraw) {
                showChooseDialog();

            }
//                else {
//                    situationPresenter.automatic(crimeItem);
//                }
        }
    }

    private void showChooseDialog() {
        String title = "提示";
        MyDialog.Builder builder = new MyDialog.Builder(this);
        dialog = builder.setTitle(title)
                .setMsg("是否使用现场平面图生成勘验情况？")
                .setPositiveButton("是", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        situationPresenter.automaticDraw(crimeItem);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        situationPresenter.automatic(crimeItem);
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public String getOverView() {
        return overview.getText().toString();
    }

    @Override
    public void setOverView(String value) {
        overview.setText(value);
    }

    @Override
    public void saveSituation(CrimeItem crimeItem) {
        Intent intent = getIntent();
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
