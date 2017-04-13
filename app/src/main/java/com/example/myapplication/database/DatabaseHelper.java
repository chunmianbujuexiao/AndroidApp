package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE IF NOT EXISTS task(_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,content TEXT," +
                "clocktime TEXT,starttime TEXT,endtime TEXT,edit_time TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))"+")";
//        String sql2 = "CREATE TABLE trash(id text,title text,content text)";
        db.execSQL(sql1);
//        db.execSQL(sql2);
/*        db.execSQL("CREATE TABLE IF NOT EXISTS person" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, info TEXT)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
