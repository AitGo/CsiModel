package com.liany.csiclient.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liany.csiclient.R;

/**
 * @创建者 ly
 * @创建时间 2019/3/20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CsiProgressDialog extends ProgressDialog {

    ProgressBar pb_progress;
    TextView tv_progress;

    private TextView tvProgress;
    private String message;
    private boolean isShow = false;

    public CsiProgressDialog(Context context, String message) {
        super(context, R.style.Translucent_NoTitle);
        this.message = message;
        setIndeterminate(true);
        setCancelable(false);
    }

    public void setMessage(String message) {
        tv_progress.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_progress_dialog);
        pb_progress = findViewById(R.id.dialog_progress);
        tv_progress = findViewById(R.id.tvProgress);
        tv_progress.setText(message);
    }

    @Override
    public void show() {
        if (!isShow) {
            super.show();
            isShow = true;
        }
    }

    @Override
    public void dismiss() {
        if (isShow) {
            super.dismiss();
            isShow = false;
        }
    }
}
