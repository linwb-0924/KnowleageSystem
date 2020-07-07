package com.example.login.bean;

/**
 * @author wb_Lin
 * @create 2020-07-05 11:02
 */
public class BlogQuery {
    private  String title;
    private  String types;

    public BlogQuery(String title, String types) {
        this.title = title;
        this.types = types;
    }

    public BlogQuery() {
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
}
