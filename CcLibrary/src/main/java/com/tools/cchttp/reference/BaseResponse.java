package com.tools.cchttp.reference;

import java.util.List;

/**
 * 公共返回格式1
 * @param <T>
 */
public class BaseResponse<T> {
	private int errorCode;
	private String errorMsg;
	private T data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg == null ? "" : errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
