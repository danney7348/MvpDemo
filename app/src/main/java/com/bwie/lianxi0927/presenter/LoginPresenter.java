package com.bwie.lianxi0927.presenter;

import android.text.TextUtils;

import com.bwie.lianxi0927.model.LoginModel;
import com.bwie.lianxi0927.view.LoginView;

import java.io.IOException;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/9/27.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class LoginPresenter implements LoginModel.ILogin {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {

        this.loginView = loginView;
        loginModel = new LoginModel();
        loginModel.setiLogin(this);
    }

    public void login(String mobile, String pass) {

        if (TextUtils.isEmpty(mobile)) {
            loginView.nameError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            loginView.passError("密码不能为空");
            return;
        }

        loginModel.login(mobile, pass);
        loginView.showProgressbar();

    }


    @Override
    public void loginSuccess(String code, String msg) {

        loginView.loginSuccess(code, msg);
        loginView.hideProgressbar();

    }

    @Override
    public void loginFail(String code, String msg) {
        loginView.loginFail(code, msg);
        loginView.hideProgressbar();
    }

    @Override
    public void failure(Call call, IOException e) {

        loginView.failure(call, e);
        loginView.hideProgressbar();

    }
}
