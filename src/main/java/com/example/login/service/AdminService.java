package com.example.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.login.bean.Admin;
import com.example.login.dao.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * @author wb_Lin
 * @create 2020-07-04 11:01
 */
@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    public void InsertByAdmin(Admin admin){
        adminMapper.insert(admin);
    }
    public List<Admin>  getALL(){
        return adminMapper.selectList(null);
    }
    public Admin SelectByadmin(Admin admin){
        QueryWrapper<Admin> wa = new QueryWrapper<>();
        wa.eq("aname",admin.getAname());
        wa.eq("apass",admin.getApass());
        List<Admin> admins = adminMapper.selectList(wa);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(0);
    }
    public Admin SelectById(Integer id){
        Admin admin = adminMapper.selectById(id);
        return admin;
    }
    public IPage<Admin> SelectByPage(IPage<Admin> page,QueryWrapper<Admin> wq){
        IPage<Admin> adminIPage = adminMapper.selectPage(page, wq);
        return adminIPage;

    }
    public Integer  getALLCount(){
        List<Admin> admins = adminMapper.selectList(null);
        return admins.size();
    }
    public void UpdateById1(Integer id){
        adminMapper.UpdateById1(id);
    }
    public void UpdateById2(Integer id){
        adminMapper.UpdateById2(id);
    }
    public void DeleteById(Integer id){
            adminMapper.deleteById(id);
    }

    public void UpdateByAdmin(Admin admin){
        adminMapper.updateById(admin);
    }
    public Admin getAdminByname(String name){
        QueryWrapper<Admin> qw  = new QueryWrapper<>();
        qw.eq("aname",name);
        return adminMapper.selectOne(qw);
    }
}
