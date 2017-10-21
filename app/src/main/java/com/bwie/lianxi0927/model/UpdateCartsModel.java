package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.UpdateCarts;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UpdateCartsModel {
    //uid=170&sellerid=15&pid=38&selected=1&num=10
    public void onUpdateCartsData(int uid,String sellerid,int pid,int selected,int num){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.UPDATECARTS_API + uid + "&&sellerid=" + sellerid + "&&pid=" + pid + "&&selected=" + selected + "&&num=" + num, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                System.out.println("data =datadatadata " + data);
                Gson gson = new Gson();
                UpdateCarts updateCarts = gson.fromJson(data, UpdateCarts.class);
                String code = updateCarts.getCode();
                String msg = updateCarts.getMsg();
                if(code!=null){
                    if (code.equals("0")){
                        onUpdateCarts.onUpdateCartsDataSuccess(msg);
                    }else if(code.equals("1")){
                        onUpdateCarts.onUpdateCartsDataFilure(msg);
                    }
                }

            }
        });
    }
    private OnUpdateCarts onUpdateCarts;

    public void setOnUpdateCarts(OnUpdateCarts onUpdateCarts) {
        this.onUpdateCarts = onUpdateCarts;
    }

    public interface OnUpdateCarts{
        void onUpdateCartsDataSuccess(String msg);
        void onUpdateCartsDataFilure(String msg);
        void failure();
    }
}
