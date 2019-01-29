package com.qm.laoxiang.common.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * http请求应答包装类
 * @param <T>
 */
@ApiModel(value = "HTTP请求应答结果")
public class ResponseResult<T> {
    /**
     * 请求结果状态码
     */
    @ApiModelProperty(value = "结果码", notes = "SUCCESS 200 ILLEGAL_PARAM 400  UNAUTHORIZED 301 FORBIDDEN 403 NOT_FOUND 404 INTERNAL_ERR 500")
    private Integer code;

    /**
     * 请求结果
     */
    @ApiModelProperty(value = "应答信息", notes = "请求执行失败包含异常信息")
    private String msg;

    /***
     * 请求结果数据
     */
    @ApiModelProperty(value = "请求结果数据")
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult() {
    }

    public ResponseResult(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMessage();
    }

    public static ResponseResult success(){
        return new ResponseResult(ResultCode.SUCCESS);
    }

    public static <T> ResponseResult success(T data){
        ResponseResult responseResult = new ResponseResult(ResultCode.SUCCESS);
        responseResult.setData(data);
        return responseResult;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}
