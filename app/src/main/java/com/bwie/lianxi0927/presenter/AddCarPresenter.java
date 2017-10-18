package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.AddCarModel;
import com.bwie.lianxi0927.view.AddCarView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class AddCarPresenter implements AddCarModel.OnAddCarData {
    private AddCarModel addCarModel;
    private Context context;
    private AddCarView addCarView;
    public AddCarPresenter(Context context, AddCarView addCarView) {
        this.context = context;
        this.addCarView = addCarView;
        addCarModel = new AddCarModel();
        addCarModel.setOnAddCarData(this);
    }

    public void requestAddCar(int uid,int pid){
        addCarModel.getAddCarData(uid,pid);
    }
    @Override
    public void onAddCarDataSuccess(String msg) {
        addCarView.onAddCarDataSuccess(msg);
    }

    @Override
    public void onAddCarDataFilure(String msg) {

        addCarView.onAddCarDataFilure(msg);
    }

    @Override
    public void failure() {

        addCarView.failure();
    }
}
