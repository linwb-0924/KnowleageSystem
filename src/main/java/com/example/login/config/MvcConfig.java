package com.example.login.config;

import com.example.login.intercepter.Myinterceter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wb_Lin
 * @create 2020-07-05 11:28
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Myinterceter()).addPathPatterns("/admin").addPathPatterns("/user/index")
                .addPathPatterns("/admin/welcome").addPathPatterns("/admin/adminlist").addPathPatterns("/admin/toAdd")
                .addPathPatterns("/admin/add").addPathPatterns("/adminlook").addPathPatterns("/adminloginout")
                .addPathPatterns("/admin/delete").addPathPatterns("/admin/edit").addPathPatterns("/admin/blog")
                .addPathPatterns("/admininput").addPathPatterns("/admin/blogs").addPathPatterns("/adminedit").addPathPatterns("/admin/blog/{id}/delete")
                .addPathPatterns("/admin/blog/search").addPathPatterns("/admin/type")
                .addPathPatterns("/admin/types/input").addPathPatterns("/admin/type").addPathPatterns("/admin/types/{id}/input")
                .addPathPatterns("/admin/type/{id}").addPathPatterns("/admin/types/{id}/delete");
    }
}
