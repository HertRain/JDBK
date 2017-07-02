package com.example.administrator.jdbk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.R;
import com.example.administrator.jdbk.bean.Exam;
import com.example.administrator.jdbk.bean.ExamInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView textView,tvExamTitle,tvop1,tvop2,tvop3,tvop4;
    ImageView Image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_randomtest);
        initView();
        initData();
    }

    private void initView() {
        textView =(TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle=(TextView) findViewById(R.id.tv_exam_title);
        tvop1 =(TextView) findViewById(R.id.tv_exam_op1);
        tvop2 =(TextView) findViewById(R.id.tv_exam_op2);
        tvop3 =(TextView) findViewById(R.id.tv_exam_op3);
        tvop4 =(TextView) findViewById(R.id.tv_exam_op4);
        Image =(ImageView) findViewById(R.id.Iv_exam_image);

    }

    private void initData() {
       ExamInfo examinfo=Examapplication.getInstance().getExamInfo() ;
        if(examinfo !=null)
        {
            showData(examinfo);
        }
        List<Exam> list=Examapplication .getInstance().getmQuestion() ;
        if(list!=null)
        {
            showExam(list);
        }
    }

    private void showExam(List<Exam> list) {
        Exam exam=list.get(0);
        if(exam!=null){
            tvExamTitle.setText(exam .getQuestion() ) ;
            tvop1 .setText(exam.getItem1()) ;
            tvop2 .setText(exam.getItem2()) ;
            tvop3 .setText(exam.getItem3()) ;
            tvop4 .setText(exam.getItem4()) ;
            Picasso .with(ExamActivity .this).load(exam .getUrl()) .into(Image);
        }
    }

    private void showData(ExamInfo examinfo) {
        textView .setText(examinfo .toString()) ;
    }
}
