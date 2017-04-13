package com.example.myapplication.database;

/**
 * Created by Administrator on 2017/4/12.
 */
public class Task {

    private int _id;
    private String title;
    private String content;
    private String clocktime;
    private String starttime;
    private String endtime;
    private String edittime;

    public Task(int id,String title,String content,String clocktime,String starttime,String endtime,String edittime) {

        this._id = id;
        this.title = title;
        this.content = content;
        this.clocktime = clocktime;
        this.starttime = starttime;
        this.endtime = endtime;
        this.edittime = edittime;
    }

    public Task(int id,String title,String content,String clocktime,String starttime,String endtime) {

        this._id = id;
        this.title = title;
        this.content = content;
        this.clocktime = clocktime;
        this.starttime = starttime;
        this.endtime = endtime;

    }

    public Task(String title,String content,String clocktime,String starttime,String endtime) {

        this.title = title;
        this.content = content;
        this.clocktime = clocktime;
        this.starttime = starttime;
        this.endtime = endtime;

    }

    public Task(int id){
        this._id = id;
    }

    public int get_id(){return this._id;}
    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public String getClocktime(){return this.clocktime;}
    public String getStarttime(){return this.starttime;}
    public String getEndtime(){return this.endtime;}
    public String getEdittime(){return this.edittime;}
}
