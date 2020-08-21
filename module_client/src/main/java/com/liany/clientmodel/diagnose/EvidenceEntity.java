package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/3/29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class EvidenceEntity implements Serializable {

    private static final long serialVersionUID = -909248588573511738L;
    private String id;
    private String crimeId;
    private String evidenceCategory;//痕迹类型
    private String evidenceCategoryKey;//痕迹类型
    private String evidence;//痕迹
    private String evidenceKey;//痕迹
    private String evidenceName;//物证名称
    private String legacySite;//遗留部位
    private String basiceFeature;//基本特征
    private String infer;//工具推断
    private String inferKey;//工具推断
    private String method;//提取方法
    private String methodKey;//提取方法
    private long time;//提取时间
    private String people;//提取人
    private String peopleKey;//提取人技术表id
    private Photo photo;
    private String rev1;//事主比对结果
    private String rev2;//提取人id
    private String rev3;
    private String rev4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getEvidenceCategory() {
        return evidenceCategory;
    }

    public void setEvidenceCategory(String evidenceCategory) {
        this.evidenceCategory = evidenceCategory;
    }

    public String getEvidenceCategoryKey() {
        return evidenceCategoryKey;
    }

    public void setEvidenceCategoryKey(String evidenceCategoryKey) {
        this.evidenceCategoryKey = evidenceCategoryKey;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getEvidenceKey() {
        return evidenceKey;
    }

    public void setEvidenceKey(String evidenceKey) {
        this.evidenceKey = evidenceKey;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getLegacySite() {
        return legacySite;
    }

    public void setLegacySite(String legacySite) {
        this.legacySite = legacySite;
    }

    public String getBasiceFeature() {
        return basiceFeature;
    }

    public void setBasiceFeature(String basiceFeature) {
        this.basiceFeature = basiceFeature;
    }

    public String getInfer() {
        return infer;
    }

    public void setInfer(String infer) {
        this.infer = infer;
    }

    public String getInferKey() {
        return inferKey;
    }

    public void setInferKey(String inferKey) {
        this.inferKey = inferKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPeopleKey() {
        return peopleKey;
    }

    public void setPeopleKey(String peopleKey) {
        this.peopleKey = peopleKey;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
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
