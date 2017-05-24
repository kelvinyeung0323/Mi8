package com.hidata.mi8pro.util;

import java.io.Serializable;

/**
 * Created by k_way on 2017/5/19.
 */

public class ResponseResult<T> implements Serializable{

    private String status;
    private T data;
    private String msg;
    private String errorCode;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
