package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.GetOrders;
import com.bwie.lianxi0927.model.GetOrdersModel;
import com.bwie.lianxi0927.view.GetOrdersView;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/21.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetOrdersPresenter implements GetOrdersModel.OnGetOrders {
    private GetOrdersModel getOrdersModel;
    private Context context;
    private GetOrdersView getOrdersView;

    public GetOrdersPresenter(Context context, GetOrdersView getOrdersView) {
        this.context = context;
        this.getOrdersView = getOrdersView;
        getOrdersModel = new GetOrdersModel();
        getOrdersModel.setOnGetOrders(this);
    }

    public void requestGetOrders(int uid,int page){
        getOrdersModel.onGetOrders(uid,page);
    }
    @Override
    public void onGetOrdersSuccess(List<GetOrders.DataBean> data1) {
        getOrdersView.onGetOrdersSuccess(data1);
    }

    @Override
    public void onGetOrdersFailure(String msg) {
        getOrdersView.onGetOrdersFailure(msg);
    }

    @Override
    public void onFailre() {
        getOrdersView.onFailre();
    }
}
