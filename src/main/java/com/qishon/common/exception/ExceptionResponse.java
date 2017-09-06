package com.qishon.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by emi on 2017/6/7.
 */
@ApiModel
public class ExceptionResponse {

    @ApiModelProperty("HTTP 500 时，细化的自定义错误码")
    private int errorCode;

    @ApiModelProperty("错误信息描述，调用者可直接展示给用户界面 或 传递到下一个服务链")
    private String errorMsg;

    public ExceptionResponse() {
        super();
        this.errorCode = 0;
        this.errorMsg = "";
    }

    public ExceptionResponse(int errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
