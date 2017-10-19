package com.bwie.lianxi0927.presenter;

import android.content.Context;

import com.bwie.lianxi0927.bean.GetAddrs;
import com.bwie.lianxi0927.model.GetAddrsModel;
import com.bwie.lianxi0927.view.GetAddrsView;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetAddrsPresenter implements GetAddrsModel.OnGetAddrs {
    private GetAddrsModel getAddrsModel;
    private Context context;
    private GetAddrsView getAddrsView;

    public GetAddrsPresenter(Context context, GetAddrsView getAddrsView) {
        this.context = context;
        this.getAddrsView = getAddrsView;
        getAddrsModel = new GetAddrsModel();
        getAddrsModel.setOnGetAddrs(this);
    }

    public void resquestGetAddrs(int uid){
        getAddrsModel.onGetAddrsData(uid);
    }
    @Override
    public void onGetAddrsSuccess(List<GetAddrs.DataBean> data1) {
        getAddrsView.onGetAddrsSuccess(data1);
    }

    @Override
    public void onGetAddrsFailure(String msg) {
        getAddrsView.onGetAddrsFailure(msg);
    }

    @Override
    public void failure() {
        getAddrsView.failure();
    }
}
