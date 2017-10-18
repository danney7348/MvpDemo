package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.ProductData;
import com.bwie.lianxi0927.bean.Products;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/10/16.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductDetailModel {
    private OnProduct onProduct;

    public void setOnProduct(OnProduct onProduct) {
        this.onProduct = onProduct;
    }

    public void getProductData(int pid,int page){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(Api.PRODUCTSL_API+pid+"&&page="+page, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                Products products = gson.fromJson(data, Products.class);
                List<Products.DataBean> data1 = products.getData();
                if (products.getCode().equals("0")){
                    onProduct.getProductSuccess(data1);
                }else if(products.getCode().equals("1")){
                    onProduct.getProductFail(products.getMsg());
                }
            }
        });
    }
    public interface OnProduct{
        void getProductSuccess(List<Products.DataBean> data1);
        void getProductFail(String msg);
        void Failure(Call call, IOException e);
    }
}
