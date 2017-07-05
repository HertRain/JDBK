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
        String ua=examList.get(position).getUseranswer() ;
        if(examList.get(position).getUseranswer()!=null &&!ua.equals(""))
        {
            if(examList.get(position).getUseranswer().equals(examList.get(position).getAnswer())  ){
            ivQuestion .setImageResource(R.drawable .answer24x24);
            }
            else {
                ivQuestion .setImageResource(R.drawable.error) ;
            }
        }
        else {
            ivQuestion .setImageResource(R.drawable.question) ;
        }
        tvNo .setText("第"+(position+1)+"题") ;
        return view;
    }
}
