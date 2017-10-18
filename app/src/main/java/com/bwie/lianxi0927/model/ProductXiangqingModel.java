package com.bwie.lianxi0927.model;

import android.content.Context;

import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductXiangqingModel {
    private OnProductXiangqing onProductXiangqing;

    public void setOnProductXiangqing(OnProductXiangqing onProductXiangqing) {
        this.onProductXiangqing = onProductXiangqing;
    }
    public void getProductXiangqing(final int pid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.PRODUCTDETAIL_API + pid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                System.out.println("data dadadadadada= " + data);
                System.out.println("Api.PRODUCTDETAIL_API + pid = " + Api.PRODUCTDETAIL_API + pid);
                Gson gson = new Gson();
                PruductXiangqing pruductXiangqing = gson.fromJson(data, PruductXiangqing.class);
                String code = pruductXiangqing.getCode();
                String msg = pruductXiangqing.getMsg();
                PruductXiangqing.DataBean data1 = pruductXiangqing.getData();
                if(code.equals("0")){
                    onProductXiangqing.onProductXiangqingSuccess(data1);
                }else if(code.equals("1")){
                    onProductXiangqing.onProductXiangqingFailure(msg);
                }
            }
        });
    }
    public interface OnProductXiangqing{
        void onProductXiangqingSuccess(PruductXiangqing.DataBean data1 );
        void onProductXiangqingFailure(String msg);
        void Failure();
    }
}
