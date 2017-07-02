package com.example.administrator.jdbk.biz;

/**
 * Created by Admin on 2017/7/2.
 */

public interface IExamBiz {
    void BeginExam();
    void NextQuestion();
    void PreQuestion();
    void CommitExam();
}
