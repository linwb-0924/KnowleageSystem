package com.example.login.control;

import com.example.login.bean.Admin;
import com.example.login.service.AdminService;
import com.example.login.utils.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wb_Lin
 * @create 2020-07-05 20:11
 */
@Controller
public class LoginContoler {
    @Resource
    AdminService adminService;

    @ResponseBody
    @RequestMapping("/")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("alogin");
        return mv;
    }

    @RequestMapping("/user/toLogin")
    public ModelAndView toLogin(HttpServletRequest request){
        String msg = (String) request.getAttribute("msg");
        ModelAndView modelAndView= new ModelAndView();
        if(msg!=null){
            modelAndView.addObject("msg",msg);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping("/user/doLogin")
    public String login(String uname, String upass, Model model, HttpSession session){
        ModelAndView mv = new ModelAndView();
        Admin admin =  new Admin(uname, MD5Utils.code(upass));
        Admin admin1 = adminService.SelectByadmin(admin);
        if(admin1 == null||admin1.getStatus()==0){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        else{
            session.setAttribute("admin",admin1);
            return "index";
        }

    }
    @GetMapping("loginoutt")
    public String loginoutt(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }
}
