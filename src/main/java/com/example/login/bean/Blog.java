package com.example.login.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

/**
 * @author wb_Lin
 * @create 2020-07-05 9:22
 */
@TableName("blog")
public class Blog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String title;
    private String types;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
    private String content;
    private String firstpic;
    private Integer views;
    private String flag;

    public Blog() {
    }

    public Blog(String name, String title, String types, String content, String firstpic) {
        this.name = name;
        this.title = title;
        this.types = types;
        this.content = content;
        this.firstpic = firstpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstpic() {
        return firstpic;
    }

    public void setFirstpic(String firstpic) {
        this.firstpic = firstpic;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
