package com.example.login.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("uuser")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String uname;
    private String upass;
    private String email;
    private String sex;
    private String born;
    private String address;
    private String profession;
    private String udescribe;
    private String website;
    private String headpic;
    private Integer ulevel;
    private String realname;

    public User() {
    }

    public User(Integer id, String uname, String upass, String email, String sex, String born, String address, String profession, String udescribe, String website, String headpic, Integer ulevel, String realname) {
        this.id = id;
        this.uname = uname;
        this.upass = upass;
        this.email = email;
        this.sex = sex;
        this.born = born;
        this.address = address;
        this.profession = profession;
        this.udescribe = udescribe;
        this.website = website;
        this.headpic = headpic;
        this.ulevel = ulevel;
        this.realname = realname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUdescribe() {
        return udescribe;
    }

    public void setUdescribe(String udescribe) {
        this.udescribe = udescribe;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public Integer getUlevel() {
        return ulevel;
    }

    public void setUlevel(Integer ulevel) {
        this.ulevel = ulevel;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", born=" + born +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", udescribe='" + udescribe + '\'' +
                ", website='" + website + '\'' +
                ", headpic='" + headpic + '\'' +
                ", ulevel=" + ulevel +
                ", realname='" + realname + '\'' +
                '}';
    }
}
