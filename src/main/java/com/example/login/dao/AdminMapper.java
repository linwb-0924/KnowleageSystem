package com.example.login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.login.bean.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wb_Lin
 * @create 2020-07-04 11:00
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    public void UpdateById1(Integer id);
    public void UpdateById2(Integer id);
}
