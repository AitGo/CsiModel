package com.liany.csiserverapp.network.webservice;

import com.google.gson.Gson;
import com.liany.csiserverapp.diagnose.CrimeScene;
import com.liany.csiserverapp.network.schedulers.BaseSchedulerProvider;
import com.liany.csiserverapp.utils.LogUtils;

import org.kobjects.base64.Base64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @创建者 ly
 * @创建时间 2019/4/22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WebServiceUtils {

    private static BaseSchedulerProvider schedulerProvider;

    /**
     * 查询数据库信息
     * @param WSDL_URI
     * @param methodName
     * @return
     * @throws Exception
     */
    public static String getDBInfo(String WSDL_URI, String methodName) throws Exception{
        String result = "";
//        String WSDL_URI = "http://192.168.31.88:8080/lianyservice/service/appUploadDataService";//wsdl 的uri
        String namespace = "http://service/";//namespace
//        String methodName = "LoadUserInfo";//要调用的方法名称

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
//        request.addProperty("mobileCode", phoneSec);
//        request.addProperty("userId", "");

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
//        envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;

        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }


    /**
     * 上传图片
     * @param WSDL_URI
     * @param methodName
     * @param filePath
     * @param picId
     * @param picHashCode
     * @return
     * @throws Exception
     */
    public static String uploadPic(String WSDL_URI, String methodName,
                                   String filePath,
                                   String picId,
                                   String sceneId,
                                   String picHashCode) throws Exception {


        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("id", picId);
        request.addProperty("sceneId", sceneId);
        request.addProperty("hashcode", picHashCode);
        request.addProperty("data", getBase64StringPic(filePath));

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 图片上传方法
     *
     * 1.把图片信息通过Base64转换成字符串
     * 2.调用connectWebService方法实现上传
     */
    private static byte[] getBytesPic(String filePath){
        byte[] bytes = new byte[]{};
        try{
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) >= 0){
                baos.write(buffer, 0, count);
            }
            bytes = baos.toByteArray();
//            String uploadBuffer = new String(Base64.encode());  //进行Base64编码
//            connectWebService(bytes);
            fis.close();
            return bytes;
        }catch(Exception e){
            e.printStackTrace();
            return bytes;
        }
    }

    /**
     * 图片上传方法
     *
     * 1.把图片信息通过Base64转换成字符串
     * 2.调用connectWebService方法实现上传
     */
    private static String getStringPic(String filePath){
        String pic = "";
        try{
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) >= 0){
                baos.write(buffer, 0, count);
            }
            pic = baos.toByteArray().toString();
//            String uploadBuffer = new String(Base64.encode());  //进行Base64编码
//            connectWebService(bytes);
            fis.close();
            return pic;
        }catch(Exception e){
            e.printStackTrace();
            return pic;
        }
    }

    /**
     * 图片上传方法
     *
     * 1.把图片信息通过Base64转换成字符串
     * 2.调用connectWebService方法实现上传
     */
    private static String getBase64StringPic(String filePath){
        String base64 = "";
        try{
            FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) != -1){
                baos.write(buffer, 0, count);
            }
            base64 = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码
//            byte[] encode = android.util.Base64.encode(baos.toByteArray(), android.util.Base64.CRLF);
//            base64 = new String(encode,"UTF-8");
            fis.close();
            return base64;
        }catch(Exception e){
            e.printStackTrace();
            return base64;
        }
    }

    /**
     * 图片上传方法
     *
     * 1.把图片信息通过Base64转换成字符串
     * 2.调用connectWebService方法实现上传
     */
    private static String getBase64String(String filePath){
        String base64 = "";
        try{
            FileInputStream fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) != -1){
                baos.write(buffer, 0, count);
            }
//            base64 = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码
            byte[] encode = android.util.Base64.encode(baos.toByteArray(), android.util.Base64.CRLF);
            base64 = new String(encode,"UTF-8");
            fis.close();
            return base64;
        }catch(Exception e){
            e.printStackTrace();
            return base64;
        }
    }

    /**
     * 上传数据
     * @param WSDL_URI
     * @param methodName
     * @param scenes
     * @return
     * @throws Exception
     */
    public static String uploadScene(String WSDL_URI, String methodName,
                                     String sceneId,String unitCode,String userName,String userId,List<CrimeScene> scenes) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace
        Gson gson = new Gson();
        String sceneDatas = gson.toJson(scenes);

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("sceneId", sceneId);
        request.addProperty("unitCode", unitCode);
        request.addProperty("userName", userName);
        request.addProperty("userId", userId);
        request.addProperty("sceneData", sceneDatas);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI,30000);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 检查更新
     * @param WSDL_URI
     * @param methodName
     * @param versionCode
     * @param appName
     * @return 0 需要更新，1版本号相同，2版本号大于服务器，500：错误
     * @throws Exception
     */
    public static String checkVersion(String WSDL_URI, String methodName,
                                     String versionCode,String appName) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("version", versionCode);
        request.addProperty("appName", appName);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 下载apk
     * @param WSDL_URI
     * @param methodName
     * @return
     * @throws Exception
     */
    public static boolean downloadApk(String WSDL_URI, String methodName,String downloadPath,String downloadFileName) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace
        SoapObject request = new SoapObject(namespace, methodName);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        byte[] bytes = Base64.decode(result);
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        // 储存下载文件的目录
        try {
            is = Byte2InputStream(bytes);
            File file = new File(downloadPath, downloadFileName);
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return true;
        } catch (Exception e) {
            LogUtils.e("下载异常", e.getMessage());
//            listener.onDownloadFailed();
            return false;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }

    }

    /**
     * 下载apk
     * @param WSDL_URI
     * @param methodName
     * @return
     * @throws Exception
     */
    public static String downloadApk(String WSDL_URI, String methodName) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace
        SoapObject request = new SoapObject(namespace, methodName);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    // 将byte[]转换成InputStream
    public static InputStream Byte2InputStream(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        return bais;
    }


    /**
     * 删除图片
     * @param WSDL_URI
     * @param methodName
     * @param picId
     * @return
     * @throws Exception
     */
    public static String deletePic(String WSDL_URI, String methodName,String picId,String sceneId) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("id", picId);
        request.addProperty("sceneId", sceneId);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    public static String uploadCrimeScene(String WSDL_URI, String methodName) throws Exception{

        return null;
    }

//    @SuppressLint("CheckResult")
//    public static void deleteServicePic(String photoId,String sceneId) {
//        schedulerProvider = SchedulerProvider.getInstance();
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) {
//                String result = "";
//                try {
//                    result = WebServiceUtils.deletePic(((String) SPUtils.getParam(MyApplication.getContext(), Constants.URL,Constants.defaultURL)).replace("?wsdl",""),
//                            Constants.DeletePic, photoId, sceneId);
//                } catch (Exception exception) {
//                    result = exception.getMessage().toString();
//                    exception.printStackTrace();
//                    e.onNext(result);
//                }
//                //将结果返回给onPostExecute方法
//                e.onNext(result);
//            }}) .compose(schedulerProvider.applySchedulers())
//                .subscribeWith(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(String result) {
//                        LogUtils.e("deleteServicePic " +result);
//                        if(result.equals("success")) {
//
//                        }else {
//                            result = "删除图片错误：" + result;
//                            Toast.makeText(MyApplication.getContext(),result,Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

    /**
     * 报案信息
     * @param WSDL_URI
     * @param methodName
     * @param data alarm的Json，传入接警开始和结束时间，接警单位代码和名称
     * @return
     * @throws Exception
     */
    public static String getAlarmInfo(String WSDL_URI, String methodName,String data) throws Exception{
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("data", data);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }


    public static String GetSameCaseInfo(String WSDL_URI, String methodName, String json) throws Exception {
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("sceneAnalysisSuggestion", json);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 发送指纹对比
     * @param WSDL_URI
     * @param methodName
     * @param json
     * @param zipPath
     * @return
     * @throws Exception
     */
    public static String sendFingerTask(String WSDL_URI, String methodName, String json, String zipPath) throws Exception {

        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("taskData", json);
//        PropertyInfo p1=new PropertyInfo();
//        p1.setName("data");
//        p1.setType(MarshalBase64.BYTE_ARRAY_CLASS);
//        p1.setValue(getBytesPic(zipPath));
//        request.addProperty(p1);
        request.addProperty("data",getBase64String(zipPath));

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 发送指纹对比
     * @param WSDL_URI
     * @param methodName
     * @param json
     * @param zipPath
     * @return
     * @throws Exception
     */
    public static String sendFootTask(String WSDL_URI, String methodName, String json, String zipPath) throws Exception {

        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("taskData", json);
        request.addProperty("data",getBase64String(zipPath));

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 发送人像对比
     * @param WSDL_URI
     * @param methodName
     * @param json
     * @param zipPath
     * @return
     * @throws Exception
     */
    public static String sendFaceTask(String WSDL_URI, String methodName, String json, String zipPath) throws Exception {

        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("taskData", json);
        request.addProperty("data",getBase64String(zipPath));

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 得到指纹对比结果
     * @param WSDL_URI
     * @param methodName
     * @param sceneId
     * @return
     * @throws Exception
     */
    public static String getFingerTaskResult(String WSDL_URI, String methodName, String sceneId) throws Exception {
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("sceneId", sceneId);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 得到足迹对比结果
     * @param WSDL_URI
     * @param methodName
     * @param sceneId
     * @return
     * @throws Exception
     */
    public static String getFootTaskResult(String WSDL_URI, String methodName, String sceneId) throws Exception {
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("sceneId", sceneId);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }

    /**
     * 得到人像对比结果
     * @param WSDL_URI
     * @param methodName
     * @param sceneId
     * @return
     * @throws Exception
     */
    public static String getFaceTaskResult(String WSDL_URI, String methodName, String sceneId) throws Exception {
        String result = "";
        String namespace = "http://service/";//namespace

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的参数
        request.addProperty("sceneId", sceneId);

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = false;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(null, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
//        result = object.getMessage();
        // 获取返回的结果
        result = object.getProperty(0).toString();
        LogUtils.d(result);
        return result;
    }
}
