package com.example.login.control;

import com.example.login.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping("/admin")
    public ModelAndView login(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/user/index")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }




}
