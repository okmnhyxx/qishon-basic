package com.qishon.common.exception;


import com.qishon.common.exception.common.AbstractDomainType;
import com.qishon.common.exception.common.AbstractParamType;
import com.qishon.common.exception.common.BaseErrorCode;

/**
 * Created by emi on 2017/6/14.
 */
public class DomainBelongNotEqualException extends RestException  {

    /**
     * 实体从属异常
     * @param slaveryDomain 实体
     * @param slaveryId 实体Id
     * @param masterDomain 从属的实体
     * @param supposeMasterId 期望属的实体Id
     * @param transMasterId 实际从属的实体Id
     */
    public DomainBelongNotEqualException(AbstractDomainType slaveryDomain, long slaveryId, AbstractDomainType masterDomain, long supposeMasterId, long transMasterId) {

        super(BaseErrorCode.DOMAIN_NOT_BELONGS, slaveryDomain + "从属信息异常",
            String.format("%s[%d]期望%s[%d]，实际传入[%d]", slaveryDomain.getDesc(), slaveryId, masterDomain.getDesc(), supposeMasterId, transMasterId));
    }
}
