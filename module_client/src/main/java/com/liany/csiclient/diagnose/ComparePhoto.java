package com.liany.csiclient.diagnose;


import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/11/13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ComparePhoto implements Serializable {

    private String id;
    private String compareId;
    private String sceneId;
    private String photoId;
    private String taskId;
    private String photoPath;
    private String servicePath;
    private String photoType;//事主或痕迹 0:事主 1:痕迹
    /**
     * 比对状态,判断下次是否需要比对
     * 0: 排队中
     * 1: 比对中
     * 2: 比对完成（待检视） 查重任务无需检视，系统直接判定是否比中
     * 3: 已取消
     * 4: 任务失败
     * 7: 认定比中
     * 8: 认定未比中
     * 107: 比中事主 仅正查，该枚现场指纹比中了事主指纹
     */
    private String status;
    private String compareInfo;//比对结果
    private int fingerNo;//比对序号，bmp文件序号

    private String createDate;
    private String updateDate;
    private String createName;
    private String updateName;
    private String evidenceName;//物证名称
    private String rev1;//案件类别@@案件地点
    private String rev2;
    private String rev3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompareId() {
        return compareId;
    }

    public void setCompareId(String compareId) {
        this.compareId = compareId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompareInfo() {
        return compareInfo;
    }

    public void setCompareInfo(String compareInfo) {
        this.compareInfo = compareInfo;
    }

    public int getFingerNo() {
        return fingerNo;
    }

    public void setFingerNo(int fingerNo) {
        this.fingerNo = fingerNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
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

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }
}
