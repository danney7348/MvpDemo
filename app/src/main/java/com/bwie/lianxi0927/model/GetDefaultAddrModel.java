package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.GetDEfaultAddr;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetDefaultAddrModel {
    public void onGetDefaultAddrData(int uid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.GETDEFAULTADDR_API + uid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                GetDEfaultAddr getDEfaultAddr = gson.fromJson(data, GetDEfaultAddr.class);
                String code = getDEfaultAddr.getCode();
                String msg = getDEfaultAddr.getMsg();
                GetDEfaultAddr.DataBean data1 = getDEfaultAddr.getData();
                if(code.equals("0")){
                    onGetDefaultAddr.onGetDefaultAddrSuccess(data1);
                }else if(code.equals("1")){
                    onGetDefaultAddr.onGetDefaultAddrFailure(msg);
                }
            }
        });
    }
    private OnGetDefaultAddr onGetDefaultAddr;

    public void setOnGetDefaultAddr(OnGetDefaultAddr onGetDefaultAddr) {
        this.onGetDefaultAddr = onGetDefaultAddr;
    }

    public interface OnGetDefaultAddr{
        void onGetDefaultAddrSuccess(GetDEfaultAddr.DataBean data1);
        void onGetDefaultAddrFailure(String msg);
        void failure();
    }
}
