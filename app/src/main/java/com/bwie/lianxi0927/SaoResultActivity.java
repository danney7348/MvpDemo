package com.bwie.lianxi0927;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class SaoResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_result);
        WebView wv = (WebView) findViewById(R.id.wv);
        wv.loadUrl(getIntent().getStringExtra("url"));
    }
}
