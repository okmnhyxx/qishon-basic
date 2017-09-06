package com.qishon.common.exception;


import com.qishon.common.exception.common.BaseErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Created by emi on 2016/12/1.
 */
public class RecordNotFoundException extends RestException {

    /**
     * 记录不存在
     * @param domainName 哪个实体
     * @param domainId 实体Id
     */
    public RecordNotFoundException(String domainName, long domainId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_FOUND,
                domainName + "记录不存在", String.format("%s[%s]记录不存在", domainName, domainId));
    }

    /**
     * 记录不存在
     * @param domainName 哪个实体
     * @param queryBy 被查询的参数名
     * @param queryByValue 被查询的参数值
     */
    public RecordNotFoundException(String domainName, String queryBy, String queryByValue) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_FOUND,
                domainName + "记录不存在", String.format("%s[%s=%s]记录不存在", domainName, queryBy, queryByValue));
    }

    /**
     * 记录不存在
     * @param errorMsg 错误信息，展示给前端的
     * @param debugMsg 详细错误信息，打印在log中的
     */
    public RecordNotFoundException(String errorMsg, String debugMsg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMsg, debugMsg);
    }
}
