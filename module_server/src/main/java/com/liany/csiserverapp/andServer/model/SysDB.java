package com.liany.csiserverapp.andServer.model;

import android.content.Context;

import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.sysDictDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysOrganDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysTechnicianDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysUserDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.SelectLocationBean;
import com.liany.csiserverapp.diagnose.selectUser;
import com.liany.csiserverapp.diagnose.sysDict;
import com.liany.csiserverapp.diagnose.sysOrgan;
import com.liany.csiserverapp.diagnose.sysTechnician;
import com.liany.csiserverapp.diagnose.sysUser;
import com.liany.csiserverapp.network.response.Response;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.GsonUtils;
import com.liany.csiserverapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/16
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SysDB {

    public static String getTechidByUserName(String name) {
        return ServerApplication.getDaoSession().getSysUserDao().queryBuilder().where(sysUserDao.Properties.UserName.eq(name)).unique().getTechId();
    }

    public static String getOrgIdByTechId(String techId) {
        return ServerApplication.getDaoSession().getSysTechnicianDao().load(techId).getOrganId();
    }

    public static sysOrgan getOrgIdById(String orgId) {
        return ServerApplication.getDaoSession().getSysOrganDao().load(orgId);
    }


    public static List<selectUser> selectPeople(String organId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<selectUser> users = new ArrayList<>();
        List<sysOrgan> organs = daoSession.getSysOrganDao().queryBuilder().where(sysOrganDao.Properties.Id.eq(organId)).list();
        for(sysOrgan organ : organs) {
            List<sysTechnician> technicians = daoSession.getSysTechnicianDao().queryBuilder().where(sysTechnicianDao.Properties.OrganId.eq(organ.getId())).list();
            for(sysTechnician technician : technicians) {
                List<sysUser> sysUsers = daoSession.getSysUserDao().queryBuilder().where(sysUserDao.Properties.TechId.eq(technician.getId())).list();
                for(sysUser user : sysUsers) {
                    selectUser item = new selectUser();
                    item.setUserId(user.getId());
                    item.setUserName(user.getUserName());
                    item.setTrueName(user.getTrueName());
                    item.setTechId(technician.getId());
                    item.setOrganId(organ.getId());
                    item.setUnitName(organ.getUnitName());
                    item.setShortName(organ.getShortName());
                    item.setUnitCode(organ.getUnitCode());
                    item.setParentId(organ.getParentId());
                    users.add(item);
                }
            }
        }
        return users;
    }

    public static List<sysDict> selectWeatherCondition() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_WeatherCondition)).list();
    }

    public static List<sysDict> selectWindDirection() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_WindDirection)).list();
    }

    public static List<selectUser> selectArea(String organId) {
        List<selectUser> areas = new ArrayList<>();
        sysOrgan organ = ServerApplication.getDaoSession().getSysOrganDao().load(organId);
        selectUser area = new selectUser();
        area.setOrganId(organ.getId());
        area.setShortName(organ.getShortName());
        area.setUnitName(organ.getUnitName());
        area.setUnitCode(organ.getUnitCode());
        areas.add(area);
        return areas;
    }

    public static List<sysDict> selectLight() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_light)).list();
    }

    public static List<sysDict> selectCasetype(Context mContext) {
        String json = FileUtils.ReadAssetsFile(mContext, "caseTypeValue.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
        return response.getData();
//        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq("AJLBDM")).list();
    }

    public static List<sysDict> selectToolType() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_ToolType)).list();
    }

    public static List<sysDict> selectToolSource() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_ToolSource)).list();
    }

    public static List<sysDict> selectHandEvidence() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_HandEvidence)).list();
    }

    public static List<sysDict> selectFootEvidence() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_FootEvidence)).list();
    }

    public static List<sysDict> selectToolEvidence() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_ToolEvidence)).list();
    }

    public static List<sysDict> selectHandMethod() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_HandMethod)).list();
    }

    public static List<sysDict> selectFootMethod() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_FootMethod)).list();
    }

    public static List<sysDict> selectToolMethod() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_ToolMethod)).list();
    }

    public static List<sysDict> selectInfer() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_Infer)).list();
    }

    public static List<sysDict> selectPeopleNumber() {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_PeopleNumber)).list();
    }

    public static List<sysDict> selectCrimeMeans() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeMeans),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectCrimeCharacter() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeCharacter),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectCrimeCharacterByCaseType(String crimeCharacter, String crimeCharacterKey) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<sysDict> dicts = new ArrayList<>();
        List<sysDict> sysDicts = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeCharacter),
                sysDictDao.Properties.DictKey.eq(crimeCharacterKey),
                sysDictDao.Properties.ParentKey.eq(Constants.DICT_CrimeCharacter))
                .list();
        dicts.addAll(sysDicts);
        List<sysDict> sysDicts1 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeCharacter),
                sysDictDao.Properties.ParentKey.eq(crimeCharacterKey))
                .list();
        dicts.addAll(sysDicts1);
        return dicts;
    }

    public static List<sysDict> selectCrimeEntrance() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.ParentKey.eq(Constants.DICT_CrimeEntrance)).list();
    }

    public static List<sysDict> selectCrimeTiming() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<sysDict> dicts = new ArrayList<>();
        List<sysDict> sysDicts = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeTiming),
                sysDictDao.Properties.ParentKey.eq("30"))
                .list();
        dicts.addAll(sysDicts);
        List<sysDict> sysDicts1 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeTiming),
                sysDictDao.Properties.ParentKey.eq(Constants.DICT_CrimeTiming),
                sysDictDao.Properties.DictKey.eq("99"))
                .list();
        dicts.addAll(sysDicts1);
        return dicts;
    }

    public static List<sysDict> selectObject() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_Object),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectCrimeFeature() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeFeature),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectIntrusiveMethod(List<sysDict> sysDicts) {
        List<sysDict> dicts = new ArrayList<>();
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        for(sysDict dict : sysDicts) {
            dicts.add(dict);
            List<sysDict> list = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(dict.getRootKey()), sysDictDao.Properties.ParentKey.eq(dict.getDictKey())).list();
            dicts.addAll(list);
        }
        return dicts;
//        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_IntrusiveMethod),
//                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectLocation() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_Location),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static List<sysDict> selectCrimePurpose() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimePurpose),
                sysDictDao.Properties.ParentKey.notEq("")).list();
    }

    public static Object selectCrimeCharacterByCrime(CrimeItem item) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<sysDict> dicts = new ArrayList<>();
        List<sysDict> sysDicts = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeCharacter),
                sysDictDao.Properties.DictKey.eq(item.getCrimeCharacterKey()),
                sysDictDao.Properties.ParentKey.eq(Constants.DICT_CrimeCharacter))
                .list();
        dicts.addAll(sysDicts);
        List<sysDict> sysDicts1 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_CrimeCharacter),
                sysDictDao.Properties.ParentKey.eq(item.getCrimeCharacterKey()))
                .list();
        dicts.addAll(sysDicts1);
        return dicts;
    }

    public static Object selectLocationBySelectLocationBean(SelectLocationBean item) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<sysDict> dicts = new ArrayList<>();
        for(SelectLocationBean.selectLocation location : item.getSelectLocationList()) {
            List<sysDict> list1 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_Location),
                    sysDictDao.Properties.DictKey.eq(location.getSelectLocationParentKey()),
                    sysDictDao.Properties.ParentKey.eq(Constants.DICT_Location)).list();
            dicts.addAll(list1);
            String selectLocationKey = location.getSelectLocationKey();
            if(StringUtils.checkString(selectLocationKey)) {
                String[] split = selectLocationKey.split(",");
                for(String key : split) {
                    List<sysDict> list2 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_Location),
                            sysDictDao.Properties.DictKey.eq(key),
                            sysDictDao.Properties.ParentKey.eq(location.getSelectLocationParentKey())).list();
                    dicts.addAll(list2);
                }
            }else {
                List<sysDict> list2 = daoSession.getSysDictDao().queryBuilder().where(sysDictDao.Properties.RootKey.eq(Constants.DICT_Location),
                        sysDictDao.Properties.ParentKey.eq(location.getSelectLocationParentKey())).list();
                dicts.addAll(list2);
            }
        }
        return dicts;
    }
}
