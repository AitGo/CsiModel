package com.liany.csiclient.model.subView.sceneStep;

import android.content.Context;

import com.liany.csiclient.contract.subView.sceneStep.WitnessContract;
import com.liany.csiclient.diagnose.WitnessEntity;
import com.liany.csiclient.utils.FileUtils;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class WitnessModel implements WitnessContract.Model {
    private Context mContext;

    public WitnessModel(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void deleteWitnessPic(WitnessEntity witnessEntity) {
        FileUtils.deleteFile(witnessEntity.getPhoto().getPath());
    }
}
