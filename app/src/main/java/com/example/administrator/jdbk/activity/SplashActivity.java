package com.example.administrator.jdbk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.jdbk.R;

/**
 * Created by Admin on 2017/6/27.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_splash) ;
        mcountDownTimer.start() ;

    }
    CountDownTimer mcountDownTimer =new CountDownTimer(2000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
           Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
           SplashActivity.this.finish() ;
        }
    };
}
