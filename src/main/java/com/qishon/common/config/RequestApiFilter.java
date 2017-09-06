package com.qishon.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author kexia.lu on 2017/9/5.
 */
@Order(100)
@WebFilter(filterName = "RequestApiFilter", urlPatterns = "/**")
@Component
public class RequestApiFilter implements Filter {

    Logger log = LoggerFactory.getLogger(RequestApiFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {

            if (!((HttpServletRequest)servletRequest).getRequestURI().equals("/swagger-resources")) {

                MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) servletRequest);
                String jsonParam = requestWrapper.getRequestBody();
                if (!StringUtils.isEmpty(jsonParam)) {
                    log.info("" + jsonParam);
                }
                filterChain.doFilter(requestWrapper, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


    private class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private byte[] requestBody = null;

        // 传入是JSON格式 转换成JSONObject
        public String getRequestBody() throws UnsupportedEncodingException {
            return null == requestBody || 0 == requestBody.length ? "" : new String(requestBody);
        }


        public MyHttpServletRequestWrapper(HttpServletRequest request) {

            super(request);

            try {
                requestBody = StreamUtils.copyToByteArray(request.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (requestBody == null) {
                requestBody = new byte[0];
            }
            final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
            return new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return bais.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener listener) {

                }
            };
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
    }
}
