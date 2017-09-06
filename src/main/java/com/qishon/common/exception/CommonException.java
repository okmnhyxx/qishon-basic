package com.qishon.common.exception;


import com.qishon.common.exception.common.BaseErrorCode;

/**
 * Created by emi on 2016/10/31.
 */
public class CommonException extends RestException {

    public CommonException(String message) {
        super(500, BaseErrorCode.COMMON_ERROR, message);
    }

    public CommonException(String message, String debugMessage) {
        super(500, BaseErrorCode.COMMON_ERROR, message, debugMessage);
    }

}
