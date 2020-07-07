package com.example.login.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.login.bean.Admin;
import com.example.login.bean.Blog;
import com.example.login.bean.Type;
import com.example.login.service.BlogService;
import com.example.login.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-04 18:59
 */
@Controller
public class TypeControler {

    @Resource
    BlogService blogService;

    @Resource
    TypeService typeService;

    @GetMapping("/admin/type")
    public String type(@RequestParam(value = "pageindex",defaultValue = "1") String pageindex, Model model, HttpServletRequest request){
        String message = request.getParameter("message");
        if(message!=null){
            model.addAttribute("message",message);
        }
        Page<Type> page = new Page<>(Integer.valueOf(pageindex),3);
        QueryWrapper<Type> qw=new QueryWrapper<>();
        qw.orderByDesc("number");
        Page<Type> typePage = typeService.SelectByPage(page, qw);
        model.addAttribute("pageindex",Integer.valueOf(pageindex));
        if(page.hasNext()){
            model.addAttribute("flag1",1);
        }
        if(page.hasPrevious()){
            model.addAttribute("flag2",1);
        }
        model.addAttribute("types",typePage.getRecords());
        return "admin/type";
    }

    @RequestMapping("/admin/types/input")
    public String typeinput(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @PostMapping("/admin/type")
    public String type1(@RequestParam("name") String name, RedirectAttributes redirectAttributes){
        Type type = new Type(name);
        Type type1 = typeService.SelectByName(name);
        if(type1!=null){
            redirectAttributes.addAttribute("message","已存在该分类");
            return "redirect:/admin/type";
        }
        typeService.SaveType(type);
        redirectAttributes.addAttribute("message","新增成功");
        return "redirect:/admin/type";
    }

    @GetMapping("/admin/types/{id}/input")
    public String editaa(@PathVariable("id") Integer id,Model model){
        Type type = typeService.SelectById(id);
        model.addAttribute("type",type);
        return "admin/type-input";
    }

    @PostMapping("/admin/type/{id}")
    public String type11(@RequestParam("name") String name, @PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        Type type = new Type(id,name);
        Type type1 = typeService.SelectByName(name);
        Type type2 = typeService.SelectById(id);
        if(type1!=null && !name.equals(type2.getName())){
            redirectAttributes.addAttribute("message","已存在该分类");
            return "redirect:/admin/type";
        }

        List<Blog> blogs = blogService.SelectByType(type2.getName());
        if(blogs!=null){
            for(Blog blog : blogs){
                blog.setTypes(name);
                blogService.UpdateByBlog(blog);
            }}

        typeService.UpdateById(type);

        redirectAttributes.addAttribute("message","修改成功");
        return "redirect:/admin/type";
    }

    @GetMapping("/admin/types/{id}/delete")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        Type type = typeService.SelectById(id);
        List<Blog> blogs = blogService.SelectByType(type.getName());
        if(blogs!=null){
            for(Blog blog : blogs){
                blog.setTypes("");
                System.out.println("修改成功-----------------------------------------------");
                blogService.UpdateByBlog(blog);
            }
        }
        typeService.DeleteById(id);
        redirectAttributes.addAttribute("message","删除成功");
        return "redirect:/admin/type";
    }

    @GetMapping("/type")
    public String type(@RequestParam(value = "pageindex",defaultValue = "1") String pageindex,Model model){
        QueryWrapper<Blog> wa=null;
        Page<Blog> page = new Page<>(Integer.valueOf(pageindex),3);
        Page<Blog> blogPage = blogService.SelectByPage(page, wa);
        model.addAttribute("pageindex",Integer.valueOf(pageindex));
        if(page.hasNext()){
            model.addAttribute("flag2","1");
        }
        if(page.hasPrevious()){
            model.addAttribute("flag1","1");
        }
        List<Type> all = typeService.getAll();
        model.addAttribute("blogs",blogPage.getRecords());
        model.addAttribute("types",all);
        model.addAttribute("total",all.size());
        return "type";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "pageindex",defaultValue = "1") String pageindex, Model model){

        QueryWrapper<Blog> wa=null;
        Page<Blog> page = new Page<>(Integer.valueOf(pageindex),3);
        Page<Blog> blogPage = blogService.SelectByPage(page, wa);
        model.addAttribute("pageindex",Integer.valueOf(pageindex));
        if(page.hasNext()){
            model.addAttribute("flag2","1");
        }
        if(page.hasPrevious()){
            model.addAttribute("flag1","1");
        }
        List<Blog> all = blogService.getAll();
        model.addAttribute("total",all.size());
        model.addAttribute("blogs",blogPage.getRecords());
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        return "a-index";
    }

    @GetMapping("/type/one")
    public String typeone(@RequestParam("name") String name, Model model){
        List<Blog> blogs = blogService.SelectByType(name);
        model.addAttribute("blogs",blogs);
        List<Type> all1 = typeService.getAll();
        model.addAttribute("types",all1);
        return "type";
    }

}
