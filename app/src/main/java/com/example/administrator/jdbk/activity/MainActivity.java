package com.example.administrator.jdbk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.jdbk.R;
import com.example.administrator.jdbk.Utils.OkHttpUtils;
import com.example.administrator.jdbk.bean.ExamInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Randomtest(View view) {

        Intent intent=new Intent(MainActivity.this,ExamActivity.class);
        startActivity(intent);

    }

    public void Exit(View view) {
        finish() ;
    }
}
