package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.GetDEfaultAddr;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface GetDefaultAddrView {
    void onGetDefaultAddrSuccess(GetDEfaultAddr.DataBean data1);
    void onGetDefaultAddrFailure(String msg);
    void failure();
}
