package com.liany.clientmodel.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.clientmodel.diagnose.WitnessEntity;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface WitnessContract {
    interface Model {

        void deleteWitnessPic(WitnessEntity witnessEntity);
    }

    interface View {

        void addWitness(WitnessEntity entity, int position);

        void showWitnessDeleteDialog(final int position);

        void saveWitness();

        void updateWitnessList(WitnessEntity entity);

    }

    interface Presenter {
        void onActivityResult(int requestCode, int resultCode, Intent data);

        void showDelWitnessDialog(int position);

        void deleteWitness(WitnessEntity witnessEntityList);
    }
}
