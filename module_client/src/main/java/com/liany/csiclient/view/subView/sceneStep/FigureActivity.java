package com.liany.csiclient.view.subView.sceneStep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.FlatPhotoDesAdapter;
import com.liany.csiclient.adapter.PositionPhotoDesAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.FigureContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.presenter.subView.sceneStep.FigurePresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.DrawSPUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.view.subView.sceneStep.step_window.Figure_PositionActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.PhotoViewActivity;
import com.liany.csiclient.widget.MyDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class FigureActivity extends BaseAcitivity implements FigureContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RelativeLayout llAddPosition;
    RecyclerView rvPosition;
    RelativeLayout llAddFlat;
    RecyclerView rvFlat;
    ImageView ivPositionState;
    ImageView ivFlatState;
    ImageButton ivPosition;
    ImageButton ivFlat;
    TextView tvPositionNum;
    TextView tvFlatNum;

    private FigureContract.Presenter figurePresenter;
    private PositionPhotoDesAdapter positionAdapter;
    private FlatPhotoDesAdapter flatAdapter;

    private List<Photo> positionEntityList = new ArrayList<>();
    private List<Photo> flatEntityList = new ArrayList<>();
    private List<Photo> dwgList = new ArrayList<>();

    private MyDialog positionDelDialog;
    private MyDialog flatDelDialog;
    private CrimeItem crimeItem;
    private Animation mShowAction;
    private Animation mHiddenAction;
    private String houseId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_figure;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        llAddPosition = findViewById(R.id.ll_add_position);
        rvPosition = findViewById(R.id.rv_position);
        llAddFlat = findViewById(R.id.ll_add_flat);
        rvFlat = findViewById(R.id.rv_flat);
        ivPositionState = findViewById(R.id.iv_position_state);
        ivFlatState = findViewById(R.id.iv_flat_state);
        ivPosition = findViewById(R.id.iv_position);
        ivFlat = findViewById(R.id.iv_flat);
        tvPositionNum = findViewById(R.id.tv_position_num);
        tvFlatNum = findViewById(R.id.tv_flat_num);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        llAddPosition.setOnClickListener(this);
        llAddFlat.setOnClickListener(this);
        ivPosition.setOnClickListener(this);
        ivFlat.setOnClickListener(this);

        tvTitle.setText(getString(R.string.title_activity_step2));
        LinearLayoutManager positionLayoutManager = new LinearLayoutManager(this);
        positionLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPosition.setLayoutManager(positionLayoutManager);
        LinearLayoutManager flatLayoutManager = new LinearLayoutManager(this);
        flatLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFlat.setLayoutManager(flatLayoutManager);

        positionAdapter = new PositionPhotoDesAdapter(R.layout.item_adapter_position_pic, positionEntityList);
        positionAdapter.setDesVisibility(View.GONE);
        tvPositionNum.setText("(" + positionEntityList.size() + ")");
        rvPosition.setAdapter(positionAdapter);
        flatAdapter = new FlatPhotoDesAdapter(R.layout.item_adapter_position_pic, flatEntityList);
        flatAdapter.setDesVisibility(View.GONE);
        tvFlatNum.setText("(" + flatEntityList.size() + ")");
        rvFlat.setAdapter(flatAdapter);

        positionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = positionEntityList.get(position);
                Intent intent = new Intent(FigureActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        positionAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showPositionDelDialog(position);
                return true;
            }
        });

        flatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = flatEntityList.get(position);
                Intent intent = new Intent(FigureActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        flatAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showFlatDelDialog(position);
                return true;
            }
        });

        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
    }

    @Override
    protected void initData() {
        figurePresenter = new FigurePresenter(this, this);
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");

        if (crimeItem.getId() != null && crimeItem.getPositionItem() != null) {
            positionEntityList.addAll(crimeItem.getPositionItem());
        }
        if (crimeItem.getId() != null && crimeItem.getFlatItem() != null) {
            flatEntityList.addAll(crimeItem.getFlatItem());
        }
        if (crimeItem.getId() != null && crimeItem.getDwgItem() != null) {
            dwgList.addAll(crimeItem.getDwgItem());
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            figurePresenter.saveFigure(crimeItem);
            finish();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            figurePresenter.saveFigure(crimeItem);
            finish();
        } else if (id == R.id.iv_title_confirm) {
            figurePresenter.saveFigure(crimeItem);
        } else if (id == R.id.ll_add_position) {
            if (rvPosition.isShown()) {
                ivPositionState.setBackgroundResource(R.mipmap.expand_close);
                rvPosition.startAnimation(mHiddenAction);
                rvPosition.setVisibility(View.GONE);
            } else {
                ivPositionState.setBackgroundResource(R.mipmap.expand_open);
                rvPosition.startAnimation(mShowAction);
                rvPosition.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_position) {
            Intent positionIntent = new Intent(FigureActivity.this, Figure_PositionActivity.class);
            startActivityForResult(positionIntent, Constants.RESULT_CODE_POSITION);
        } else if (id == R.id.ll_add_flat) {
            if (rvFlat.isShown()) {
                ivFlatState.setBackgroundResource(R.mipmap.expand_close);
                rvFlat.startAnimation(mHiddenAction);
                rvFlat.setVisibility(View.GONE);
            } else {
                ivFlatState.setBackgroundResource(R.mipmap.expand_open);
                rvFlat.startAnimation(mShowAction);
                rvFlat.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_flat) {
            if (Constants.isUseDraw) {
                houseId = StringUtils.getUUID();
                LogUtils.e("houseId:" + houseId);
                figurePresenter.startDraw(crimeItem, houseId);
                DrawSPUtils.setParam(this, "openCount", 1);
            }
//                else {
//                    //修改cad程序=============================
//                    //使用当前时间作为文件名
//                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//                    Intent myIntent = new Intent(FigureActivity.this, MxCADAppActivity.class);
//                    String p = GetFlatDir();
//                    myIntent.putExtra("file", p + File.separator + "Flat_" + sDateFormat.format(new Date()));
//                    myIntent.putExtra("path", p);
//                    startActivityForResult(myIntent, Constants.REQUEST_FLAT);
//                }
        }
    }

    private String GetFlatDir() {
        File mediaStorageDir = new File(Constants.path_flatDir);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String dirMxDraw = mediaStorageDir.getPath();
        return dirMxDraw;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        figurePresenter.onActivityResult(requestCode, resultCode, data, crimeItem);
    }

    @Override
    public void showPositionDelDialog(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        positionDelDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        figurePresenter.deletePositionPic(positionEntityList.get(position));
                        positionDelDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        positionDelDialog.dismiss();
                    }
                })
                .create();
        positionDelDialog.show();
    }

    @Override
    public void showFlatDelDialog(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        flatDelDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setEditButtonShow(true)
                .setEditButton(getString(R.string.editbtn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Constants.isUseDraw) {
                            LogUtils.e("houseId:" + flatEntityList.get(position).getId());
                            houseId = flatEntityList.get(position).getId();
                            figurePresenter.startDraw(crimeItem,flatEntityList.get(position).getId());
                            flatDelDialog.dismiss();
                        }
//                        else {
//                            figurePresenter.setUpdatePosition(position);
//                            //修改，调用cad程序=============================
//                            Intent myIntent = new Intent(FigureActivity.this, MxCADAppActivity.class);
//                            String file = flatEntityList.get(position).getPath();
//                            String fileName = file.substring(file.lastIndexOf("/") + 1,file.length());
//                            String filePath = Constants.path_flatDir + "/" + fileName.replaceAll(".jpg", "").replace("Legend_", "");
//                            String allPath = filePath + ".dwg";
//                            File flatFile = new File(allPath);
//                            if(flatFile.exists()) {
//                                String path = GetFlatDir();
//                                myIntent.putExtra("path", path);
//                                myIntent.putExtra("file", filePath);
//                                startActivityForResult(myIntent, Constants.EVENT_EDIT_FLAT);
//                                flatDelDialog.dismiss();
//                            }else {
//                                ToastUtils.showShort("文件未找到");
//                            }
//                        }
                    }
                })
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        figurePresenter.deleteFlatPic(flatEntityList.get(position));
//                        figurePresenter.deleteDwgPic(dwgList.get(position));
                        flatDelDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        flatDelDialog.dismiss();
                    }
                })
                .create();
        flatDelDialog.show();
    }

    @Override
    public void setPositionListNotify(Photo positionEntity) {
        positionEntityList.add(positionEntity);
        tvPositionNum.setText("(" + positionEntityList.size() + ")");
        positionAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFlatListNotify(Photo flatEntity) {
        if(Constants.isUseDraw) {
            if (flatEntity != null) {
                boolean isEdit = false;
                Iterator<Photo> iterator = flatEntityList.iterator();
                while (iterator.hasNext()) {
                    Photo photo = iterator.next();
                    if(photo.getId().equals(flatEntity.getId())) {
                        isEdit = true;
                        int i = flatEntityList.indexOf(photo);
                        flatEntityList.remove(i);
                        flatEntityList.add(i,flatEntity);
                    }
                }
                if(!isEdit) {
                    flatEntityList.add(flatEntity);
                    tvFlatNum.setText("(" + flatEntityList.size() + ")");
                }
            }
            flatAdapter.notifyDataSetChanged();
        }
//        else {
//            if (flatEntity != null) {
//                flatEntityList.add(flatEntity);
//                tvFlatNum.setText("(" + flatEntityList.size() + ")");
//            }
//            flatAdapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void saveFigure(CrimeItem crimeItem) {
        Intent intent = getIntent();
        crimeItem.setFlatItem(flatEntityList);
        crimeItem.setPositionItem(positionEntityList);
        crimeItem.setDwgItem(dwgList);
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void updateFlat(Photo flatPhoto) {
        flatEntityList.remove(flatPhoto);
        tvFlatNum.setText("(" + flatEntityList.size() + ")");
        flatAdapter.notifyDataSetChanged();
    }

    @Override
    public void updatePosition(Photo positionPhoto) {
        positionEntityList.remove(positionPhoto);
        tvPositionNum.setText("(" + positionEntityList.size() + ")");
        positionAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDwg(Photo dwg) {
        dwgList.add(dwg);
    }

    @Override
    public void reomveDwg(Photo dwg) {
        dwgList.remove(dwg);
    }

    @Override
    public void reomveFlat(int positon) {
        flatEntityList.remove(positon);
        flatAdapter.notifyDataSetChanged();
    }

    @Override
    public String getPhotoId() {
        LogUtils.e("houseId getPhotoId:" + houseId);
        return houseId;
    }
}
