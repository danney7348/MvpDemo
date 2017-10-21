package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.DeleteCart;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/20.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class DeleteCartModel {
    public void onDeleteCartData(int uid,int pid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.DELETECART_API + uid + "&&pid=" + pid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                DeleteCart deleteCart = gson.fromJson(data, DeleteCart.class);
                String code = deleteCart.getCode();
                String msg = deleteCart.getMsg();
                if(code.equals("0")){
                    onDeleteCart.onDeleteCartDataSuccess(msg);
                }else {
                    onDeleteCart.onDeleteCartDataFilure(msg);
                }
            }
        });
    }
    private OnDeleteCart onDeleteCart;

    public void setOnDeleteCart(OnDeleteCart onDeleteCart) {
        this.onDeleteCart = onDeleteCart;
    }

    public interface OnDeleteCart{
        void onDeleteCartDataSuccess(String msg);
        void onDeleteCartDataFilure(String msg);
        void failure();
    }
}
