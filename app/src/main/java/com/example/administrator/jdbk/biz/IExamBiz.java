package com.example.administrator.jdbk.biz;

import com.example.administrator.jdbk.bean.Exam;

/**
 * Created by Admin on 2017/7/2.
 */

public interface IExamBiz {
    void BeginExam();
    Exam getExam();
    Exam NextQuestion();
    Exam PreQuestion();
    int CommitExam();
    String getIndex();
}
