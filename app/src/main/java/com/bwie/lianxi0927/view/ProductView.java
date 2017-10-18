package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.ProductData;
import com.bwie.lianxi0927.bean.Products;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/10/16.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface ProductView {
    void getProductSuccess(List<Products.DataBean> data1);
    void getProductFail(String msg);
    void Failure(Call call, IOException e);
}
