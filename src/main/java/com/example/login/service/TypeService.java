package com.example.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.login.bean.Type;
import com.example.login.dao.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-04 18:58
 */
@Service
public class TypeService {

    @Resource
    TypeMapper typeMapper;


    public List<Type> getAll(){
        return typeMapper.selectList(null);
    }
    public Type SelectByName(String name){
        QueryWrapper<Type> qw = new QueryWrapper<>();
        qw.eq("name",name);
        return typeMapper.selectOne(qw);
    }

    public  void SaveType(Type type){
        typeMapper.insert(type);
    }

    public void UpdateById(Type type){
        typeMapper.updateById(type);
    }

    public void DeleteById(Integer id){
        typeMapper.deleteById(id);
    }

    public Type SelectById(Integer id){

        return typeMapper.selectById(id);
    }

    public Page<Type> SelectByPage(Page<Type> page, QueryWrapper<Type> typeQueryWrapper){
        Page<Type> typePage = typeMapper.selectPage(page, typeQueryWrapper);
        return typePage;
    }

    public void UpdateByName(Type type){
        typeMapper.updateById(type);
    }




}
