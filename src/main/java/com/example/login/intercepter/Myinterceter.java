package com.example.login.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wb_Lin
 * @create 2020-07-05 11:24
 */
public class Myinterceter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object admin = request.getSession().getAttribute("admin");
        if(admin ==null){
            request.setAttribute("msg","请先登录");
            //request.getRequestDispatcher("/adminlogin").forward(request,response);
            response.sendRedirect("/user/toLogin");
            return false;
        }
        return true;
    }
}
