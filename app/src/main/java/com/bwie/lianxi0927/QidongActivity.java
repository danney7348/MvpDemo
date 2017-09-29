package com.bwie.lianxi0927;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class QidongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qidong);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int num = 3;
            @Override
            public void run() {
                num--;
                if(num == 0){
                    Intent intent = new Intent(QidongActivity.this,ZhuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.schedule(task,1000,1000);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
    }
}
