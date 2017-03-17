package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DbOperation {

    private Db db = null;
    public DbOperation(Context context){

        db = new Db(context);
        SQLiteDatabase dbWrite = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name","xiaoli");
        cv.put("sex","fale");
        dbWrite.insert("user",null,cv);
        dbWrite.close();
        SQLiteDatabase dbRead = db.getReadableDatabase();
    }

    public boolean DbInsert(String table){
        return false;
    }


}
