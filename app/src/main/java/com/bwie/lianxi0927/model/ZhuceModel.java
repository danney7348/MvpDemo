package com.bwie.lianxi0927.model;

import com.bwie.lianxi0927.bean.Users;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.LOGIN_API;
import static com.bwie.lianxi0927.common.Api.ZHUCE_API;

/**
 * 作者： 张少丹
 * 时间：  2017/9/27.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ZhuceModel {
    public void login(String mobile,String pwd){

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        FormBody body = builder.build();
        Request request = new Request.Builder().url(ZHUCE_API).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    String result = response.body().string();
                    System.out.println("result======="+result);
                    Gson gson = new Gson();
                    Users users = gson.fromJson(result, Users.class);
                    String code = users.getCode();
                    String msg = users.getMsg();
                    if(code != null){
                        if(code.equals("0")){
                            iLogin.zhuceSuccess(code,msg);
                        }else {
                            iLogin.zhuceFail(code,msg);
                        }
                    }else {
                        if(code.equals("0")){
                            iLogin.zhuceSuccess(code,msg);
                        }else {
                            iLogin.zhuceFail(null,msg);
                        }
                    }

                    System.out.println("result======="+result);
                }
            }
        });
    }
    private ILogin iLogin;

    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    public interface ILogin{
        void zhuceSuccess(String code, String msg);
        void zhuceFail(String code, String msg);
        void failure(Call call, IOException e);
    }
}
