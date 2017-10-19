package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.GetOrders;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/19.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetOrdersModel {
    //http://120.27.23.105/product/getOrders?uid=71?page=1
    public void onGetOrders(int uid,int page){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.GETORDERS_API + uid + "&&page=" + page, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                gson.fromJson(data,GetOrders.class);
            }
        });
    }
}
