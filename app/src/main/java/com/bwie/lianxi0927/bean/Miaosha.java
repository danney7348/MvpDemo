package com.bwie.lianxi0927.bean;

/**
 * 作者： 张少丹
 * 时间：  2017/10/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class Miaosha {
    private String img;
    private String name;
    private String price;

    public Miaosha(String img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public Miaosha() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
