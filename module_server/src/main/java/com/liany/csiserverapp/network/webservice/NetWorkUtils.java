package com.liany.csiserverapp.network.webservice;

import android.annotation.SuppressLint;
import android.content.Context;

import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.diagnose.CrimeScene;
import com.liany.csiserverapp.network.schedulers.BaseSchedulerProvider;
import com.liany.csiserverapp.network.schedulers.SchedulerProvider;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.SPUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @创建者 ly
 * @创建时间 2019/10/8
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class NetWorkUtils {
    private static BaseSchedulerProvider schedulerProvider;

    public interface Callback{
        void onNext(String result);
        void onError(Throwable e);
    }

    @SuppressLint("CheckResult")
    public static void updateDB(Context mContext,String methodName,Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.getDBInfo(((String) SPUtils.getParam(mContext, Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),methodName);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onNext(result);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String result) {
                        // 解析json同步数据库
                        LogUtils.e("updateDB " + result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("updateUserDB error" + e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void uploadPic(Context mContext, String methodName, String filePath, String picId, String sceneId, String picHashCode, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.uploadPic(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName,
                            filePath,
                            picId,
                            sceneId,
                            picHashCode);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("uploadPic " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void deletePic(Context mContext, String methodName, String picId, String sceneId, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.deletePic(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, picId, sceneId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("deletePositionPic " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void uploadScene(Context mContext, String methodName,
                                   String sceneId, String unitCode, String userName, String userId, List<CrimeScene> scenes, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.uploadScene(((String) SPUtils.getParam(mContext, Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName,
                            sceneId,
                            unitCode,
                            userName,
                            userId,
                            scenes);
                } catch (Exception exception) {
                    result = exception.getMessage().toString();
                    exception.printStackTrace();
                    e.onNext(result);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }
        }).compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("uploadCrimeScene " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }

                });
    }

    @SuppressLint("CheckResult")
    public static void checkVersion(Context mContext, String methodName, String versionCode, String appName, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.checkVersion(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, versionCode + "",appName);
                } catch (Exception exception) {
                    result = exception.getMessage().toString();
                    exception.printStackTrace();
                    e.onNext(result);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("checkUpdateApp " + result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void downloadApp(Context mContext,String methodName,Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.downloadApk(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName);
                } catch (Exception e1) {
                    e.onError(e1);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void downloadAppByWebService(Context mContext, String methodName, String downloadPath, String downloadFileName, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) {
                boolean isSuccess = false;
                try {
                    isSuccess = WebServiceUtils.downloadApk(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName,downloadPath, downloadFileName);
                } catch (Exception e1) {
                    e.onError(e1);
                }
                //将结果返回给onPostExecute方法
                e.onNext(isSuccess);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean result) {
                        LogUtils.e("downloadApk onNext " +result);
                        callback.onNext(String.valueOf(result));
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("downloadApk onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void getAlarmInfo(Context mContext, String methodName, String data, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.getAlarmInfo(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, data);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("getAlarmList onNext" +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getAlarmList onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void getSameCaseInfo(Context mContext, String methodName, String data, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.GetSameCaseInfo(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, data);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("GetSameCaseInfo onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("GetSameCaseInfo onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void sendFingerTask(Context mContext, String methodName, String json, String zipPath, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.sendFingerTask(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, json, zipPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("sendFootTask onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("sendFootTask onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void sendFootTask(Context mContext, String methodName, String json, String zipPath, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.sendFootTask(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, json, zipPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("sendFootTask onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("sendFootTask onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void sendFaceTask(Context mContext, String methodName, String json, String zipPath, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.sendFootTask(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, json, zipPath);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("sendFaceTask onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("sendFaceTask onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void getFingerTaskResult(Context mContext, String methodName, String sceneId, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.getFingerTaskResult(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, sceneId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("getFingerTaskResult onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getFingerTaskResult onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void getFootTaskResult(Context mContext, String methodName, String sceneId, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.getFootTaskResult(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, sceneId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("getFingerTaskResult onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getFingerTaskResult onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void getFaceTaskResult(Context mContext, String methodName, String sceneId, Callback callback) {
        schedulerProvider = SchedulerProvider.getInstance();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                String result = "";
                try {
                    result = WebServiceUtils.getFootTaskResult(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            methodName, sceneId);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }
                //将结果返回给onPostExecute方法
                e.onNext(result);
            }}) .compose(schedulerProvider.applySchedulers())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String result) {
                        LogUtils.e("getFaceTaskResult onNext " +result);
                        callback.onNext(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getFaceTaskResult onError " +e.getMessage());
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
