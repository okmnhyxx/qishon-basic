package com.qishon.common.exception;


import com.qishon.common.exception.common.AbstractDomainType;
import com.qishon.common.exception.common.AbstractParamType;
import com.qishon.common.exception.common.BaseErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author kexia.lu on 2017/8/17.
 */
public class RecordHasExistException extends RestException {

    /**
     * 记录已存在
     * @param domainType 实体类型
     * @param constraintParamType 约束的参数类型
     * @param constraintParam 约束的参数值
     */
    public RecordHasExistException(AbstractDomainType domainType, AbstractParamType constraintParamType, String constraintParam) {

        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_UNIQUE_ERROR,
                String.format("%s%s已存在", domainType.getDesc(), constraintParamType.getDesc()),
                String.format("%s - %s[%s]已存在", domainType.getDesc(), constraintParamType.getDesc(), constraintParam));
    }


    /**
     * 记录已存在
     * @param domainType 实体类型
     * @param constraintParamType 约束的参数类型
     * @param constraintParam 约束的参数值
     * @param precondition 前提，同一个paramType下
     */
    public RecordHasExistException(AbstractDomainType domainType, AbstractParamType constraintParamType, String constraintParam, AbstractParamType precondition) {

        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.RECORD_NOT_UNIQUE_ERROR,
                String.format("同一%s下，该%s%s已存在", precondition.getDesc(), domainType.getDesc(), constraintParamType.getDesc()),
                String.format("%s - %s[%s]已存在(先决条件：%s)", domainType.getDesc(), constraintParamType.getDesc(), constraintParam,
                        precondition.getDesc()));
    }

    /**
     * 记录已存在
     * @param errorMsg 错误信息，展示给前端的
     * @param debugMsg 详细错误信息，打印在log中的
     */
    public RecordHasExistException(String errorMsg, String debugMsg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMsg, debugMsg);
    }
}
