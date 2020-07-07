package com.example.login.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.login.handler.AdminMetaObjectHandler;

import java.util.Arrays;
import java.util.Date;

/**
 * @author wb_Lin
 * @create 2020-07-04 10:53
 */
@TableName("admin")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String aname;
    private String apass;
    private byte[] pic;
    private String telephone;
    private String email;
    private String role;
    @TableField(fill = FieldFill.INSERT)
    private Date begintime;
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    public Admin(String aname, String apass) {
        this.aname = aname;
        this.apass = apass;
    }

    public Admin() {
    }

    public Admin(Integer id, String aname, String apass, String telephone, String email, String role, Date begintime, Integer status) {
        this.id = id;
        this.aname = aname;
        this.apass = apass;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.begintime = begintime;
        this.status = status;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApass() {
        return apass;
    }

    public void setApass(String apass) {
        this.apass = apass;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                ", apass='" + apass + '\'' +
                ", pic=" + Arrays.toString(pic) +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", begintime=" + begintime +
                ", status=" + status +
                '}';
    }
}
