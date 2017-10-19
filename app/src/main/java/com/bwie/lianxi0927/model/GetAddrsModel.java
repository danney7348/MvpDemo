package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.GetAddrs;
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

public class GetAddrsModel {
    public void onGetAddrsData(int uid){
        HttpUtils utils = new HttpUtils();
        utils.getJson(Api.GETADDRS_API + uid, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                GetAddrs getAddrs = gson.fromJson(data, GetAddrs.class);
                List<GetAddrs.DataBean> data1 = getAddrs.getData();
                String code = getAddrs.getCode();
                String msg = getAddrs.getMsg();
                if(code.equals("0")){
                    onGetAddrs.onGetAddrsSuccess(data1);
                }else if(code.equals("1")){
                    onGetAddrs.onGetAddrsFailure(msg);
                }
            }
        });
    }

    private OnGetAddrs onGetAddrs;

    public void setOnGetAddrs(OnGetAddrs onGetAddrs) {
        this.onGetAddrs = onGetAddrs;
    }

    public interface OnGetAddrs{
        void onGetAddrsSuccess(List<GetAddrs.DataBean> data1);
        void onGetAddrsFailure(String msg);
        void failure();
    }
}
