package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.model.ProductXiangqingModel;
import com.bwie.lianxi0927.view.ProductXiangqingView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductXiangqingPresenter implements ProductXiangqingModel.OnProductXiangqing {
    private ProductXiangqingModel productXiangqingModel;
    private ProductXiangqingView productXiangqingView;
    private Context context;

    public ProductXiangqingPresenter(ProductXiangqingView productXiangqingView, Context context) {
        this.productXiangqingView = productXiangqingView;
        this.context = context;
        productXiangqingModel = new ProductXiangqingModel();
        productXiangqingModel.setOnProductXiangqing(this);
    }

    public void requestProductXiangqing(int pid){
        productXiangqingModel.getProductXiangqing(pid);
    };
    @Override
    public void onProductXiangqingSuccess(PruductXiangqing.DataBean data1) {
        productXiangqingView.onProductXiangqingSuccess(data1);
    }

    @Override
    public void onProductXiangqingFailure(String msg) {

        productXiangqingView.onProductXiangqingFailure(msg);
    }

    @Override
    public void Failure() {

        productXiangqingView.Failure();
    }
}
