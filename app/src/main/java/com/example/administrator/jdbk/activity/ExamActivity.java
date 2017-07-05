package com.example.administrator.jdbk.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.jdbk.Examapplication;
import com.example.administrator.jdbk.R;
import com.example.administrator.jdbk.bean.Exam;
import com.example.administrator.jdbk.bean.ExamInfo;
import com.example.administrator.jdbk.biz.Exambiz;
import com.example.administrator.jdbk.dao.ExamDao;
import com.example.administrator.jdbk.biz.IExamBiz ;
import com.example.administrator.jdbk.view.QuestionAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView textView,tvExamTitle,tvop1,tvop2,tvop3,tvop4,tvload,tvExamNo,tvTime;
    CheckBox cb1,cb2,cb3,cb4;
    CheckBox[] cbs=new CheckBox[4];
    ImageView Image;
    IExamBiz biz;
    LinearLayout LayoutLoading,Layout03,Layout04;
    ProgressBar dialog;
    Gallery mGallery;
    boolean isLoadExamInfo=false ;
    boolean isLoadQuestion=false ;
    boolean isLoadExamInfoReceiver=false ;
    boolean isLoadQuestionReceiver=false ;
    QuestionAdapter mAdapter;

    LoadExamBroadcast loadExamBroadcast;
    LoadQuestionBroadcast loadQuestionBroadcast ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_randomtest);
        loadQuestionBroadcast =new LoadQuestionBroadcast();
        loadExamBroadcast = new LoadExamBroadcast();
        biz=new Exambiz() ;
        estListener();
        initView();
        LoadData();
    }
    //注册
    private void estListener() {

        registerReceiver(loadExamBroadcast ,new IntentFilter(Examapplication .LOAD_EXAM_INFO ) ) ;
        registerReceiver(loadQuestionBroadcast,new IntentFilter(Examapplication .LOAD_EXAM_QUESTION) ) ;

    }

    @Override
    protected void onDestroy() {
         super .onDestroy() ;
        if(loadExamBroadcast !=null)
        {
            unregisterReceiver(loadExamBroadcast );
        }
        if(loadQuestionBroadcast!=null){
            unregisterReceiver(loadQuestionBroadcast) ;
        }
    }

    private void LoadData() {
        dialog .setVisibility(View.VISIBLE) ;
        tvload .setText("加载数据中...") ;
        LayoutLoading .setEnabled(false) ;
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.BeginExam() ;
            }
        }).start();
    }

    private void initView() {
        textView =(TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle=(TextView) findViewById(R.id.tv_exam_title);
        tvop1 =(TextView) findViewById(R.id.tv_exam_op1);
        tvop2 =(TextView) findViewById(R.id.tv_exam_op2);
        tvop3 =(TextView) findViewById(R.id.tv_exam_op3);
        tvop4 =(TextView) findViewById(R.id.tv_exam_op4);
        tvTime  =(TextView) findViewById(R.id.tv_time);
        Image =(ImageView) findViewById(R.id.Iv_exam_image);
        mGallery =(Gallery)  findViewById(R.id.gallery) ;
        cb1=(CheckBox)  findViewById(R.id.cb_01);
        cb2=(CheckBox)  findViewById(R.id.cb_02);
        cb3=(CheckBox)  findViewById(R.id.cb_03);
        cb4=(CheckBox)  findViewById(R.id.cb_04);
        cbs[0]=cb1;
        cbs[1]=cb2;
        cbs[2]=cb3;
        cbs[3]=cb4;

        LayoutLoading =(LinearLayout) findViewById(R.id.Layout_loading);
        Layout03 =(LinearLayout) findViewById(R.id.Layout_03);
        Layout04 =(LinearLayout) findViewById(R.id.Layout_04);
        tvload=(TextView) findViewById(R.id.tv_load);
        dialog =(ProgressBar) findViewById(R.id.Load_dialog);
        tvExamNo =(TextView)  findViewById(R.id.tv_exam_no);
        Gallery gallery=(Gallery)  findViewById(R.id.gallery);
        LayoutLoading .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData() ;
            }
        }) ;
        //注册Checkbox的点击事件
        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);
        cb4.setOnCheckedChangeListener(listener);
    }
    CompoundButton.OnCheckedChangeListener listener =new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked )
            {
                 int  Useranswer=0;
                    switch (buttonView.getId()){
                        case R.id.cb_01:
                            Useranswer =1;
                            break;
                        case R.id.cb_02:
                            Useranswer =2;
                            break;
                        case R.id.cb_03:
                            Useranswer =3;
                            break;
                        case R.id.cb_04:
                            Useranswer =4;
                            break;
                    }
                Log.e("LogChange","usera="+Useranswer+"Ischecked="+isChecked);
                if(Useranswer>0){
                        for(CheckBox cb:cbs){
                            cb.setChecked(false);
                        }
                        cbs[Useranswer-1].setChecked(true) ;
                    }
            }
        }
    };
    private void initData() {

        if(isLoadQuestionReceiver &&isLoadExamInfoReceiver )
        {       if(isLoadExamInfo &&isLoadQuestion )
                {
                    LayoutLoading.setVisibility(View.GONE) ;
                    ExamInfo examinfo = Examapplication.getInstance().getExamInfo();
                    Log.e("examinfo","examinfo = "+examinfo);
                    if (examinfo != null) {
                        showData(examinfo);
                        InitTimer(examinfo);
                    }
                    initGallery();
                    List<Exam> list = Examapplication.getInstance().getmQuestion();
                    Log.e("list","list = "+list);
                    if (list != null) {
                       showExam(biz.getExam());
                    }
                }
                else
                {
                    LayoutLoading .setEnabled(true) ;
                    dialog .setVisibility(View.GONE) ;
                    tvload .setText("加载数据失败，点击重新加载") ;
                }
        }

    }

    private void initGallery() {
            mAdapter =new QuestionAdapter(this) ;
            mGallery.setAdapter(mAdapter) ;
            mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Log.e("Gallery", "Gallery Item position+" + position);
                                                    //biz.getExam(position) ;
                                                    saveUseranswer();
                                                    showExam(biz.getExam(position));
                                                }
                                            });
    }


    //加载剩余考试时间
    private void InitTimer(ExamInfo examinfo) {
        int  sumTime=examinfo.getLimitTime()*60*1000;
        final long overTime=(long) (System.currentTimeMillis()+sumTime);
        final Timer timer=new Timer() ;
        //设置计时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            long l=overTime -System.currentTimeMillis() ;
            final long min=(long) (l/1000/60);
            final long sec=(long) l/1000%60;
            runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            tvTime .setText("剩余时间"+min+"分"+sec+"秒") ;
                        }
                    });
            }
        },0,1000) ;
        //注销计时器，并提交试题
       timer .schedule(new TimerTask() {
           @Override
           public void run() {
               timer .cancel() ;
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       commit(null) ;
                   }
               }) ;
           }
       },sumTime);
    }


    private void showExam(Exam exam) {
        Log.e("showExam","showExam,exam="+exam);
        if(exam!=null){
            tvExamNo .setText(biz.getIndex()) ;
            tvExamTitle.setText(exam .getQuestion() ) ;
            tvop1 .setText(exam.getItem1()) ;
            tvop2 .setText(exam.getItem2()) ;
            tvop3 .setText(exam.getItem3()) ;
            tvop4 .setText(exam.getItem4()) ;
            Layout03.setVisibility(exam.getItem3() .equals("")?View.GONE:View.VISIBLE);
            cb3.setVisibility(exam.getItem3() .equals("")?View.GONE:View.VISIBLE);
            Layout04.setVisibility(exam.getItem4() .equals("")?View.GONE:View.VISIBLE);
            cb4.setVisibility(exam.getItem3() .equals("")?View.GONE:View.VISIBLE);
            if(exam .getUrl()!=null&& !exam .getUrl() .equals("") ){
                Image .setVisibility(View.VISIBLE) ;
            Picasso .with(ExamActivity .this)
                    .load(exam .getUrl()) .into(Image);
            }
            else {
                Image .setVisibility(View.GONE) ;
            }
            resetOptions();

            String  UserAnswer=exam.getUseranswer() ;
            Log.e("Integer","cb="+UserAnswer);
            //设置显示考生答案
            if(UserAnswer!=null&&!UserAnswer.equals(""))
            {

                int usercb=Integer .parseInt(UserAnswer)-1;
                Log.e("Integer","cb="+usercb);
                cbs[usercb].setChecked(true) ;
            }
        }
    }
    //重置选项
    private void resetOptions() {
        for(CheckBox cb:cbs){
            cb.setChecked(false) ;
        }
    }
    //保存考生答案
    private void saveUseranswer(){

        for(int i=0;i<cbs.length ;i++)
        {
            if(cbs[i].isChecked()){
                Log.e("Integer", "cb=" + String.valueOf(i + 1));
                biz.getExam().setUseranswer(String.valueOf(i + 1));
                mAdapter .notifyDataSetChanged() ;
                return ;
            }
        }
        biz.getExam() .setUseranswer("") ;
        mAdapter .notifyDataSetChanged() ;
    }

    private void showData(ExamInfo examinfo) {
        textView .setText(examinfo .toString()) ;
    }

    public void preExam(View view) {

        saveUseranswer() ;
        showExam(biz .PreQuestion()) ;
    }

    public void nextExam(View view) {
        saveUseranswer() ;
        showExam(biz.NextQuestion()) ;
    }
//交卷
    public void commit(View view) {
        saveUseranswer() ;
        int s=biz.CommitExam() ;
        View inflate=View.inflate(this,R.layout.layout_result,null);
        TextView tvResult=(TextView)  inflate.findViewById(R.id .tv_result);
        tvResult .setText("你的分数为:"+s+"分") ;
        AlertDialog .Builder builder=new AlertDialog.Builder(this) ;
        builder .setIcon(R.drawable .exam_commit32x32)
                .setTitle("考试结果")
                //.setMessage("你的分数为/n"+s+"分")
                .setView(inflate)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish() ;
                    }
                }) ;
        builder .create() .show();
    }


    class LoadExamBroadcast extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("LoadExamBroadcast","LoadExamBroadcast-onReceive is ok");
            boolean isSuccess=intent.getBooleanExtra(Examapplication .LOAD_DATA_SUCCESS ,false) ;
            Log.e("LoadExamBroadcast="+loadExamBroadcast  ,"isSuccess="+isSuccess);
            if(isSuccess)
            {
                isLoadExamInfo =true ;
            }
            isLoadExamInfoReceiver =true;
            initData() ;
        }
    }
    class LoadQuestionBroadcast extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess=intent.getBooleanExtra(Examapplication .LOAD_DATA_SUCCESS ,false) ;
            Log.e("LoadQuestionBroadcast="+loadQuestionBroadcast ,"isSuccess="+isSuccess);
            if(isSuccess)
            {
                isLoadQuestion  =true ;
            }
            isLoadQuestionReceiver =true;
            initData() ;
        }
    }
}
