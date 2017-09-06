package com.qishon.common.exception;


import com.qishon.common.exception.common.BaseErrorCode;

/**
 * @author kexia.lu on 2017/8/11.
 */
public class CommonException extends RestException {

    /**
     * 公共异常
     * @param message 错误信息
     */
    public CommonException(String message) {
        super(500, BaseErrorCode.COMMON_ERROR, message);
    }

    /**
     * 公共异常
     * @param message 错误信息，展示给前端的
     * @param debugMsg 详细错误信息，打印在log中的
     */
    public CommonException(String message, String debugMsg) {
        super(500, BaseErrorCode.COMMON_ERROR, message, debugMsg);
    }

}
