package com.liany.clientmodel.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.clientmodel.adapter.AddContactsAdapter;
import com.liany.clientmodel.adapter.AddItemAdapter;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.ItemEntity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface VisitContract {
    interface Model {
        void startComparePeople(CrimeItem crimeItem, callBack callBack);
    }

    interface View {
        void startAddContactsActivity(ContactsEntity contactsEntityItem, int position);

        void startAddItemActivity(ItemEntity itemEntity, int position);

        void showContactsDeleteDialog(final List<ContactsEntity> contactsEntityList, final int position, final AddContactsAdapter addContactsAdapter);

        void showItemDeleteDialog(final List<ItemEntity> contactsEntityList, final int position, final AddItemAdapter addItemAdapter);

        void showCompareDialog(List<ContactsEntity> contactsEntities, CrimeItem crimeItem);

        void addContacts(ContactsEntity entity, int position);

        void addItem(ItemEntity entity, int position);

        void close();

        void setCaseOccurProcess(String result);

        void setAccessReason(String result);

        void setOccurredStartTime(long date);
        void setOccurredEndTime(long date);
        void setAccessTime(long date);//接勘时间
        void setAccessStartTime(long date);
        void setAccessEndTime(long date);

        long getOccurredStartTime();
        long getOccurredEndTime();
        long getAccessTime();//接勘时间
        long getAccessStartTime();
        long getAccessEndTime();
    }
    interface Presenter {
        void initView(CrimeItem item);

        void onAddContactsItemClick(List<ContactsEntity> contactsEntityList, int position);

        void onAddContactsItemLongClick(List<ContactsEntity> contactsEntityList, int position, AddContactsAdapter addContactsAdapter);

        void deleteContacts(List<ContactsEntity> contactsEntityList, int position, AddContactsAdapter addContactsAdapter);

        void onAddItemItemClick(List<ItemEntity> itemEntityList, int position);

        void onAddItemItemLongClick(List<ItemEntity> itemEntityList, int position, AddItemAdapter addItemAdapter);

        void deleteItem(List<ItemEntity> itemEntityList, int position, AddItemAdapter addItemAdapter);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void getCaseOccurProcess(CrimeItem crimeItem);

        void getAccessReason(CrimeItem crimeItem);

        void saveVisit();

        void compareContacts(CrimeItem crimeItem);

        void occurredStartTime();
        void occurredEndTime();
        void getAccessTime();//接勘时间
        void accessStartTime();

        void accessEndTime();
    }
}
