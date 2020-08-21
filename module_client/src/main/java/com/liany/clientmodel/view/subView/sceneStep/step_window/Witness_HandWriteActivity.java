package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.widget.paint.PaintView;
import com.liany.clientmodel.widget.paint.PaintViewCallBack;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Witness_HandWriteActivity extends AppCompatActivity implements View.OnClickListener {

    Button mClear;
    PaintView mPathView;
    LinearLayout ll;
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_witness_handwrite);
        setResult(50);
        mClear = findViewById(R.id.clear1);
        mPathView = findViewById(R.id.view);
        ll = findViewById(R.id.ll);
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText("签名");
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathView.clearAll(true);
            }
        });


        initCallBack();
    }

    private void initCallBack() {
        mPathView.setCallBack(new PaintViewCallBack() {
            // 当画了之后对Button进行更新
            @Override
            public void onHasDraw() {
            }

            // 当点击之后让各个弹出的窗口都消失
            @Override
            public void onTouchDown() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_confirm) {
            getPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Constants.RESULT_CODE_STARTWRITE) {
            boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraAccepted) {
                getHandWriteFile();
            } else {
                //用户授权拒绝
                LogUtils.e("get premissions fail");
            }
        }
    }

    private void getPermission() {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getHandWriteFile();
        } else {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, perms, Constants.RESULT_CODE_STARTWRITE);
        }
    }

    private void getHandWriteFile() {
        if (mPathView.getTouched()) {
            try {
                FileUtils.makeRootDirectory(Constants.path_photoDir + File.separator);
                // Create a media file name
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                String path = Constants.path_photoDir  + File.separator + "Hand_" + timeStamp + ".jpg";
                mPathView.save(path, 10);
                Intent result = getIntent().putExtra("SIGN", path);
                setResult(Activity.RESULT_OK, result);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtils.showShort("您没有签名");
        }
    }

}
