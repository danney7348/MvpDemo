package com.bwie.lianxi0927.presenter;

import android.text.TextUtils;

import com.bwie.lianxi0927.model.LoginModel;
import com.bwie.lianxi0927.model.ZhuceModel;
import com.bwie.lianxi0927.view.LoginView;
import com.bwie.lianxi0927.view.ZhuceView;

import java.io.IOException;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/9/27.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ZhucePresenter implements ZhuceModel.ILogin {

    private ZhuceModel zhuceModel;
    private ZhuceView zhuceView;

    public ZhucePresenter(ZhuceView zhuceView) {

       this.zhuceView = zhuceView;
        zhuceModel = new ZhuceModel();
        zhuceModel.setiLogin(this);
    }

    public void zhuce(String mobile, String pass) {

        if (TextUtils.isEmpty(mobile)) {
            zhuceView.nameError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            zhuceView.passError("密码不能为空");
            return;
        }

        zhuceModel.login(mobile, pass);
        zhuceView.showProgressbar();

    }


    @Override
    public void zhuceSuccess(String code, String msg) {

        zhuceView.zhuceSuccess(code, msg);
        zhuceView.hideProgressbar();

    }

    @Override
    public void zhuceFail(String code, String msg) {
        zhuceView.zhuceFail(code, msg);
        zhuceView.hideProgressbar();
    }

    @Override
    public void failure(Call call, IOException e) {

        zhuceView.failure(call, e);
        zhuceView.hideProgressbar();

    }
}
