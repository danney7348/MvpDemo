package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.CreateOrder;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/21.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class CreateOrderModel {
    public void onCreateOrderData(int uid,double price){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.CREATEORDER_API + uid + "&&price=" + price, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                CreateOrder createOrder = gson.fromJson(data, CreateOrder.class);
                String code = createOrder.getCode();
                String msg = createOrder.getMsg();
                if(code.equals("0")){
                    onCreateOrder.onCreateOrderSuccess(msg);
                }else if(code.equals("1")){
                    onCreateOrder.onCreateOrderFailure(msg);
                }
            }
        });
    }
    private OnCreateOrder onCreateOrder;

    public void setOnCreateOrder(OnCreateOrder onCreateOrder) {
        this.onCreateOrder = onCreateOrder;
    }

    public interface OnCreateOrder{
        void onCreateOrderSuccess(String msg);
        void onCreateOrderFailure(String msg);
        void onFailure();
    }
}
