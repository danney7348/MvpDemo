package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.SetAddrModel;
import com.bwie.lianxi0927.view.SetAddrView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class SetAddrPresenter implements SetAddrModel.OnSetAddr {
    private SetAddrModel setAddrModel;
    private Context context;
    private SetAddrView setAddrView;

    public SetAddrPresenter(Context context, SetAddrView setAddrView) {
        this.context = context;
        this.setAddrView = setAddrView;
        setAddrModel = new SetAddrModel();
        setAddrModel.setOnSetAddr(this);
    }

    public void requestSetAddr(int uid,int addrid,int status){
        setAddrModel.onSetAddrData(uid,addrid,status);
    }
    @Override
    public void onSetAddrSuccess(String msg) {
        setAddrView.onSetAddrSuccess(msg);
    }

    @Override
    public void onSetAddrFailure(String msg) {
        setAddrView.onSetAddrFailure(msg);
    }

    @Override
    public void failure() {
        setAddrView.failure();
    }
}
