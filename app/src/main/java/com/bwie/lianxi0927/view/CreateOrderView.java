package com.bwie.lianxi0927.view;

/**
 * 作者： 张少丹
 * 时间：  2017/10/21.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface CreateOrderView {
    void onCreateOrderSuccess(String msg);
    void onCreateOrderFailure(String msg);
    void onFailure();
}
