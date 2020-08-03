package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liany.collection.easycollectlibrary.EasyCollect_Scan_Activity;
import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.ContactsEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.utils.BitmapUtils;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.widget.CsiProgressDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @创建者 ly
 * @创建时间 2020/4/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Visit_AddContacts_FingerActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivLeft16;
    ImageView ivLeft17;
    ImageView ivLeft18;
    ImageView ivLeft19;
    ImageView ivLeft20;
    ImageView ivLeft11;
    ImageView ivLeft12;
    ImageView ivLeft13;
    ImageView ivLeft14;
    ImageView ivLeft15;

    private ContactsEntity contactsEntity;
    private List<Photo> fingers = new ArrayList<>();
    private CsiProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_visit_finger;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivLeft11 = findViewById(R.id.iv_left_11);
        ivLeft12 = findViewById(R.id.iv_left_12);
        ivLeft13 = findViewById(R.id.iv_left_13);
        ivLeft14 = findViewById(R.id.iv_left_14);
        ivLeft15 = findViewById(R.id.iv_left_15);
        ivLeft16 = findViewById(R.id.iv_left_16);
        ivLeft17 = findViewById(R.id.iv_left_17);
        ivLeft18 = findViewById(R.id.iv_left_18);
        ivLeft19 = findViewById(R.id.iv_left_19);
        ivLeft20 = findViewById(R.id.iv_left_20);

        ivTitleBack.setOnClickListener(this);
        ivLeft11.setOnClickListener(this);
        ivLeft12.setOnClickListener(this);
        ivLeft13.setOnClickListener(this);
        ivLeft14.setOnClickListener(this);
        ivLeft15.setOnClickListener(this);
        ivLeft16.setOnClickListener(this);
        ivLeft17.setOnClickListener(this);
        ivLeft18.setOnClickListener(this);
        ivLeft19.setOnClickListener(this);
        ivLeft20.setOnClickListener(this);

        progressDialog = new CsiProgressDialog(this,"正在上传图片");
        if(contactsEntity.getPhotos() != null) {
            fingers.addAll(contactsEntity.getPhotos());
            for(Photo photo : contactsEntity.getPhotos()) {
                String photoRev1 = photo.getRev1();
                if(StringUtils.checkString(photo.getRev1())) {
                    setFingerImg(Integer.valueOf(photoRev1),photo.getServerPath());
                }
            }
        }
        LogUtils.e("fingers init" + fingers.size());
    }

    @Override
    protected void initData() {
        contactsEntity = (ContactsEntity) getIntent().getSerializableExtra("contactsEntity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = getIntent();
            contactsEntity.setPhotos(fingers);
            intent.putExtra("entity", contactsEntity);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            Intent intent = getIntent();
            contactsEntity.setPhotos(fingers);
            intent.putExtra("entity", contactsEntity);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else if (id == R.id.iv_left_11) {
            startAddFinger(11);
        } else if (id == R.id.iv_left_12) {
            startAddFinger(12);
        } else if (id == R.id.iv_left_13) {
            startAddFinger(13);
        } else if (id == R.id.iv_left_14) {
            startAddFinger(14);
        } else if (id == R.id.iv_left_15) {
            startAddFinger(15);
        } else if (id == R.id.iv_left_16) {
            startAddFinger(16);
        } else if (id == R.id.iv_left_17) {
            startAddFinger(17);
        } else if (id == R.id.iv_left_18) {
            startAddFinger(18);
        } else if (id == R.id.iv_left_19) {
            startAddFinger(19);
        } else if (id == R.id.iv_left_20) {
            startAddFinger(20);
        }
    }

    private void startAddFinger(int fingerNo) {
        Map<Integer,String> result = new HashMap<>();
        Intent intent = new Intent(this, EasyCollect_Scan_Activity.class);
        intent.putExtra("com.liany.easycollect.filePath", Constants.path_photoDir);
        intent.putExtra("com.liany.easycollect.resultMap", (Serializable) result);
        intent.putExtra("com.liany.easycollect.fingerNo",fingerNo);
        startActivityForResult(intent, Constants.REQUEST_COLLECT_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.REQUEST_COLLECT_SCAN) {
                //指纹图片
                /**
                 * 指纹读取返回的是Map<Integer,String>,key是指纹代码,value是指纹图片的完整路径
                 * 指纹代码：
                 * 11:  "右手拇指";
                 * 12:  "右手食指";
                 * 13:  "右手中指";
                 * 14:  "右手环指";
                 * 15:  "右手小指";
                 * 16:  "左手拇指";
                 * 17:  "左手食指";
                 * 18:  "左手中指";
                 * 19:  "左手环指";
                 * 20:  "左手小指";
                 * 97:  "右手不确定指位";
                 * 98:  "左手不确定指位";
                 * 99:  "其他不确定指位";
                 */
                Map<Integer,String> result = (Map<Integer, String>) data.getSerializableExtra("com.liany.easycollect.resultMap");
                int fingerNo = data.getIntExtra("com.liany.easycollect.fingerNo", 11);
                LogUtils.e("fingerNo" + fingerNo);
                for(int i : result.keySet()) {
                    //png图片转bmp
                    String filePath = result.get(i);
                    if(!StringUtils.checkString(filePath)) {
                        return;
                    }
//                    File file = new File(filePath);
                    if(FileUtils.checkFileExists(filePath)) {
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        //裁剪图片
                        bitmap = BitmapUtils.imageCrop(bitmap,512,512);
                        bitmap = BitmapUtils.save640Bitmap(bitmap);
                        Bitmap resultBitmap = BitmapUtils.convertGray(bitmap);
                        String fileName = "Finger_" + StringUtils.long2FileName(new Date());
                        BitmapUtils.save8BitBmp(resultBitmap, Constants.path_photoDir + File.separator + fileName + ".bmp");
                        File file = new File(Constants.path_photoDir + File.separator + fileName + ".bmp");
                        String photoId = StringUtils.getUUID();
//                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        progressDialog.show();
                        uploadPic(file,
                                photoId + "," + fingerNo,
                                Constants.state_visit_people,
                                contactsEntity.getId(),
                                contactsEntity.getCrimeId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        progressDialog.dismiss();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setPath(Constants.path_photoDir + File.separator + fileName + ".bmp");
                                            photo.setServerPath(Constants.ipAddress + File.separator + contactsEntity.getCrimeId() + File.separator + response.getData());
                                            photo.setParentId(contactsEntity.getId());
                                            photo.setCrimeId(contactsEntity.getCrimeId());
                                            photo.setType("bmp");
                                            photo.setUUID(StringUtils.md5HashCode32(Constants.path_photoDir + File.separator + fileName + ".bmp"));
                                            photo.setFileName(fileName + ".bmp");
                                            photo.setWidth(String.valueOf(resultBitmap.getWidth()));
                                            photo.setHeight(String.valueOf(resultBitmap.getHeight()));
                                            photo.setState(Constants.state_visit_people);
                                            //采集的指位
                                            photo.setRev1(fingerNo + "");
                                            setFingerImg(i,photo.getServerPath());
//                                            fingers.add(photo);
                                            updateFingers(photo);
                                        }else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        progressDialog.dismiss();
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                    }
                                });
                        //删除原图
                        FileUtils.deleteFile(filePath);
                    }else {
                        ToastUtils.showShort("指纹采集错误：文件未找到");
                    }
                }

            }
        }
    }

    private void updateFingers(Photo photo) {
        Iterator<Photo> it = fingers.iterator();
        while(it.hasNext()){
            Photo finger = it.next();
            if(photo.getRev1() != null && finger.getRev1() != null && finger.getRev1().equals(photo.getRev1())){
                it.remove();
            }
        }
        fingers.add(photo);
        LogUtils.e("fingers uplateFingers" + fingers.size());
    }

    /**
     * 指纹 指位代码
     *
     * @param FPcode
     * @return
     */
    public String setFingerImg(int FPcode,String servicePath) {
        switch (FPcode) {
            case 11:
                setImg(servicePath,ivLeft11);
                return "右手拇指";
            case 12:
                setImg(servicePath,ivLeft12);
                return "右手食指";
            case 13:
                setImg(servicePath,ivLeft13);
                return "右手中指";
            case 14:
                setImg(servicePath,ivLeft14);
                return "右手环指";
            case 15:
                setImg(servicePath,ivLeft15);
                return "右手小指";
            case 16:
                setImg(servicePath,ivLeft16);
                return "左手拇指";
            case 17:
                setImg(servicePath,ivLeft17);
                return "左手食指";
            case 18:
                setImg(servicePath,ivLeft18);
                return "左手中指";
            case 19:
                setImg(servicePath,ivLeft19);
                return "左手环指";
            case 20:
                setImg(servicePath,ivLeft20);
                return "左手小指";
            case 97:
                return "右手不确定指位";
            case 98:
                return "左手不确定指位";
            case 99:
                return "其他不确定指位";
            default:
                return "未知";
        }
    }

    private void setImg(String path,ImageView imageView) {
        Glide.with(this).load(path)
                .dontAnimate()
                .into(imageView);
    }

    private void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/uploadPic")
                .tag(this)
                .params("pic",file)
                .params("photoId",photoId)
                .params("state",state)
                .params("parentId",parentId)
                .params("crimeId",crimeId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        LogUtils.e(response.body());
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        LogUtils.e(response.getException().getMessage());
                        callBack.onFail(response.getException().getMessage());
                    }
                });
    }

}
