package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.CreateOrderModel;
import com.bwie.lianxi0927.view.CreateOrderView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/21.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class CreateOrderPresenter implements CreateOrderModel.OnCreateOrder {
    private CreateOrderModel createOrderModel;
    private Context context;
    private CreateOrderView createOrderView;

    public CreateOrderPresenter(Context context, CreateOrderView createOrderView) {
        this.context = context;
        this.createOrderView = createOrderView;
        createOrderModel = new CreateOrderModel();
        createOrderModel.setOnCreateOrder(this);
    }

    public void requestCreateOrder(int uid,double price){
        createOrderModel.onCreateOrderData(uid,price);
    }
    @Override
    public void onCreateOrderSuccess(String msg) {
        createOrderView.onCreateOrderSuccess(msg);
    }

    @Override
    public void onCreateOrderFailure(String msg) {
        createOrderView.onCreateOrderFailure(msg);
    }

    @Override
    public void onFailure() {
        createOrderView.onFailure();
    }
}
