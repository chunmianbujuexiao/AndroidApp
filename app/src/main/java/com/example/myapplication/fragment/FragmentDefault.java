package com.example.myapplication.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myapplication.ActivityAddTask;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDefault extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private ArrayList<HashMap<String, Object>> listItem;
    DatabaseHelper databaseHelper = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatActivity a = (AppCompatActivity) getActivity();
        databaseHelper = new DatabaseHelper(a);
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        listView = (ListView) view.findViewById(R.id.id_listview_fragment_default_task);
        listView.setOnItemClickListener(this);
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

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
                map.put("id_listview_fragment_default_task_title",title);
                map.put("id_listview_fragment_default_task_content",edittime+" "+content);
                if(clocktime == null){
                    map.put("id_listview_fragment_default_task_icon_clock",null);
                }else {
                    map.put("id_listview_fragment_default_task_icon_clock",R.drawable.ic_menu_add);
                }
                if(starttime == null){
                    map.put("id_listview_fragment_default_task_icon_lock_screen",null);
                }else {
                    map.put("id_listview_fragment_default_task_icon_lock_screen",R.drawable.ic_menu_forward);
                }

                listItem.add(map);
            }
        //}
        cursor.close();
        dbRead.close();
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(a,listItem
                ,R.layout.listview_fragment_default_task
                ,new String[]{"id_listview_fragment_default_task_title", "id_listview_fragment_default_task_title","id_listview_fragment_default_task_icon_clock","id_listview_fragment_default_task_icon_lock_screen"}
                ,new int[]{R.id.id_listview_fragment_default_task_title,R.id.id_listview_fragment_default_task_content,R.id.id_listview_fragment_default_task_icon_clock,R.id.id_listview_fragment_default_task_icon_lock_screen});
        listView.setAdapter(mSimpleAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.query("task", null, null, null, null, null, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String clocktime = cursor.getString(3);
            String starttime = cursor.getString(4);
            String endtime = cursor.getString(5);
            String edittime = cursor.getString(6);
            if(content.length()>20){
                content = content.substring(0,20)+"....";
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id",Integer.toString(id));
            map.put("id_listview_fragment_default_task_title",title);
            map.put("id_listview_fragment_default_task_content",edittime+" "+content);
            if(clocktime == null){
                map.put("id_listview_fragment_default_task_icon_clock",null);
            }else {
                map.put("id_listview_fragment_default_task_icon_clock",R.drawable.ic_icon_clock);
            }
            if(starttime == null){
                map.put("id_listview_fragment_default_task_icon_lock_screen",null);
            }else {
                map.put("id_listview_fragment_default_task_icon_lock_screen",R.drawable.ic_icon_display);
            }

            listItem.add(map);
        }
        //}
        cursor.close();
        dbRead.close();
        AppCompatActivity a = (AppCompatActivity) getActivity();
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(a,listItem
                ,R.layout.listview_fragment_default_task
                ,new String[]{"id_listview_fragment_default_task_title", "id_listview_fragment_default_task_content","id_listview_fragment_default_task_icon_clock","id_listview_fragment_default_task_icon_lock_screen"}
                ,new int[]{R.id.id_listview_fragment_default_task_title,R.id.id_listview_fragment_default_task_content,R.id.id_listview_fragment_default_task_icon_clock,R.id.id_listview_fragment_default_task_icon_lock_screen});
        listView.setAdapter(mSimpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, Object> hashMap = listItem.get(position);
        String str = (String) hashMap.get("id");

        int idItem = Integer.parseInt(str);
        AppCompatActivity a = (AppCompatActivity) getActivity();
        Intent i = new Intent(a,ActivityAddTask.class);
        Bundle b = new Bundle();
        b.putInt("id",idItem);
        i.putExtras(b);
        startActivity(i);
    }
}
