package com.qishon.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author kexia.lu on 2017/8/14.
 */
@Configuration
public class WebHttpAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestApiInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-resources");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestApiFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

//    class AuthorizeInterceptor extends HandlerInterceptorAdapter {
//
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//            String userId = request.getHeader("auth");
//            if (StringUtils.isEmpty(request.getHeader("auth")) || "0".equals(userId)) {
////                throw new RestException(ErrorCode.CODE_401, "", "");
//            }
//            return super.preHandle(request, response, handler);
//        }
//    }

}
