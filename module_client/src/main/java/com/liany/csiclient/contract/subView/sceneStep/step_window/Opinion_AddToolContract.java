package com.liany.csiclient.contract.subView.sceneStep.step_window;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.ToolEntity;
import com.liany.csiclient.diagnose.sysDict;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface Opinion_AddToolContract {
    interface Model {
        void selectToolType(callBack callBack);
        void selectToolSource(callBack callBack);
    }

    interface View {
        void setToolName(String toolName);
        void setToolCategory(String toolCategory);
        void setToolCategory(sysDict dict);
        void setToolSource(String toolSource);
        void setToolSource(sysDict dict);

        String getToolName();
        String getToolCategory();
        String getToolSource();

        void close(ToolEntity entity);

        void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);

        void showMsgDialog(String s);
    }

    interface Presenter {
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void toolSource();
        void toolType();

        void initViewDate(ToolEntity toolEntity);

        void saveTool(ToolEntity entity, String crimeId, boolean isCheck);
    }
}
