package com.example.administrator.jdbk.biz;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.bean.Exam;
import com.example.administrator.jdbk.dao.ExamDao;
import com.example.administrator.jdbk.dao.IExamDao;

import java.util.List;

/**
 * Created by Admin on 2017/7/2.
 */

public class Exambiz implements IExamBiz  {
    IExamDao dao;
    int examIndex=0;
    List<Exam> examList =null;
    public Exambiz() {
        this.dao = new ExamDao();
    }

    @Override

    public void BeginExam() {
            examIndex =0;
            dao.LoadExamInfo() ;
            dao.LoadQuestionLists() ;
    }

    @Override
    public Exam getExam() {
        examList=Examapplication .getInstance() .getmQuestion() ;
        if(examList !=null){
        return examList.get(examIndex);
        }
        else{
            return null;
        }
    }

    @Override
    public Exam  NextQuestion() {
        if(examList !=null&& examIndex<examList.size()-1 ){
            examIndex++;
            return examList.get(examIndex+1);
        }
        else{
            return null;
        }
    }

    @Override
    public Exam PreQuestion() {
        if(examList !=null&& examIndex>0){
            examIndex--;
            return examList.get(examIndex+1);
        }
        else{
            return null;
        }
    }

    @Override
    public void CommitExam() {

    }

    @Override
    public String getIndex() {
        return (examIndex+1)+".";
    }
}
