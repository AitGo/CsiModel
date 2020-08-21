package com.liany.clientmodel.view.subView.sceneStep;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.DetailDesAdapter;
import com.liany.clientmodel.adapter.ImportantPhotoDesAdapter;
import com.liany.clientmodel.adapter.OverViewPhotoDesAdapter;
import com.liany.clientmodel.adapter.PositionPicDesAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.BaseEvent;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.PhotoContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.presenter.subView.sceneStep.PhotoPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.GlideEngine;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.SysCameraUtils;
import com.liany.clientmodel.view.subView.sceneStep.step_window.PhotoViewActivity;
import com.liany.clientmodel.widget.MyDialog;
import com.liany.clientmodel.widget.OrientationDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.tools.PictureFileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
public class PhotoActivity extends BaseAcitivity implements PhotoContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    RelativeLayout llPhotoPosition;
    RecyclerView rvPhotoPosition;
    RelativeLayout llPhotoLike;
    RecyclerView rvPhotoLike;
    RelativeLayout llPhotoImportant;
    RecyclerView rvPhotoImportant;
    ImageButton ivPosition;
    ImageView ivPositionState;
    ImageView ivLikeState;
    ImageButton ivLike;
    ImageView ivImportantState;
    ImageButton ivImportant;
    TextView tvPositionNum;
    TextView tvLikeNum;
    TextView tvImportantNum;
    ImageView ivDetailState;
    ImageView ivDetailIcon;
    TextView tvDetailNum;
    ImageButton ivDetail;
    RelativeLayout llPhotoDetail;
    RecyclerView rvPhotoDetail;
    ImageButton ivPositionImg;
    ImageButton ivLikeImg;
    ImageButton ivImportantImg;
    ImageButton ivDetailImg;

    private CrimeItem crimeItem;
    private PhotoContract.Presenter photoPresenter;
    private List<Photo> positionList = new ArrayList();
    private List<Photo> likeList = new ArrayList<>();
    private List<Photo> importantList = new ArrayList<>();
    private List<Photo> detailList = new ArrayList<>();

    private PositionPicDesAdapter positionAdapter;
    private OverViewPhotoDesAdapter likeAdapter;
    private ImportantPhotoDesAdapter importantAdapter;
    private DetailDesAdapter detailAdapter;

    private Animation mShowAction;
    private Animation mHiddenAction;

    private File outputImagepath;

    private MyDialog positionDel, likeDel, importantDel, detailDel;
    private OrientationDialog orientationDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_photo;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        llPhotoPosition = findViewById(R.id.ll_photo_position);
        rvPhotoPosition = findViewById(R.id.rv_photo_position);
        llPhotoLike = findViewById(R.id.ll_photo_like);
        rvPhotoLike = findViewById(R.id.rv_photo_like);
        llPhotoImportant = findViewById(R.id.ll_photo_important);
        rvPhotoImportant = findViewById(R.id.rv_photo_important);
        ivPosition = findViewById(R.id.iv_position);
        ivPositionState = findViewById(R.id.iv_position_state);
        ivLikeState = findViewById(R.id.iv_like_state);
        ivLike = findViewById(R.id.iv_like);
        ivImportantState = findViewById(R.id.iv_important_state);
        ivImportant = findViewById(R.id.iv_important);
        tvPositionNum = findViewById(R.id.tv_position_num);
        tvLikeNum = findViewById(R.id.tv_like_num);
        tvImportantNum = findViewById(R.id.tv_important_num);
        ivDetailState = findViewById(R.id.iv_detail_state);
        ivDetailIcon = findViewById(R.id.iv_detail_icon);
        tvDetailNum = findViewById(R.id.tv_detail_num);
        ivDetail = findViewById(R.id.iv_detail);
        llPhotoDetail = findViewById(R.id.ll_photo_detail);
        rvPhotoDetail = findViewById(R.id.rv_photo_detail);
        ivPositionImg = findViewById(R.id.iv_position_img);
        ivLikeImg = findViewById(R.id.iv_like_img);
        ivImportantImg = findViewById(R.id.iv_important_img);
        ivDetailImg = findViewById(R.id.iv_detail_img);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        llPhotoPosition.setOnClickListener(this);
        ivPosition.setOnClickListener(this);
        llPhotoLike.setOnClickListener(this);
        llPhotoImportant.setOnClickListener(this);
        llPhotoDetail.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        ivImportant.setOnClickListener(this);
        ivDetail.setOnClickListener(this);
        ivDetailImg.setOnClickListener(this);
        ivPositionImg.setOnClickListener(this);
        ivLikeImg.setOnClickListener(this);
        ivImportantImg.setOnClickListener(this);

        tvTitle.setText(getString(R.string.title_activity_step3));
        LinearLayoutManager positionLayoutManager = new LinearLayoutManager(this);
        positionLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPhotoPosition.setLayoutManager(positionLayoutManager);
        LinearLayoutManager likeLayoutManager = new LinearLayoutManager(this);
        likeLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPhotoLike.setLayoutManager(likeLayoutManager);
        LinearLayoutManager importantLayoutManager = new LinearLayoutManager(this);
        importantLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPhotoImportant.setLayoutManager(importantLayoutManager);
        LinearLayoutManager detailLayoutManager = new LinearLayoutManager(this);
        detailLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPhotoDetail.setLayoutManager(detailLayoutManager);

        positionAdapter = new PositionPicDesAdapter(R.layout.item_adapter_position, positionList);
        rvPhotoPosition.setAdapter(positionAdapter);
        tvPositionNum.setText("(" + positionList.size() + ")");
        likeAdapter = new OverViewPhotoDesAdapter(R.layout.item_adapter_position, likeList);
        rvPhotoLike.setAdapter(likeAdapter);
        tvLikeNum.setText("(" + likeList.size() + ")");
        importantAdapter = new ImportantPhotoDesAdapter(R.layout.item_adapter_position, importantList);
        rvPhotoImportant.setAdapter(importantAdapter);
        tvImportantNum.setText("(" + importantList.size() + ")");
        detailAdapter = new DetailDesAdapter(R.layout.item_adapter_position, detailList);
        rvPhotoDetail.setAdapter(detailAdapter);
        tvDetailNum.setText("(" + detailList.size() + ")");

        positionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = positionList.get(position);
                Intent intent = new Intent(PhotoActivity.this, PhotoViewActivity.class);
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

        likeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = likeList.get(position);
                Intent intent = new Intent(PhotoActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        likeAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showLikeDelDialog(position);
                return true;
            }
        });

        importantAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo positionEntity = importantList.get(position);
                Intent intent = new Intent(PhotoActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", positionEntity.getServerPath());
                startActivity(intent);
            }
        });
        importantAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showImportantDelDialog(position);
                return true;
            }
        });

        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Photo photo = detailList.get(position);
                Intent intent = new Intent(PhotoActivity.this, PhotoViewActivity.class);
                intent.putExtra("filePath", photo.getServerPath());
                startActivity(intent);
            }
        });
        detailAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDetailDelDialog(position);
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
        photoPresenter = new PhotoPresenter(this, this);

        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");

        if (crimeItem.getId() != null && crimeItem.getPositionPhotoItem() != null) {
            positionList.addAll(crimeItem.getPositionPhotoItem());
        }
        if (crimeItem.getId() != null && crimeItem.getOverviewPhotoItem() != null) {
            likeList.addAll(crimeItem.getOverviewPhotoItem());
        }
        if (crimeItem.getId() != null && crimeItem.getImportantPhotoItem() != null) {
            importantList.addAll(crimeItem.getImportantPhotoItem());
        }
        if (crimeItem.getId() != null && crimeItem.getDetailPhotoItem() != null) {
            detailList.addAll(crimeItem.getDetailPhotoItem());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            savePhoto();
            finish();
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent event) {
        if (event == BaseEvent.CommonEvent.CAMERA_DES_POSITION) {
            photoPresenter.setPositionDes((String) event.getObject());
        } else if (event == BaseEvent.CommonEvent.CAMERA_DES_LIKE) {
            photoPresenter.setLikeDes((String) event.getObject());
        }
        hideInput();
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {//                photoPresenter.deletePositionPic(positionList);
//                photoPresenter.deleteLikePic(likeList);
//                photoPresenter.deleteImportantPic(importantList);
            savePhoto();
            finish();
        } else if (id == R.id.iv_title_confirm) {
            savePhoto();
        } else if (id == R.id.ll_photo_position) {
            if (rvPhotoPosition.isShown()) {
                ivPositionState.setBackgroundResource(R.mipmap.expand_close);
                rvPhotoPosition.startAnimation(mHiddenAction);
                rvPhotoPosition.setVisibility(View.GONE);
            } else {
                ivPositionState.setBackgroundResource(R.mipmap.expand_open);
                rvPhotoPosition.startAnimation(mShowAction);
                rvPhotoPosition.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_position_img) {//                goPhotoAlbum(Constants.REQUEST_TAKE_PHOTO_POSITION_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .renameCompressFile(StringUtils.long2FileName(new Date()) + ".jpg")
                    .forResult(Constants.REQUEST_TAKE_PHOTO_POSITION_img_more);
        } else if (id == R.id.iv_position) {
            photoPresenter.addPosition(Constants.REQUEST_TAKE_PHOTO_POSITION);
        } else if (id == R.id.ll_photo_like) {
            if (rvPhotoLike.isShown()) {
                ivLikeState.setBackgroundResource(R.mipmap.expand_close);
                rvPhotoLike.startAnimation(mHiddenAction);
                rvPhotoLike.setVisibility(View.GONE);
            } else {
                ivLikeState.setBackgroundResource(R.mipmap.expand_open);
                rvPhotoLike.startAnimation(mShowAction);
                rvPhotoLike.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_like_img) {//                goPhotoAlbum(Constants.REQUEST_TAKE_PHOTO_LIKE_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .renameCompressFile(StringUtils.long2FileName(new Date()) + ".jpg")
                    .forResult(Constants.REQUEST_TAKE_PHOTO_LIKE_img_more);
        } else if (id == R.id.iv_like) {
            photoPresenter.addPosition(Constants.REQUEST_TAKE_PHOTO_LIKE);
        } else if (id == R.id.ll_photo_important) {
            if (rvPhotoImportant.isShown()) {
                ivImportantState.setBackgroundResource(R.mipmap.expand_close);
                rvPhotoImportant.startAnimation(mHiddenAction);
                rvPhotoImportant.setVisibility(View.GONE);
            } else {
                ivImportantState.setBackgroundResource(R.mipmap.expand_open);
                rvPhotoImportant.startAnimation(mShowAction);
                rvPhotoImportant.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_important_img) {//                goPhotoAlbum(Constants.REQUEST_TAKE_PHOTO_IMPORTANT_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .renameCompressFile(StringUtils.long2FileName(new Date()) + ".jpg")
                    .forResult(Constants.REQUEST_TAKE_PHOTO_IMPORTANT_img_more);
        } else if (id == R.id.iv_important) {
            photoPresenter.addPosition(Constants.REQUEST_TAKE_PHOTO_IMPORTANT);
        } else if (id == R.id.ll_photo_detail) {
            if (rvPhotoDetail.isShown()) {
                ivDetailState.setBackgroundResource(R.mipmap.expand_close);
                rvPhotoDetail.startAnimation(mHiddenAction);
                rvPhotoDetail.setVisibility(View.GONE);
            } else {
                ivDetailState.setBackgroundResource(R.mipmap.expand_open);
                rvPhotoDetail.startAnimation(mShowAction);
                rvPhotoDetail.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.iv_detail_img) {//                goPhotoAlbum(Constants.REQUEST_TAKE_PHOTO_DETAIL_img);
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .isCompress(true)// 是否压缩
                    .minimumCompressSize(300)// 小于多少kb的图片不压缩
                    .renameCompressFile(StringUtils.long2FileName(new Date()) + ".jpg")
                    .forResult(Constants.REQUEST_TAKE_PHOTO_DETAIL_img_more);
        } else if (id == R.id.iv_detail) {
            photoPresenter.addPosition(Constants.REQUEST_TAKE_PHOTO_DETAIL);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoPresenter.onActivityResult(requestCode, resultCode, data, outputImagepath, crimeItem);
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

    /**
     * 打开系统相机
     */
    public void takePhoto(int code) {
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir, "position", code);
    }

    private void goPhotoAlbum(int code) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, code);
    }

    private void goCamera(int code) {
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir, "position", code);
    }

    @Override
    public void addPositionPic(Photo position) {
        positionList.add(position);
        tvPositionNum.setText("(" + positionList.size() + ")");
        positionAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPositionPics(List<Photo> positions) {
        positionList.addAll(positions);
        tvPositionNum.setText("(" + positionList.size() + ")");
        positionAdapter.notifyDataSetChanged();
    }


    @Override
    public void addLikePic(Photo like) {
        likeList.add(like);
        tvLikeNum.setText("(" + likeList.size() + ")");
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void addLikePics(List<Photo> likes) {
        likeList.addAll(likes);
        tvLikeNum.setText("(" + likeList.size() + ")");
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void addImportantPic(Photo important) {
        importantList.add(important);
        tvImportantNum.setText("(" + importantList.size() + ")");
        importantAdapter.notifyDataSetChanged();
    }

    @Override
    public void addImportantPics(List<Photo> importants) {
        importantList.addAll(importants);
        tvImportantNum.setText("(" + importantList.size() + ")");
        importantAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDetail(Photo photo) {
        detailList.add(photo);
        tvDetailNum.setText("(" + detailList.size() + ")");
        detailAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDetails(List<Photo> photos) {
        detailList.addAll(photos);
        tvDetailNum.setText("(" + detailList.size() + ")");
        detailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPositionDelDialog(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        positionDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        photoPresenter.deletePositionPic(positionList.get(position));
                        positionDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        positionDel.dismiss();
                    }
                })
                .create();
        positionDel.show();
    }

    @Override
    public void showLikeDelDialog(final int position) {

        MyDialog.Builder builder = new MyDialog.Builder(this);
        likeDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        photoPresenter.deleteLikePic(likeList.get(position));
                        likeDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        likeDel.dismiss();
                    }
                })
                .create();
        likeDel.show();
    }

    @Override
    public void showImportantDelDialog(final int position) {

        MyDialog.Builder builder = new MyDialog.Builder(this);
        importantDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        photoPresenter.deleteImportantPic(importantList.get(position));
                        importantDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        importantDel.dismiss();
                    }
                })
                .create();
        importantDel.show();
    }

    @Override
    public void showDetailDelDialog(final int position) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        detailDel = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        photoPresenter.deleteDetailPic(detailList.get(position));
                        detailDel.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detailDel.dismiss();
                    }
                })
                .create();
        detailDel.show();
    }

    @Override
    public void savePhoto() {
        for (Photo pic : positionList) {
            if (pic.getId() == null || pic.getId().equals("")) {
                pic.setId(StringUtils.getUUID());
            }
        }
        Intent intent = getIntent();
        crimeItem.setPositionPhotoItem(positionList);
        crimeItem.setOverviewPhotoItem(likeList);
        crimeItem.setImportantPhotoItem(importantList);
        crimeItem.setDetailPhotoItem(detailList);
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public List<Photo> savePositionPicDes(List<Photo> positionPicList) {
        return null;
    }

    @Override
    public void updatePositionList(Photo positionPic) {
        positionList.remove(positionPic);
        tvPositionNum.setText("(" + positionList.size() + ")");
        positionAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateLikeList(Photo overViewPhoto) {
        likeList.remove(overViewPhoto);
        tvLikeNum.setText("(" + likeList.size() + ")");
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateImportantList(Photo importantPhoto) {
        importantList.remove(importantPhoto);
        tvImportantNum.setText("(" + importantList.size() + ")");
        importantAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateDetailList(Photo photo) {
        detailList.remove(photo);
        tvDetailNum.setText("(" + detailList.size() + ")");
        detailAdapter.notifyDataSetChanged();
    }

    @Override
    public void showOrientationDialog(int code) {
        //显示弹窗
        OrientationDialog.Builder builder = new OrientationDialog.Builder(this);
        orientationDialog = builder.setPositiveButton(getString(R.string.confirm), new OrientationDialog.ConfirmListener() {
            @Override
            public void onClick(String input) {
//                if(code == Constants.REQUEST_TAKE_PHOTO_POSITION) {
//                    EventBus.getDefault().post(new PicEvent(PicEvent.MSG_CAMERA_DES_POSITION,input));
//                }else if(code == Constants.REQUEST_TAKE_PHOTO_LIKE) {
//                    EventBus.getDefault().post(new PicEvent(PicEvent.MSG_CAMERA_DES_LIKE,input));
//                }
                if (code == Constants.REQUEST_TAKE_PHOTO_POSITION) {
                    photoPresenter.setPositionDes(input);
                } else if (code == Constants.REQUEST_TAKE_PHOTO_LIKE) {
                    photoPresenter.setLikeDes(input);
                }
                hideInput();
                orientationDialog.dismiss();
            }
        })
                .create();
        orientationDialog.show();
    }

    @Override
    public void updatePositionListOnDes(Photo positionPic) {
        for (Photo pic : positionList) {
            if (pic.getId().equals(positionPic.getId())) {
                pic.setPhotoInfo(positionPic.getPhotoInfo());
            }
        }
        positionAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateOverviewListOnDes(Photo uploadOverViewPhoto) {
        for (Photo photo : likeList) {
            if (photo.getId().equals(uploadOverViewPhoto.getId())) {
                photo.setPhotoInfo(uploadOverViewPhoto.getPhotoInfo());
            }
        }
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteDetail(String photoPath) {
        Iterator<Photo> it = detailList.iterator();
        while (it.hasNext()) {
            // index and number
            Photo photo = it.next();
            if (photo.getServerPath().equals(photoPath)) {
                it.remove();
            }
        }
        tvDetailNum.setText("(" + detailList.size() + ")");
        detailAdapter.notifyDataSetChanged();
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 清空缓存包括裁剪、压缩、AndroidQToPath所生成的文件，注意调用时机必须是处理完本身的业务逻辑后调用；非强制性
     */
    public void clearCache() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PictureFileUtils.deleteCacheDirFile(this, PictureMimeType.ofImage());
//            PictureFileUtils.deleteAllCacheDirFile(this);
        } else {
            PermissionChecker.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
        }
    }

}
