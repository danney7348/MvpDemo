package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.UpdateAddr;

/**
 * 作者： 张少丹
 * 时间：  2017/10/19.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface UpdateAddrView {
    void onUpdateAddrSuccess(UpdateAddr.DataBean data1);
    void onUpdateAddrFailure(String msg);
    void failure();
}
