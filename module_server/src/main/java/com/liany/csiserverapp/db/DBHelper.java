package com.liany.csiserverapp.db;

import android.content.Context;
import android.util.Log;

import com.liany.csiserverapp.dao.database.greenDao.db.CompareEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ComparePhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CrimeItemDao;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoMaster;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.GoodEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ItemEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.KCTBASESTATIONDATABeanDao;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.SceneWifiInfoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ToolEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.WitnessEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysDictDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysOrganDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysTechnicianDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysUserDao;

import org.greenrobot.greendao.database.Database;

/**
 * @创建者 ly
 * @创建时间 2019/4/19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class DBHelper extends DaoMaster.DevOpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        DBMigrationHelper.getInstance().migrate(db, CrimeItemDao.class, ContactsEntityDao.class, ItemEntityDao.class, ToolEntityDao.class,
                EvidenceEntityDao.class,
                WitnessEntityDao.class, KCTBASESTATIONDATABeanDao.class, GoodEntityDao.class,
                SceneWifiInfoDao.class,
                sysUserDao.class, sysDictDao.class, sysOrganDao.class, sysTechnicianDao.class,
                CompareEntityDao.class, ComparePhotoDao.class, PhotoDao.class);
    }
}
