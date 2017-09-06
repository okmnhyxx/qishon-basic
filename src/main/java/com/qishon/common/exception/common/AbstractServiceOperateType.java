package com.qishon.common.exception.common;

/**
 * @author kexia.lu on 2017/8/24.
 */
public interface AbstractServiceOperateType {

    AbstractServiceType getServiceType();

    String getDesc();

    String fetchUrl();

}
