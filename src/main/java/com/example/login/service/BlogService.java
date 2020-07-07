package com.example.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.login.bean.Blog;
import com.example.login.bean.BlogQuery;
import com.example.login.bean.Type;
import com.example.login.dao.BlogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-05 9:29
 */
@Service
public class BlogService {

    @Resource
    BlogMapper blogMapper;

    public List<Blog> getAll(){
        return blogMapper.selectList(null);
    }

    public void InsertByBlog(Blog blog){
        blogMapper.insert(blog);
    }
    public Blog SelectByname(String title){
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("title",title);
        Blog blog = blogMapper.selectOne(qw);
        return blog;
    }
    public Blog SelectById(Integer id){
        return blogMapper.selectById(id);
    }
    public void DeleteById(Integer id){
        blogMapper.deleteById(id);
    }
    public void UpdateByBlog(Blog blog){
        blogMapper.updateById(blog);
    }
    public Page<Blog> SelectByPage(Page<Blog> page , QueryWrapper<Blog> blogQueryWrapper){
        Page<Blog> blogPage = blogMapper.selectPage(page, blogQueryWrapper);
        return blogPage;
    }
    public List<Blog> SelectByBlogQuery(BlogQuery blogQuery){
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        if(blogQuery.getTitle()!=null && !"".equals(blogQuery.getTitle())){
            qw.like("title",blogQuery.getTitle());
        }
        if(blogQuery.getTypes()!=null && !"".equals(blogQuery.getTypes())){
            qw.eq("types",blogQuery.getTypes());
        }
        return blogMapper.selectList(qw);
    }
    public List<Blog> SelectByType(String name){
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("types",name);
        return blogMapper.selectList(qw);
    }
    public Blog Selectname(String name){
        QueryWrapper<Blog> qw = new QueryWrapper<>();
        qw.eq("name",name);
        return blogMapper.selectOne(null);
    }
}
