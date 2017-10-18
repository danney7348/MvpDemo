package com.bwie.lianxi0927.view;

import com.bwie.lianxi0927.bean.PruductXiangqing;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface ProductXiangqingView {
    void onProductXiangqingSuccess(PruductXiangqing.DataBean data1 );
    void onProductXiangqingFailure(String msg);
    void Failure();
}
