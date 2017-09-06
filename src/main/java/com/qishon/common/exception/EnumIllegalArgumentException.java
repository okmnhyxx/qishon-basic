package com.qishon.common.exception;


import com.qishon.common.exception.common.BaseErrorCode;

/**
 * @author kexia.lu on 2017/8/11.
 * 枚举参数异常
 */
public class EnumIllegalArgumentException extends RestException {

    public EnumIllegalArgumentException(Class className, String value) {
        super(500, BaseErrorCode.ENUM_ILLEGAL_ARGS, "枚举参数不存在", String.format("枚举%s不存在该值%s", className.getName(), value));

    }

    public EnumIllegalArgumentException(Class className, int value) {
        super(500, BaseErrorCode.ENUM_ILLEGAL_ARGS, "枚举参数不存在", String.format("枚举%s不存在该值%d", className.getName(), value));
    }

}
