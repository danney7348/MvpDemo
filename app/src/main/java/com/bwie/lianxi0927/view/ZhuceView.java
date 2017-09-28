package com.bwie.lianxi0927.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/9/27.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface ZhuceView {

    void showProgressbar();

    void hideProgressbar();

    void nameError(String msg);

    void passError(String msg);

    void zhuceSuccess(String code, String msg);

    void zhuceFail(String code, String msg);

    void failure(Call call, IOException e);
}
