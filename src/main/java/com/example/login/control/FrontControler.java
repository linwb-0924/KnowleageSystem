package com.example.login.control;

import com.example.login.bean.Admin;
import com.example.login.bean.Blog;
import com.example.login.bean.Content;
import com.example.login.bean.User;
import com.example.login.service.AdminService;
import com.example.login.service.BlogService;
import com.example.login.service.ContentService;
import com.example.login.service.UserService;
import com.example.login.utils.MarkdownUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-05 13:32
 */
@Controller
public class FrontControler {

    @Resource
    BlogService blogService;

    @Resource
    AdminService adminService;

    @Resource
    ContentService contentService;

    @Resource
    UserService userService;


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Integer id, Model model, HttpSession session){
        Blog blog = blogService.SelectById(id);
        blog.setViews(blog.getViews()+1);
        blogService.UpdateByBlog(blog);
        String marktohtml = MarkdownUtils.marktohtml(blog.getContent());
        blog.setContent(marktohtml);
        Admin admin = adminService.getAdminByname(blog.getName());
        model.addAttribute("blog",blog);
        model.addAttribute("admin",admin);
        List<Content> contents = contentService.SelectBy(blog.getId());
        model.addAttribute("contents",contents);
        Admin admin1 = (Admin) session.getAttribute("admin");


        //model.addAttribute("adminname",admin1.getAname());
        if(admin1!=null){
            model.addAttribute("adminid",admin1.getId());
        }else{
            model.addAttribute("adminid",null);
        }

        User user1 = (User) session.getAttribute("user");
        if(user1!=null){
            model.addAttribute("userid",user1.getId());
        }else{
            model.addAttribute("userid",null);
        }

        return "blog";
    }

    @PostMapping("/blog/content")
    public  String content(@RequestParam("userid") Integer userid,@RequestParam("content") String content,@RequestParam("id") Integer id){
        //Admin admin = adminService.SelectById(adminid);
        User byId = userService.findById(userid);
        Content content1 = new Content(id,byId.getUname(),content);
        //content1.setPic(admin.getPic());
        content1.setCreatetime(new Date());
        contentService.Insert(content1);
        return "redirect:/blog/"+id;
    }

    @GetMapping("adminlookkk")
    public void adminlookkk(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        Content content = contentService.SelectByID(id);
        byte[] pic= content.getPic();
        if(pic !=null) {
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("UTF-8");
            OutputStream outputSream = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(pic);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf, 0, 1024)) != -1) {
                outputSream.write(buf, 0, len);
            }
            outputSream.close();
        }
    }
}
