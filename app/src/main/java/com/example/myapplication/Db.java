package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Db extends SQLiteOpenHelper {

    public Db(Context context) {
        super(context, "Db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" + "name TEXT DEFAULT \"\"," + "sex TEXT DEFAULT \"\")");
        System.out.println("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
