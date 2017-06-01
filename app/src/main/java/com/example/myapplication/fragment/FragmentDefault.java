package com.example.myapplication.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.ActivityAddTask;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.database.DatabaseTaskOpImpl;
import com.example.myapplication.database.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDefault extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private ListView listView;
    private ArrayList<HashMap<String, Object>> listItem;
    DatabaseHelper databaseHelper = null;
    SQLiteDatabase dbRead = null;
    private DatabaseTaskOpImpl dtoi = new DatabaseTaskOpImpl();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatActivity a = (AppCompatActivity) getActivity();
        databaseHelper = new DatabaseHelper(a);
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        listView = (ListView) view.findViewById(R.id.id_listview_fragment_default_task);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        AppCompatActivity a = (AppCompatActivity) getActivity();
        dbRead = databaseHelper.getReadableDatabase();

        int order = ((MainActivity)a).getOrder();
        Cursor cursor = null;
        if(order == 0){
            cursor = queryByEditTimeSTOL();
        }else if(order == 1){
            cursor = queryByEditTimeLTOS();
        }else if(order == 2){
            cursor = queryByIdSTOL();
        }else if(order == 3){
            cursor = queryByIdLTOS();
        }

//        Cursor cursor = dbRead.query("task", null, null, null, null, null, null);
//        Cursor cursor = dbRead.rawQuery("select * from task order by edittime DESC",null);//query("task", null, null, null, null, null,"_id ASC");
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Time","TExt");
        final int p = position;
        AppCompatActivity a = (AppCompatActivity) getActivity();
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(a);
        normalDialog.setIcon(R.drawable.back);
        normalDialog.setTitle("删除Dialog");
        normalDialog.setMessage("选择是否删除此Task");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        HashMap<String, Object> hashMap = listItem.get(p);
                        String str = (String) hashMap.get("id");
                        int idItem = Integer.parseInt(str);
                        Task t = new Task(idItem);
                        dtoi.deleteTask(databaseHelper,t);
                        onStart();
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-docc
                    }
                });
        // 显示
        normalDialog.show();
        return true;
    }

    //编辑时间从后往前排序
    public Cursor queryByEditTimeLTOS(){
        Cursor cursor = dbRead.rawQuery("select * from task order by edittime DESC",null);
        return cursor;
    }

    //编辑时间从前往后排序
    public Cursor queryByEditTimeSTOL(){
        Cursor cursor = dbRead.rawQuery("select * from task order by edittime ASC",null);
        return cursor;
    }

    //id从小到大排序
    public Cursor queryByIdSTOL(){
        Cursor cursor = dbRead.rawQuery("select * from task order by edittime ASC",null);
        return cursor;
    }

    //id从大到小排序
    public Cursor queryByIdLTOS(){
        Cursor cursor = dbRead.rawQuery("select * from task order by edittime DESC",null);
        return cursor;
    }


}
