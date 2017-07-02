package com.example.administrator.jdbk.dao;

import android.util.Log;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.Utils.OkHttpUtils;
import com.example.administrator.jdbk.Utils.ResultUtils;
import com.example.administrator.jdbk.activity.ExamActivity;
import com.example.administrator.jdbk.bean.Exam;
import com.example.administrator.jdbk.bean.ExamInfo;
import com.example.administrator.jdbk.bean.Result;

import java.util.List;

/**
 * Created by Admin on 2017/7/2.
 */

public class ExamDao implements  IExamDao {
    @Override
    public void LoadExamInfo() {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(Examapplication.getInstance());
        String uri="http://101.251.196.90:8080/JztkServer/examInfo ";
        utils .url(uri)
                .targetClass(ExamInfo.class )
                .execute(new OkHttpUtils.OnCompleteListener <ExamInfo>() {

                    @Override
                    public void onSuccess(ExamInfo result) {
                        Log.e("main","result"+result);
                        Examapplication.getInstance() .setExamInfo(result ) ;

                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main","error"+error);
                    }
                });
    }

    @Override
    public void LoadQuestionLists() {
        OkHttpUtils <String> utils1 =new OkHttpUtils<>(Examapplication .getInstance());
        String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
        utils1 .url(url2)
                .targetClass(String.class)
                .execute(new OkHttpUtils.OnCompleteListener<String>()
                {

                    @Override
                    public void onSuccess(String jsonStr) {
                        Result result= ResultUtils.getListResultFromJson(jsonStr);
                        if(result!=null && result .getError_code() ==0)
                        {
                            List<Exam> list =result .getResult() ;
                            if(list !=null && list.size()>0)
                            {
                                Examapplication .getInstance() .setmQuestion(list) ;
                            }
                        }
                    }

                    @Override
                    public void onError(String error)
                    {
                        Log.e("main","error"+error);
                    }
                });
    }
}
