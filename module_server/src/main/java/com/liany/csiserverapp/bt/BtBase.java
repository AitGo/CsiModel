package com.liany.csiserverapp.bt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.BitmapFactory;

import com.liany.csiserverapp.andServer.model.SceneDB;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.network.webservice.NetWorkUtils;
import com.liany.csiserverapp.utils.BitmapUtils;
import com.liany.csiserverapp.utils.Compare;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.SPUtils;
import com.liany.csiserverapp.utils.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;


/**
 * 客户端和服务端的基类，用于管理socket长连接
 */
public class BtBase {
    static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String FILE_PATH = Constants.photoPath + File.separator;
    private static final int FLAG_MSG = 0;  //消息标记
    private static final int FLAG_FILE = 1; //文件标记
    private static final int FLAG_PHOTO_FINGER = 601; //指纹图片标记
    private static final int FLAG_PHOTO_FOOT = 602; //足迹图片标记

    private BluetoothSocket mSocket;
    private DataOutputStream mOut;
    private Listener mListener;
    private boolean isRead;
    private boolean isSending;

    BtBase(Listener listener) {
        mListener = listener;
    }

    /**
     * 循环读取对方数据(若没有数据，则阻塞等待)
     */
    void loopRead(BluetoothSocket socket) {
        mSocket = socket;
        try {
            if (!mSocket.isConnected())
                mSocket.connect();
            notifyUI(Listener.CONNECTED, mSocket.getRemoteDevice());
            mOut = new DataOutputStream(mSocket.getOutputStream());
            DataInputStream in = new DataInputStream(mSocket.getInputStream());
            isRead = true;
            while (isRead) { //死循环读取
                switch (in.readInt()) {
                    case FLAG_MSG: //读取短消息
                        String msg = in.readUTF();
                        notifyUI(Listener.MSG, "接收短消息：" + msg);
                        break;
                    case FLAG_FILE: //读取文件
                        writeFile(in,null);
                        break;
                    case FLAG_PHOTO_FINGER:
                        String presentSceneId = getPresentScene();
                        String photoId = StringUtils.getUUID();
                        String evidenceId = StringUtils.getUUID();
                        if(!StringUtils.checkString(presentSceneId)) {
                            //没有当前现场
                            notifyUI(Listener.MSG, "没有当前现场");
                            break;
                        }
                        String fileName = writeFile(in,presentSceneId);
                        if(!StringUtils.checkString(fileName)) {
                            //图片名为空
                            notifyUI(Listener.MSG, "图片名为空");
                            break;
                        }
                        File localFile = new File(FILE_PATH + presentSceneId + File.separator + fileName);
                        if(!localFile.exists()) {
                            //图片文件不存在
                            notifyUI(Listener.MSG, "图片文件不存在");
                            break;
                        }
                        DaoSession daoSession = ServerApplication.getDaoSession();
                        //上传图片
                        Photo photo = new Photo();
                        photo.setId(photoId);
                        photo.setParentId(evidenceId);
                        photo.setCrimeId(presentSceneId);
                        photo.setPath(localFile.getAbsolutePath());
                        LogUtils.e("Path local: " + localFile.getAbsolutePath());
                        LogUtils.e("Path photo: " + photo.getPath());
                        photo.setServerPath(Constants.ipAddress + presentSceneId + File.separator + localFile.getName());
                        String fileType = localFile.getName().substring(localFile.getName().lastIndexOf(".") + 1);
                        photo.setType(fileType);
                        String path = photo.getPath();
                        photo.setWidth(BitmapFactory.decodeFile(localFile.getPath()).getWidth() + "");
                        photo.setHeight(BitmapFactory.decodeFile(localFile.getPath()).getHeight() + "");
                        photo.setUUID(StringUtils.md5HashCode32(localFile.getPath()));
                        photo.setFileName(localFile.getName());
                        if(fileType.equals("bmp")) {
                            //如果是bmp文件，新建一个jpg图片上传到服务器
                            path = photo.getPath().replace("bmp","jpg");
                            BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(photo.getPath()),path);
                        }
                        photo.setState(Constants.photoState_evidence);
                        SceneDB.insertPhoto(photo);
                        String finalPath = path;
                        NetWorkUtils.uploadPic(ServerApplication.getContext(), Constants.method_uploadPic,
                                path,
                                photoId,
                                presentSceneId,
                                StringUtils.md5HashCode32(path), new NetWorkUtils.Callback() {
                                    @Override
                                    public void onNext(String result) {
                                        LogUtils.e("uploadPic " +result);
                                        if(result.equals("success")) {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        photo.setRev4(Compare.getFeature(finalPath));
                                                        SceneDB.insertPhoto(photo);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }).start();
                                            photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                                        }else {
                                            photo.setIsUpload(Constants.UPLOAD_FAIL);
                                        }
                                        SceneDB.insertPhoto(photo);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        LogUtils.e("uploadPic " +e.getMessage());
                                        photo.setIsUpload(Constants.UPLOAD_FAIL);
                                        SceneDB.insertPhoto(photo);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    photo.setRev4(Compare.getFeature(finalPath));
                                                    SceneDB.insertPhoto(photo);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }).start();
                                    }
                                });
                        //插入指纹数据
                        EvidenceEntity finger = new EvidenceEntity();
                        finger.setId(evidenceId);
                        finger.setCrimeId(presentSceneId);
                        finger.setEvidenceCategory("手印");
                        finger.setEvidence("指纹");
                        finger.setEvidenceKey("1101");
                        finger.setEvidenceName("指纹");
                        finger.setLegacySite("遗留部位");
                        finger.setMethod("直接照相");
                        finger.setMethodKey("A01");
                        finger.setTime(System.currentTimeMillis());
                        finger.setPeople((String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_accessInspectors, ""));
                        finger.setPeopleKey((String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_accessInspectorsKey, ""));
                        finger.setPhotoId(photoId);
                        daoSession.getEvidenceEntityDao().insert(finger);
                        break;
                    case FLAG_PHOTO_FOOT:
                        String presentSceneFootId = getPresentScene();
                        String photoFootId = StringUtils.getUUID();
                        String evidenceFootId = StringUtils.getUUID();
                        if(!StringUtils.checkString(presentSceneFootId)) {
                            //没有当前现场
                            notifyUI(Listener.MSG, "没有当前现场");
                            break;
                        }
                        String fileFootName = writeFile(in,presentSceneFootId);
                        if(!StringUtils.checkString(fileFootName)) {
                            //图片名为空
                            notifyUI(Listener.MSG, "图片名为空");
                            break;
                        }
                        File localFootFile = new File(FILE_PATH + presentSceneFootId + File.separator + fileFootName);
                        if(!localFootFile.exists()) {
                            //图片文件不存在
                            notifyUI(Listener.MSG, "图片文件不存在");
                            break;
                        }
                        DaoSession daoFootSession = ServerApplication.getDaoSession();
                        //上传图片
                        Photo footphoto = new Photo();
                        footphoto.setId(photoFootId);
                        footphoto.setParentId(evidenceFootId);
                        footphoto.setCrimeId(presentSceneFootId);
                        footphoto.setPath(localFootFile.getAbsolutePath());
                        LogUtils.e("Path local: " + localFootFile.getAbsolutePath());
                        LogUtils.e("Path photo: " + footphoto.getPath());
                        footphoto.setServerPath(Constants.ipAddress + presentSceneFootId + File.separator + localFootFile.getName());
                        String fileFootType = localFootFile.getName().substring(localFootFile.getName().lastIndexOf(".") + 1);
                        footphoto.setType(fileFootType);
                        String footpath = footphoto.getPath();
                        footphoto.setWidth(BitmapFactory.decodeFile(localFootFile.getPath()).getWidth() + "");
                        footphoto.setHeight(BitmapFactory.decodeFile(localFootFile.getPath()).getHeight() + "");
                        footphoto.setUUID(StringUtils.md5HashCode32(localFootFile.getPath()));
                        if(fileFootType.equals("bmp")) {
                            //如果是bmp文件，新建一个jpg图片上传到服务器
                            footpath = footphoto.getPath().replace("bmp","jpg");
                            BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(footphoto.getPath()),footpath);
                        }
                        footphoto.setFileName(localFootFile.getName());
                        footphoto.setState(Constants.photoState_evidence);
                        SceneDB.insertPhoto(footphoto);
                        NetWorkUtils.uploadPic(ServerApplication.getContext(), Constants.method_uploadPic,
                                footpath,
                                photoFootId,
                                presentSceneFootId,
                                StringUtils.md5HashCode32(footpath), new NetWorkUtils.Callback() {
                                    @Override
                                    public void onNext(String result) {
                                        LogUtils.e("uploadPic " +result);
                                        if(result.equals("success")) {
                                            footphoto.setIsUpload(Constants.UPLOAD_SUCCESS);
                                        }else {
                                            footphoto.setIsUpload(Constants.UPLOAD_FAIL);
                                        }
                                        SceneDB.insertPhoto(footphoto);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        LogUtils.e("uploadPic " +e.getMessage());
                                        footphoto.setIsUpload(Constants.UPLOAD_FAIL);
                                        SceneDB.insertPhoto(footphoto);
                                    }
                                });
                        //插入指纹数据
                        EvidenceEntity foot = new EvidenceEntity();
                        foot.setId(evidenceFootId);
                        foot.setCrimeId(presentSceneFootId);
                        foot.setEvidenceCategory("足迹");
                        foot.setEvidence("鞋印");
                        foot.setEvidenceKey("1");
                        foot.setEvidenceName("足迹");
                        foot.setLegacySite("遗留部位");
                        foot.setMethod("直接照相");
                        foot.setMethodKey("B01");
                        foot.setTime(System.currentTimeMillis());
                        foot.setPeople((String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_accessInspectors, ""));
                        foot.setPeopleKey((String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_accessInspectorsKey, ""));
                        foot.setPhotoId(photoFootId);
                        daoFootSession.getEvidenceEntityDao().insert(foot);
                        break;
                }
            }
        } catch (Throwable e) {
            close();
        }
    }

    /**
     * 发送短消息
     */
    public void sendMsg(String msg) {
        if (checkSend()) return;
        isSending = true;
        try {
            mOut.writeInt(FLAG_MSG); //消息标记
            mOut.writeUTF(msg);
            mOut.flush();
            notifyUI(Listener.MSG, "发送短消息：" + msg);
        } catch (Throwable e) {
            close();
        }
        isSending = false;
    }

    /**
     * 发送文件
     */
    public void sendFile(final String filePath) {
        if (checkSend()) return;
        isSending = true;
        Util.EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream in = new FileInputStream(filePath);
                    File file = new File(filePath);
                    mOut.writeInt(FLAG_FILE); //文件标记
                    mOut.writeUTF(file.getName()); //文件名
                    mOut.writeLong(file.length()); //文件长度
                    int r;
                    byte[] b = new byte[4 * 1024];
                    notifyUI(Listener.MSG, "正在发送文件(" + filePath + "),请稍后...");
                    while ((r = in.read(b)) != -1)
                        mOut.write(b, 0, r);
                    mOut.flush();
                    notifyUI(Listener.MSG, "文件发送完成.");
                } catch (Throwable e) {
                    close();
                }
                isSending = false;
            }
        });
    }

    /**
     * 释放监听引用(例如释放对Activity引用，避免内存泄漏)
     */
    public void unListener() {
        mListener = null;
    }

    /**
     * 关闭Socket连接
     */
    public void close() {
        try {
            isRead = false;
            mSocket.close();
            notifyUI(Listener.DISCONNECTED, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 当前设备与指定设备是否连接
     */
    public boolean isConnected(BluetoothDevice dev) {
        boolean connected = (mSocket != null && mSocket.isConnected());
        if (dev == null)
            return connected;
        return connected && mSocket.getRemoteDevice().equals(dev);
    }

    // ============================================通知UI===========================================================
    private boolean checkSend() {
        if (isSending) {
            ServerApplication.toast("正在发送其它数据,请稍后再发...", 0);
            return true;
        }
        return false;
    }

    private void notifyUI(final int state, final Object obj) {
        ServerApplication.runUi(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mListener != null)
                        mListener.socketNotify(state, obj);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface Listener {
        int DISCONNECTED = 0;
        int CONNECTED = 1;
        int MSG = 2;

        void socketNotify(int state, Object obj);
    }

    private String writeFile(DataInputStream in, String sceneId) throws Exception {
        String fileName = in.readUTF(); //文件名
        String path = FILE_PATH ;
        if(sceneId != null) {
            path = FILE_PATH + sceneId + File.separator;
        }
        Util.mkdirs(path);
        long fileLen = in.readLong(); //文件长度
        // 读取文件内容
        long len = 0;
        int r;
        byte[] b = new byte[4 * 1024];
        FileOutputStream out = new FileOutputStream(path + fileName);
        notifyUI(Listener.MSG, "正在接收文件(" + fileName + "),请稍后...");
        while ((r = in.read(b)) != -1) {
            out.write(b, 0, r);
            len += r;
            if (len >= fileLen)
                break;
        }
        notifyUI(Listener.MSG, "文件接收完成(存放在:" + path + ")");
        return fileName;
    }

    private String getPresentScene() {
        String presentSceneId = (String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_presentScene,"");
        if(!StringUtils.checkString(presentSceneId)) {
            //默认现场为最新现场
            CrimeItem crime = SceneDB.getNewCrime();
            if(crime != null) {
                return crime.getId();
            }else {
                return "";
            }
        }
        return presentSceneId;
    }
}
