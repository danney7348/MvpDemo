package com.bwie.lianxi0927.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.lianxi0927.MainActivity;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.ZhanghuShezhiActivity;
import com.bwie.lianxi0927.bean.UserLogin;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.bwie.lianxi0927.common.Api.USERINFO_API;

/**
 * 作者： 张少丹
 * 时间：  2017/9/29.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class WodeFragment extends Fragment {

    private View view;
    private SharedPreferences sp;
    private ImageView iv;
    private TextView tv_login_zhuce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.wode_item,null);
        sp = getActivity().getSharedPreferences("con", MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout login = view.findViewById(R.id.ll_login);
        iv = view.findViewById(R.id.imageView);
        tv_login_zhuce = view.findViewById(R.id.tv_login_zhuce);
        int anInt = getActivity().getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 0);
        if(anInt!=0){

            OkHttpClient client = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            FormBody build = builder.build();
            final int uid = getActivity().getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 0);
            Request request = new Request.Builder().url(USERINFO_API+"?uid="+uid).post(build).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response!=null&&response.isSuccessful()){
                        final String result;
                        try {
                            result = response.body().string();
                            System.out.println("result======="+result);
                            Gson gson = new Gson();
                            UserLogin userLogin = gson.fromJson(result, UserLogin.class);
                            final UserLogin.DataBean data = userLogin.getData();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String icon = data.getIcon();
                                    String nickname = data.getNickname()+"";
                                    Glide.with(getContext()).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
                                    tv_login_zhuce.setText(nickname);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

        }else if(anInt==0){
            iv.setImageResource(R.mipmap.ic_launcher);
            tv_login_zhuce.setText("登陆/注册");
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLogin = sp.getBoolean("isLogin", false);
                if(isLogin == true){

                    Intent intent = new Intent(getContext(), ZhanghuShezhiActivity.class);
                    getActivity().startActivity(intent);
                }else {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                }
            }
        });
    }
}
