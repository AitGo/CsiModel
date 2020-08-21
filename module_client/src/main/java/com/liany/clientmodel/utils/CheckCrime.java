package com.liany.clientmodel.utils;

import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.diagnose.ItemEntity;
import com.liany.clientmodel.diagnose.KCTBASESTATIONDATABean;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.SceneWifiInfo;
import com.liany.clientmodel.diagnose.WitnessEntity;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/4/11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CheckCrime {


    public static String checkBaseInfo(CrimeItem crimeItem) {
        int writeCount = 14;
        if(crimeItem.getAccessInspectors() != null && !crimeItem.getAccessInspectors().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getWeatherCondition() != null && !crimeItem.getWeatherCondition().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getWindDirection() != null && !crimeItem.getWindDirection().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getTemperature() != null && !crimeItem.getTemperature().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getHumidity() != null && !crimeItem.getHumidity().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getArea() != null && !crimeItem.getArea().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getUnitsAssigned() != null && !crimeItem.getUnitsAssigned().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getAccessPolicemen() != null && !crimeItem.getAccessPolicemen().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getIlluminationCondition() != null && !crimeItem.getIlluminationCondition().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getProductPeopleName() != null && !crimeItem.getProductPeopleName().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getProductPeopleUnit() != null && !crimeItem.getProductPeopleUnit().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getProductPeopleDuties() != null && !crimeItem.getProductPeopleDuties().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getSafeguard() != null && !crimeItem.getSafeguard().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getSceneConductor() != null && !crimeItem.getSceneConductor().isEmpty()) {
            writeCount -- ;
        }
        return writeCount +"";
    }

    public static String checkProspecting(CrimeItem crimeItem) {
        int writeCount = 11;
        if(crimeItem.getCasetype() != null && !crimeItem.getCasetype().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getLocation() != null && !crimeItem.getLocation().isEmpty()) {
            writeCount -- ;
        }
//        if(crimeItem.getAccessInspectors() != null && !crimeItem.getAccessInspectors().isEmpty()) {
//            writeCount ++ ;
//        }
        if(crimeItem.getAccessReason() != null && !crimeItem.getAccessReason().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getCaseOccurProcess() != null && !crimeItem.getCaseOccurProcess().isEmpty()) {
            writeCount -- ;
        }
//        if(crimeItem.getWeatherCondition() != null && !crimeItem.getWeatherCondition().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getWindDirection() != null && !crimeItem.getWindDirection().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getTemperature() != null && !crimeItem.getTemperature().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getHumidity() != null && !crimeItem.getHumidity().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getArea() != null && !crimeItem.getArea().isEmpty()) {
//            writeCount ++ ;
//        }
        if(Long.valueOf(crimeItem.getOccurred_start_time()) != null) {
            writeCount -- ;
        }
        if(Long.valueOf(crimeItem.getOccurred_end_time()) != null) {
            writeCount -- ;
        }
        if(Long.valueOf(crimeItem.getGet_access_time()) != null) {
            writeCount -- ;
        }
//        if(crimeItem.getUnitsAssigned() != null && !crimeItem.getUnitsAssigned().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getAccessPolicemen() != null && !crimeItem.getAccessPolicemen().isEmpty()) {
//            writeCount ++ ;
//        }
        if(Long.valueOf(crimeItem.getAccess_start_time()) != null) {
            writeCount -- ;
        }
        if(Long.valueOf(crimeItem.getAccess_end_time()) != null) {
            writeCount -- ;
        }
        if(crimeItem.getAccessLocation() != null && !crimeItem.getAccessLocation().isEmpty()) {
            writeCount -- ;
        }
        if(crimeItem.getSceneCondition() != null && !crimeItem.getSceneCondition().isEmpty()) {
            writeCount -- ;
        }
//        if(crimeItem.getChangeOption() != null && !crimeItem.getChangeOption().isEmpty()) {
//            writeCount -- ;
//        }
//        if(crimeItem.getChangeReason() != null && !crimeItem.getChangeReason().isEmpty()) {
//            writeCount -- ;
//        }
//        if(crimeItem.getIlluminationCondition() != null && !crimeItem.getIlluminationCondition().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getProductPeopleName() != null && !crimeItem.getProductPeopleName().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getProductPeopleUnit() != null && !crimeItem.getProductPeopleUnit().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getProductPeopleDuties() != null && !crimeItem.getProductPeopleDuties().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getSafeguard() != null && !crimeItem.getSafeguard().isEmpty()) {
//            writeCount ++ ;
//        }
//        if(crimeItem.getSceneConductor() != null && !crimeItem.getSceneConductor().isEmpty()) {
//            writeCount ++ ;
//        }
        return writeCount +"";
    }

    public static String checkVisit(List<ContactsEntity> contactsEntityList, List<ItemEntity> itemEntityList) {
        int writeCount = 3;
        if(!contactsEntityList.isEmpty()) {
            writeCount --;
        }
        if(!itemEntityList.isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkFigure(List<Photo> positionPhotoList, List<Photo> flatPhotoList) {
        int writeCount = 2;
        if(!positionPhotoList.isEmpty()) {
            writeCount --;
        }
        if(!flatPhotoList.isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkPhoto(List<Photo> positionPicList, List<Photo> overViewPhotoList, List<Photo> importantPhotoList) {
        int writeCount = 3;
        if(!positionPicList.isEmpty()) {
            writeCount --;
        }
        if(!overViewPhotoList.isEmpty()) {
            writeCount --;
        }
        if(!importantPhotoList.isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkEvidence(List<EvidenceEntity> evidenceEntityList, List<Photo> monitoringPhotoList, List<Photo> cameraPhotoList) {
        int writeCount = 3;
        if(!evidenceEntityList.isEmpty()) {
            writeCount --;
        }
        if(!monitoringPhotoList.isEmpty()) {
            writeCount --;
        }
        if(!cameraPhotoList.isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkSituation(CrimeItem crimeItem) {
        int writeCount = 1;
        if(crimeItem.getOverview() != null && !crimeItem.getOverview().isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkOpinion(CrimeItem crimeItem) {
        int writeCount = 12;
        if(crimeItem.getCrimePeopleNumber() != null && !crimeItem.getCrimePeopleNumber().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeMeans() != null && !crimeItem.getCrimeMeans().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeCharacter() != null && !crimeItem.getCrimeCharacter().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeEntrance() != null && !crimeItem.getCrimeEntrance().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeTiming() != null && !crimeItem.getCrimeTiming().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getSelectObject() != null && !crimeItem.getSelectObject().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeExport() != null && !crimeItem.getCrimeExport().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimePeopleFeature() != null && !crimeItem.getCrimePeopleFeature().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimeFeature() != null && !crimeItem.getCrimeFeature().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getIntrusiveMethod() != null && !crimeItem.getIntrusiveMethod().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getSelectLocation() != null && !crimeItem.getSelectLocation().isEmpty()) {
            writeCount --;
        }
        if(crimeItem.getCrimePurpose() != null && !crimeItem.getCrimePurpose().isEmpty()) {
            writeCount --;
        }

        return writeCount + "";
    }

    public static String checkOpinionOne(CrimeItem crimeItem) {
        int writeCount = 1;
        if(crimeItem.getCrimePeopleNumber() != null && !crimeItem.getCrimePeopleNumber().isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkWitness(List<WitnessEntity> witnessEntityList) {
        int writeCount = 1;
        if(!witnessEntityList.isEmpty()) {
            writeCount --;
        }
        return writeCount + "";
    }

    public static String checkExtract(List<GoodEntity> extractList) {
        if(extractList.size() > 0) {
            return "0";
        }else {
            return "1";
        }
    }

    public static String checkWifi(List<SceneWifiInfo> wifiInfoList) {
        if(wifiInfoList.size() > 0) {
            return "0";
        }else {
            return "1";
        }
    }


    public static String checkKct(List<KCTBASESTATIONDATABean> kctbasestationdataBeans) {
        if(kctbasestationdataBeans.size() > 0) {
            return "0";
        }else {
            return "1";
        }
    }


    public static String needToCheckInformation(CrimeItem crimeItem, String message){

        if(crimeItem.getCasetype() == null || crimeItem.getCasetype().isEmpty()) {
            message = message + "案件类别\n";
        }
        if(crimeItem.getArea() == null || crimeItem.getArea().isEmpty()) {
            message = message + "发案区划\n";
        }
        if(crimeItem.getLocation() == null || crimeItem.getLocation().isEmpty()) {
            message = message + "发案地点\n";
        }
        if(crimeItem.getUnitsAssigned() == null || crimeItem.getUnitsAssigned().isEmpty()) {
            message = message + "指派单位\n";
        }
        if(crimeItem.getAccessPolicemen() == null || crimeItem.getAccessPolicemen().isEmpty()) {
            message = message + "接警人\n";
        }
        if(crimeItem.getAccessLocation() == null || crimeItem.getAccessLocation().isEmpty()) {
            message = message + "勘验地点\n";
        }
        if(crimeItem.getCaseOccurProcess() == null || crimeItem.getCaseOccurProcess().isEmpty()) {
            message = message + "案件发现过程\n";
        }
        if(crimeItem.getSceneCondition() == null || crimeItem.getSceneCondition().isEmpty()) {
            message = message + "现场条件\n";
        }
        if(crimeItem.getWeatherCondition() == null || crimeItem.getWeatherCondition().isEmpty()) {
            message = message + "天气状况\n";
        }
        if(crimeItem.getWindDirection() == null || crimeItem.getWindDirection().isEmpty()) {
            message = message + "风向\n";
        }
        if(crimeItem.getTemperature() == null || crimeItem.getTemperature().isEmpty()) {
            message = message + "温度\n";
        }
        if(crimeItem.getHumidity() == null || crimeItem.getHumidity().isEmpty()) {
            message = message + "湿度\n";
        }
        if(crimeItem.getAccessReason() == null || crimeItem.getAccessReason().isEmpty()) {
            message = message + "勘验事由\n";
        }
        if(crimeItem.getIlluminationCondition() == null || crimeItem.getIlluminationCondition().isEmpty()) {
            message = message + "光照条件\n";
        }
        if(crimeItem.getProductPeopleName() == null || crimeItem.getProductPeopleName().isEmpty()) {
            message = message + "保护人姓名\n";
        }
        if(crimeItem.getProductPeopleUnit() == null || crimeItem.getProductPeopleUnit().isEmpty()) {
            message = message + "保护人单位\n";
        }
        if(crimeItem.getProductPeopleDuties() == null || crimeItem.getProductPeopleDuties().isEmpty()) {
            message = message + "保护人职务\n";
        }
        if(crimeItem.getSafeguard() == null || crimeItem.getSafeguard().isEmpty()) {
            message = message + "保护措施\n";
        }
        if(crimeItem.getSceneConductor() == null || crimeItem.getSceneConductor().isEmpty()) {
            message = message + "现场指挥人员\n";
        }
        if(crimeItem.getAccessInspectors() == null || crimeItem.getAccessInspectors().isEmpty()) {
            message = message + "勘验检查人员\n";
        }
        if(!StringUtils.checkString(crimeItem.getCrimeCharacter())) {
            message = message + "案件性质\n";
        }
        if(!StringUtils.checkString(crimeItem.getSelectLocation())) {
            message = message + "选择处所\n";
        }
        if(!StringUtils.checkString(crimeItem.getCrimeTiming())) {
            message = message + "作案时机\n";
        }
        if(!StringUtils.checkString(crimeItem.getCrimeEntrance())) {
            message = message + "作案入口\n";
        }
        if(!StringUtils.checkString(crimeItem.getCrimeExport())) {
            message = message + "作案出口\n";
        }
        if(!StringUtils.checkString(crimeItem.getIntrusiveMethod())) {
            message = message + "侵入方式\n";
        }
        if(!StringUtils.checkString(crimeItem.getCrimeMeans())) {
            message = message + "作案手段\n";
        }
        if(crimeItem.getCrimePeopleNumber() == null || crimeItem.getCrimePeopleNumber().isEmpty()) {
            message = message + "作案人数\n";
        }
        if(crimeItem.getCrimePeopleFeature() == null || crimeItem.getCrimePeopleFeature().isEmpty()) {
            message = message + "作案人特点\n";
        }
        if(crimeItem.getOverview() == null || crimeItem.getOverview().isEmpty()) {
            message = message + "勘验情况\n";
        }
        if(crimeItem.getPositionPhotoItem().isEmpty()) {
            message = message + "方位示意图\n";
        }
        if(crimeItem.getWitnessItem().isEmpty()) {
            message = message + "见证人\n";
        }
        if(crimeItem.getPositionItem().isEmpty()) {
            message = message + "方位照片\n";
        }
        if(crimeItem.getOverviewPhotoItem().isEmpty()) {
            message = message + "概貌照片\n";
        }
        if(crimeItem.getImportantPhotoItem().isEmpty()) {
            message = message + "重点部位\n";
        }
        if(crimeItem.getOccurred_end_time() < crimeItem.getOccurred_start_time()) {
            message=message+"发案开始时间必须在发案结束时间之前\n";
        }
        if(crimeItem.getGet_access_time() < crimeItem.getOccurred_end_time()) {
            message=message+"接勘时间必须在发案结束时间之后\n";
        }
        if(crimeItem.getAccess_start_time() < crimeItem.getGet_access_time()) {
            message=message+"勘验开始时间必须在接勘时间之后\n";
        }
        if(crimeItem.getAccess_end_time() < crimeItem.getAccess_start_time()) {
            message=message+"勘验开始时间必须在勘验结束时间之前\n";
        }

        return message;
    }

    public static boolean checkInformation(CrimeItem item){
        boolean result = false;
        if(item.getCasetype() != null && !item.getCasetype().isEmpty()
                &&item.getArea() != null && !item.getArea().isEmpty()
                &&item.getLocation() != null && !item.getLocation().isEmpty()
                &&item.getUnitsAssigned() != null && !item.getUnitsAssigned().isEmpty()
                &&item.getAccessPolicemen() != null && !item.getAccessPolicemen().isEmpty()
                &&item.getAccessLocation() != null && !item.getAccessLocation().isEmpty()
                &&item.getCaseOccurProcess() != null && !item.getCaseOccurProcess().isEmpty()
                &&item.getSceneCondition() != null && !item.getSceneCondition().isEmpty()
                &&item.getWeatherCondition() != null && !item.getWeatherCondition().isEmpty()
                &&item.getWindDirection() != null && !item.getWindDirection().isEmpty()
                &&item.getTemperature() != null && !item.getTemperature().isEmpty()
                &&item.getHumidity() != null && !item.getHumidity().isEmpty()
                &&item.getAccessReason() != null && !item.getAccessReason().isEmpty()
                &&item.getIlluminationCondition() != null && !item.getIlluminationCondition().isEmpty()
                &&item.getProductPeopleName() != null && !item.getProductPeopleName().isEmpty()
                &&item.getProductPeopleUnit() != null && !item.getProductPeopleUnit().isEmpty()
                &&item.getProductPeopleDuties() != null && !item.getProductPeopleDuties().isEmpty()
                &&item.getSafeguard() != null && !item.getSafeguard().isEmpty()
                &&item.getSceneConductor() != null && !item.getSceneConductor().isEmpty()
                &&item.getAccessInspectors() != null && !item.getAccessInspectors().isEmpty()
                &&item.getCrimePeopleNumber() != null && !item.getCrimePeopleNumber().isEmpty()
                &&item.getCrimePeopleFeature() != null && !item.getCrimePeopleFeature().isEmpty()
                &&item.getOverview() != null && !item.getOverview().isEmpty()
                && !item.getPositionItem().isEmpty()
                && !item.getWitnessItem().isEmpty()
                && !item.getPositionPhotoItem().isEmpty()
                && !item.getOverviewPhotoItem().isEmpty()
                && !item.getImportantPhotoItem().isEmpty()
                && StringUtils.checkString(item.getCrimeCharacterKey()) && StringUtils.checkString(item.getCrimeCharacter())
                && StringUtils.checkString(item.getSelectLocationKey()) && StringUtils.checkString(item.getSelectLocation())
                && StringUtils.checkString(item.getCrimeTimingKey()) && StringUtils.checkString(item.getCrimeTimingKey())
                && StringUtils.checkString(item.getCrimeEntranceKey()) && StringUtils.checkString(item.getCrimeEntrance())
                && StringUtils.checkString(item.getCrimeExportKey()) && StringUtils.checkString(item.getCrimeExport())
                && StringUtils.checkString(item.getIntrusiveMethodKey()) && StringUtils.checkString(item.getIntrusiveMethod())
                && StringUtils.checkString(item.getCrimeMeansKey()) && StringUtils.checkString(item.getCrimeMeans())
                )
            result = true;
        return result;
    }
}
