package com.example.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.login.bean.User;
import com.example.login.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService{
    @Resource
    UserMapper userMapper;

    public List findAll() {
        List<User> users = userMapper.selectList(null);
        return users;
    }


    public User findByName(String uname) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("uname",uname);
        return userMapper.selectOne(qw);
    }


    public User findById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }


    public int deleteById(Integer id) {
        int data = userMapper.deleteById(id);
        return data;
    }


    public void addUser(User user) {
        userMapper.insert(user);
    }


    public int updateUser(User user) {
        int data = userMapper.updateById(user);
        return data;
    }


}
