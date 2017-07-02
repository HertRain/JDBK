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

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class Examapplication extends Application {
    ExamInfo examInfo ;
    List <Exam> mQuestion;
    private static Examapplication instance;
    @Override
    public void onCreate() {
         super .onCreate() ;
        instance =this;
            initData();
    }

    private void initData() {
        //创建一个一个线程来加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(getApplicationContext());
                String uri="http://101.251.196.90:8080/JztkServer/examInfo ";
                utils .url(uri)
                        .targetClass(ExamInfo.class )
                        .execute(new OkHttpUtils.OnCompleteListener <ExamInfo>() {

                            @Override
                            public void onSuccess(ExamInfo result) {
                                Log.e("main","result"+result);
                                examInfo =result ;
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("main","error"+error);
                            }
                        });
                OkHttpUtils <String> utils1 =new OkHttpUtils<>(instance);
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
                utils1 .url(url2)
                        .targetClass(String.class)
                        .execute(new OkHttpUtils.OnCompleteListener<String>() {

                            @Override
                            public void onSuccess(String jsonStr) {
                                Result result=ResultUtils.getListResultFromJson(jsonStr);
                                if(result!=null && result .getError_code() ==0)
                                {
                                    List<Exam> list =result .getResult() ;
                                    if(list !=null && list.size()>0){
                                        mQuestion =list;
                                    }
                                }
                            }

                            @Override
                            public void onError(String error) {
                                Log.e("main","error"+error);
                            }
                        });
            }
        }).start();

    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public List<Exam> getmQuestion() {
        return mQuestion;
    }

    public static  Examapplication getInstance() {
        return instance;
    }
}
