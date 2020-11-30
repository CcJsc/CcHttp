package com.tools.cchttp.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2019/11/21 11:17
 */
public class BaseResponseList<T> {
    private int errorCode;
    private String errorMsg;
    private List<T> data;

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

    public List<T> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
