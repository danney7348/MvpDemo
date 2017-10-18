package com.bwie.lianxi0927.bean;

/**
 * 作者： 张少丹
 * 时间：  2017/9/29.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MiddleBean {
    private String img;
    private String name;

    public MiddleBean(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public MiddleBean() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
