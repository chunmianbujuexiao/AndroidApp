package com.example.myapplication.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/4/12.
 */
public class DatabaseTaskOpImpl implements IAddTask,IUpdateTask,IQueryById {

    private ContentValues cv = null;
    private SQLiteDatabase dbWrite = null;
    private SQLiteDatabase dbRead = null;

    public DatabaseTaskOpImpl(){

    }

    public boolean addTask(DatabaseHelper d, Task t) {
        //添加Task
        dbWrite = d.getWritableDatabase();
        cv.put("title", t.getTitle());
        cv.put("content", t.getContent());
        cv.put("clocktime",t.getClocktime());
        cv.put("starttime",t.getStarttime());
        cv.put("endtime",t.getEndtime());
        dbWrite.insert("task", null, cv);
        dbWrite.close();
        cv = null;
        return true;
    }

    @Override
    public boolean updateTask(DatabaseHelper d,Task t) {
        //更新Task信息
        SQLiteDatabase dbWrite = d.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", t.getTitle());
        cv.put("content", t.getContent());
        cv.put("clocktime",t.getClocktime());
        cv.put("starttime",t.getStarttime());
        cv.put("endtime",t.getEndtime());
        String whereClause = "_id=?";
        String[] whereArgs = {String.valueOf(t.get_id())};
        dbWrite.update("task",cv,whereClause,whereArgs);
        dbWrite.close();
        return true;
    }

    @Override
    public Task queryById(DatabaseHelper d, Task t) {
        //查询Task信息
        Task t1 = null;
        SQLiteDatabase dbRead = d.getReadableDatabase();
        String []value = {String.valueOf(t.get_id())};
        Cursor cursor = dbRead.query("task",null,"_id=?",value,null,null,null);
        while(cursor.moveToNext()){
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String clocktime = cursor.getString(3);
            String starttime = cursor.getString(4);
            String endtime = cursor.getString(5);
            String edittime = cursor.getString(6);
            t1 = new Task(t.get_id(),title,content,clocktime,starttime,endtime,edittime);
        }
        dbRead.close();
        return t1;
    }
}
