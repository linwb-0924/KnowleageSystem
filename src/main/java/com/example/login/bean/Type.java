package com.example.login.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author wb_Lin
 * @create 2020-07-04 18:52
 */
@TableName("type")
public class Type {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer number;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(Integer id) {
        this.id = id;
    }

    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
