package com.liany.csiserverapp.andServer.controller;

import android.content.Context;

import com.liany.csiserverapp.andServer.service.SceneService;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.utils.GsonUtils;
import com.liany.csiserverapp.utils.StringUtils;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/6
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
@RestController
@RequestMapping(path = "/scene")
public class SceneController {

    /**
     * 获取当前现场
     * @return
     */
    @GetMapping("/getPresentScene")
    public String getPresentScene(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取当前现场");
        return SceneService.getPresentScene(mContext);
    }

    /**
     * 设置当前现场
     * @param crimeId 现场id
     * @return
     */
    @GetMapping("/setPresentScene")
    public String setPresentScene(HttpRequest request, Context mContext, @RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "设置当前现场");
        return SceneService.setPresentScene(mContext,crimeId);
    }

    /**
     * 获取现场列表
     * @param type 0：未上传 1：已上传 2：全部
     * @return
     */
    @PostMapping("/getList")
    public String getSceneList(HttpRequest request, Context mContext, @RequestParam("type") String type) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取现场列表");
        return SceneService.getSceneList(Integer.valueOf(type));
    }

    /**
     * 获取现场列表
     * @param userName 用戶名
     * @param type 0：未上传 1：已上传 2：全部
     * @param offset 当前页数
     * @return
     */
    @PostMapping("/getPageList")
    public String getSceneListByPage(HttpRequest request, @RequestParam("userName") String userName, @RequestParam("type") String type, @RequestParam("offset") String offset) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取现场列表,当前第" + offset + "页");
        return SceneService.getSceneListByPage(request,userName,Integer.valueOf(type),Integer.valueOf(offset));
    }

    /**
     * 获取现场详情
     * @param crimeId 现场id
     * @return
     */
    @PostMapping("/getInfo")
    public String getSceneInfo(HttpRequest request, Context mContext, @RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取现场详情，id:" + crimeId);
        return SceneService.getSceneInfo(mContext,crimeId);
    }

    /**
     * 保存现场
     * @param crimeItem 现场json
     * @return
     */
    @PostMapping("/save")
    public String saveScene(HttpRequest request, Context mContext, @RequestParam("crimeItem")String crimeItem) {

        CrimeItem item = GsonUtils.gsonBean(crimeItem, CrimeItem.class);
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "保存现场，id:" + item.getId());
        return SceneService.saveScene(mContext,item);
    }

    /**
     * 删除现场
     * @param crimeItems 现场json
     * @param userName 人员名称
     * @return
     */
    @PostMapping("/delete")
    public String deleteScene(HttpRequest request, Context mContext, @RequestParam("crimeItems") String crimeItems, @RequestParam("userName") String userName) {
//        String userName = RequestUtils.getUser(request);
        List<CrimeItem> response = GsonUtils.jsonToList(crimeItems, CrimeItem.class);
        return SceneService.deleteScene(mContext,response,userName);
    }

    /**
     * 上传现场
     * @param data
     * @return
     */
    @PostMapping("/upload")
    public String uploadScene(HttpRequest request, Context mContext, @RequestParam("crimeItems") String data, @RequestParam("userId") String userId) {
//        String userName = RequestUtils.getUser(request);
//        String password = RequestUtils.getPassword(request);
        List<CrimeItem> crimeItems = GsonUtils.jsonToList(data, CrimeItem.class);
        return SceneService.uploadScene(mContext,crimeItems,userId);
    }

    /**
     * 创建现场
     * @param request
     * @param baseInfo 基本信息
     * @return
     */
    @PostMapping("/createScene")
    public String createScene(HttpRequest request, @RequestParam("baseInfo") String baseInfo) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "创建现场");
        CrimeItem crimeItem = GsonUtils.gsonBean(baseInfo, CrimeItem.class);
        return SceneService.createScene(request,crimeItem);
    }

    /**
     * 上传图片
     * @param request
     * @param pic
     * @return
     */
    @PostMapping("/uploadPic")
    public String uploadPic(HttpRequest request, Context mContext, @RequestParam("pic") MultipartFile pic,
                            @RequestParam("photoId") String photoId,
                            @RequestParam("state") String state,
                            @RequestParam("parentId") String parentId,
                            @RequestParam("crimeId") String crimeId,
                            @RequestParam(name = "photoInfo", required = false, defaultValue = "") String photoInfo,
                            @RequestParam(name = "rev2", required = false, defaultValue = "") String rev2) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "上传图片");
        return SceneService.uploadPic(mContext,pic,photoId,state,parentId,crimeId,photoInfo,rev2);
    }

    @PostMapping("/uploadPicInfo")
    public String uploadPicInfo() {
        return "";
    }

    /**
     * 上传多张图片
     * @param request
     * @param pics
     * @return
     */
    @PostMapping("/uploadPics")
    public String uploadPics(HttpRequest request, Context mContext,
                             @RequestParam("pics") MultipartFile[] pics,
                             @RequestParam("photoIds") String photoIds,
                             @RequestParam("state") String state,
                             @RequestParam("parentId") String parentId,
                             @RequestParam("crimeId") String crimeId,
                             @RequestParam(name = "photoInfos", required = false, defaultValue = "") String photoInfos,
                             @RequestParam(name = "rev2s", required = false, defaultValue = "") String rev2s) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "上传图片");
        List<String> photoIdList = GsonUtils.jsonToList(photoIds, String.class);
        List<String> photoInfoList = new ArrayList<>();
        List<String> rev2List = new ArrayList<>();
        if(StringUtils.checkString(photoInfos)) {
            photoInfoList.addAll(GsonUtils.jsonToList(photoInfos, String.class));
        }
        if(StringUtils.checkString(rev2s)) {
            rev2List.addAll(GsonUtils.jsonToList(rev2s, String.class));
        }
        return SceneService.uploadPics(mContext,pics,photoIdList,state,parentId,crimeId,photoInfoList,rev2List);
    }

    /**
     * 删除图片
     * @param request
     * @param mContext
     * @param fileName
     * @param crimeId
     * @return
     */
    @PostMapping("/deletePic")
    public String deletePic(HttpRequest request, Context mContext, @RequestParam("photoId") String photoId, @RequestParam("fileName") String fileName, @RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "删除图片");
        return SceneService.deletePic(mContext,photoId,fileName,crimeId);
    }

//    /**
//     * 删除多个图片
//     * @param request
//     * @param mContext
//     * @param fileNames
//     * @param crimeId
//     * @return
//     */
//    @PostMapping("/deletePicList")
//    String deletePicList(HttpRequest request,Context mContext,@RequestParam("fileNameList") String fileNames,@RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "删除图片");
//        List<String> nameList = GsonUtils.jsonToList(fileNames, String.class);
//        return SceneService.deletePics(mContext,nameList,crimeId);
//    }
    /**
     * 删除多个图片
     * @param request
     * @param mContext
     * @param photoList
     * @param crimeId
     * @return
     */
    @PostMapping("/deletePicList")
    public String deletePicList(HttpRequest request, Context mContext, @RequestParam("photoList") String photoList, @RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "删除图片");
        List<Photo> nameList = GsonUtils.jsonToList(photoList, Photo.class);
        return SceneService.deletePics(mContext,nameList,crimeId);
    }

    /**
     * 开始采集基站数据
     * @param request
     * @param mContext
     * @return
     */
    @PostMapping("/startCollectionKct")
    public String startCollectionKct(HttpRequest request, Context mContext, @RequestParam("crimeId") String crimeId, @RequestParam("lat") String lat, @RequestParam("lon") String lon) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "开始采集基站数据");
        return SceneService.startCollectionKct(mContext,crimeId,lat,lon);
    }

    @PostMapping("/stopCollectionKct")
    public String stopCollectionKct(HttpRequest request, Context mContext) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "停止采集基站数据");
        return SceneService.stopCollectionKct(mContext);
    }

    @PostMapping("/getCollectionKct")
    public String getCollectionKctData(HttpRequest request, Context mContext, @RequestParam("crimeId") String crimeId) {
//        LogUtils.i(RequestUtils.getUser(request) + ":" + "获取基站数据");
        return SceneService.getCollectionKctData(mContext,crimeId);
    }

    @PostMapping("/uploadEvidence")
    public String uploadEvidence(HttpRequest request, @RequestParam("evidence") String evidence) {
        EvidenceEntity entity = GsonUtils.gsonBean(evidence, EvidenceEntity.class);
        return SceneService.uploadEvidence(entity);
    }

    @PostMapping("/deleteEvidence")
    public String deleteEvidence(HttpRequest request, @RequestParam("evidenceId") String evidenceId, @RequestParam("crimeId") String crimeId) {
        return SceneService.deleteEvidence(evidenceId,crimeId);
    }

    /**
     * 痕迹图片接口
     * @param request
     * @param pic
     * @param type 1：指纹，2：足迹，3：工痕，4：其他
     * @return
     */
    @PostMapping("/uploadEvidencePic")
    public String uploadEvidencePic(HttpRequest request, Context mContext, @RequestParam("pic") MultipartFile pic, @RequestParam(name = "type", required = false, defaultValue = "1") String type) {
        if(type.equals("1")) {
            return SceneService.uploadFingerEvidencePic(mContext,pic);
        }else if(type.equals("2")){
            return SceneService.uploadFootEvidencePic(mContext,pic);
        }
//        else if(type.equals("3")) {
//            return SceneService.uploadToolEvidencePic(pic);
//        }else if(type.equals("4")) {
//            return SceneService.uploadOtherEvidencePic(pic);
//        }
        return SceneService.uploadFingerEvidencePic(mContext,pic);
    }

}
