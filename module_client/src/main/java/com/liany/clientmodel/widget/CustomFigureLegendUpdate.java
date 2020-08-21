package com.liany.clientmodel.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseEvent;
import com.liany.clientmodel.diagnose.LegendEntity;
import com.lxj.xpopup.core.CenterPopupView;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;

/**
 * @创建者 ly
 * @创建时间 2019/7/23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CustomFigureLegendUpdate extends CenterPopupView implements View.OnClickListener {

    TextView tvCustomTitle;
    EditText etTitle;
    EditText etTime;
    EditText etUnit;
    EditText etPerson;
    Button btnConfirm;
    Button btnCancel;

    private LegendEntity entity;
    private String type;

    public CustomFigureLegendUpdate(@NonNull Context context, LegendEntity entity) {
        super(context);
        this.entity = entity;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.item_figure_legend_update;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tvCustomTitle = findViewById(R.id.tv_custom_title);
        etTitle = findViewById(R.id.et_title);
        etTime = findViewById(R.id.et_time);
        etUnit = findViewById(R.id.et_unit);
        etPerson = findViewById(R.id.et_person);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnCancel = findViewById(R.id.btn_cancel);

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        if(entity.getType() == 0) {
            type = "现场方位示意图";
            tvCustomTitle.setText("请修改方位示意图");
        }else if(entity.getType() == 1){
            type = "现场平面示意图";
            tvCustomTitle.setText("请修改平面示意图");
        }
        etTitle.setText(entity.getTitle());
        etTime.setText(entity.getTime());
        etUnit.setText(entity.getUnit());
        etPerson.setText(entity.getPerson());
    }

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_confirm) {
            BaseEvent.CommonEvent event = BaseEvent.CommonEvent.UPDATE_LEGEND;
            event.setObject(getLegndEntity());
            EventBus.getDefault().post(event);
            dismiss();
        } else if (id == R.id.btn_cancel) {
            dismiss();
        }
    }

    private LegendEntity getLegndEntity() {
        LegendEntity entity = new LegendEntity();
        entity.setTitle(etTitle.getText().toString());
        entity.setTime(etTime.getText().toString());
        entity.setUnit(etUnit.getText().toString());
        entity.setPerson(etPerson.getText().toString());
        return entity;
    }

}
