package com.liany.csiserverapp.andServer.service;

import android.content.Context;

import com.liany.csiserverapp.andServer.model.UserDb;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.diagnose.LoginUserResult;
import com.liany.csiserverapp.diagnose.sysDict;
import com.liany.csiserverapp.diagnose.sysOrgan;
import com.liany.csiserverapp.diagnose.sysTechnician;
import com.liany.csiserverapp.diagnose.sysUser;
import com.liany.csiserverapp.network.response.Response;
import com.liany.csiserverapp.network.webservice.NetWorkUtils;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.GsonUtils;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.SPUtils;
import com.liany.csiserverapp.utils.StringUtils;
import com.yanzhenjie.andserver.http.HttpResponse;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class UserService {

    private static int isUpdate = 0;

    /**
     * 检查是否第一次登陆，用于同步字典数据
     * @return true 第一次登陆
     */
    public static String checkFirstLogin(Context mContext) {
        String url = (String) SPUtils.getParam(mContext, Constants.sp_url, "");
        if(!StringUtils.checkString(url)) {
//            SPUtils.setParam(MyApplication.getContext(),sp_isFirstLogin,false);
            return GsonUtils.successJson("同步信息");
        }else {
            return GsonUtils.faildJson(500,"已经同步");
        }
    }

    /**
     * 开始同步数据
     * @return
     */
    public static String updateDB(Context mContext, String url) {
        try {
        LogUtils.e("updateUserDB start");
        if(StringUtils.checkString(url)) {
            SPUtils.setParam(mContext, Constants.sp_url,url);
        }
        //同步数据开始时+1，为开始同步状态
        isUpdate = 1;
        //开始同步字典等数据
        NetWorkUtils.updateDB(mContext, Constants.method_loadUserInfo, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                // 解析json同步数据库
                LogUtils.e("updateUserDB UserInfo " + result);
                if(result != null && !result.equals("")) {
                    Response<List<sysUser>> response = GsonUtils.fromJsonArray(result, sysUser.class);
                    List<sysUser> users = response.getData();
                    UserDb.deleteSysUser();
                    UserDb.insertSysUser(users);
                    isUpdate += 1;
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("updateUserDB UserInfo onError " + e.getMessage());
            }
        });
        NetWorkUtils.updateDB(mContext, Constants.method_loadTechnicianInfo, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                // 解析json同步数据库
                LogUtils.e("updateTechDB TechnicianInfo " + result);
                if(result != null && !result.equals("")) {
                    Response<List<sysTechnician>> response = GsonUtils.fromJsonArray(result, sysTechnician.class);
                    List<sysTechnician> technicians = response.getData();
                    UserDb.deleteSysTechnician();
                    UserDb.insertSysTechnician(technicians);
                    isUpdate += 1;
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("updateUserDB TechnicianInfo onError " + e.getMessage());
            }
        });
        NetWorkUtils.updateDB(mContext, Constants.method_loadOrganInfo, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                // 解析json同步数据库
                LogUtils.e("updateOrganDB OrganInfo " + result);
                if(result != null && !result.equals("")) {
                    Response<List<sysOrgan>> response = GsonUtils.fromJsonArray(result, sysOrgan.class);
                    List<sysOrgan> organs = response.getData();
                    UserDb.deleteSysOrgan();
                    UserDb.insertSysOrgan(organs);
                    isUpdate += 1;
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("updateUserDB OrganInfo onError " + e.getMessage());
            }
        });
//        NetWorkUtils.updateDB(mContext, Constants.method_loadDictInfo, new NetWorkUtils.Callback() {
//            @Override
//            public void onNext(String result) {
//                // 解析json同步数据库
//                LogUtils.e("updateDictDB DictInfo " + result);
//                if(result != null && !result.equals("")) {
//                    Response<List<sysDict>> response = GsonUtils.fromJsonArray(result, sysDict.class);
//                    List<sysDict> dicts = response.getData();
//                    UserDb.deleteSysDict();
//                    UserDb.insertSysDict(dicts);
//                    isUpdate += 1;
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("updateUserDB DictInfo onError " + e.getMessage());
//            }
//        });
        String json = FileUtils.ReadAssetsFile(mContext, "sysDict.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
        UserDb.deleteSysDict();
        UserDb.insertSysDict(response.getData());
        return GsonUtils.successJson("同步中");
        }catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.successJson("同步中");
        }
    }

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public static String userLogin(Context mContext,String account, String password) {
        //检查是否正在同步中
        LogUtils.e("userLogin " + isUpdate);
//        if(isUpdate >= 1 && isUpdate <= 3) {
//            return GsonUtils.faildJson(500,"正在同步数据中");
//        }
        sysUser sysUser = UserDb.selectUser(account, password);
        if(sysUser!= null) {
            String techId = sysUser.getTechId();
            String organId = UserDb.selectorganId(techId);
            sysOrgan organ = UserDb.selectUnitName(organId);
            LoginUserResult result = new LoginUserResult();
            result.setOrganId(organId);
            result.setTechId(techId);
            result.setUserName(sysUser.getTrueName());
            result.setUnitName(organ.getUnitName());
            result.setUnitCode(organ.getUnitCode());
            result.setUserId(sysUser.getId());
            result.setPersonId(UserDb.selectPersonId(techId));
            result.setDeviceId((String) SPUtils.getParam(mContext,Constants.sp_deviceId,""));
//            XfUtils.startSpeak(sysUser.getTrueName() + "登录成功");
            return GsonUtils.successJson(result);
        }
        return GsonUtils.faildJson(500,"用户名密码错误");
    }

    public static String userAutoLogin(HttpResponse response, Context mContext, String account) {
       List<sysUser> sysUsers = UserDb.selectUserByName(account);
       if(sysUsers.size() > 0) {
           sysUser sysUser = sysUsers.get(0);
           String techId = sysUser.getTechId();
           String organId = UserDb.selectorganId(techId);
           sysOrgan organ = UserDb.selectUnitName(organId);
           LoginUserResult result = new LoginUserResult();
           result.setOrganId(organId);
           result.setTechId(techId);
           result.setUserName(sysUser.getTrueName());
           result.setUnitName(organ.getUnitName());
           result.setUnitCode(organ.getUnitCode());
           result.setUserId(sysUser.getId());
           result.setDeviceId((String) SPUtils.getParam(mContext,Constants.sp_deviceId,""));
           return GsonUtils.successJson(result);
       }
        return GsonUtils.faildJson(500,"该用户暂无权限");
    }

    public static String checkVersion(HttpResponse response, Context mContext, String versionCode, String versionName) {
        NetWorkUtils.checkVersion(mContext, Constants.method_checkVersion,versionCode,versionName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                // 解析json同步数据库
                LogUtils.e("checkVersion " + result);
                if(result != null && !result.equals("") && !result.contains("Failed")) {
                    Response<String> response = GsonUtils.fromJsonObject(result, String.class);
                    if(response.getCode() == 200) {
                        SPUtils.setParam(mContext,Constants.sp_version,false);
                    }else {
                        SPUtils.setParam(mContext,Constants.sp_version,true);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("checkVersion onError " + e.getMessage());
            }
        });
        return GsonUtils.successJson("");
    }
}
