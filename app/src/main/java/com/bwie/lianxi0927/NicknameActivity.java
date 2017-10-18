package com.bwie.lianxi0927;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.lianxi0927.bean.UserLogin;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.USERINFO_API;
import static com.bwie.lianxi0927.common.Api.XIUGAINICHEG_API;

public class NicknameActivity extends AppCompatActivity {

    private EditText nickname;
    private Button xiugai;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        initView();


    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        final int uid = getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 0);
        Request request = new Request.Builder().url(XIUGAINICHEG_API+"?uid="+uid+"&&nickname="+nickname.getText().toString()).post(build).build();
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
                                Intent in = new Intent();
                                in.putExtra("nickname",nickname.getText().toString());
                                setResult(101,in);
                                finish();
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
        nickname = (EditText) findViewById(R.id.et_nickname);
        ImageView back = (ImageView) findViewById(R.id.iv_nc_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        xiugai = (Button) findViewById(R.id.btn_xiugai);
        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }
}
