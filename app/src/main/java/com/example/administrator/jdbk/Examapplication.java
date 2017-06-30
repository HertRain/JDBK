package com.example.administrator.jdbk;

import android.app.Application;
import android.util.Log;

import com.example.administrator.jdbk.Utils.OkHttpUtils;
import com.example.administrator.jdbk.bean.ExamInfo;
import com.example.administrator.jdbk.bean.Question;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class Examapplication extends Application {
    ExamInfo examInfo ;
    List <Question> mQuestion;
    private static Examapplication instance;
    @Override
    public void onCreate() {
         super .onCreate() ;
        instance =this;
            initData();
    }

    private void initData() {
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
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public List<Question> getmQuestion() {
        return mQuestion;
    }

    public static  Examapplication getInstance() {
        return instance;
    }
}
