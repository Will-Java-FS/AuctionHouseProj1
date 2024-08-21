package com.revature.auction.config;

import com.revature.auction.logging.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggerInterceptorConfig implements WebMvcConfigurer
{
    @Autowired
    LoggerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        // this interceptor will be applied to all URLs
        registry.addInterceptor(interceptor);
    }
}
