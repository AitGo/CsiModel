package com.liany.csiclient.aidl;

import java.io.Serializable;

/**
 * 预定义网络接口错误信息
 * Created by daiyu on 16-10-12.
 */
public class ServiceError implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Code {
        用户未登陆,没有开启网络,传入参数错误,接口服务故障,系统错误,没有查到结果
    }

    private Code code;
    private String message;

    public ServiceError(Code code, String message) {
        this.code = code;
        this.message = message;
    }

	/**
	 * 获取网络接口错误信息
	 * @return
     */
	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
