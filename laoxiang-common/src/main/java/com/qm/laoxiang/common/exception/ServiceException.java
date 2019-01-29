package com.qm.laoxiang.common.exception;

import com.qm.laoxiang.common.web.domain.ResultCode;

public class ServiceException extends Exception {
    /**
     * 异常代码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**

     * 异常消息
     */
    private String msg;

    public ServiceException() {
    }

    public ServiceException(int code, String msg, Exception e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(ResultCode rc) {
        super(rc.getMessage());
        this.code = rc.getCode();
        this.msg = rc.getMessage();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}
