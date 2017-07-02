package com.example.administrator.jdbk;

import android.app.Application;
import android.database.CursorJoiner;
import android.graphics.Interpolator;
import android.util.Log;

import com.example.administrator.jdbk.Utils.OkHttpUtils;
import com.example.administrator.jdbk.Utils.ResultUtils;
import com.example.administrator.jdbk.bean.ExamInfo;
import com.example.administrator.jdbk.bean.Exam;
import com.example.administrator.jdbk.bean.Result;
import com.example.administrator.jdbk.biz.Exambiz;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class Examapplication extends Application {
    ExamInfo examInfo ;
    List <Exam> mQuestion;
    Exambiz biz;
    private static Examapplication instance;
    @Override
    public void onCreate() {
         super .onCreate() ;
        instance =this;
        biz=new Exambiz();
            initData();
    }

    private void initData() {
        //创建一个一个线程来加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
            biz.BeginExam() ;

            }
        }).start();

    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public List<Exam> getmQuestion() {
        return mQuestion;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public void setmQuestion(List<Exam> mQuestion) {
        this.mQuestion = mQuestion;
    }

    public static  Examapplication getInstance() {
        return instance;
    }
}
