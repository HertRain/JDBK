package com.example.administrator.jdbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.jdbk.Utils.OkHttpUtils;
import com.example.administrator.jdbk.bean.Subject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Randomtest(View view) {
        OkHttpUtils<Subject> utils=new OkHttpUtils<>(getApplicationContext());
        String uri="http://101.251.196.90:8080/JztkServer/examInfo ";
        utils .url(uri)
                .targetClass(Subject .class )
                .execute(new OkHttpUtils.OnCompleteListener <Subject>() {

                    @Override
                    public void onSuccess(Subject result) {
                        Log.e("main","result"+result);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error"+error);
                    }
                });
        Intent intent=new Intent(MainActivity.this,ExamActivity.class);
        startActivity(intent);

    }

    public void Exit(View view) {
        finish() ;
    }
}
