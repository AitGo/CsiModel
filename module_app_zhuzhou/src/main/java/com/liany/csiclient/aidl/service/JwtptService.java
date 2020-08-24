package com.liany.csiclient.aidl.service;

import android.app.Application;
import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.aj.client.jwtpt.ICallJwtService;
import com.aj.frame.ydjwpt.common.beans.UserExt;
import com.liany.csiclient.aidl.ServiceError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对警务通平台提供的aidl service进行封装
 * 
 * 
 */
public class JwtptService {
	// aidl service的action名称
	public static final String JwtptServiceAction = "com.aj.client.jwtpt.aidl.CallJwtService";
	// app全局实例
	Application context;
	private static JwtptService instance = null;

	public enum MethodName {
		 查询人员, 查询在逃, 查询驾驶人, 查询车辆, 查询盗抢车辆, 查询案件信息, 查询涉案人员, 获取地理位置, 打开修改密码界面, 评论当前APP
	}

	private static Object wait=1L;

	// aidl service的实例
	private ICallJwtService iCallJwtService = null;
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			Log.i("JwtptService", "开始连接ICallJwtService");
			// 初始化连接
			iCallJwtService = ICallJwtService.Stub.asInterface(arg1);
			synchronized (wait) {
				wait.notify();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Log.i("JwtptService", "开始断开ICallJwtService");
			// 断开连接
			iCallJwtService = null;
			Toast.makeText(context.getApplicationContext(),"警务通SDK服务已断开，请重新登录", Toast.LENGTH_SHORT).show();
		}

	};

	private JwtptService(Application context) {
		this.context = context;
		try {
			Intent intent = new Intent(JwtptServiceAction);
			intent.setPackage("com.aj.client.jwtpt");
//			this.context.startService(intent);
			this.context.bindService(intent, serviceConnection, ContextWrapper.BIND_AUTO_CREATE);

		}catch (Exception ie){
			ie.printStackTrace();
		}
	}
	
	/**
	 * 解除service的绑定
	 */
	public void close() {
		if(serviceConnection != null) {
			this.context.unbindService(serviceConnection);
			//serviceConnection = null;
			//iCallJwtService = null;
		}
	}

	/**
	 * 全局唯一单例
	 * 
	 * @param context
	 * @return
	 */
	public static JwtptService getInstance(Application context) {
		if (instance == null) {
			// 确保多线程时的安全问题
			synchronized (wait) {
				if (instance == null) {
					instance = new JwtptService(context);
					try {
						wait.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return instance;
	}

	/**
	 * aidl提供的远程方法
	 * 
	 * @param method
	 * @param obj
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Object getObject(String method, Object obj) throws RemoteException,
            Exception {
		if (iCallJwtService != null) {
			byte[] bts = iCallJwtService.getObject(method,
					getBytesFromObject(obj));
			if (bts != null)
				return getObjectFromBytes(bts);
		} else {

		}
		return null;
	}

	public UserExt getCurrentUser() throws RemoteException, Exception {
		if (iCallJwtService != null) {
			byte[] bts = iCallJwtService
					.getCurrentUser(null);
			if(bts == null)
				Log.i("JwtptService", "拿到一个空的用户对象");
			if (bts != null) {
				Log.i("JwtptService", "拿到用户对象");
				Object obj=getObjectFromBytes(bts);
				if(obj instanceof UserExt) {
					return (UserExt) obj;
				}else{
					ServiceError error=(ServiceError)obj;
					throw new Exception(error.getMessage());
				}

			}
		}

		return null;
	}

	/**
	 * 从字节数组获取对象
	 */
	public Object getObjectFromBytes(byte[] objBytes) throws Exception {
		if (objBytes == null || objBytes.length == 0) {
			return null;
		}
		ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
	}

	/**
	 * 从对象获取一个字节数组
	 */
	public byte[] getBytesFromObject(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		return bo.toByteArray();
	}


}
