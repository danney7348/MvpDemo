package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.GetOrders;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/21.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface GetOrdersView {
    void onGetOrdersSuccess(List<GetOrders.DataBean> data1);
    void onGetOrdersFailure(String msg);
    void onFailre();
}
