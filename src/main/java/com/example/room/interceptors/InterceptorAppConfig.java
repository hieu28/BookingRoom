package com.example.room.interceptors;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableWebSecurity
@Configuration
public class InterceptorAppConfig implements WebMvcConfigurer {
    private final GeneralInterceptor generalInterceptor;
    private final LoginInterceptor loginInterceptor;

    public InterceptorAppConfig(GeneralInterceptor generalInterceptor, LoginInterceptor loginInterceptor) {
        this.generalInterceptor = generalInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry){
        registry.addInterceptor(generalInterceptor).addPathPatterns("/hi","/api/hello");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/authen");
    }
}
