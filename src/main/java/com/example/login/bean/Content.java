package com.example.login.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author wb_Lin
 * @create 2020-07-05 15:09
 */
@TableName("content")
public class Content {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer blogid;
    private String name;
    private byte[] pic;
    private String content;
    private Date createtime;

    public Content() {
    }

    public Content(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Content(Integer blogid, String name, String content) {
        this.blogid = blogid;
        this.name = name;
        this.content = content;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Content(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }
}
