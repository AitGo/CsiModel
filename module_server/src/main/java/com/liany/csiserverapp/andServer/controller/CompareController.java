package com.liany.csiserverapp.andServer.controller;

import android.content.Context;

import com.liany.csiserverapp.andServer.service.CompareService;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.utils.GsonUtils;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
@RestController
@RequestMapping(path = "/compare")
public class CompareController {

    /**
     * 获取人员比对的现场列表
     * @return
     */
    @PostMapping("/contactsCrimeList")
    public String getContactsCrimeList(HttpRequest request, @RequestParam("userName") String userName) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取人员比对的现场列表");
        return CompareService.getContactsCrimeList(userName);
    }

    /**
     * 获取指纹比对的现场列表
     * @return
     */
    @PostMapping("/evidenceFingerCrimeList")
    public String evidenceFingerCrimeList(HttpRequest request, @RequestParam("userName") String userName) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取指纹比对的现场列表");
        return CompareService.getEvidenceFingerCrimeList(userName);
    }

    /**
     * 获取足迹比对的现场列表
     * @return
     */
    @PostMapping("/evidenceFootCrimeList")
    public String evidenceFootCrimeList(HttpRequest request, @RequestParam("userName") String userName) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取指纹比对的现场列表");
        return CompareService.getEvidenceFootCrimeList(userName);
    }


    /**
     * 开始比对
     * @param crime 现场详情
     * @param type 比对类型，1：指纹比对，2事主比对, 3足迹比对 4:人脸
     * @param userId 用户id
     * @return
     */
    @PostMapping("/startCompare")
    public String startCompare(HttpRequest request, Context mContext, @RequestParam("crime") String crime, @RequestParam("type") String type, @RequestParam("userId") String userId) {
        CrimeItem item = GsonUtils.gsonBean(crime, CrimeItem.class);
        if(type.equals("1")) {
//            LogUtils.i(RequestUtils.getUser(request) + ":" + "提交现场比对");
            return CompareService.startEvidence(request,mContext,item,userId);
        }else if (type.equals("2")){
//            LogUtils.i(RequestUtils.getUser(request) + ":" + "提交事主比对");
//            return CompareService.startPeople(request,mContext,item,userId);
            return CompareService.startPeopleLocal(request,mContext,item,userId);
        }else if (type.equals("3")) {
            return CompareService.startFoot(request,mContext,item,userId);
        }
        else if (type.equals("4")) {
            return CompareService.startFace(request,mContext,item,userId);
        }
        return GsonUtils.faildJson(500,"比对类型错误");
    }

    /**
     * 开始比对(单个比对)
     * @param evidenceId 痕迹id
     * @param type 比对类型，1：指纹比对，2事主比对, 3足迹比对 4:人脸
     * @param userId 用户id
     * @return
     */
    @PostMapping("/startCompareOne")
    public String startCompareOne(HttpRequest request, Context mContext, @RequestParam("evidenceId") String evidenceId, @RequestParam("type") String type, @RequestParam("userId") String userId) {
        if(type.equals("1")) {
            return CompareService.startEvidenceOne(request,mContext,evidenceId,userId);
        }else if (type.equals("2")){
            return CompareService.startPeopleLocalOne(request,mContext,evidenceId,userId);
        }else if (type.equals("3")) {
            return CompareService.startFootOne(request,mContext,evidenceId,userId);
        }
        else if (type.equals("4")) {
            return CompareService.startFaceOne(request,mContext,evidenceId,userId);
        }
        return GsonUtils.faildJson(500,"比对类型错误");
    }

    /**
     * 开始比对(不去创建现场，直接比对)
     * @param caseType 案件类别
     * @param location 案发地点
     * @param crimeId 现场id（临时生成，只关联痕迹照片）
     * @param photoId photoId
     * @param unitCode 单位代码
     * @param unitName 单位名称
     * @param type 比对类型，1：指纹比对，2事主比对, 3足迹比对 4:人脸
     * @param userId 用户id
     * @return
     */
    @PostMapping("/startCompareOut")
    public String startCompareOut(HttpRequest request, Context mContext, @RequestParam("caseType") String caseType,
                                  @RequestParam("location") String location, @RequestParam("crimeId") String crimeId,
                                  @RequestParam("photoId") String photoId, @RequestParam("unitCode") String unitCode,
                                  @RequestParam("unitName") String unitName,
                                  @RequestParam("type") String type, @RequestParam("userId") String userId) {
        if(type.equals("1")) {
            return CompareService.startEvidenceOut(request,mContext,caseType,location,crimeId,photoId,unitCode,unitName,userId);
        }
//        else if (type.equals("2")){
//            return CompareService.startPeopleLocalOut(request,mContext,caseType,location,crimeId,photoId,unitCode,unitName,userId);
//        }
        else if (type.equals("3")) {
            return CompareService.startFootOut(request,mContext,caseType,location,crimeId,photoId,unitCode,unitName,userId);
        }
        else if (type.equals("4")) {
            return CompareService.startFaceOut(request,mContext,caseType,location,crimeId,photoId,unitCode,unitName,userId);
        }
        return GsonUtils.faildJson(500,"比对类型错误");
    }

    /**
     * 获取比对结果
     * @param crimeId 现场id
     * @param state 比对类型 1:现场比对，2:事主比对 3:足迹 4:人脸
     * @return
     */
    @PostMapping("/getCompareResult")
    public String getCompareResult(HttpRequest request, Context mContext, @RequestParam("crimeId") String crimeId, @RequestParam("state") String state) {
        return CompareService.getCompareResult(mContext,crimeId,state);
    }

    /**
     * 获取全部比对结果
     * @param userId 人员id
     * @param state 比对类型 1:现场比对，2:事主比对 3:足迹 4:人脸
     * @return
     */
    @PostMapping("/getAllCompareResult")
    public String startAllCompareResult(HttpRequest request, Context mContext, @RequestParam("userId") String userId, @RequestParam("state") String state) {
        return CompareService.getAllCompareResult(mContext, userId, state);
    }

    /**
     * 从服务器获取全部类型比对结果，客户端轮询使用
     * @param userId 人员Id
     * @return
     */
    @PostMapping("/getAllCompareFromService")
    public String getAllCompareFromService(HttpRequest request, Context mContext, @RequestParam("userId") String userId) {
        return CompareService.getAllCompareFromService(mContext,userId);
    }
}
