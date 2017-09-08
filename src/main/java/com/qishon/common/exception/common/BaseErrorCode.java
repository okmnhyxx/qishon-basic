package com.qishon.common.exception.common;

/**
 * @author kexia.lu on 2017/8/11.
 * 500错误时 细化的错误状态码
 */
public interface BaseErrorCode {

    /**
     * 常规异常
     */
    int COMMON_ERROR = 900000;

    /**
     * 远程服务访问不通
     */
    int REST_EXCEPTION = 900001;

    /**
     * 枚举参数异常
     */
    int ENUM_ILLEGAL_ARGS = 900002;

    /**
     * 实体从属异常
     */
    int DOMAIN_NOT_BELONGS = 200003;

    /**
     * 记录不存在
     */
    int RECORD_NOT_FOUND = 900006;

    /**
     * 状态异常
     */
    int RECORD_STATUS_ERROR = 900007;

    /**
     * 不符合唯一性
     */
    int RECORD_NOT_UNIQUE_ERROR = 900008;
}
