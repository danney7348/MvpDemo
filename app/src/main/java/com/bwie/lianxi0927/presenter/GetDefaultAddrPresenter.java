package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.GetDEfaultAddr;
import com.bwie.lianxi0927.model.GetDefaultAddrModel;
import com.bwie.lianxi0927.view.GetDefaultAddrView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetDefaultAddrPresenter implements GetDefaultAddrModel.OnGetDefaultAddr {
    private GetDefaultAddrModel getDefaultAddrModel;
    private Context context;
    private GetDefaultAddrView getDefaultAddrView;

    public GetDefaultAddrPresenter(Context context, GetDefaultAddrView getDefaultAddrView) {
        this.context = context;
        this.getDefaultAddrView = getDefaultAddrView;
        getDefaultAddrModel = new GetDefaultAddrModel();
        getDefaultAddrModel.setOnGetDefaultAddr(this);
    }

    public void requrest(int uid){
        getDefaultAddrModel.onGetDefaultAddrData(uid);
    }
    @Override
    public void onGetDefaultAddrSuccess(GetDEfaultAddr.DataBean data1) {
        getDefaultAddrView.onGetDefaultAddrSuccess(data1);
    }

    @Override
    public void onGetDefaultAddrFailure(String msg) {
        getDefaultAddrView.onGetDefaultAddrFailure(msg);
    }

    @Override
    public void failure() {
        getDefaultAddrView.failure();
    }
}
