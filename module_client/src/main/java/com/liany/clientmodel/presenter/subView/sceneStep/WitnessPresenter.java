package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.WitnessContract;
import com.liany.clientmodel.diagnose.WitnessEntity;
import com.liany.clientmodel.model.subView.sceneStep.WitnessModel;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class WitnessPresenter implements WitnessContract.Presenter {
    private WitnessContract.View view;
    private WitnessContract.Model model;
    private Context mContext;

    public WitnessPresenter(Context mContext, WitnessContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new WitnessModel(mContext);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == Constants.REQUEST_WITNESS_ADD) {
                WitnessEntity entity = (WitnessEntity) data.getSerializableExtra("witness");
                int position = data.getIntExtra("position",-1);
                view.addWitness(entity,position);
            }
        }
    }

    @Override
    public void showDelWitnessDialog(int position) {
        view.showWitnessDeleteDialog(position);
    }

    @Override
    public void deleteWitness(WitnessEntity witnessEntity) {
        view.updateWitnessList(witnessEntity);
        //删除图片

    }

}
