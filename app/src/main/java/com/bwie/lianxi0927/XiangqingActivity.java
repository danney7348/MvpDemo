package com.bwie.lianxi0927;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class XiangqingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        WebView wv = (WebView) findViewById(R.id.wv_wv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }
}
