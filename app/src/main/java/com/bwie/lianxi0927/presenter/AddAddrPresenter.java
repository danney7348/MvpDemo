package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.AddAddrModel;
import com.bwie.lianxi0927.view.AddCarView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class AddAddrPresenter implements AddAddrModel.OnAddAddr {
    private AddAddrModel addAddrModel;
    private Context context;
    private AddCarView addCarView;

    public AddAddrPresenter(Context context, AddCarView addCarView) {
        this.context = context;
        this.addCarView = addCarView;
        addAddrModel = new AddAddrModel();
        addAddrModel.setOnAddAddr(this);
    }

    public void resquestAddAddrData(int uid,String addr,String mobile,String name){
        addAddrModel.onAddAddr(uid,addr,mobile,name);
    }
    @Override
    public void onAddAddrSussess(String msg) {
        addCarView.onAddCarDataSuccess(msg);
    }

    @Override
    public void onAddAddrFilure(String msg) {
        addCarView.onAddCarDataFilure(msg);
    }

    @Override
    public void failure() {
        addCarView.failure();
    }
}
