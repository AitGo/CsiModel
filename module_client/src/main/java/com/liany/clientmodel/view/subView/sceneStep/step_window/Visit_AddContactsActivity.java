package com.liany.clientmodel.view.subView.sceneStep.step_window;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.AddContactsFingerAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Visit_AddContactsContract;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.presenter.subView.sceneStep.step_window.Visit_AddContactsPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.CsiProgressDialog;
import com.liany.clientmodel.widget.MyDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Visit_AddContactsActivity extends BaseAcitivity implements Visit_AddContactsContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    ImageView ivTitleConfirm;
    RadioButton radioBaoanren;
    RadioButton radioShouhairen;
    RadioGroup radioGroupPeopleRelation;
    EditText etName;
    RadioButton radioMan;
    RadioButton radioWoman;
    RadioGroup radioGroupSex;
    ClearableEditText etId;
    ClearableEditText etTel;
    ClearableEditText etAddress;
    ImageView ivCollect;
    RecyclerView rvPhotoFinger;
    ImageButton btnScanCardId;

    private Visit_AddContactsContract.Presenter addContactsPresenter;
    private ContactsEntity contactsEntity;
    private List<Photo> photos = new ArrayList<>();
    private int position = -1;
    private String sex = "男";
    private String sexKey = "1";
    private String type = "报案人";
    private String crimeId;
    private MyDialog myDialog;
    private AddContactsFingerAdapter adapter;
    private Context mContext;
    private static int RESULT_CODE_STARTCAMERA = 10002;
    private CsiProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_view_visit_addcontacts;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        radioBaoanren = findViewById(R.id.radio_Baoanren);
        radioShouhairen = findViewById(R.id.radio_Shouhairen);
        radioGroupPeopleRelation = findViewById(R.id.radioGroupPeopleRelation);
        etName = findViewById(R.id.et_name);
        radioMan = findViewById(R.id.radio_man);
        radioWoman = findViewById(R.id.radio_woman);
        radioGroupSex = findViewById(R.id.radioGroupSex);
        etId = findViewById(R.id.et_id);
        etTel = findViewById(R.id.et_tel);
        etAddress = findViewById(R.id.et_address);
        ivCollect = findViewById(R.id.iv_collect);
        rvPhotoFinger = findViewById(R.id.rv_photo_finger);
        btnScanCardId = findViewById(R.id.btn_scanCardId);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        ivCollect.setOnClickListener(this);

        ivCollect.setVisibility(View.VISIBLE);
        ivTitleConfirm.setVisibility(View.VISIBLE);
        btnScanCardId.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.add_people));
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvPhotoFinger.setLayoutManager(layoutManager);
//        adapter = new AddContactsFingerAdapter(R.layout.item_adapter_visit_contacts_finger, photos);
//        rvPhotoFinger.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if(photos.size() > 0) {
//                    Intent photo = new Intent(Visit_AddContactsActivity.this, PhotoViewActivity.class);
//                    photo.putExtra("filePath", photos.get(position).getPath());
//                    startActivity(photo);
//                }else {
//                    ToastUtils.showShort("暂无图片");
//                }
//            }
//        });
//        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//                MyDialog.Builder builder = new MyDialog.Builder(mContext);
//                myDialog = builder.setTitle(getString(R.string.prompt))
//                        .setMsg("是否要删除该指纹图片")
//                        .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
//                            @Override
//                            public void onClick() {
//                                //删除指纹图片
//                                addContactsPresenter.deleteFingerPhoto(photos, position, contactsEntity.getId());
//                                myDialog.dismiss();
//                            }
//                        })
//                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                myDialog.dismiss();
//                            }
//                        })
//                        .create();
//                myDialog.show();
//                return false;
//            }
//        });

        addContactsPresenter.initViewDate(contactsEntity);
        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radio_man) {
                    sex = "男";
                    sexKey = "1";
                } else if (checkedRadioButtonId == R.id.radio_woman) {
                    sex = "女";
                    sexKey = "2";
                }
            }
        });
        radioGroupPeopleRelation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radio_Baoanren) {
                    type = "报案人";
                } else if (checkedRadioButtonId == R.id.radio_Shouhairen) {
                    type = "受害人";
                }
            }
        });

        etId.setKeyListener(DigitsKeyListener.getInstance("abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        etTel.setKeyListener(DigitsKeyListener.getInstance("()-0123456789"));

        etId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtils.d("Anita", "s.length = " + s.length() + ", s =" + s.toString());
                if (s.length() == 17) etId.setKeyListener(new DigitsKeyListener() {
                    @Override
                    public int getInputType() {
                        return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                    }

                    @Override
                    protected char[] getAcceptedChars() {
                        char[] data = getResources().getString(R.string.login_only_can_input).toCharArray();
                        return data;
                    }
                });
                if (s.length() != 17)
                    etId.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                if (s.length() >= 18) etId.setKeyListener(DigitsKeyListener.getInstance(""));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void initData() {
        mContext = this;
        progressDialog = new CsiProgressDialog(mContext,"正在上传图片");
        photos.clear();
        checkSavePermission();
        addContactsPresenter = new Visit_AddContactsPresenter(this, this);
        contactsEntity = (ContactsEntity) getIntent().getSerializableExtra("contactsEntity");
        position = getIntent().getIntExtra("position", -1);
        crimeId = getIntent().getStringExtra("crimeId");
        if (contactsEntity == null) {
            contactsEntity = new ContactsEntity();
            contactsEntity.setId(StringUtils.getUUID());
            contactsEntity.setCrimeId(crimeId);
        } else {
            photos.addAll(contactsEntity.getPhotos());
        }
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
            finish();
        } else if (id == R.id.iv_title_confirm) {
            addContactsPresenter.saveContract(contactsEntity, crimeId, true, photos);
        } else if (id == R.id.iv_collect) {//指纹采集
//                addContactsPresenter.startFigerPrint(contactsEntity);
            Intent intent = new Intent(Visit_AddContactsActivity.this, Visit_AddContacts_FingerActivity.class);
            intent.putExtra("contactsEntity", contactsEntity);
            startActivityForResult(intent, Constants.REQUEST_VISIT_ADD_CONTACTS_FINGER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addContactsPresenter.onActivityResult(requestCode, resultCode, data, contactsEntity);
    }

    @Override
    public void setType(String type) {
        if (type == null) {
            type = "报案人";
        }
        if (type.equals("报案人")) {
            radioBaoanren.setChecked(true);
            radioShouhairen.setChecked(false);
        } else if (type.equals("受害人")) {
            radioBaoanren.setChecked(false);
            radioShouhairen.setChecked(true);
        }
    }

    @Override
    public void setName(String name) {
        etName.setText(name == null ? "" : name);
    }

    @Override
    public void setSex(String sexKey) {
        if (sexKey == null || sexKey.equals("")) {
            sexKey = "1";
            this.sexKey = "1";
            sex = "男";
        }
        if (sexKey.equals("1")) {
            radioMan.setChecked(true);
            radioWoman.setChecked(false);
            this.sexKey = "1";
            sex = "男";
        } else if (sexKey.equals("2")) {
            radioMan.setChecked(false);
            radioWoman.setChecked(true);
            this.sexKey = "2";
            sex = "女";
        }
    }

    @Override
    public void setId(String id) {
        etId.setText(id == null ? "" : id);
    }

    @Override
    public void setTel(String tel) {
        etTel.setText(tel == null ? "" : tel);
    }

    @Override
    public void setAddress(String address) {
        etAddress.setText(address == null ? "" : address);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return etName.getText().toString() == null ? "" : etName.getText().toString();
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public String getSexKey() {
        return sexKey;
    }

    @Override
    public String getId() {
        return etId.getText().toString() == null ? "" : etId.getText().toString();
    }

    @Override
    public String getTel() {
        return etTel.getText().toString() == null ? "" : etTel.getText().toString();
    }

    @Override
    public String getAddress() {
        return etAddress.getText().toString() == null ? "" : etAddress.getText().toString();
    }

    /**
     * 传入文件保存路径，结尾不要"/"，不传默认的路径是：Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "easycollect"
     * Map<Integer,String> result为已经采集的指纹，传入后会在图片上显示已经采集,key为指纹代码，value传"" 空字符串即可
     *                  * 指纹代码：
     *                  * 11:  "右手拇指";
     *                  * 12:  "右手食指";
     *                  * 13:  "右手中指";
     *                  * 14:  "右手环指";
     *                  * 15:  "右手小指";
     *                  * 16:  "左手拇指";
     *                  * 17:  "左手食指";
     *                  * 18:  "左手中指";
     *                  * 19:  "左手环指";
     *                  * 20:  "左手小指";
     *                  * 97:  "右手不确定指位";
     *                  * 98:  "左手不确定指位";
     *                  * 99:  "其他不确定指位";
     */
    @Override
    public void startFigerPrintDevice(Map<Integer,String> result, String filePath) {
//        Intent intent2 = new Intent(this, EasyCollect_Scan_Activity.class);
//        intent2.putExtra("com.liany.easycollect.filePath",filePath);
//        intent2.putExtra("com.liany.easycollect.resultMap", (Serializable) result);
//        startActivityForResult(intent2, Constants.REQUEST_COLLECT_SCAN);
    }

    @Override
    public void addFigerPhoto(Photo photo) {
        photos.add(photo);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void removeFigerPhoto(int position) {
        photos.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void close(ContactsEntity entity) {
        Intent intent = getIntent();
        intent.putExtra("contactsEntity", entity);
        intent.putExtra("position", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void showMsgDialog(String msg) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
//                        addContactsPresenter.saveContract(contactsEntity, crimeId, false);
                        //删除指纹图片
//                        addContactsPresenter.deleteFingerPhoto(photos, -1, contactsEntity.getId());
                        myDialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("补全信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

    @Override
    public void showProgress(String msg) {
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setPhotos(List<Photo> fingers) {
        photos.clear();
        photos.addAll(fingers);
        contactsEntity.setPhotos(photos);
//        adapter.notifyDataSetChanged();
    }

    private void checkSavePermission() {
        //是否授权
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //提示用户打开权限
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, perms, RESULT_CODE_STARTCAMERA);
        }

    }

    private void copy(Context context, String fileName, String saveName,
                      String savePath) {
        File path = new File(savePath);
        if (!path.exists()) {
            path.mkdir();
        }
        try {
            File e = new File(savePath + "/" + saveName);
            if (e.exists() && e.length() > 0L) {
                Log.i("LU", saveName + "存在了");
                return;
            }
            FileOutputStream fos = new FileOutputStream(e);
            InputStream inputStream = context.getResources().getAssets()
                    .open(fileName);
            byte[] buf = new byte[1024];
            boolean len = false;

            int len1;
            while ((len1 = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len1);
            }

            fos.close();
            inputStream.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            Log.i("LU", "IO异常");
        }

    }

}
