package com.liany.csiserverapp.utils;

import android.util.Log;
import android.util.Xml;

import com.liany.csiserverapp.diagnose.KCTBASESTATIONDATABean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/9/6
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class XmlUtils {
    private static String encoding = "UTF-8";

    private static String NODE_CDMA = "cdma";
    private static String NODE_GSM = "gsm";
    private static String NODE_WCDMA = "wcdma";
    private static String NODE_LTE = "lte";
    private static String NODE_SCDMA = "scdma";

    private static String TYPE_CDMA = "CDMA";
    private static String TYPE_GSM = "GSM";
    private static String TYPE_WCDMA = "WCDMA";
    private static String TYPE_LTE = "LTE";
    private static String TYPE_SCDMA = "SCDMA";

    private static String MODE_CDMA_CREATETIME = "create_time";
    private static String MODE_CDMA_NID = "nid";
    private static String MODE_CDMA_BID = "bid";
    private static String MODE_CDMA_SID = "sid";
    private static String MODE_CDMA_RX = "rx";
    private static String MODE_CDMA_PN = "pn";

    private static String MODE_CREATETIME = "create_time";
    private static String MODE_LAC = "lac";
    private static String MODE_CELLID = "cellid";
    private static String MODE_MCC = "mcc";
    private static String MODE_MNC = "mnc";
    private static String MODE_PSCPCI = "psc_pci";
    private static String MODE_SIG1 = "sig1";
    private static String MODE_SIG1DBM = "sig1_dbm";
    private static String MODE_SIG2 = "sig2";
    private static String MODE_SIG2DBM = "sig2_dbm";


    public static List<KCTBASESTATIONDATABean> xml2KCT(InputStream inputStream, String uuid, long startTime, long endTime, String crimeId, String lat, String lon) throws XmlPullParserException, IOException {
        List<KCTBASESTATIONDATABean> kctbasestationdataBeans = new ArrayList<>();
        KCTBASESTATIONDATABean bean = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, encoding);
        int eventType = parser.getEventType();
        String nodeName  = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String data = null;
                    nodeName = parser.getName();
                    Log.e("text nodeName",nodeName + " ");
                    if(nodeName.equals(NODE_CDMA) ) {
                        bean = new KCTBASESTATIONDATABean();
                        bean.setBS_TYPE(TYPE_CDMA);
                        bean.setMCC_MNC("460-11");
                    }else if(nodeName.equals(NODE_GSM)) {
                        bean = new KCTBASESTATIONDATABean();
                        bean.setBS_TYPE(TYPE_GSM);
                    }else if(nodeName.equals(NODE_WCDMA)) {
                        bean = new KCTBASESTATIONDATABean();
                        bean.setBS_TYPE(TYPE_WCDMA);
                    }else if(nodeName.equals(NODE_LTE)) {
                        bean = new KCTBASESTATIONDATABean();
                        bean.setBS_TYPE(TYPE_LTE);
                    }else if(nodeName.equals(NODE_SCDMA)) {
                        bean = new KCTBASESTATIONDATABean();
                        bean.setBS_TYPE(TYPE_SCDMA);
                    }

                    if(bean != null) {
                        bean.setId(StringUtils.getUUID());
                        bean.setCrimeId(crimeId);
                        bean.setUUID(uuid);
                        bean.setIFACTIVE("ACTIVE");
                        bean.setStartTime(startTime);
                        bean.setEndTime(endTime);
                        bean.setLAT(lat);
                        bean.setLON(lon);
                        if(nodeName.equals(MODE_CREATETIME)) {
                            data = parser.nextText();
                            Log.e("text createtime",data + " ");
                            bean.setCOL_TIME(StringUtils.StringDateFormat(data));
                        } else if(nodeName.equals(MODE_CDMA_NID)) {
                            data = parser.nextText();
                            Log.e("text nid",data + " nid");
                            bean.setNID(data);
                        } else if(nodeName.equals(MODE_CDMA_BID)) {
                            data = parser.nextText();
                            Log.e("text bid",data + " bid");
                            bean.setBASE_ID(data);
                        } else if(nodeName.equals(MODE_CDMA_SID)) {
                            data = parser.nextText();
                            Log.e("text sid",data + " sid");
                            bean.setSID(data);
                        } else if(nodeName.equals(MODE_CDMA_RX)) {
                            data = parser.nextText();
                            Log.e("text rx",data + " rx");
                            bean.setSTRENGTH(data);
                        } else if(nodeName.equals(MODE_CDMA_PN)) {
                            data = parser.nextText();
                            Log.e("text pn",data + " pn");
                            bean.setPN(data);
                        } else if(nodeName.equals(MODE_LAC)) {
                            data = parser.nextText();
                            Log.e("text lac",data + " lac");
                            bean.setLAC(data);
                        } else if(nodeName.equals(MODE_CELLID)) {
                            data = parser.nextText();
                            Log.e("text cellid",data + " cellid");
                            bean.setCELL_ID(data);
                        } else if(nodeName.equals(MODE_MCC)) {
                            data = parser.nextText();
                            Log.e("text mcc",data + " mcc");
                            bean.setMCC_MNC(data);
                        } else if(nodeName.equals(MODE_MNC)) {
                            data = parser.nextText();
                            String sData = "11";

                            if(data != null) {
                                if(data.length() < 2) {
                                    sData = "0" + data;
                                }
                                int iData = Integer.valueOf(data);
                                if(iData == 0) {
                                    if(bean.getBS_TYPE().equals(TYPE_GSM) || bean.getBS_TYPE().equals(TYPE_LTE)) {
                                        bean.setBS_TYPE("CMCC_" + bean.getBS_TYPE());
                                    }
                                    if(bean.getBS_TYPE().equals(TYPE_SCDMA)) {
                                        bean.setBS_TYPE("TD_" + bean.getBS_TYPE());
                                    }
                                }else if(iData == 1) {
                                    if(bean.getBS_TYPE().equals(TYPE_GSM) || bean.getBS_TYPE().equals(TYPE_LTE)) {
                                        bean.setBS_TYPE("CU_" + bean.getBS_TYPE());
                                    }
                                }else if(iData == 11) {
                                    if(bean.getBS_TYPE().equals(TYPE_LTE)) {
                                        bean.setBS_TYPE("CTCC_" + bean.getBS_TYPE());
                                    }
                                }
                                String mcc = bean.getMCC_MNC();
                                bean.setMCC_MNC(mcc + "-"+ sData);
                            }
                            Log.e("text mnc"," mnc" + data + " sdata " + sData );
                        } else if(nodeName.equals(MODE_PSCPCI)) {
                            data = parser.nextText();
                            Log.e("text pscpci",data + " pscpci");
                            bean.setPCI(data);
                        } else if(nodeName.equals(MODE_SIG1)) {
                            data = parser.nextText();
                            Log.e("text sig1",data + " sig1");
                            bean.setPN(data);
                        } else if(nodeName.equals(MODE_SIG1DBM)) {
                            data = parser.nextText();
                            Log.e("text sig1DBM",data + " sig1DBM");
                            bean.setSTRENGTH(data);
                        } else if(nodeName.equals(MODE_SIG2)) {
                            data = parser.nextText();
                            Log.e("text sig2",data + " sig2");
//                            bean.set
                        } else if(nodeName.equals(MODE_SIG2DBM)) {
                            data = parser.nextText();
                            Log.e("text sig2DBM",data + " sig2DBM");
//                            bean.set
                        }
                    }
                    break;
                case XmlPullParser.TEXT:
                    Log.e("text text",parser.getText());
                    break;
                case XmlPullParser.END_TAG:
                    nodeName = parser.getName();
                    Log.e("text end",nodeName);
                    if(nodeName.equals(NODE_CDMA) || nodeName.equals(NODE_WCDMA) || nodeName.equals(NODE_GSM) || nodeName.equals(NODE_LTE)) {
                        kctbasestationdataBeans.add(bean);
                        bean = null;
                    }
                    break;

                case XmlPullParser.END_DOCUMENT:
                    break;
            }
            eventType = parser.next();
        }
        return kctbasestationdataBeans;
    }
}
