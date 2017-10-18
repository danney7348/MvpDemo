package com.example.kson.tablayout.widget.bean;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2017/08/20
 * Description:
 */
public class CategoryBean {
    public int id;
    public String name;

    public CategoryBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
