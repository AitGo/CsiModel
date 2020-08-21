package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.liany.clientmodel.adapter.AddContactsAdapter;
import com.liany.clientmodel.adapter.AddItemAdapter;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.VisitContract;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.ItemEntity;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.sceneStep.VisitModel;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class VisitPresenter implements VisitContract.Presenter {
    private VisitContract.View visitView;
    private VisitContract.Model model;
    private Context mContext;
    private TimePickerView pv_time;

    public VisitPresenter(Context mContext, VisitContract.View view) {
        this.mContext = mContext;
        this.visitView = view;
        model = new VisitModel(mContext);
    }

    @Override
    public void initView(CrimeItem crimeItem) {
        if(crimeItem.getOccurred_start_time() != 0) {
            visitView.setOccurredStartTime(crimeItem.getOccurred_start_time());
        }
        if(crimeItem.getOccurred_end_time() != 0) {
            visitView.setOccurredEndTime(crimeItem.getOccurred_end_time());
        }
        if(crimeItem.getGet_access_time() != 0) {
            visitView.setAccessTime(crimeItem.getGet_access_time());
        }
        if(crimeItem.getAccess_start_time() != 0) {
            visitView.setAccessStartTime(crimeItem.getAccess_start_time());
        }
        if(crimeItem.getAccess_end_time() != 0) {
            visitView.setAccessEndTime(crimeItem.getAccess_end_time());
        }
        visitView.setAccessReason(crimeItem.getAccessReason());
        visitView.setCaseOccurProcess(crimeItem.getCaseOccurProcess());
    }

    @Override
    public void onAddContactsItemClick(List<ContactsEntity> contactsEntityList, int position) {
        visitView.startAddContactsActivity(contactsEntityList.get(position),position);
    }

    @Override
    public void onAddContactsItemLongClick(List<ContactsEntity> contactsEntityList, int position, AddContactsAdapter addContactsAdapter) {
        visitView.showContactsDeleteDialog(contactsEntityList,position,addContactsAdapter);
    }

    @Override
    public void deleteContacts(List<ContactsEntity> contactsEntityList, int position, AddContactsAdapter addContactsAdapter) {
        contactsEntityList.remove(position);
        addContactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddItemItemClick(List<ItemEntity> itemEntityList, int position) {
        visitView.startAddItemActivity(itemEntityList.get(position),position);
    }

    @Override
    public void onAddItemItemLongClick(List<ItemEntity> itemEntityList, int position, AddItemAdapter addItemAdapter) {
        visitView.showItemDeleteDialog(itemEntityList,position,addItemAdapter);
    }

    @Override
    public void deleteItem(List<ItemEntity> itemEntityList, int position, AddItemAdapter addItemAdapter) {
        itemEntityList.remove(position);
        addItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.REQUEST_VISIT_ADD_CONTACTS:
                    ContactsEntity entity = (ContactsEntity) data.getSerializableExtra("contactsEntity");
                    int position = data.getIntExtra("position",-1);
                    visitView.addContacts(entity,position);
                    break;
                case Constants.REQUEST_VISIT_ADD_ITEM:
                    ItemEntity itemEntity = (ItemEntity) data.getSerializableExtra("itemEntity");
                    int itemPosition = data.getIntExtra("position",-1);
                    visitView.addItem(itemEntity,itemPosition);
                    break;
            }
        }
    }

    public void getCaseOccurProcess(CrimeItem crimeItem){
        //Example : "据<被害人/报案人>报称:<发案开始时间> 在<发案地点>，该处发现一起<案件类别>，后拨打电话报警"
        String result = "据";
        if(crimeItem.getReleatedPeopleItem().size()>0){
            for(int i = 0;i<crimeItem.getReleatedPeopleItem().size();i++){
                result = result + crimeItem.getReleatedPeopleItem().get(i).getName();
                if(i!=crimeItem.getReleatedPeopleItem().size()-1) result = result + ",";
            }
        }else{
            result = result + "<被害人/报案人>";
        }

        result = result + "报称:"+ StringUtils.long2String(crimeItem.getOccurred_start_time())
                + "，在" + crimeItem.getLocation()
                + "，该处发现一起" + crimeItem.getCasetype()
                + "，后拨打电话报警。";
        visitView.setCaseOccurProcess(result);
    }

    public void getAccessReason(CrimeItem crimeItem){
        //Example : "<发案区划><接警人>接到<指派单位>的指派: 在该所管界内<发案地点>发生一起<案件类别>，请速派人员勘查现场"
        String result = crimeItem.getArea() + "接到"
                + crimeItem.getUnitsAssigned() + "的指派: 在该所管界内"
                + crimeItem.getLocation() + "发生一起" + crimeItem.getCasetype()
                + "，请速派人员勘查现场。";
        visitView.setAccessReason(result);
    }

    @Override
    public void saveVisit() {
        visitView.close();
    }

    /**
     * 事主对比
     */
    @Override
    public void compareContacts(CrimeItem crimeItem) {
        ProgressUtils.showProgressDialog(mContext,"正在提交数据");
        model.startComparePeople(crimeItem, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showLong(response.getData());
                }else {
                    ToastUtils.showLong(response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("提交事主比对错误:" + msg);
            }
        });
    }

    @Override
    public void occurredStartTime() {
        Calendar selectedDate;
        String sDate = StringUtils.long2String(visitView.getOccurredStartTime());
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                visitView.setOccurredStartTime(date.getTime());
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void occurredEndTime() {
        Calendar selectedDate;
        String sDate = StringUtils.long2String(visitView.getOccurredEndTime());
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                visitView.setOccurredEndTime(date.getTime());
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void getAccessTime() {
        Calendar selectedDate;
        String sDate = StringUtils.long2String(visitView.getAccessTime());
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                visitView.setAccessTime(date.getTime());
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void accessStartTime() {
        Calendar selectedDate;
        String sDate = StringUtils.long2String(visitView.getAccessStartTime());
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                visitView.setAccessStartTime(date.getTime());
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void accessEndTime() {
        Calendar selectedDate;
        String sDate = StringUtils.long2String(visitView.getAccessEndTime());
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                visitView.setAccessEndTime(date.getTime());
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }
}
