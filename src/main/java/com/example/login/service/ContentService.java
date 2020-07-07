package com.example.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.login.bean.Content;
import com.example.login.dao.ContentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-05 15:18
 */
@Service
public class ContentService {

    @Resource
    ContentMapper contentMapper;

    public List<Content> getAll(){
        return contentMapper.selectList(null);
    }
    public void Insert(Content content){
        contentMapper.insert(content);
    }
    public List<Content> SelectBy(Integer blogid){
        QueryWrapper<Content> qw = new QueryWrapper<>();
        qw.eq("blogid",blogid);
        return contentMapper.selectList(qw);
    }
    public Content SelectByID(Integer id){
        return contentMapper.selectById(id);
    }
}
