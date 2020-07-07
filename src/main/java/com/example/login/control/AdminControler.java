package com.example.login.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.login.bean.*;
import com.example.login.service.AdminService;
import com.example.login.service.BlogService;
import com.example.login.service.TypeService;
import com.example.login.utils.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-04 10:18
 */
@Controller
public class AdminControler {

    @Resource
    AdminService adminService;

    @Resource
    BlogService blogService;

    @Resource
    TypeService typeService;

    @RequestMapping("/admin/welcome")
    public String welcome(){
        return "admin/index";
    }


    @RequestMapping("/admin/adminlist")
    public String adminlist(@RequestParam(value = "pageindex",defaultValue = "1") String pageindex,Model model, HttpServletRequest request){
        QueryWrapper<Admin> wa = new QueryWrapper<>();
        String username = request.getParameter("username");
        if("".equals(username)||username==null){
             wa = null;
        }else{
            wa=new QueryWrapper<>();
            wa.eq("aname",username);
        }
        Page<Admin> page = new Page<>(Integer.valueOf(pageindex),3);
        IPage<Admin> adminIPage = adminService.SelectByPage(page,wa);
        model.addAttribute("pageindex",Integer.valueOf(pageindex));
        model.addAttribute("total","共有数据"+adminIPage.getTotal()+"条");
        if(page.hasNext()){
            model.addAttribute("flag2","1");
        }
        if(page.hasPrevious()){
            model.addAttribute("flag1","1");
        }
        model.addAttribute("list",adminIPage.getRecords());
        return "admin-list";
    }

    @RequestMapping("/admin/toAdd")
    public String admintoadd(){
        return "admin-add";
    }

    @ResponseBody
    @PostMapping("/admin/add")
    public String adminadd(Admin admin, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        try {
            System.out.println(file.getOriginalFilename());
            InputStream is = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            is.read(pic);
            admin.setPic(pic);
            String code = MD5Utils.code(admin.getApass());
            admin.setApass(code);
            adminService.InsertByAdmin(admin);
            return "添加成功！！！";
        }catch (Exception e){
            return "添加失败！！！";
        }
    }

    @RequestMapping("/adminlook")
    public void look(HttpSession session, HttpServletResponse response) throws IOException {
        Admin admin1 = (Admin) session.getAttribute("admin");
        Integer id = admin1.getId();
        System.out.println("查看id"+id);
        Admin admin = adminService.SelectById(id);
        byte[] pic = admin.getPic();
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
    @RequestMapping("/adminlookk")
    public void look1(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        Admin admin = adminService.SelectById(id);
        byte[] pic = admin.getPic();
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
    @RequestMapping("/adminlookkkk")
    public void look11(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        Admin admin = adminService.getAdminByname(name);
        byte[] pic = admin.getPic();
        if(pic !=null) {
            System.out.println("查看"+name);
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

    @RequestMapping("/adminloginout")
    public String loginout(HttpSession session){
            session.removeAttribute("admin");
            return "login";
    }
    @GetMapping("/admin/status")
    public String status1(@RequestParam("id") Integer id){
        Admin admin = adminService.SelectById(id);
        if(admin.getStatus()==1){
            adminService.UpdateById1(id);
        }else{
            adminService.UpdateById2(id);
        }

        return "redirect:/admin/adminlist";
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") Integer id){
        adminService.DeleteById(id);
        return "redirect:/admin/adminlist";
    }

    @GetMapping("/admin/edit")
    public String edit(@RequestParam("id") Integer id,Model model){
        model.addAttribute("id",id);
        return "admin-edit";
    }
    @PostMapping("/admin/edit")
    public String edit1(@RequestParam("id") Integer id,@RequestParam("apass") String apass,Model model){
        Admin admin = adminService.SelectById(id);
        admin.setApass(MD5Utils.code(apass));
        adminService.UpdateByAdmin(admin);
        return "redirect:/admin/adminlist";
    }

    @GetMapping("/admin/blog")
    public String blog(@RequestParam(value = "pageindex",defaultValue = "1") String pageindex, Model model,HttpServletRequest request){
        String message = request.getParameter("message");
        if(message!=null){
            model.addAttribute("message",message);
        }
        Page<Blog> page = new Page<>(Integer.valueOf(pageindex),3);
        QueryWrapper<Blog> qw  =new QueryWrapper<>();
        qw.orderByDesc("views");
        Page<Blog> blogPage = blogService.SelectByPage(page, qw);
        model.addAttribute("blogs",blogPage.getRecords());
        model.addAttribute("pageindex",Integer.valueOf(pageindex));
        if(page.hasNext()){
            model.addAttribute("flag1",1);
        }
        if(page.hasPrevious()){
            model.addAttribute("flag2",1);
        }
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        return "admin/blog";
    }

    @GetMapping("/admininput")
    public String admininput(Model model){
        model.addAttribute("id",null);
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        model.addAttribute("blog",new Blog());
        return "admin/imput";
    }

    @PostMapping("/admin/blogs")
    public String blogs(Blog blog,RedirectAttributes redirectAttributes,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        blog.setName(admin.getAname());
        blog.setFlag("发布");
        System.out.println(blog.getFirstpic());
        if(blog.getId()==null){
            Blog blog1 = blogService.SelectByname(blog.getTitle());
            if(blog1!=null){
                redirectAttributes.addAttribute("message","已存在改标题文章");
                return "redirect:/admin/blog";
            }
            blogService.InsertByBlog(blog);
            redirectAttributes.addAttribute("message","新增文章成功");
            Type type = typeService.SelectByName(blog.getTypes());
            type.setNumber(type.getNumber()+1);
            typeService.UpdateById(type);
            return "redirect:/admin/blog";
        }
        Blog blog4 = blogService.SelectById(blog.getId());
        Blog blog2 = blogService.SelectByname(blog.getTitle());
        if(blog2!=null && !blog.getTitle().equals(blog4.getTitle())){
            redirectAttributes.addAttribute("message","已存在改标题文章");
            return "redirect:/admin/blog";
        }
        Blog blog3 = blogService.SelectById(blog.getId());
        Type type = typeService.SelectByName(blog3.getTypes());
        if(type!=null){
            type.setNumber(type.getNumber()-1);
            typeService.UpdateById(type);
        }

        Type type1 = typeService.SelectByName(blog.getTypes());
        type1.setNumber(type1.getNumber()+1);
        typeService.UpdateById(type1);

        blogService.UpdateByBlog(blog);
        redirectAttributes.addAttribute("message","修改文章成功");

        return "redirect:/admin/blog";

    }

    @GetMapping("/adminedit")
    public String edita(@RequestParam("id") Integer id,Model model){
        model.addAttribute("id",id);
        Blog blog = blogService.SelectById(id);
        model.addAttribute("blog",blog);
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        return "admin/imput";
    }
    @GetMapping("/admin/blog/{id}/delete")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        Blog blog = blogService.SelectById(id);
        Type type = typeService.SelectByName(blog.getTypes());
        if(type!=null) {
            type.setNumber(type.getNumber() - 1);
            typeService.UpdateById(type);
        }

        blogService.DeleteById(id);
        redirectAttributes.addAttribute("message","删除成功");
        return "redirect:/admin/blog";

    }
    @PostMapping("/admin/blog/search")
    public String search(BlogQuery blogQuery,Model model){
        List<Blog> blogs = blogService.SelectByBlogQuery(blogQuery);
        model.addAttribute("blogs",blogs);
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        return "admin/blog :: blogList";
    }
}
