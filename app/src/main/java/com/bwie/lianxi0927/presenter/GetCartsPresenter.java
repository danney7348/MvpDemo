package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.model.GetCartsModel;
import com.bwie.lianxi0927.view.GetCartsView;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetCartsPresenter implements GetCartsModel.GetCarts {
    private GetCartsModel getCartsModel;
    private Context context;
    private GetCartsView getCartsView;

    public GetCartsPresenter(Context context, GetCartsView getCartsView) {
        this.context = context;
        this.getCartsView = getCartsView;
        getCartsModel = new GetCartsModel();
        getCartsModel.setGetCarts(this);
    }

    public void requestCarts(int uid){
        getCartsModel.getCartsData(uid);
    }
    @Override
    public void getCartsDataSussess(List<GetCartsData.DataBean> data1) {
        getCartsView.getCartsDataSussess(data1);
    }

    @Override
    public void getCartsDataFailure(String msg) {
        getCartsView.getCartsDataFailure(msg);
    }

    @Override
    public void failure() {
        getCartsView.failure();
    }
}
