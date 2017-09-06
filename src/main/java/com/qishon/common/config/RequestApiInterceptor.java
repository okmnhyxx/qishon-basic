package com.qishon.common.config;

import com.qishon.common.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author kexia.lu on 2017/8/30.
 */
public class RequestApiInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(RequestApiInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String agent = request.getHeader("User-Agent");
        if (request.getRequestURI().equals("/error")) {
            log.info("request [{}] =>[{}] ", IpUtils.getIpAddress(request), request.getRequestURL());
            return super.preHandle(request, response, handler);
        }

        String apiOperationValue = this.fetchApiOperationValue(handler);
        String params = this.fetchParam(request);

        log.info("request [{}] =>[{}] [{}] [{}] {}", IpUtils.getIpAddress(request), apiOperationValue, request.getMethod(), request.getRequestURL(), params);
        request.setAttribute("_START_TIME", System.currentTimeMillis());
        return super.preHandle(request, response, handler);

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (!request.getRequestURI().contains("/swagger-resources")) {

            if (request.getRequestURI().equals("/error")) {
                log.info("response [{}] =>[{}] ", IpUtils.getIpAddress(request), request.getRequestURL());
            } else {
                long start = Long.parseLong(request.getAttribute("_START_TIME").toString());
                log.info("response [{}] =>[{}] [{}] cost[{}]ms", IpUtils.getIpAddress(request), request.getMethod(), request.getRequestURL(),
                        System.currentTimeMillis() - start);
            }
        }
    }


    private String fetchApiOperationValue(Object handler) {

        ApiOperation annotation = ((HandlerMethod)handler).getMethod().getAnnotation(ApiOperation.class);
        if (null == annotation) {
            return "";
        }

        return annotation.value();
    }

    private String fetchParam(HttpServletRequest request) {
        Enumeration parameterNames = request.getParameterNames();
        String params;
        StringBuilder sb = new StringBuilder();
        while (parameterNames.hasMoreElements()) {
            params = (String) parameterNames.nextElement();
            String[] values = request.getParameterValues(params);
            sb.append("[").append(params).append(":").append(ArrayUtils.toString(values)).append("]");
        }
//        return StringUtils.isNotEmpty(sb.toString()) ? sb.insert(0, "\n").toString(): "";
        return StringUtils.isEmpty(sb.toString()) ? "": sb.toString();
    }


//                String uri = request.getScheme() + "://" + request.getServerName()
//                        + ("http".equals(request.getScheme()) && request.getServerPort() == 80
//                        || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? ""
//                        : ":" + request.getServerPort())
//                        + request.getRequestURI()
//                        + (request.getQueryString() != null ? "?" + request.getQueryString() : "");

}
