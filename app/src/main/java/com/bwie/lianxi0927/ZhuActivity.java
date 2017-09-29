package com.bwie.lianxi0927;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.lianxi0927.fragment.ShouyeFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ZhuActivity extends AppCompatActivity {

    private LinearLayout shouye;
    private LinearLayout ll_fenlei;
    private LinearLayout ll_faxian;
    private LinearLayout ll_gouwuche;
    private LinearLayout ll_wode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initView();
        initFragment();
    }

    private void initFragment() {
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuActivity.this, "首页", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
            }
        });
        ll_fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuActivity.this, "分类", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
            }
        });
        ll_faxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuActivity.this, "发现", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
            }
        });
        ll_gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuActivity.this, "购物车", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
            }
        });
        ll_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZhuActivity.this, "我的", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
            }
        });
    }

    private void initView() {
        shouye = (LinearLayout) findViewById(R.id.ll_shouye);
        ll_fenlei = (LinearLayout) findViewById(R.id.ll_fenlei);
        ll_faxian = (LinearLayout) findViewById(R.id.ll_faxian);
        ll_gouwuche = (LinearLayout) findViewById(R.id.ll_gouwuche);
        ll_wode = (LinearLayout) findViewById(R.id.ll_wode);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(ZhuActivity.this,"内容为空",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ZhuActivity.this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Toast.makeText(ZhuActivity.this, ScanResult, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
