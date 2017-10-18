package com.bwie.lianxi0927;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.lianxi0927.adapter.MyListViewAdapter;
import com.bwie.lianxi0927.bean.Fenlei;
import com.bwie.lianxi0927.bean.UserLogin;
import com.bwie.lianxi0927.fragment.FenleiXiangqingFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.FENLEI_API;
import static com.bwie.lianxi0927.common.Api.USERINFO_API;

public class ZhanghuShezhiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_usericon;
    private ImageView iv_back;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghu_shezhi);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        final int uid = getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 0);
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
                        runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    icon = data.getIcon();
                                    System.out.println("iconssssssssssssssssssss = " + icon);
                                    Glide.with(ZhanghuShezhiActivity.this).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_usericon);
                                }
                            });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    private void initView() {
        iv_usericon = (ImageView) findViewById(R.id.iv_usericon);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_usericon.setOnClickListener(this);
        Button btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        btn_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences("con", MODE_PRIVATE).edit().clear().commit();
                Intent intent = new Intent(ZhanghuShezhiActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_usericon:
                startActivity(new Intent(ZhanghuShezhiActivity.this,GerenxinxiActivity.class));
                break;
        }
    }

}
