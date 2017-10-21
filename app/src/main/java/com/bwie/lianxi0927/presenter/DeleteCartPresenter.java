package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.adapter.MyGouwucheShopAdapter;
import com.bwie.lianxi0927.model.DeleteCartModel;
import com.bwie.lianxi0927.view.DeleteCartView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/20.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class DeleteCartPresenter implements DeleteCartModel.OnDeleteCart {
    private DeleteCartModel deleteCartModel;
    private Context context;
    private DeleteCartView deleteCartView;

    public DeleteCartPresenter(Context context, DeleteCartView deleteCartView) {
        this.context = context;
        this.deleteCartView = deleteCartView;
        deleteCartModel = new DeleteCartModel();
        deleteCartModel.setOnDeleteCart(this);
    }



    public void requestDeleteCart(int uid,int pid){
        deleteCartModel.onDeleteCartData(uid,pid);
    }
    @Override
    public void onDeleteCartDataSuccess(String msg) {
        deleteCartView.onDeleteCartDataSuccess(msg);
    }

    @Override
    public void onDeleteCartDataFilure(String msg) {
        deleteCartView.onDeleteCartDataFilure(msg);
    }

    @Override
    public void failure() {
        deleteCartView.failure();
    }
}
