package com.example.administrator.jdbk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.R;
import com.example.administrator.jdbk.bean.ExamInfo;

/**
 * Created by Admin on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_randomtest);
        initView();
        initData();
    }

    private void initView() {
      textView =(TextView) findViewById(R.id.tv_examinfo);
    }

    private void initData() {
       ExamInfo examinfo=Examapplication.getInstance().getExamInfo() ;
        if(examinfo !=null)
        {
            showData(examinfo);
        }
    }

    private void showData(ExamInfo examinfo) {
        textView .setText(examinfo .toString()) ;
    }
}
