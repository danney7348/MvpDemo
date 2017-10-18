package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.Product;
import com.bwie.lianxi0927.bean.ProductData;
import com.bwie.lianxi0927.bean.Products;
import com.bwie.lianxi0927.model.ProductDetailModel;
import com.bwie.lianxi0927.view.ProductView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/10/16.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductPresenter implements ProductDetailModel.OnProduct {
    private ProductDetailModel productDetailModel;
    private ProductView productView;
    private Context context;

    public ProductPresenter(ProductView productView, Context context) {
        this.productView = productView;
        this.context = context;
        productDetailModel = new ProductDetailModel();
        productDetailModel.setOnProduct(this);
    }

    public void requestProduct(int pid,int page){
        productDetailModel.getProductData(pid,page);
    }
    @Override
    public void getProductSuccess(List<Products.DataBean> data1) {
        productView.getProductSuccess(data1);
    }
    @Override
    public void getProductFail(String msg) {
        productView.getProductFail(msg);
    }

    @Override
    public void Failure(Call call, IOException e) {
        productView.Failure(call,e);
    }
}
