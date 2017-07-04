package com.example.administrator.jdbk.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.R;
import com.example.administrator.jdbk.bean.Exam;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class QuestionAdapter extends BaseAdapter {
    Context mcontext ;
    List <Exam> examList ;

    public QuestionAdapter(Context mcontext) {
        this.mcontext = mcontext;
        examList = Examapplication .getInstance() .getmQuestion() ;
    }

    @Override
    public int getCount() {
        return examList ==null?0:examList .size() ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view=View .inflate(mcontext , R.layout .item_question,null) ;
        TextView tvNo=(TextView) view.findViewById(R.id.tv_no) ;
        ImageView ivQuestion=(ImageView) view.findViewById(R.id.iv_question) ;
        tvNo .setText("第"+(position+1)+"题") ;
        return null;
    }
}
