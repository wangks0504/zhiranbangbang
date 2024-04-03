package org.zhiran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhiran.Interceptors.LoginInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
   private LoginInterceptor loginInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
       //引入LoginInterceptor拦截器,并且对登录和注册接口添加登录拦截白名单
    }
}
