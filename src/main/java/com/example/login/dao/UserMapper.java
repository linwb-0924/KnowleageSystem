package com.example.login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.login.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-01 13:44
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User SelectByUser(User user);
    void InsertByUser(User user);
    List<User> SelectAll();
    void DelectByUser(User user);
    void InsertByUserr(User user);
    User SelectById(Integer id);
}
