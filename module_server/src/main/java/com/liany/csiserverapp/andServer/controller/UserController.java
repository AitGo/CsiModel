package com.liany.csiserverapp.andServer.controller;

import android.content.Context;

import com.liany.csiserverapp.andServer.service.UserService;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yanzhenjie.andserver.http.cookie.Cookie;

/**
 * @创建者 ly
 * @创建时间 2020/3/2
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    /**
     * 检测是否第一次登录
     * @return
     */
    @GetMapping("/checkFirst")
    public String checkFirstLogin(HttpRequest request, HttpResponse response, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "检测是否第一次登录");
        return UserService.checkFirstLogin(mContext);
    }

    /**
     * 更新字典数据
     * @param request
     * @param mContext
     * @param url 后台URL地址
     * @return
     */
    @GetMapping("/updateDB")
    public String updateDB(HttpRequest request, Context mContext, @RequestParam("url") String url) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "更新数据库");
        return UserService.updateDB(mContext,url);
//        return "";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param account 用户名
     * @param password  密码
     * @return 返回LoginUserResult对象的json
     */
    @PostMapping("/login")
    public String login(HttpRequest request, HttpResponse response, Context mContext, @RequestParam("account") String account, @RequestParam("password") String password) {
        Cookie cookie = new Cookie("Set-Cookie", account + "=" + password);
        response.addCookie(cookie);
//        response.setHeader("Set-Cookie", account + "=" + password);
//        ResponseBody body = new StringBody(UserService.userLogin(account,password));
//        response.setBody(body);
        return UserService.userLogin(mContext,account,password);
    }

    /**
     * 自动登录，内网时使用
     * @param request
     * @param response
     * @param mContext
     * @param account 警号
     * @return
     */
    @PostMapping("/autoLogin")
    public String autoLogin(HttpRequest request, HttpResponse response, Context mContext, @RequestParam("account") String account) {
        return UserService.userAutoLogin(response,mContext,account);
    }

    /**
     * 检查更新
     * @param request
     * @param response
     * @param mContext
     * @param versionCode
     * @return
     */
    @PostMapping("/checkVersion")
    public String checkVersion(HttpRequest request, HttpResponse response, Context mContext, @RequestParam("versionCode") String versionCode, @RequestParam("versionName") String versionName) {
        return UserService.checkVersion(response,mContext,versionCode,versionName);

    }
}
