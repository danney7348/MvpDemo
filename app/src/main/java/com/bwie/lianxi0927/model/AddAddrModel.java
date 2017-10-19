package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.AddAddr;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class AddAddrModel {
    //http://120.27.23.105/user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
    public void onAddAddr(int uid,String addr,String mobile,String name){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.ADDADDR_API + uid + "&&addr=" + addr + "&&mobile=" + mobile + "&&name=" + name, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                System.out.println("data = " + data);
                Gson gson = new Gson();
                AddAddr addAddr = gson.fromJson(data, AddAddr.class);
                String code = addAddr.getCode();
                String msg = addAddr.getMsg();
                if(code.equals("0")){
                    onAddAddr.onAddAddrSussess(msg);
                }else if(code.equals("1")){
                    onAddAddr.onAddAddrFilure(msg);
                }
            }
        });
    }
    private OnAddAddr onAddAddr;

    public void setOnAddAddr(OnAddAddr onAddAddr) {
        this.onAddAddr = onAddAddr;
    }

    public interface OnAddAddr{
        void onAddAddrSussess(String msg);
        void onAddAddrFilure(String msg);
        void failure();
    }
}
