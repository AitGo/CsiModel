package com.liany.csiserverapp.andServer.controller;

import android.content.Context;

import com.liany.csiserverapp.andServer.service.SysService;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;

/**
 * @创建者 ly
 * @创建时间 2020/3/16
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
@RestController
@RequestMapping(path = "/sys")
public class SysController {

    /**
     * 人员
     * @param organId 单位Id
     * @return
     */
    @PostMapping("/people")
    public String getPeople(HttpRequest request, @RequestParam("organId") String organId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取字典人员");
        return SysService.getPeople(organId);
    }

    /**
     * 天气状况
     * @return
     */
    @PostMapping("/weatherCondition")
    public String getWeatherCondition(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取天气状况");
        return SysService.getWeatherCondition();
    }

    /**
     * 风向
     * @return
     */
    @PostMapping("/windDirection")
    public String getWindDirection(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取风向");
        return SysService.getWindDirection();
    }

    /**
     * 案发区划
     * @param organId 单位Id
     * @return
     */
    @PostMapping("/area")
    public String getArea(HttpRequest request, @RequestParam("organId") String organId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取案发区划");
        return SysService.getArea(organId);
    }

    /**
     * 光照条件
     * @return
     */
    @PostMapping("/light")
    public String getLight(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取光照条件");
        return SysService.getLight();
    }

    /**
     * 案件类别
     * @return
     */
    @PostMapping("/caseType")
    public String getCaseType(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取案件类别");
        return SysService.getCaseType(mContext);
    }

    /**
     * 工具类型
     * @return
     */
    @PostMapping("/toolType")
    public String getToolType(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取工具类型");
        return SysService.getToolType();
    }

    /**
     * 工具来源
     * @return
     */
    @PostMapping("/toolSource")
    public String getToolSource(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取工具来源");
        return SysService.getToolSource();
    }

    /**
     * 指纹痕迹
     * @return
     */
    @PostMapping("/handEvidence")
    public String getHandEvidence(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取指纹痕迹");
        return SysService.getHandEvidence();
    }

    /**
     * 足迹痕迹
     * @return
     */
    @PostMapping("/footEvidence")
    public String getFootEvidence(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取足迹痕迹");
        return SysService.getFootEvidence();
    }

    /**
     * 工具痕迹
     * @return
     */
    @PostMapping("/toolEvidence")
    public String getToolEvidence(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取工具痕迹");
        return SysService.getToolEvidence();
    }

    /**
     * 指纹提取方法
     * @return
     */
    @PostMapping("/handMethod")
    public String getHandMethod(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取指纹提取方法");
        return SysService.getHandMethod();
    }

    /**
     * 足迹提取方法
     * @return
     */
    @PostMapping("/footMethod")
    public String getFootMethod(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取足迹提取方法");
        return SysService.getFootMethod();
    }

    /**
     * 工痕提取方法
     * @return
     */
    @PostMapping("/toolMethod")
    public String getToolMethod(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取工痕提取方法");
        return SysService.getToolMethod();
    }

    /**
     * 工具推断
     * @return
     */
    @PostMapping("/infer")
    public String getInfer(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取工具推断");
        return SysService.getInfer();
    }

    /**
     * 作案人数
     * @return
     */
    @PostMapping("/peopleNumber")
    public String getPeopleNumber(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案人数");
        return SysService.getPeopleNumber(mContext);
    }

    /**
     * 作案手段
     * @return
     */
    @PostMapping("/crimeMeans")
    public String getCrimeMeans(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案手段");
        return SysService.getCrimeMeans();
    }

    /**
     * 案件性质
     * @param caseTypeKey 案件类别key
     * @return
     */
    @PostMapping("/crimeCharacter")
    public String getCrimeCharacter(HttpRequest request, Context mContext, @RequestParam("caseTypeKey") String caseTypeKey) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取案件性质");
        return SysService.getCrimeCharacter(mContext,caseTypeKey);
    }

    /**
     * 作案时机
     * @return
     */
    @PostMapping("/crimeTiming")
    public String getCrimeTiming(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案时机");
        return SysService.getCrimeTiming(mContext);
    }

    /**
     * 选择对象
     * @return
     */
    @PostMapping("/selectObject")
    public String getSelectObject(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取选择对象");
        return SysService.getSelectObject();
    }

    /**
     * 作案入口
     * @return
     */
    @PostMapping("/crimeEntrance")
    public String getCrimeEntrance(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案入口");
        return SysService.getCrimeEntrance(mContext);
    }

    /**
     * 作案出口
     * @return
     */
    @PostMapping("/crimeExport")
    public String getCrimeExport(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案出口");
        return SysService.getCrimeExport(mContext);
    }

    /**
     * 作案特点
     * @return
     */
    @PostMapping("/crimeFeature")
    public String getCrimeFeature(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案特点");
        return SysService.getCrimeFeature();
    }

    /**
     * 侵入方式
     * @param entranceKey 作案入口
     * @param exportKey 作案出口
     * @return
     */
    @PostMapping("/intrusiveMethod")
    public String getIntrusiveMethod(HttpRequest request, Context mContext, @RequestParam(name = "exportKey", required = false, defaultValue = "") String exportKey, @RequestParam(name = "entranceKey", required = false, defaultValue = "") String entranceKey) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取侵入方式");
        return SysService.getIntrusiveMethod(mContext,exportKey,entranceKey);
    }

    /**
     * 选择处所
     * @param caseTypeKey 案件类别key
     * @return
     */
    @PostMapping("/selectLocation")
    public String getSelectLocation(HttpRequest request, Context mContext, @RequestParam("caseTypeKey") String caseTypeKey) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取选择处所");
        return SysService.getSelectLocation(mContext,caseTypeKey);
    }

    /**
     * 作案动机目的
     * @return
     */
    @PostMapping("/crimePurpose")
    public String getCrimePurpose(HttpRequest request) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取作案动机目的");
        return SysService.getCrimePurpose();
    }
}
