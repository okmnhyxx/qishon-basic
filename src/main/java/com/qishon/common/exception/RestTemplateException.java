package com.qishon.common.exception;

import com.qishon.common.exception.common.AbstractServiceOperateType;
import com.qishon.common.exception.common.BaseErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author kexia.lu on 2017/8/24.
 */
public class RestTemplateException extends RestException {

    /**
     * rest请求发送失败
     * @param operateType 操作类型
     * @param params 请求参数
     * @param pathVariables url上的参数，一般指pathVariables
     */
    public RestTemplateException(AbstractServiceOperateType operateType, String params, Object[] pathVariables) {

        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.REST_EXCEPTION, String.format("访问接口[%s]失败", operateType.getDesc()),
                String.format("访问[%s-%s]失败，url[%s]", operateType.getServiceType().getDesc(), operateType.getDesc(), operateType.fetchUrl()) +
                        (StringUtils.isEmpty(params) ? "" : String.format(",访问参数为：%s", params)) +
                        (null == pathVariables || 0 == pathVariables.length ? "" : String.format(",路径参数为：%s", Arrays.toString(pathVariables))));
    }
//                    String.format("访问[%s-%s]失败，url[%s],访问参数为：%s，路径参数为：%s", operateType.getServiceType().getDesc(), operateType.getDesc() ,
//                        operateType.fetchUrl(), params, Arrays.toString(pathVariables))

    /**
     * rest请求发送失败
     * @param operateType 操作类型
     * @param statusCode 错误状态码
     * @param params 请求参数
     * @param pathVariables url上的参数，一般指pathVariables
     */
    public RestTemplateException(AbstractServiceOperateType operateType, HttpStatus statusCode, String params, Object[] pathVariables) {

        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), BaseErrorCode.REST_EXCEPTION, String.format("访问接口[%s]%d错误", operateType.getDesc(), statusCode.value()),
                String.format("访问[%s-%s]%d，url[%s]", operateType.getServiceType().getDesc(), operateType.getDesc(), statusCode.value(), operateType.fetchUrl()) +
                        (StringUtils.isEmpty(params) ? "" : String.format(",访问参数为：%s", params)) +
                        (null == pathVariables || 0 == pathVariables.length ? "" : String.format(",路径参数为：%s", Arrays.toString(pathVariables))));
    }
//        String.format("访问[%s-%s]%d，url[%s],访问参数为：%s，路径参数为：%s", operateType.getServiceType().getDesc(), operateType.getDesc(),
//                statusCode.value(), operateType.fetchUrl(), params, Arrays.toString(pathVariables))
}
