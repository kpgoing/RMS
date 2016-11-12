//package org.sel.rms.config;
//
//import org.sel.rms.interceptor.AdminInterceptor;
//import org.sel.rms.interceptor.TeacherInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * Created by xubowei on 08/11/2016.
// */
//@Configuration
//public class WebAppConfigurer extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AdminInterceptor())
//                .addPathPatterns("/admin/**");
//        registry.addInterceptor(new TeacherInterceptor())
//                .addPathPatterns("/**");
//    }
//
//}
