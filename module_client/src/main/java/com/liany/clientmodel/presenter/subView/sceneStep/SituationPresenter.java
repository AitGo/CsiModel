package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;
import android.widget.Toast;

import com.liany.clientmodel.contract.subView.sceneStep.SituationContract;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.ItemEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.model.subView.sceneStep.SituationModel;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SituationPresenter implements SituationContract.Presenter {
    private SituationContract.View view;
    private SituationContract.Model model;
    private Context mContext;

    public SituationPresenter(Context mContext, SituationContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new SituationModel(mContext);
    }

    @Override
    public void saveSituation(CrimeItem crimeItem) {
        crimeItem.setOverview(view.getOverView());
        if(crimeItem.getOverviewId() == null) {

        }
        view.saveSituation(crimeItem);
    }

    @Override
    public void automatic(CrimeItem mItem) {
        String type = mItem.getCasetype();

        if(type == null || type.isEmpty()){
            Toast.makeText(mContext, "请先选择案件类别", Toast.LENGTH_LONG).show();
            return;
        }

        String text = "";

        String text1 = "";
        text1 = text1 + StringUtils.long2SceneDateSecond(mItem.getGet_access_time());
        text1 = text1 + mItem.getUnitsAssigned() + "接到一报警电话。\n";

        String text2 = "";
        text2 = text2 + "接报后，" + StringUtils.long2SceneDateSecond(mItem.getAccess_start_time()) + "，";
        text2 = text2 + "到达现场并对现场进行保护。\n";

        String text3 = "";
        text3 = text3 + "经勘查，现场位于" + mItem.getLocation() + "，";
        text3 = text3 + "现场东侧为，西侧为，南侧为，北侧为。\n";

        String text4 = "";
        if(mItem.getReleatedPeopleItem().size()>0) {
            text4 = text4 + "事主身份信息：";
            for(ContactsEntity entity : mItem.getReleatedPeopleItem()) {
                text4 = text4 + "姓名为" + entity.getName()
                        + "，性别为" + entity.getSex()
                        + "，身份证号为" + entity.getPeopleId()
                        + "，联系电话为" + entity.getTel()
                        + "，现居住地为，" + entity.getAddress();
                text4 = text4 + "\n";
            }
        }

        String text5 = "";
        if(mItem.getLostItem().size()>0) {
            text5 = text5 + "损失物品";
            for(ItemEntity entity : mItem.getLostItem()) {
                text5 = text5 + "品名为" + entity.getItemName()
                        + "，厂牌型号为" + entity.getBrandModel()
                        + "，数量为" + entity.getAmount()
                        + "，价值为" + entity.getValue()
                        + "，特征为，" + entity.getFeatureDescription();
                text5 = text5 + "\n";
            }
        }

        String text6 = "";
        if(mItem.getEvidenceItem().size()>0) {
            text6 = text6 + "对现场内进行痕迹显现，发现";
            int fingerSize = 0;
            int footSize = 0;
            int toolSize = 0;
            int otherSize = 0;
            for(int i=0;i<mItem.getEvidenceItem().size();i++){
                if(mItem.getEvidenceItem().get(i).getEvidenceCategory().equals("手印")) {
                    fingerSize++;
                }
                if(mItem.getEvidenceItem().get(i).getEvidenceCategory().equals("足迹")) {
                    footSize++;
                }
                if(mItem.getEvidenceItem().get(i).getEvidenceCategory().equals("工痕")) {
                    toolSize++;
                }
                if(mItem.getEvidenceItem().get(i).getEvidenceCategory().equals("其他")) {
                    otherSize++;
                }
            }
            if(fingerSize > 0) {
                text6 = text6 + fingerSize + "枚可疑手印，";
            }
            if(footSize > 0) {
                text6 = text6 + footSize + "枚可疑足迹，";
            }
            if(toolSize > 0) {
                text6 = text6 + toolSize + "枚可疑工痕，";
            }
            if(otherSize > 0) {
                text6 = text6 + otherSize + "枚可疑其他痕迹，";
            }
            text6 = text6.substring(0,text6.length() - 1);
            text6 = text6 + "。对现场及现场周围进行搜索，未发现其他物证。\n";
        }
        String typeKey = mItem.getCasetypeKey();
        switch (typeKey){
            case "050237":
                //盗窃牲畜
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现牲畜被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050102":
                //挡路抢劫
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "遭遇抢劫，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050300":
                //诈骗
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现被骗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050228":
                //竊盜電動自行車
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将电动车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050240":
                //扒窃
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发现被盗，随即报案。\n";
                view.setOverView(text1 + text + text3 + text4 + text5 + text6);
                break;
            case "050224":
                //盗窃摩托车
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将摩托车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050227":
                //盗窃自行车
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将自行车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "040103":
                //故意伤害
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起故意伤害案，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            case "050400":
                //抢夺
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "行走时被抢，随即报案。\n";
                view.setOverView(text1 + text + text3 + text4 + text5 + text6);
                break;
            case "050201":
                //入室盗窃案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现家里被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050225":
                //盗窃车内财物案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车内被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050234":
                //盗窃电缆线案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现电缆线被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "050248":
                //其他盗窃案件
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现被盗，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text5 + text6);
                break;
            case "040101":
                //故意杀人案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起故意杀人案，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            case "040105":
                //强奸案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起强奸案，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            case "040107":
                //猥亵案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起猥亵案，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            case "110000":
                //"非正常死亡案（事）件"
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起非正常死亡案，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            case "119000":
                //"非正常死亡案（事）件"
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起其他案件，随即报案。\n";
                view.setOverView(text1 + text + text2 + text3 + text4 + text6);
                break;
            default:
                break;
        }
        mItem.setOverview(view.getOverView());
    }

    @Override
    public void automaticDraw(CrimeItem mItem) {
        List<Photo> flatItem = mItem.getFlatItem();
        if(flatItem.size() == 1) {
            String rev2 = flatItem.get(0).getRev2();
            if(StringUtils.checkString(rev2)) {
                mItem.setOverview(getSituation(mItem) + flatItem.get(0).getRev2());
                view.setOverView(getSituation(mItem) + flatItem.get(0).getRev2());
            }else {
                automatic(mItem);
                ToastUtils.showShort("暂无平面图生成信息，已使用现场信息生成");
            }
        }else {
            String text = "";
            for(int i = 0; i < flatItem.size(); i++) {
                String rev2 = flatItem.get(i).getRev2();
                if(StringUtils.checkString(rev2)) {
                    text = text + flatItem.get(i).getPhotoInfo() + "\n" + getSituation(mItem) + rev2 + "\n";
                }
            }
            if(StringUtils.checkString(text)) {
                mItem.setOverview(text);
                view.setOverView(text);
            }else {
                automatic(mItem);
                ToastUtils.showShort("暂无平面图生成信息，已使用现场信息生成");
            }
        }
    }


    private String getSituation(CrimeItem mItem) {
        String situation = "";
        String text = "";

        String text1 = "";
        text1 = text1 + StringUtils.long2SceneDateSecond(mItem.getGet_access_time());
        text1 = text1 + mItem.getUnitsAssigned() + "接到一报警电话。\n";

        String text2 = "";
        text2 = text2 + "接报后，" + StringUtils.long2SceneDateSecond(mItem.getAccess_start_time()) + "，";
        text2 = text2 + "到达现场并对现场进行保护。\n";

        String typeKey = mItem.getCasetypeKey();
        switch (typeKey){
            case "050237":
                //盗窃牲畜
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现牲畜被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050102":
                //挡路抢劫
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "遭遇抢劫，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050300":
                //诈骗
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现被骗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050228":
                //竊盜電動自行車
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将电动车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050240":
                //扒窃
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发现被盗，随即报案。\n";
                view.setOverView(text1 + text);
                break;
            case "050224":
                //盗窃摩托车
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将摩托车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050227":
                //盗窃自行车
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "将自行车锁好后，停放在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车辆被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "040103":
                //故意伤害
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起故意伤害案，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050400":
                //抢夺
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "行走时被抢，随即报案。\n";
                view.setOverView(text1 + text);
                break;
            case "050201":
                //入室盗窃案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现家里被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050225":
                //盗窃车内财物案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现车内被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050234":
                //盗窃电缆线案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现电缆线被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "050248":
                //其他盗窃案件
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "在" + mItem.getLocation() + "，";
                text = text + StringUtils.long2SceneDateSecond(mItem.getOccurred_end_time());
                text = text + "发现被盗，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "040101":
                //故意杀人案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起故意杀人案，随即报案。\n";
                view.setOverView(text1 + text + text2 );
                break;
            case "040105":
                //强奸案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起强奸案，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "040107":
                //猥亵案
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起猥亵案，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "110000":
                //"非正常死亡案（事）件"
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起非正常死亡案，随即报案。\n";
                situation = text1 + text + text2;
                break;
            case "119000":
                //"非正常死亡案（事）件"
                text = text + "据报案人称，" + StringUtils.long2SceneDateSecond(mItem.getOccurred_start_time());
                text = text + "其在" + mItem.getLocation() + "发生一起其他案件，随即报案。\n";
                situation = text1 + text + text2;
                break;
            default:
                situation = text1 + text2;
                break;
        }
        return situation;
    }
}
