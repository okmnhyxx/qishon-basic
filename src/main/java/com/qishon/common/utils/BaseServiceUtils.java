package com.qishon.common.utils;


import com.qishon.common.exception.DomainBelongNotEqualException;
import com.qishon.common.exception.RecordHasDeletedException;
import com.qishon.common.exception.RecordNotFoundException;
import com.qishon.common.exception.common.AbstractDomainType;
import com.qishon.common.exception.common.AbstractParamType;
import com.qishon.common.model.AbstractBaseDomain;
import com.qishon.common.model.AbstractSimplyBaseDomain;
import org.springframework.stereotype.Component;

/**
 * @author kexia.lu on 2017/8/14.
 */
@Component
public class BaseServiceUtils {

    /**
     * 检查实体是否为空，为空会抛异常
     * @param domainType 实体类型
     * @param baseDomain 实体
     * @param domainId 实体Id
     */
    public void checkNull(AbstractDomainType domainType, AbstractSimplyBaseDomain baseDomain, long domainId) {
        if (null == baseDomain) {
            throw new RecordNotFoundException(domainType.getDesc(), domainId);
        }
    }

    /**
     * 检查实体是否为空，为空会抛异常，不兼容多字段查询
     * @param domainType 实体类型
     * @param baseDomain 实体
     * @param paramType 更具什么字段查询的
     * @param domainId 查询字段值
     */
    public void checkNull(AbstractDomainType domainType, AbstractSimplyBaseDomain baseDomain, AbstractParamType paramType, long domainId) {
        if (null == baseDomain) {
            throw new RecordNotFoundException(domainType.getDesc(), paramType.getDesc(), domainId + "");
        }
    }

    /**
     * 查询实体是否为空，且是否已被删除， 是的话会抛出异常
     * @param domainType 实体类型
     * @param baseDomain 实体
     * @param domainId 实体Id
     */
    public void checkDeleted(AbstractDomainType domainType, AbstractBaseDomain baseDomain, long domainId) {
        this.checkNull(domainType, baseDomain, domainId);
        if (baseDomain.isDeleted()) {
            throw new RecordHasDeletedException(domainType, domainId);
        }
    }

    /**
     * 查询实体是否为空，且是否已被删除，是的话会抛出异常，不兼容多字段查询
     * @param domainType 实体类型
     * @param baseDomain 实体
     * @param paramType 更具什么字段查询的
     * @param domainId 查询字段值
     */
    public void checkDeleted(AbstractDomainType domainType, AbstractBaseDomain baseDomain, AbstractParamType paramType, long domainId) {
        this.checkNull(domainType, baseDomain, paramType, domainId);
        if (baseDomain.isDeleted()) {
            throw new RecordHasDeletedException(domainType, paramType, domainId + "");
        }
    }

    public void checkDomainRelation(AbstractDomainType slaveryDomain, long slaveryId, AbstractDomainType masterDomain, long supposeMasterId, long transMasterId) {
        if (transMasterId != 0) {//当传入的Id不为空时才验证
            if (supposeMasterId != transMasterId) {
                throw new DomainBelongNotEqualException(slaveryDomain, slaveryId, masterDomain, supposeMasterId, transMasterId);
            }
        }
    }
}
