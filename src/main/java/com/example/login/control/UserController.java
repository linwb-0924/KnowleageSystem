package com.example.login.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.login.bean.Blog;
import com.example.login.bean.Type;
import com.example.login.bean.User;
import com.example.login.service.BlogService;
import com.example.login.service.TypeService;
import com.example.login.service.UserService;
import com.example.login.utils.Result;
import com.example.login.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    BlogService blogService;

    @Resource
    TypeService typeService;

    /*加载登入页面*/


    /*处理登入请求*/
//    @RequestMapping("/do")
//    public ModelAndView doLogin(@RequestParam String user_name, @RequestParam String password, HttpSession session){
//        ModelAndView mv = new ModelAndView("failed");   //默认跳到失败页面
//        System.out.println("uname=【"+user_name+"】，upass=【"+password+"】");
//        User user = userService.findByName(user_name);
//        if (user!=null && user.getUpass().equals(password)){
//
//            QueryWrapper<Blog> wa=null;
//            Page<Blog> page = new Page<>(1,3);
//            Page<Blog> blogPage = blogService.SelectByPage(page, wa);
//            mv.addObject("pageindex",1);
//            if(page.hasNext()){
//                mv.addObject("flag2","1");
//            }
//            if(page.hasPrevious()){
//                mv.addObject("flag1","1");
//            }
//            mv.addObject("total",blogPage.getTotal());
//            mv.addObject("blogs",blogPage.getRecords());
//            List<Type> all1 = typeService.getAll();
//            mv.addObject("types",all1);
//            //session.setAttribute("user",user);
//            mv.setViewName("a-index");
//        }
//        return mv;
//    }

    /*处理登入请求*/
    @RequestMapping("/do")
    public Result doLogin(@RequestParam String uname, @RequestParam String upass,HttpSession session){
        Result result = ResultUtils.success();  //code = 10000
        User user = userService.findByName(uname);
        System.out.println("uname=【"+uname+"】，upass=【"+upass+"】");
        if (user == null)
            result.setMsg("账号不存在");
        else if (! user.getUpass().equals(upass))
            result.setMsg("密码错误");
        else {
            result.setMsg("登入成功");
            session.setAttribute("user",user);
            result.setCode(1);
        }
        return result;
    }


    /*加载注册界面*/
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(){
        return new ModelAndView("user_register");
    }

    /*处理注册请求*/
    @RequestMapping("/doRegister")
    public Result doRegister(User user, @RequestParam(name = "headpic") String hp){
        User beFoundUser = userService.findByName(user.getUname());
        Result result = ResultUtils.success(user);
        if (beFoundUser != null){
            System.out.println("已有同名用户：【"+beFoundUser+"】");
            result.setMsg("注册失败，用户名已存在");
            return result;
        }
        System.out.println("headPic==【"+hp);
        user.setHeadpic(hp);
        //将user对象存进数据库
        userService.addUser(user);
        System.out.println("注册新用户：【"+user+"】");
        result.setCode(0);
        result.setMsg("注册成功");
        return result;
    }

    @RequestMapping("/toList")
    public ModelAndView toList(){
        return new ModelAndView("User_list");
    }

    /*查询所有用户*/
    @RequestMapping("/getUsers")
    public Result getUsers(){
        List<User> users = userService.findAll();
        for (User user: users)
            System.out.println();
        Result result = ResultUtils.success(users);
        result.setData(users);
        result.setCode(0);
        result.setMsg("查询成功");
        return result;
    }

    /*删除指定用户*/
    @RequestMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Integer id){
        Result result = null;
        int data = userService.deleteById(id);
        result = ResultUtils.success(data);
        if (data>0){
            result.setCode(0);
            result.setMsg("删除成功");
        }else{
            result.setMsg("删除失败");
        }
        return result;
    }

    /*加载添加用户界面*/
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        return new ModelAndView("user_add");
    }

    /*用户头像上传*/
    @RequestMapping("/uploadHeadPic")
    public Result uploadHeadPic(@RequestParam("file") MultipartFile file) throws IOException {
        // 项目路径
        File projectPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("projectPath= 【"+projectPath);
        // 绝对路径=项目路径+自定义路径
        File upload = new File(projectPath.getAbsolutePath(), "static/user/");
        if (!upload.exists())
            upload.mkdirs();
        Result result=null;
        if (file.isEmpty()) {
            result=ResultUtils.error(-1,"上传失败");
        }
        else{
            //获取文件名称
            String fileName=file.getOriginalFilename();
            System.out.println("dest="+upload.getAbsolutePath()+"\\"+fileName);

            File dest=new File(upload.getAbsolutePath()+"\\"+fileName);
            //文件IO
            file.transferTo(dest);
            result=ResultUtils.success();
            result.setCode(0);
            result.setMsg("上传成功");
            Map<String,String> map=new HashMap<>();
            map.put("src",fileName);
            result.setData(map);
        }

        System.out.println("在/uploadHeadPic下的result：【"+result.getData());  //{src=child.jpg}
        return result;
    }

    /*处理添加请求*/
    @RequestMapping("/doAdd")
    public Result doAdd(User user, @RequestParam(name = "headpic") String hp){
        System.out.println("headPic==【"+hp);
        user.setHeadpic(hp);
        //将user对象存进数据库
        userService.addUser(user);
        System.out.println("添加数据：【"+user+"】");
        Result result = ResultUtils.success(user);
        result.setCode(0);
        result.setMsg("添加成功");
        return result;
    }

    /*加载编辑界面*/
    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable Integer id){
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setViewName("user_edit");
        User user = userService.findById(id);
        //设置数据
        mv.addObject("user", user);
        mv.addObject("url","/user/"+user.getHeadpic());
        return mv;
    }

    /*处理编辑请求*/
    @RequestMapping("/doEdit")
    public Result doEdit(User user){    //user是包含更新内容的对象
        System.out.println("接收到的User："+user);
        Result result = null;
        int data = userService.updateUser(user);
        result = ResultUtils.success(data);
        if (data>0){
            result.setCode(0);
            result.setMsg("更新成功");
        }else{
            result.setMsg("更新失败");
        }
        return result;
    }

    /*加载单个用户界面*/
    @RequestMapping("/toScan/{id}")
    public ModelAndView toScan(@PathVariable Integer id){
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setViewName("user_scan");
        User user = userService.findById(id);
        //设置数据
        mv.addObject("user", user);
        mv.addObject("url","/user/"+user.getHeadpic());
//        mv.addObject("userBorn",userBorn);
        return mv;
    }

}

