package com.qm.laoxiang.common.web.domain;

public enum ResultCode {
    SUCCESS(200, "SUCCESS"),
    ILLEGAL_PARAM(400, "parameter error"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not found"),
    SESSION_EXPIRED(405, "Session expired"),
    INTERNAL_ERR(500, "server error");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
