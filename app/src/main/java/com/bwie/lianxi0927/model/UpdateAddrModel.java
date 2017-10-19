package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.UpdateAddr;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/19.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UpdateAddrModel {
    //http://120.27.23.105/user/updateAddr?uid=71&addrid=2
    public void OnUpdateAddrData(int uid,int addrid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.UPDATEADDR_API + uid + "&&addrid=" + addrid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                UpdateAddr updateAddr = gson.fromJson(data, UpdateAddr.class);
                String code = updateAddr.getCode();
                String msg = updateAddr.getMsg();
                UpdateAddr.DataBean data1 = updateAddr.getData();
                if(code.equals("0")){
                    onUpdateAddr.onUpdateAddrSuccess(data1);
                }else if(code.equals("1")){
                    onUpdateAddr.onUpdateAddrFailure(msg);
                }
            }
        });
    }
    private OnUpdateAddr onUpdateAddr;

    public void setOnUpdateAddr(OnUpdateAddr onUpdateAddr) {
        this.onUpdateAddr = onUpdateAddr;
    }

    public interface OnUpdateAddr{
        void onUpdateAddrSuccess(UpdateAddr.DataBean data1);
        void onUpdateAddrFailure(String msg);
        void failure();
    }
}
