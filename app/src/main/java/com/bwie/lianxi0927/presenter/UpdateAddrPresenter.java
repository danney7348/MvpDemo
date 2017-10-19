package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.UpdateAddr;
import com.bwie.lianxi0927.model.UpdateAddrModel;
import com.bwie.lianxi0927.view.UpdateAddrView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/19.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UpdateAddrPresenter implements UpdateAddrModel.OnUpdateAddr {
    private UpdateAddrModel updateAddrModel;
    private Context context;
    private UpdateAddrView updateAddrView;

    public UpdateAddrPresenter(Context context, UpdateAddrView updateAddrView) {
        this.context = context;
        this.updateAddrView = updateAddrView;
        updateAddrModel = new UpdateAddrModel();
        updateAddrModel.setOnUpdateAddr(this);
    }

    public void resquestUpdateAddr(int uid,int addrid){
        updateAddrModel.OnUpdateAddrData(uid,addrid);
    }
    @Override
    public void onUpdateAddrSuccess(UpdateAddr.DataBean data1) {
        updateAddrView.onUpdateAddrSuccess(data1);
    }

    @Override
    public void onUpdateAddrFailure(String msg) {
        updateAddrView.onUpdateAddrFailure(msg);
    }

    @Override
    public void failure() {
        updateAddrView.failure();
    }
}
