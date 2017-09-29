package com.bwie.lianxi0927;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.fragment.ShouyeFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ZhuActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout m_shouye;
    private LinearLayout m_fenlei;
    private LinearLayout m_faxian;
    private LinearLayout m_gouwu;
    private LinearLayout m_wode;
    private ImageView img_shouye,img_wode,img_fenlei,img_faxian,img_gouwu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initView();
        img_shouye.setImageResource(R.drawable.shouye2);
        img_faxian.setImageResource(R.drawable.faxian1);
        img_gouwu.setImageResource(R.drawable.gouwuche1);
        img_wode.setImageResource(R.drawable.wode1);
        img_fenlei.setImageResource(R.drawable.fenlei1);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
    }


    private void initView() {
        m_shouye = (LinearLayout) findViewById(R.id.m_shouye);
        m_fenlei = (LinearLayout) findViewById(R.id.m_fenlei);
        m_faxian = (LinearLayout) findViewById(R.id.m_faxian);
        m_gouwu = (LinearLayout) findViewById(R.id.m_gouwu);
        m_wode = (LinearLayout) findViewById(R.id.m_wode);
        m_shouye.setOnClickListener(this);
        m_fenlei.setOnClickListener(this);
        m_faxian.setOnClickListener(this);
        m_gouwu.setOnClickListener(this);
        m_wode.setOnClickListener(this);
        img_faxian = (ImageView) findViewById(R.id.img_faxian);
        img_shouye = (ImageView) findViewById(R.id.img_shouye);
        img_wode = (ImageView) findViewById(R.id.img_wode);
        img_fenlei = (ImageView) findViewById(R.id.img_fenlei);
        img_gouwu = (ImageView) findViewById(R.id.img_gouwu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.m_shouye:
                img_shouye.setImageResource(R.drawable.shouye2);
                img_faxian.setImageResource(R.drawable.faxian1);
                img_gouwu.setImageResource(R.drawable.gouwuche1);
                img_wode.setImageResource(R.drawable.wode1);
                img_fenlei.setImageResource(R.drawable.fenlei1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
                break;
            case  R.id.m_fenlei:
                img_shouye.setImageResource(R.drawable.shouye1);
                img_faxian.setImageResource(R.drawable.faxian1);
                img_gouwu.setImageResource(R.drawable.gouwuche1);
                img_wode.setImageResource(R.drawable.wode1);
                img_fenlei.setImageResource(R.drawable.fenlei2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
                break;
            case R.id.m_faxian:
                img_shouye.setImageResource(R.drawable.shouye1);
                img_faxian.setImageResource(R.drawable.faxian2);
                img_gouwu.setImageResource(R.drawable.gouwuche1);
                img_wode.setImageResource(R.drawable.wode1);
                img_fenlei.setImageResource(R.drawable.fenlei1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
                break;
            case  R.id.m_gouwu:
                img_shouye.setImageResource(R.drawable.shouye1);
                img_faxian.setImageResource(R.drawable.faxian1);
                img_gouwu.setImageResource(R.drawable.gouwuche2);
                img_wode.setImageResource(R.drawable.wode1);
                img_fenlei.setImageResource(R.drawable.fenlei1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
                break;
            case R.id.m_wode:
                img_shouye.setImageResource(R.drawable.shouye1);
                img_faxian.setImageResource(R.drawable.faxian1);
                img_gouwu.setImageResource(R.drawable.gouwuche1);
                img_wode.setImageResource(R.drawable.wode2);
                img_fenlei.setImageResource(R.drawable.fenlei1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ShouyeFragment()).commit();
                break;
        }
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
                Intent intent = new Intent(ZhuActivity.this,SaoResultActivity.class);
                intent.putExtra("url",ScanResult);
                startActivity(intent);
                Toast.makeText(ZhuActivity.this, ScanResult, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
