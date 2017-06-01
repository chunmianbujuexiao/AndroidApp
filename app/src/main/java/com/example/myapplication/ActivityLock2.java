package com.example.myapplication;

import android.app.Activity;
import android.app.KeyguardManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityLock2 extends Activity {

    DatabaseHelper databaseHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Test","There is a lock");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lock);
//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//        setContentView(R.layout.activity_activity_lock);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        databaseHelper = new DatabaseHelper(ActivityLock2.this);

        ListView listView = (ListView) findViewById(R.id.id_listview_activitylock_task);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.query("task", null, null, null, null, null, null);

//        if (cursor.isFirst()){
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String clocktime = cursor.getString(3);
            String starttime = cursor.getString(4);
            String endtime = cursor.getString(5);
            String edittime = cursor.getString(6);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id",Integer.toString(id));
            map.put("id_listview_lock_task_title",title);
            map.put("id_listview_lock_task_content",edittime+" "+content);
            if(clocktime == null){
                map.put("id_listview_lock_task_icon_clock",null);
            }else {
                map.put("id_listview_lock_task_icon_clock",R.drawable.ic_icon_clock);
            }
            if(starttime == null){
                map.put("id_listview_lock_task_icon_lock_screen",null);
            }else {
                map.put("id_listview_lock_task_icon_lock_screen",R.drawable.ic_icon_display);
            }

            listItem.add(map);
        }
        //}
        cursor.close();
        dbRead.close();
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(ActivityLock2.this,listItem
                ,R.layout.list_lock_task
                ,new String[]{"id_listview_lock_task_title", "id_listview_lock_task_content","id_listview_lock_task_icon_clock","id_listview_lock_task_icon_lock_screen"}
                ,new int[]{R.id.id_listview_lock_task_title,R.id.id_listview_lock_task_content,R.id.id_listview_lock_task_icon_clock,R.id.id_listview_lock_task_icon_lock_screen});
        listView.setAdapter(mSimpleAdapter);

        ImageView imageView = (ImageView) findViewById(R.id.id_imagebutton_quit_lock);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        Button b = (Button)findViewById(R.id.id_button_activity_lock);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
//                KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("");
//                keyguardLock.disableKeyguard();
//                Toast.makeText(ActivityLock2.this,"lock success",Toast.LENGTH_SHORT);
//            }
//        });
    }
}

