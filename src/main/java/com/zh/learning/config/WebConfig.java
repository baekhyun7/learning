package com.zh.learning.config;

import com.zh.learning.config.interceptor.LoginRequireInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zh
 * @version 1.0
 * @date 2021/2/3 10:50
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    LoginRequireInterceptor loginRequireInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequireInterceptor);
    }
}
