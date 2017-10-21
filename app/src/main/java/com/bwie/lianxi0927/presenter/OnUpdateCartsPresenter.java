package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.model.UpdateCartsModel;
import com.bwie.lianxi0927.view.UpdateCartsView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class OnUpdateCartsPresenter implements UpdateCartsModel.OnUpdateCarts {
    private UpdateCartsModel updateCartsModel;
    private Context context;
    private UpdateCartsView updateCartsView;

    public OnUpdateCartsPresenter(Context context, UpdateCartsView updateCartsView) {
        this.context = context;
        this.updateCartsView = updateCartsView;
        updateCartsModel = new UpdateCartsModel();
        updateCartsModel.setOnUpdateCarts(this);
    }

    public void requestUpdateCarts(int uid,String sellerid,int pid,int selected,int num){
        updateCartsModel.onUpdateCartsData(uid,sellerid,pid,selected,num);
    }
    @Override
    public void onUpdateCartsDataSuccess(String msg) {
        updateCartsView.onUpdateCartsDataSuccess(msg);
    }

    @Override
    public void onUpdateCartsDataFilure(String msg) {
        updateCartsView.onUpdateCartsDataFilure(msg);
    }

    @Override
    public void failure() {
        updateCartsView.failure();
    }
}
