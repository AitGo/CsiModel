package com.liany.csiclient.aidl.service;

import android.app.Application;
import android.os.RemoteException;
import android.util.Log;

import com.aj.frame.beans.PagingInfo;
import com.aj.frame.um.usm.beans.User;
import com.aj.frame.ydjwpt.common.beans.Ajxx;
import com.aj.frame.ydjwpt.common.beans.DqJdcxx;
import com.aj.frame.ydjwpt.common.beans.GpsInfo;
import com.aj.frame.ydjwpt.common.beans.Jdcxx;
import com.aj.frame.ydjwpt.common.beans.JsrInfo;
import com.aj.frame.ydjwpt.common.beans.RyInfo;
import com.aj.frame.ydjwpt.common.beans.UserExt;
import com.aj.frame.ydjwpt.common.beans.WffzRyInfo;
import com.aj.frame.ydjwpt.common.beans.ZtryInfo;


/**
 * jwtpt提供的服务的封装类，使用者不需要关心aidl的具体调用，可以直接通过该类来使用远程服务接口
 * 所有的查询支持两个参数，一个是条件数组，一个是翻页对象。
 * 条件数组遵循样本查询原则：
 * 当单个对象存在非空值时，单个对象的非空值会作为“与”条件查询；
 * 当存在多个对象时，多个对象之间的非空值会作为“或”条件查询
 * PagingInfo对象使用：
 * setPageNo来表示第几页，从1开始计数
 * setPageSize标识一页显示多少条记录
 * 
 * 
 */
public class JwtptServiceManager {

	JwtptService jwtptService;

	/**
	 * @param context
	 *            仅支持传入Application对象
	 */
	public JwtptServiceManager(Application context) {
		jwtptService = JwtptService.getInstance(context);
	}

	/**
	 * @return 返回一个默认的每页数量为10，页码为1的PagingInfo对象
	 */
	private PagingInfo getDefaultPagingInfo() {
		PagingInfo pi = new PagingInfo();
		pi.setPageSize(10);
		pi.setPageNo(1);

		return pi;
	}

	/**
	 * 返回当前用户，如果处于未登陆时调用，会打开警务通平台的登陆界面，当登陆完成后，会关闭登陆界面重新会到调用的app，并返回完成登陆的当前用户。
	 * 这个功能可以用于第三方app共享警务通平台app的登陆界面和用户体系。 第三方app也必须通过这个来完成登陆，才能合法的使用安全链路的数据交换
	 * 
	 * @return 当前用户
	 * @throws Exception
	 * @throws RemoteException
	 */
	public UserExt getCurrentUser() throws RemoteException, Exception {
		UserExt user = jwtptService.getCurrentUser();
		
		return user;
	}

	/**
	 * 打开警务通平台的密码修改界面，如果已经登陆，会进入警务通平台的密码修改界面
	 * @throws Exception
	 * @throws RemoteException
	 */
	public void callSetPasswordActivity() throws RemoteException, Exception {
		this.jwtptService.getObject(JwtptService.MethodName.打开修改密码界面.name(),null);

	}

	/**
	 * 打开警务通平台的评论界面，如果已经登陆会进入当前app的评论界面
	 * @throws Exception
	 * @throws RemoteException
	 */
	public void callCommentActivity() throws RemoteException, Exception {
		this.jwtptService.getObject(JwtptService.MethodName.评论当前APP.name(),null);
	}

	/**
	 * 组合数组
	 * 
	 * @param objs
	 *            已经存在的数组
	 * @param obj
	 *            需要添加的数组
	 * @return 把新的对象添加到已有的数组，并返回一个新的数组
	 */
	private Object[] groupArray(Object obj, Object[] objs) {

		Object[] paraObject = new Object[objs.length + 1];
		paraObject[0] = obj;
		for (int i = 0; i < objs.length; i++) {
			paraObject[i + 1] = objs[i];
		}
		return paraObject;
	}

	/**
	 * 对人员信息进行检索
	 * 
	 * @param ryInfo 人员信息查询条件
	 * @param pagingInfo 分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<RyInfo>，RyInfo，ServiceError
	 * @throws Exception
	 * @throws RemoteException
	 */
	public Object searchRyInfo(RyInfo[] ryInfo, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (ryInfo == null) {
			return returnObj;
		}
		if (ryInfo.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询人员.name(),
					groupArray(pagingInfo, ryInfo));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchRyInfo出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对在逃人员进行检索
	 * 
	 * @param ztryInfo 在逃人员信息查询条件
	 * @param pagingInfo	分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<ZtryInfo>，ZtryInfo，ServiceError
	 */
	public Object searchZtryInfo(ZtryInfo[] ztryInfo, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (ztryInfo == null) {
			return returnObj;
		}
		if (ztryInfo.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询在逃.name(),
					groupArray(pagingInfo, ztryInfo));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchZtryInfo出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对驾驶人进行检索
	 * 
	 * @param jsrInfo 驾驶人信息查询条件
	 * @param pagingInfo 分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<JsrInfo>，JsrInfo，ServiceError
	 */
	public Object searchJsrInfo(JsrInfo[] jsrInfo, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (jsrInfo == null) {
			return returnObj;
		}
		if (jsrInfo.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询驾驶人.name(),
					groupArray(pagingInfo, jsrInfo));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchJsrInfo出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对机动车进行检索
	 * 
	 * @param jdcxx 机动车信息查询条件
	 * @param pagingInfo	分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<Jdcxx>，Jdcxx，ServiceError
	 */
	public Object searchJdcxx(Jdcxx[] jdcxx, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (jdcxx == null) {
			return returnObj;
		}
		if (jdcxx.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询车辆.name(),
					groupArray(pagingInfo, jdcxx));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchJdcxx出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对盗抢机动车进行检索
	 * 
	 * @param dqJdcxx 盗抢机动车查询条件
	 * @param pagingInfo	分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<DqJdcxx>，DqJdcxx，ServiceError
	 */
	public Object searchDqJdcxx(DqJdcxx[] dqJdcxx, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (dqJdcxx == null) {
			return returnObj;
		}
		if (dqJdcxx.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询盗抢车辆.name(),
					groupArray(pagingInfo, dqJdcxx));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchDqJdcxx出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对案件信息进行检索
	 * 
	 * @param ajxx 案件信息查询条件
	 * @param pagingInfo	分页信息
	 * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<Ajxx>，Ajxx，ServiceError
	 */
	public Object searchAjxx(Ajxx[] ajxx, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (ajxx == null) {
			return returnObj;
		}
		if (ajxx.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询案件信息.name(),
					groupArray(pagingInfo, ajxx));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("searchAjxx出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 对涉案人员进行检索
	 * 
	 * @param wffzRyInfo  违法犯罪人员查询条件
	 * @param pagingInfo 分页信息
	 * @return  返回的结果需要判断类型，返回的对象类型包括：ArrayList<WffzRyInfo>，WffzRyInfo，ServiceError
	 */
	public Object searchWffzRyInfo(WffzRyInfo[] wffzRyInfo,
                                   PagingInfo pagingInfo) {
		Object returnObj = null;
		if (wffzRyInfo == null) {
			return returnObj;
		}
		if (wffzRyInfo.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.查询涉案人员.name(),
					groupArray(pagingInfo, wffzRyInfo));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(), String.format(
					"searchWffzRyInfo出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}

	/**
	 * 获取当前设备的gps信息
	 * 
	 * @param gpsInfo
	 * @param pagingInfo
	 * @return  返回gps信息对象
	 */
	public Object getSelfGpsInfo(GpsInfo[] gpsInfo, PagingInfo pagingInfo) {
		Object returnObj = null;
		if (gpsInfo == null) {
			return returnObj;
		}
		if (gpsInfo.length < 1) {
			return returnObj;
		}
		if (pagingInfo == null) {
			pagingInfo = getDefaultPagingInfo();
		}

		try {
			returnObj = this.jwtptService.getObject(
					JwtptService.MethodName.获取地理位置.name(),
					groupArray(pagingInfo, gpsInfo));
		} catch (RemoteException e) {
			Log.e(this.getClass().getName(),
					String.format("远程服务出现了错误：%s", String.valueOf(e)));
		} catch (Exception e) {
			Log.e(this.getClass().getName(),
					String.format("getSelfGpsInfo出现了错误：%s", String.valueOf(e)));
		}
		return returnObj;
	}


	/**
	 * 查询警员信息
	 * @param users   警员信息查询条件
	 * @param pagingInfo 分页信息
     * @return 返回的结果需要判断类型，返回的对象类型包括：ArrayList<User>，User，ServiceError
     */
	public Object searchPoliceInfo(User[] users, PagingInfo pagingInfo ){

		return null;
	}
}
