package com.qishon.common.exception;

import com.qishon.common.exception.common.AbstractDomainParam;
import com.qishon.common.exception.common.BaseErrorCode;

import java.util.Arrays;

/**
 * @author kexia.lu on 2017/8/11.
 */
public class RecordStatusException extends RestException {

    /**
     * 记录状态异常
     * @param realState 实际状态
     * @param domainType 实体类型
     * @param domainId 实体Id
     * @param supposeState 期望的状态
     */
    public RecordStatusException(int realState, AbstractDomainParam domainType, long domainId, int[] supposeState) {
        super(500, BaseErrorCode.RECORD_STATUS_ERROR, domainType.getDesc() + "当前状态不支持该操作",
                String.format("%s[%d]合理状态：%s，实际传入状态：%d", domainType.getDesc(), domainId, Arrays.toString(supposeState), realState));
    }
}
