package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetCartsModel {

    public void getCartsData(int uid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.GETCARTS_API + uid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                GetCartsData getCartsData = gson.fromJson(data, GetCartsData.class);
                String code = getCartsData.getCode();
                List<GetCartsData.DataBean> data1 = getCartsData.getData();

                String msg = getCartsData.getMsg();
                if(code.equals("0")){
                    getCarts.getCartsDataSussess(data1);
                }else if(code.equals("1")){
                    getCarts.getCartsDataFailure(msg);
                }
            }
        });
    }
    private GetCarts getCarts;

    public void setGetCarts(GetCarts getCarts) {
        this.getCarts = getCarts;
    }

    public interface GetCarts{
        void getCartsDataSussess( List<GetCartsData.DataBean> data1);
        void getCartsDataFailure(String msg);
        void failure();
    }
}
