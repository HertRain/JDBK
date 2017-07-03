package com.example.administrator.jdbk.biz;

import com.example.administrator.jdbk.dao.ExamDao;
import com.example.administrator.jdbk.dao.IExamDao;

/**
 * Created by Admin on 2017/7/2.
 */

public class Exambiz implements IExamBiz  {
    IExamDao dao;

    public Exambiz() {
        this.dao = new ExamDao();
    }

    @Override

    public void BeginExam() {
            dao.LoadExamInfo() ;
            dao.LoadQuestionLists() ;
    }

    @Override
    public void NextQuestion() {

    }

    @Override
    public void PreQuestion() {

    }

    @Override
    public void CommitExam() {

    }
}
