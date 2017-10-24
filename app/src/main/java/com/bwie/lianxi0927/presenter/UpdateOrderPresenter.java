package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.UpdateOrderModel;
import com.bwie.lianxi0927.view.UpdateOrderView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/22.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UpdateOrderPresenter implements UpdateOrderModel.OnUpDateOrder {
    private UpdateOrderModel updateOrderModel;
    private Context context;
    private UpdateOrderView updateOrderView;

    public UpdateOrderPresenter(Context context, UpdateOrderView updateOrderView) {
        this.context = context;
        this.updateOrderView = updateOrderView;
        updateOrderModel = new UpdateOrderModel();
        updateOrderModel.setOnUpDateOrder(this);
    }

    public void requestUpdateOrder(int uid, int status, int orderId){
        updateOrderModel.onUpdateOrderData(uid,status,orderId);
    }
    @Override
    public void onUpdateOrderSuccess(String msg) {
        updateOrderView.onUpdateOrderSuccess(msg);
    }

    @Override
    public void onUpdateOrderFilure(String msg) {
        updateOrderView.onUpdateOrderFilure(msg);
    }

    @Override
    public void onFailure() {
        updateOrderView.onFailure();
    }
}
