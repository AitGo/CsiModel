package com.liany.csiclient.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface ProspectingContract {
    interface Model {
        void getCaseType(callBack callBack);

        void selectObject(callBack callBack);

        void saveCrime(CrimeItem crimeItem, callBack callBack);
    }

    interface View {
        void setCaseType(sysDict dict);
        void setCaseType(String caseType);
        String getCaseType();
        void setLocation(String location);
        String getLocation();
        void setAccessLocation(String accessLocation);
        String getAccessLocation();

        void closeEdit();

        void startRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startExpandCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startExpandRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);

        void startSelectUserView(int selectType, String title, List<selectUser> users, String value);

        void startSelectUserRadioView(int selectType, String title, List<selectUser> users, String value);

        void startSelectUnitRadioView(int selectType, String title, List<selectUser> users, String value);

        void setQhdx(String selectObject);
        void setQhdx(List<sysDict> dicts);
        String getQhdx();

        void showCreateDialog();
        void showMsgDialog();

        void showCreateView();

    }

    interface Presenter {
        void initValue(CrimeItem item);

        void casetype();

        void qhdx();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void createCrime(CrimeItem item);

        void checkDate(CrimeItem item);
    }
}
