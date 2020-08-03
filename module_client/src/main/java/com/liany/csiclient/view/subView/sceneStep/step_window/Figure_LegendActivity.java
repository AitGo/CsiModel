package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.BaseEvent;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.LegendEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.utils.BitmapUtils;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.widget.CustomFigureLegendUpdate;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;

import uk.co.senab.photoview.PhotoView;

/**
 * @创建者 ly
 * @创建时间 2019/7/22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Figure_LegendActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    ImageView ivTitleUpdate;
    TextView tvDes;
    PhotoView ivImg;
    LinearLayout llPosition;
    TextView tvMeasure;
    TableLayout tlLagendDesImage;
    TableLayout tlLagendDesNo;
    TextView tvOccurredStartTime;
    TextView tvLocation;
    TextView tvPicUnit;
    TextView tvPicPerson;
    TextView tvPicTime;
    TableLayout tlLagendDes;
    RelativeLayout rlLegend;
    TableRow trOccurredStartTime;
    TableRow trLocation;

    private Photo entity;
    private Photo flat;
    private CrimeItem crimeItem;
    private int type;
    private String filePath = Constants.path_photoDir;
    private String fileName;
    private Photo dwg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_figure_legend;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        ivTitleUpdate = findViewById(R.id.iv_title_update);
        tvDes = findViewById(R.id.tv_des);
        ivImg = findViewById(R.id.iv_img);
        llPosition = findViewById(R.id.ll_position);
        tvMeasure = findViewById(R.id.tv_measure);
        tlLagendDesImage = findViewById(R.id.tl_lagend_des_image);
        tlLagendDesNo = findViewById(R.id.tl_lagend_des_no);
        tvOccurredStartTime = findViewById(R.id.tv_occurred_start_time);
        tvLocation = findViewById(R.id.tv_location);
        tvPicUnit = findViewById(R.id.tv_pic_unit);
        tvPicPerson = findViewById(R.id.tv_pic_person);
        tvPicTime = findViewById(R.id.tv_pic_time);
        tlLagendDes = findViewById(R.id.tl_lagend_des);
        rlLegend = findViewById(R.id.rl_legend);
        trOccurredStartTime = findViewById(R.id.tr_occurred_start_time);
        trLocation = findViewById(R.id.tr_location);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        ivTitleUpdate.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        ivTitleUpdate.setVisibility(View.VISIBLE);

        if (type == 0) {
            //方位图
            trLocation.setVisibility(View.GONE);
            trOccurredStartTime.setVisibility(View.GONE);
            tlLagendDesImage.setVisibility(View.VISIBLE);
            tlLagendDesNo.setVisibility(View.GONE);
            tvDes.setText(entity.getPhotoInfo());
            ivImg.setImageBitmap(BitmapFactory.decodeFile(entity.getPath()));
        } else if (type == 1) {
            //平面图
            trLocation.setVisibility(View.VISIBLE);
            trOccurredStartTime.setVisibility(View.VISIBLE);
            tlLagendDesImage.setVisibility(View.GONE);
            tlLagendDesNo.setVisibility(View.VISIBLE);
            tvDes.setText(flat.getPhotoInfo());
            ivImg.setImageBitmap(BitmapFactory.decodeFile(flat.getPath()));
        } else if (type == 2) {
            //平面图
            trLocation.setVisibility(View.VISIBLE);
            trOccurredStartTime.setVisibility(View.VISIBLE);
            tlLagendDesImage.setVisibility(View.GONE);
            tlLagendDesNo.setVisibility(View.VISIBLE);
            tvDes.setText(flat.getPhotoInfo());
            ivImg.setImageBitmap(BitmapFactory.decodeFile(flat.getPath()));
        }

        tvOccurredStartTime.setText(StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy年MM月dd日"));
        tvLocation.setText(crimeItem.getLocation());
        tvPicUnit.setText((String) SPUtils.getParam(this, Constants.sp_unitName, ""));
        tvPicPerson.setText((String) SPUtils.getParam(this, Constants.sp_userName, ""));

        tvPicTime.setText(StringUtils.long2String(System.currentTimeMillis(),"yyyy年MM月dd日"));
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crimeItem");
        if (type == 0) {
            entity = (Photo) getIntent().getSerializableExtra("entity");
            fileName = "Legend_" + entity.getFileName().replace("png","jpg");
            filePath = Constants.path_photoDir;
        } else if (type == 1) {
            dwg = (Photo) getIntent().getSerializableExtra("dwg");
            flat = (Photo) getIntent().getSerializableExtra("entity");
            fileName = "Legend_" + flat.getFileName().replace("png","jpg");
            filePath = Constants.path_flatDir;
        } else if (type == 2) {
            dwg = (Photo) getIntent().getSerializableExtra("dwg");
            flat = (Photo) getIntent().getSerializableExtra("entity");
            fileName = "Legend_" + flat.getFileName().replace("png","jpg");
            filePath = Constants.path_flatDir;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent.CommonEvent event) {
        if (event == BaseEvent.CommonEvent.UPDATE_LEGEND) {
            LegendEntity entity = (LegendEntity) event.getObject();
            tvDes.setText(entity.getTitle());
            tvPicPerson.setText(entity.getPerson());
            tvPicUnit.setText(entity.getUnit());
            tvPicTime.setText(entity.getTime());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_confirm) {//                fileName = "Legend_" + System.currentTimeMillis() + ".png";
            saveBitmapFile(Constants.path_photoDir, fileName);

            Intent intent = getIntent();
            if (type == 0) {
                getPositionEntity(entity);
                intent.putExtra("entity", entity);
                intent.putExtra("type", 0);
            } else if (type == 1) {
                getFlatEntity(flat);
                intent.putExtra("entity", flat);
                intent.putExtra("type", 1);
                intent.putExtra("dwg", dwg);
            } else if (type == 2) {
                getFlatEntity(flat);
                intent.putExtra("entity", flat);
                intent.putExtra("type", 2);
                intent.putExtra("dwg", dwg);
            }
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else if (id == R.id.iv_title_update) {
            new XPopup.Builder(this)
                    .autoOpenSoftInput(false)
                    .asCustom(new CustomFigureLegendUpdate(this, getLegend()))
                    .show();
        }
    }

    private void getFlatEntity(Photo flat) {

        flat.setPath(Constants.path_photoDir + File.separator + fileName);
        flat.setFileName(fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(Constants.path_photoDir + File.separator + fileName);
        flat.setHeight(bitmap.getHeight() + "");
        flat.setWidth(bitmap.getWidth() + "");
        flat.setUUID(StringUtils.md5HashCode32(Constants.path_photoDir + File.separator + fileName));
    }

    private void getPositionEntity(Photo entity) {
        entity.setPhotoInfo(tvDes.getText().toString());
        entity.setPath(Constants.path_photoDir + File.separator + fileName);
        entity.setFileName(fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(Constants.path_photoDir + File.separator + fileName);
        entity.setHeight(bitmap.getHeight() + "");
        entity.setWidth(bitmap.getWidth() + "");
        entity.setUUID(StringUtils.md5HashCode32(Constants.path_photoDir + File.separator + fileName));
//        entity.setPhotoInfo();
    }

    private LegendEntity getLegend() {
        LegendEntity entity = new LegendEntity();
        entity.setCaseTime(StringUtils.long2String(crimeItem.getOccurred_start_time(),"yyyy.MM.dd"));
        entity.setCaseType(crimeItem.getCasetype() == null ? "" : crimeItem.getCasetype());
        entity.setType(type);
        entity.setTitle(tvDes.getText().toString());
        entity.setTime(tvOccurredStartTime.getText().toString());
        entity.setAddress(tvLocation.getText().toString());
        entity.setUnit(tvPicUnit.getText().toString());
        entity.setPerson(tvPicPerson.getText().toString());
        return entity;
    }

    private void saveBitmapFile(String filePath, String fileName) {
        FileOutputStream fos = null;
        try {
            Bitmap bitmap = BitmapUtils.convertViewToBitmap(rlLegend, null);
            fos = new FileOutputStream(filePath + File.separator + fileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
