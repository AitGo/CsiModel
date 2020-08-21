package com.liany.clientmodel.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.clientmodel.adapter.AddToolAdapter;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.ToolEntity;
import com.liany.clientmodel.diagnose.sysDict;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface OpinionContract {
    interface Model {
        void selectPeopleNumber(callBack callBack);
        void selectCrimeMeans(callBack callBack);
        void selectCrimeCharacter(CrimeItem crimeItem, callBack callBack);
        void selectCrimeEntrance(callBack callBack);
        void selectCrimeTiming(callBack callBack);
        void selectObject(callBack callBack);
        void selectCrimeFeature(callBack callBack);
        void selectIntrusiveMethod(callBack callBack);
        void selectLocation(CrimeItem crimeItem, callBack callBack);
        void selectCrimePurpose(callBack callBack);
    }

    interface View {
        String getPeopleNumber();
        void setPeopleNumber(String number);
        void setPeopleNumber(sysDict dict);
        String getCrimeMeans();
        void setCrimeMeans(String means);
        void setCrimeMeans(List<sysDict> dicts);
        String getCrimeCharacter();
        void setCrimeCharacter(String character);
        void setCrimeCharacter(List<sysDict> dicts);
        void setCrimeCharacter(sysDict dicts);
        String getCrimeEntrance();
        void setCrimeEntrance(String entrance);
        void setCrimeEntrance(List<sysDict> dict);
        String getCrimeTiming();
        void setCrimeTiming(String timing);
        void setCrimeTiming(List<sysDict> dict);
        String getSelectObject();
        void setSelectObject(String object);
        void setSelectObject(List<sysDict> dicts);
        String getCrimeExport();
        void setCrimeExport(String export);
        void setCrimeExport(List<sysDict> dict);
        String getPeopleFeature();
        void setPeopleFeature(String peopleFeature);
        String getCrimeFeature();
        void setCrimeFeature(String crimeFeature);
        void setCrimeFeature(List<sysDict> dicts);
        String getIntrusiveMethod();
        void setIntrusiveMethod(String method);
        void setIntrusiveMethod(List<sysDict> dicts);
        String getSelectLocation();
        void setSelectLocation(String location);
        void setSelectLocation(List<sysDict> dicts);
        String getCrimePurpose();
        void setCrimePurpose(String purpose);
        void setCrimePurpose(List<sysDict> dict);

        void saveOpinion(CrimeItem crimeItem);
        void startRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startExpandCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);
        void startExpandRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);

        void showMsgDialog(String msg);

        void startExpand3CheckSelectDictView(int requestExpandCheckDict, String crimeMeans, List<sysDict> data, List<Integer> selectList);

        void addTool(ToolEntity toolEntity, int toolPosition);

        void startAddToolActivity(ToolEntity toolEntity, int position);

        void showToolDeleteDialog(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter);
    }

    interface Presenter {

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void peopleNumber();

        void crimeMeans();

        void crimeCharacter(CrimeItem crimeItem);

        void crimeEntrance();

        void crimeTiming();

        void selectObject();

        void crimeExport();

        void crimeFeature();

        void intrusiveMethod();

        void selectLocation(CrimeItem crimeItem);

        void crimePurpose();

        void initValue(CrimeItem crimeItem);

        void saveOpinion(CrimeItem crimeItem, List<ToolEntity> toolEntities);

        void getOpinionSearchResult(CrimeItem crimeItem);

        void onAddToolItemClick(List<ToolEntity> toolEntityList, int position);

        void onAddToolItemLongClick(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter);

        void deleteTool(List<ToolEntity> toolEntityList, int position, AddToolAdapter addToolAdapter);
    }
}
