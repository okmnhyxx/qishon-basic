package com.qishon.common.exception;


import com.qishon.common.exception.common.AbstractDomainType;
import com.qishon.common.exception.common.AbstractParamType;
import com.qishon.common.exception.common.BaseErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author kexia.lu on 2017/8/30.
 */
public class RecordHasDeletedException extends RestException {

    /**
     * 根据Id查询的记录已被删除
     * @param domainType 实体类型
     * @param domainId 实体Id
     */
    public RecordHasDeletedException(AbstractDomainType domainType, long domainId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_FOUND, domainType.getDesc() + "已被删除",
                String.format("%s[%s]记录已被删除", domainType.getDesc(), domainId));
    }

    /**
     * 根据参数查询出的实体，已被删除
     * @param domainType 实体类型
     * @param paramTypeQueriedBy 被查询的参数类型
     * @param paramValueQueriedBy 被查询的参数值
     */
    public RecordHasDeletedException(AbstractDomainType domainType, AbstractParamType paramTypeQueriedBy, String paramValueQueriedBy) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_FOUND, domainType.getDesc() + "已被删除",
                String.format("%s[%s=%s]记录已被删除", domainType.getDesc(), paramTypeQueriedBy.getDesc(), paramValueQueriedBy));
    }

    /**
     * 记录已被删除
     * @param errorMsg 错误信息，展示给前端的
     * @param debugMsg 详细错误信息，打印在log中的
     */
    public RecordHasDeletedException(String errorMsg, String debugMsg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMsg, debugMsg);
    }

}
