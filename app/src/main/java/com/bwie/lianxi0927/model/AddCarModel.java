package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.AddCar;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class AddCarModel {
    public void getAddCarData(final int uid, final int pid){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(Api.ADDCAR_API + uid + "&&pid=" + pid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                System.out.println("uid+pid = " + uid+pid);
                System.out.println("data =&&&&&&&&&&&&&&&&&&& " + data);
                System.out.println("Api.ADDCAR_API + uid + \"&&pid=\" + pid = " + Api.ADDCAR_API + uid + "&&pid=" + pid);
                Gson gson = new Gson();
                AddCar addCar = gson.fromJson(data, AddCar.class);
                String code = addCar.getCode();
                String msg = addCar.getMsg();
                if (code.equals("0")){
                    onAddCarData.onAddCarDataSuccess(msg);
                }else if(code.equals("1")){
                    onAddCarData.onAddCarDataFilure(msg);
                }
            }
        });
    }
    private OnAddCarData onAddCarData;
    public void setOnAddCarData(OnAddCarData onAddCarData) {
        this.onAddCarData = onAddCarData;
    }
    public interface OnAddCarData{
        void onAddCarDataSuccess(String msg);
        void onAddCarDataFilure(String msg);
        void failure();
    }
}
