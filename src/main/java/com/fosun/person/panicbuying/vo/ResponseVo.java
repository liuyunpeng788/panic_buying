package com.fosun.person.panicbuying.vo;

import java.io.Serializable;

/**
 * @author: created by aimy
 * date: 2019/6/29 18:49
 */
public class ResponseVo<T> implements Serializable {
    private int errCode;
    private String errMsg;
    private T data;

    private static final int SUCCESS = 1000;

    public ResponseVo(int errCode, String errMsg,T data){
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ResponseVo(){}

    public ResponseVo(T data){
        this.errCode = SUCCESS;
        this.data = data;
    }

    public ResponseVo(int errCode, String errMsg){
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }
}
