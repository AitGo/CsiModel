package com.liany.csiserverapp.andServer.model;

import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.sysUserDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.sysDict;
import com.liany.csiserverapp.diagnose.sysOrgan;
import com.liany.csiserverapp.diagnose.sysTechnician;
import com.liany.csiserverapp.diagnose.sysUser;
import com.liany.csiserverapp.utils.MD5Utils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class UserDb {

    public static void insertSysUser(List<sysUser> sysUsers) {
        ServerApplication.getDaoSession().getSysUserDao()
                .insertOrReplaceInTx(sysUsers);
    }

    public static void insertSysOrgan(List<sysOrgan> sysOrgans) {
        ServerApplication.getDaoSession().getSysOrganDao()
                .insertOrReplaceInTx(sysOrgans);
    }

    public static void insertSysTechnician(List<sysTechnician> sysTechnicians) {
        ServerApplication.getDaoSession().getSysTechnicianDao()
                .insertOrReplaceInTx(sysTechnicians);
    }

    public static void insertSysDict(List<sysDict> sysDicts) {
        ServerApplication.getDaoSession().getSysDictDao()
                .insertOrReplaceInTx(sysDicts);
    }

    public static void deleteSysUser() {
        ServerApplication.getDaoSession().getSysUserDao().deleteAll();
    }

    public static void deleteSysOrgan() {
        ServerApplication.getDaoSession().getSysOrganDao().deleteAll();
    }

    public static void deleteSysTechnician() {
        ServerApplication.getDaoSession().getSysTechnicianDao().deleteAll();
    }

    public static void deleteSysDict() {
        ServerApplication.getDaoSession().getSysDictDao().deleteAll();
    }

    public static sysUser selectUser(String account, String password) {
        final String MD5Password = MD5Utils.MD5(password);
        return ServerApplication.getDaoSession().getSysUserDao()
                .queryBuilder()
                .where(sysUserDao.Properties.UserName.eq(account),sysUserDao.Properties.Password.eq(MD5Password))
                .unique();
    }

    public static String selectorganId(String techId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        sysTechnician technician = daoSession.getSysTechnicianDao().load(techId);
        return technician.getOrganId();
    }

    public static String selectPersonId(String techId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        sysTechnician technician = daoSession.getSysTechnicianDao().load(techId);
        return technician.getIdCardNo();
    }

    public static sysOrgan selectUnitName(String organId) {
        return ServerApplication.getDaoSession().getSysOrganDao().load(organId);
    }

    public static sysUser selectUserById(String userId) {
        return ServerApplication.getDaoSession().getSysUserDao().load(userId);
    }

    public static List<sysUser> selectUserByName(String account) {
        return ServerApplication.getDaoSession().getSysUserDao().queryBuilder().where(sysUserDao.Properties.UserName.eq(account)).list();
    }
}
