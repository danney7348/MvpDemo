package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.SetAddr;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class SetAddrModel {

    //http://120.27.23.105/user/setAddr?uid=71&addrid=3&status=1
    public void onSetAddrData(int uid,int addrid,int status){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.SETADDR_API + uid + "&&addrid=" + addrid + "&&status=" + status, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                SetAddr setAddr = gson.fromJson(data, SetAddr.class);
                String code = setAddr.getCode();
                String msg = setAddr.getMsg();
                if (code.equals("0")){
                    onSetAddr.onSetAddrSuccess(msg);
                }else if(code.equals("1")){
                    onSetAddr.onSetAddrFailure(msg);
                }
            }
        });
    }
    private OnSetAddr onSetAddr;

    public void setOnSetAddr(OnSetAddr onSetAddr) {
        this.onSetAddr = onSetAddr;
    }

    public interface OnSetAddr{
        void onSetAddrSuccess(String msg);
        void onSetAddrFailure(String msg);
        void failure();
    }
}
