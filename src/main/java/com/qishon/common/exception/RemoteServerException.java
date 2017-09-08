package com.qishon.common.exception;

import com.qishon.common.exception.common.AbstractServiceOperateType;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author kexia.lu on 2017/8/24.
 */
public class RemoteServerException extends RestException {

    /**
     * 远程服务抛出异常
     * @param realStatus 实际http状态码
     * @param exceptionResponse 异常信息
     * @param operateType 操作类型
     * @param params 请求参数
     * @param pathVariables url上的参数，一般指pathVariables
     */
    public RemoteServerException(HttpStatus realStatus, ExceptionResponse exceptionResponse,
                                 AbstractServiceOperateType operateType, String params, Object[] pathVariables) {

        super(realStatus.value(), exceptionResponse.getErrorCode(), exceptionResponse.getErrorMsg(),
                String.format("[%s-%s]内部异常，url[%s]", operateType.getServiceType().getDesc(), operateType.getDesc() ,operateType.fetchUrl()) +
                        (StringUtils.isEmpty(params) ? "" : String.format(",访问参数为：%s", params)) +
                        (null == pathVariables || 0 == pathVariables.length ? "" : String.format(",路径参数为：%s", Arrays.toString(pathVariables))));
//        String.format("[%s-%s]内部异常，url[%s],访问参数为：%s，路径参数为：%s", operateType.getServiceType().getDesc(), operateType.getDesc() ,
//                operateType.fetchUrl(), params, Arrays.toString(pathVariables))
    }
}
