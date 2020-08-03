package com.liany.csiclient.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2020/3/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Photo implements Serializable {

    private static final long serialVersionUID = 3487591476659566898L;
    private String id;

    private String parentId;//
    private String crimeId;
    private String path;
    private String serverPath;
    private String photoInfo;
    private String width;
    private String height;
    private String fileName;
    private String type;//文件类型
    private String UUID;//文件hashcode ，MD5
    /**
     * 0:方位图
     * 1 现场图
     * 2 现场照片
     * 3 概貌照片
     * 4 重点部位
     * 5 细目照片
     * 6 痕迹照片
     * 601 痕迹指纹照片
     * 602 痕迹足迹照片
     * 603 痕迹工痕照片
     * 604 痕迹其他照片
     * 7 监控画面
     * 8 摄像头位置
     * 9 见证人签名
     * 10 提取物品
     * 11 现场图dwg文件
     * 12 事主指纹
     */
    private String state;
    private int isUpload;//图片在服务器的状态:1:上传失败,2:上传成功
    private int isDelete;//1:删除失败,2:删除成功
    /**
     * state = 12时为事主指纹
     * 指纹代码：
     *  11:  "右手拇指";
     *  12:  "右手食指";
     *  13:  "右手中指";
     *  14:  "右手环指";
     *  15:  "右手小指";
     *  16:  "左手拇指";
     *  17:  "左手食指";
     *  18:  "左手中指";
     *  19:  "左手环指";
     *  20:  "左手小指";
     *  97:  "右手不确定指位";
     *  98:  "左手不确定指位";
     *  99:  "其他不确定指位";
     */
    private String rev1;
    /**
     * state = 1 时为平面图
     * rev2为生成的现场笔录
     */
    private String rev2;
    private String rev3;
    private String rev4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(String photoInfo) {
        this.photoInfo = photoInfo;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(int isUpload) {
        this.isUpload = isUpload;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getRev1() {
        return rev1;
    }

    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }

    public String getRev2() {
        return rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getRev3() {
        return rev3;
    }

    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }

    public String getRev4() {
        return rev4;
    }

    public void setRev4(String rev4) {
        this.rev4 = rev4;
    }
}
