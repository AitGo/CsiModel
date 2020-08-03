package com.liany.csiclient.view.subView.sceneStep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.csiclient.R;
import com.liany.csiclient.adapter.AddContactsAdapter;
import com.liany.csiclient.adapter.AddItemAdapter;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.contract.subView.sceneStep.VisitContract;
import com.liany.csiclient.diagnose.ContactsEntity;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.ItemEntity;
import com.liany.csiclient.presenter.subView.sceneStep.VisitPresenter;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.TimeUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.view.subView.sceneStep.step_window.Visit_AddContactsActivity;
import com.liany.csiclient.view.subView.sceneStep.step_window.Visit_AddItemActivity;
import com.liany.csiclient.widget.ClearableEditText;
import com.liany.csiclient.widget.MyDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
public class VisitActivity extends BaseAcitivity implements VisitContract.View, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    ImageView ivTitleConfirm;
    LinearLayout visitAddPeople;
    RecyclerView visitPeopleListView;
    LinearLayout visitAddItem;
    RecyclerView visitItemListView;
    ImageButton btnCompare;
    Button accessReasonButton;
    ClearableEditText accessReason;
    Button caseOccurProcessButton;
    ClearableEditText caseOccurProcess;
    TextView occurredStartTime;
    TextView occurredEndTime;
    TextView getAccessTime;
    TextView accessStartTime;
    TextView accessEndTime;

    private VisitContract.Presenter visitPresenter;
    private AddContactsAdapter addContactsAdapter;
    private AddItemAdapter addItemAdapter;
    private List<ContactsEntity> contactsEntityList = new ArrayList<>();
    private List<ItemEntity> itemEntityList = new ArrayList<>();
    private MyDialog contactsDelDialog;
    private MyDialog itemDelDialog;
    private MyDialog compareDialog;
    private CrimeItem crimeItem;

    static final String STATE_Contacts = "contactsList";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_visit;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleSetting = findViewById(R.id.iv_title_setting);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        visitAddPeople = findViewById(R.id.visit_add_people);
        visitPeopleListView = findViewById(R.id.visit_people_listView);
        visitAddItem = findViewById(R.id.visit_add_item);
        visitItemListView = findViewById(R.id.visit_item_listView);
        btnCompare = findViewById(R.id.btn_compare);
        accessReasonButton = findViewById(R.id.accessReason_button);
        accessReason = findViewById(R.id.accessReason);
        caseOccurProcessButton = findViewById(R.id.caseOccurProcess_button);
        caseOccurProcess = findViewById(R.id.caseOccurProcess);
        occurredStartTime = findViewById(R.id.occurred_start_time);
        occurredEndTime = findViewById(R.id.occurred_end_time);
        getAccessTime = findViewById(R.id.get_access_time);
        accessStartTime = findViewById(R.id.access_start_time);
        accessEndTime = findViewById(R.id.access_end_time);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        visitAddPeople.setOnClickListener(this);
        visitAddItem.setOnClickListener(this);
        btnCompare.setOnClickListener(this);
        caseOccurProcessButton.setOnClickListener(this);
        accessReasonButton.setOnClickListener(this);
        occurredStartTime.setOnClickListener(this);
        occurredEndTime.setOnClickListener(this);
        getAccessTime.setOnClickListener(this);
        accessStartTime.setOnClickListener(this);
        accessEndTime.setOnClickListener(this);

        btnCompare.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.title_activity_step1));
        accessReason.setMaxLines(10);
        caseOccurProcess.setMaxLines(10);
        visitPresenter.initView(crimeItem);
        //initRecyclerview
        visitPeopleListView.setLayoutManager(new LinearLayoutManager(this));
        visitItemListView.setLayoutManager(new LinearLayoutManager(this));

        addContactsAdapter = new AddContactsAdapter(R.layout.item__adapter_contacts, contactsEntityList);
        visitPeopleListView.setAdapter(addContactsAdapter);
        addItemAdapter = new AddItemAdapter(R.layout.item__adapter_item, itemEntityList);
        visitItemListView.setAdapter(addItemAdapter);

        addContactsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                visitPresenter.onAddContactsItemClick(contactsEntityList, position);
            }
        });

        addContactsAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                visitPresenter.onAddContactsItemLongClick(contactsEntityList, position, addContactsAdapter);
                return true;
            }
        });

        addItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                visitPresenter.onAddItemItemClick(itemEntityList, position);
            }
        });

        addItemAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                visitPresenter.onAddItemItemLongClick(itemEntityList, position, addItemAdapter);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        visitPresenter = new VisitPresenter(this, this);

        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        if(crimeItem.getAccess_start_time() == 0) {
            String accessStartTime = TimeUtils.getTime(null, Calendar.HOUR_OF_DAY,0);
            crimeItem.setAccess_start_time(StringUtils.String2long(accessStartTime));
        }
        if (crimeItem.getId() != null && crimeItem.getReleatedPeopleItem() != null) {
            contactsEntityList.addAll(crimeItem.getReleatedPeopleItem());
        }
        if (crimeItem.getId() != null && crimeItem.getLostItem() != null) {
            itemEntityList.addAll(crimeItem.getLostItem());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (contactsEntityList != null) {
            outState.putSerializable(STATE_Contacts, (Serializable) contactsEntityList);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        List<ContactsEntity> lists = (List<ContactsEntity>) savedInstanceState.getSerializable(STATE_Contacts);
        if (lists.size() > 0) {
            contactsEntityList.clear();
            contactsEntityList.addAll(lists);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            visitPresenter.saveVisit();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            visitPresenter.saveVisit();
        } else if (id == R.id.iv_title_confirm) {
            visitPresenter.saveVisit();
        } else if (id == R.id.visit_add_people) {//添加关联人
            Intent addPeople = new Intent(VisitActivity.this, Visit_AddContactsActivity.class);
            addPeople.putExtra("crimeId", crimeItem.getId());
            startActivityForResult(addPeople, Constants.REQUEST_VISIT_ADD_CONTACTS);
        } else if (id == R.id.visit_add_item) {//添加损失工具
            Intent addItem = new Intent(VisitActivity.this, Visit_AddItemActivity.class);
            addItem.putExtra("crimeId", crimeItem.getId());
            startActivityForResult(addItem, Constants.REQUEST_VISIT_ADD_ITEM);
        } else if (id == R.id.btn_compare) {
            showCompareDialog(contactsEntityList, crimeItem);
        } else if (id == R.id.caseOccurProcess_button) {
            visitPresenter.getCaseOccurProcess(crimeItem);
        } else if (id == R.id.accessReason_button) {
            visitPresenter.getAccessReason(crimeItem);
        } else if (id == R.id.occurred_start_time) {//案发开始时间
            hideInput();
            visitPresenter.occurredStartTime();
        } else if (id == R.id.occurred_end_time) {//案发结束时间
            hideInput();
            visitPresenter.occurredEndTime();
        } else if (id == R.id.get_access_time) {//接勘时间
            hideInput();
            visitPresenter.getAccessTime();
        } else if (id == R.id.access_start_time) {//勘验开始时间
            hideInput();
            visitPresenter.accessStartTime();
        } else if (id == R.id.access_end_time) {//勘验结束时间
            hideInput();
            visitPresenter.accessEndTime();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        visitPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startAddContactsActivity(ContactsEntity contactsEntity, int position) {
        Intent intent = new Intent(VisitActivity.this, Visit_AddContactsActivity.class);
        intent.putExtra("contactsEntity", contactsEntity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_VISIT_ADD_CONTACTS);
    }

    @Override
    public void startAddItemActivity(ItemEntity itemEntity, int position) {
        Intent intent = new Intent(VisitActivity.this, Visit_AddItemActivity.class);
        intent.putExtra("itemEntity", itemEntity);
        intent.putExtra("position", position);
        intent.putExtra("crimeId", crimeItem.getId());
        startActivityForResult(intent, Constants.REQUEST_VISIT_ADD_ITEM);
    }

    @Override
    public void showContactsDeleteDialog(final List<ContactsEntity> contactsEntityList, final int position, final AddContactsAdapter addContactsAdapter) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        contactsDelDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        visitPresenter.deleteContacts(contactsEntityList, position, addContactsAdapter);
                        contactsDelDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactsDelDialog.dismiss();
                    }
                })
                .create();
        contactsDelDialog.show();
    }

    @Override
    public void showItemDeleteDialog(final List<ItemEntity> itemEntityList, final int position, final AddItemAdapter addItemAdapter) {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        itemDelDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg(getString(R.string.delete_info))
                .setPositiveButton(getString(R.string.delete), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        visitPresenter.deleteItem(itemEntityList, position, addItemAdapter);
                        itemDelDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemDelDialog.dismiss();
                    }
                })
                .create();
        itemDelDialog.show();
    }

    @Override
    public void showCompareDialog(List<ContactsEntity> contactsEntities, CrimeItem item) {
        List<EvidenceEntity> fingers = new ArrayList<>();
        for(EvidenceEntity evidenceEntity : item.getEvidenceItem()) {
            if(evidenceEntity.getEvidenceCategory().equals("手印")) {
                fingers.add(evidenceEntity);
            }
        }
        if(contactsEntities.size() == 0) {
            ToastUtils.showLong("暂无人员指纹，请提交后重试");
            return;
        }else if(fingers.size() == 0) {
            ToastUtils.showLong("暂无现场指纹，请提交后重试");
            return;
        }
        MyDialog.Builder builder = new MyDialog.Builder(this);
        compareDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要提交事主指纹进行比对")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        //打包bmp文件并上传
                        crimeItem.setReleatedPeopleItem(contactsEntityList);
                        visitPresenter.compareContacts(crimeItem);
                        compareDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        compareDialog.dismiss();
                    }
                })
                .create();
        compareDialog.show();
    }

    @Override
    public void addContacts(ContactsEntity entity, int position) {
        if (position != -1) {
            contactsEntityList.remove(position);
            contactsEntityList.add(position, entity);
        } else {
            contactsEntityList.add(entity);
            if(contactsEntityList.size() == 1) {
                //判断是否是第一个录入项，如果是，则复制一个不同类型的
                ContactsEntity entity1 = new ContactsEntity();
                if(entity.getType().equals("报案人")) {
                    entity1.setType("受害人");
                }else {
                    entity1.setType("报案人");
                }
                entity1.setId(StringUtils.getUUID());
                entity1.setAddress(entity.getAddress());
                entity1.setCrimeId(entity.getCrimeId());
                entity1.setName(entity.getName());
                entity1.setSex(entity.getSex());
                entity1.setSexKey(entity.getSexKey());
                entity1.setTel(entity.getTel());
                entity1.setPeopleId(entity.getPeopleId());
                //不复制指纹图片
//                entity1.setPhotos(entity.getPhotos());
                entity1.setPhotos(new ArrayList<>());
                entity1.setRev1(entity.getRev1());
                entity1.setRev2(entity.getRev2());
                entity1.setRev3(entity.getRev3());
                entity1.setRev4(entity.getRev4());
                contactsEntityList.add(entity1);
            }
        }
        crimeItem.setReleatedPeopleItem(contactsEntityList);
        addContactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addItem(ItemEntity entity, int position) {
        if (position != -1) {
            itemEntityList.remove(position);
            itemEntityList.add(position, entity);
        } else {
            itemEntityList.add(entity);
        }
        addItemAdapter.notifyDataSetChanged();
    }


    @Override
    public void close() {
        Intent intent = getIntent();
        crimeItem.setReleatedPeopleItem(contactsEntityList);
        crimeItem.setLostItem(itemEntityList);
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void setCaseOccurProcess(String result) {
        caseOccurProcess.setText(result);
        crimeItem.setCaseOccurProcess(result);
    }

    @Override
    public void setAccessReason(String result) {
        accessReason.setText(result);
        crimeItem.setAccessReason(result);
    }

    @Override
    public void setOccurredStartTime(long date) {
        occurredStartTime.setText(StringUtils.long2String(date));
        crimeItem.setOccurred_start_time(date);
    }

    @Override
    public void setOccurredEndTime(long date) {
        occurredEndTime.setText(StringUtils.long2String(date));
        crimeItem.setOccurred_end_time(date);
    }

    @Override
    public void setAccessTime(long date) {
        getAccessTime.setText(StringUtils.long2String(date));
        crimeItem.setGet_access_time(date);
    }

    @Override
    public void setAccessStartTime(long date) {
        accessStartTime.setText(StringUtils.long2String(date));
        crimeItem.setAccess_start_time(date);
    }

    @Override
    public void setAccessEndTime(long date) {
        accessEndTime.setText(StringUtils.long2String(date));
        crimeItem.setAccess_end_time(date);
    }

    @Override
    public long getOccurredStartTime() {
        return StringUtils.String2long(occurredStartTime.getText().toString());
    }

    @Override
    public long getOccurredEndTime() {
        return StringUtils.String2long(occurredEndTime.getText().toString());
    }

    @Override
    public long getAccessTime() {
        return StringUtils.String2long(getAccessTime.getText().toString());
    }

    @Override
    public long getAccessStartTime() {
        return StringUtils.String2long(accessStartTime.getText().toString());
    }

    @Override
    public long getAccessEndTime() {
        return StringUtils.String2long(accessEndTime.getText().toString());
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

}
