package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.GetCartsData;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface GetCartsView {
    void getCartsDataSussess( List<GetCartsData.DataBean> data1);
    void getCartsDataFailure(String msg);
    void failure();
}
