package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.UpdateOrder;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/22.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UpdateOrderModel {
    //http://120.27.23.105/product/updateOrder?uid=71&status=1&orderId=1
    public void onUpdateOrderData(int uid, int status, int orderId){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.UPDATEORDER + uid + "&&status=" + status + "&&orderId=" + orderId, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                UpdateOrder updateOrder = gson.fromJson(data, UpdateOrder.class);
                String code = updateOrder.getCode();
                String msg = updateOrder.getMsg();
                if(code.equals("0")){
                    onUpDateOrder.onUpdateOrderSuccess(msg);
                }else if(code.equals(1)){
                    onUpDateOrder.onUpdateOrderFilure(msg);
                }
            }
        });
    }
    private OnUpDateOrder onUpDateOrder;

    public void setOnUpDateOrder(OnUpDateOrder onUpDateOrder) {
        this.onUpDateOrder = onUpDateOrder;
    }

    public interface OnUpDateOrder{
        void onUpdateOrderSuccess(String msg);
        void onUpdateOrderFilure(String msg);
        void onFailure();
    }
}
