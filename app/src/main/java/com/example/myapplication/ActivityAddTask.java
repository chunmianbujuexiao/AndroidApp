package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.database.DatabaseTaskOpImpl;
import com.example.myapplication.database.Task;

import java.util.Calendar;

public class ActivityAddTask extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTitle = null;
    private EditText editTextContent = null;
    private DatabaseHelper databaseHelper = null;
    private ImageButton imageButtonClock = null;
    private ImageButton iamgeButtonStartTime = null;
    private ImageButton imageButtonEndTime = null;
    private TextView textViewClockDisplay = null;
    private TextView textViewStartTime = null;
    private TextView textViewEndTime = null;
    private String clocktime = null;
    private String starttime = null;
    private String endtime = null;
    private int id = -1;
    private DatabaseTaskOpImpl dtoi = new DatabaseTaskOpImpl();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar_add_task);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextTitle = (EditText)findViewById(R.id.id_edtitext_add_task_title);
        editTextContent = (EditText)findViewById(R.id.id_edtitext_add_task_content);
        imageButtonClock = (ImageButton) findViewById(R.id.id_imagebutton_add_task_set_clock_time);
        iamgeButtonStartTime = (ImageButton) findViewById(R.id.id_imagebutton_add_task_set_start_time);
        imageButtonEndTime = (ImageButton) findViewById(R.id.id_imagebutton_add_task_set_end_time);
        textViewClockDisplay = (TextView) findViewById(R.id.id_textview_add_task_set_clock_time);
        textViewStartTime = (TextView) findViewById(R.id.id_textview_add_task_set_start_time);
        textViewEndTime = (TextView) findViewById(R.id.id_textview_add_task_set_end_time);

        imageButtonClock.setOnClickListener(this);
        iamgeButtonStartTime.setOnClickListener(this);
        imageButtonEndTime.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(this);

        //获取点击的listview的id，加载相应内容
        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getInt("id");
        if(id >= 0 ){
            Task t = new Task(id);
            Task tnew = dtoi.queryById(databaseHelper,t);
            SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();
            editTextTitle.setText(tnew.getTitle());
            editTextContent.setText(tnew.getContent());
            textViewClockDisplay.setText("闹钟时间："+tnew.getClocktime());
            textViewStartTime.setText("开始时间："+tnew.getStarttime());
            textViewEndTime.setText("结束时间："+tnew.getEndtime());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_add_task, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.id_toolbar_add_task_commit:
                //添加保存或者更新操作
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();

                if(id == -1){
                    //添加任务信息
                    Task t = new Task(title,content,clocktime,starttime,endtime);
                    boolean b = dtoi.addTask(databaseHelper,t);

                }else{
                    //更新任务信息
                    Task t = new Task(id,title,content,clocktime,starttime,endtime);
                    boolean b = dtoi.updateTask(databaseHelper,t);
                }
                //还原默认内容
                setDefaultDisplay();
                break;

            case R.id.id_toolbar_add_task_cancel:
                //还原默认内容
                setDefaultDisplay();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setTime(id);
    }

    public void setDefaultDisplay(){
        editTextTitle.setText("");
        editTextContent.setText("");
        textViewClockDisplay.setText("设置闹钟");
        textViewStartTime.setText("设置开始时间");
        textViewEndTime.setText("设置结束时间");
        clocktime = "";
        starttime = "";
        endtime = "";
    }

    public void setTime(int id){
        switch(id){
            case R.id.id_imagebutton_add_task_set_clock_time:
                //出现设置闹钟界面
                Calendar c = Calendar.getInstance();
                new DateAndTimePickerDialog(ActivityAddTask.this, 0, new DateAndTimePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth, TimePicker TimePicker, int hourOfDay, int minute) {
                        String textString = String.format("%d-%d-%d %d：%d\n", year,
                                monthOfYear , dayOfMonth, hourOfDay, minute);
                        textViewClockDisplay.setText("闹钟时间: "+textString);
                        clocktime = textString;
                    }

                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
                break;
            case R.id.id_imagebutton_add_task_set_start_time:
                //出现设置锁屏开始时间界面
                Calendar c1 = Calendar.getInstance();
                new DateAndTimePickerDialog(ActivityAddTask.this, 0, new DateAndTimePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth, TimePicker TimePicker, int hourOfDay, int minute) {
                        String textString = String.format("%d-%d-%d %d：%d\n", year,
                                monthOfYear , dayOfMonth, hourOfDay, minute);
                        textViewStartTime.setText("开始时间："+textString);
                        starttime = textString;
                    }

                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE), true).show();
                break;
            case R.id.id_imagebutton_add_task_set_end_time:
                //出现设置锁屏结束时间界面
                Calendar c2 = Calendar.getInstance();
                new DateAndTimePickerDialog(ActivityAddTask.this, 0, new DateAndTimePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth, TimePicker TimePicker, int hourOfDay, int minute) {
                        String textString = String.format("%d-%d-%d %d：%d\n", year,
                                monthOfYear , dayOfMonth, hourOfDay, minute);
                        textViewEndTime.setText("结束时间："+textString);
                        endtime = textString;
                    }

                }, c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DATE), true).show();
                break;

        }
    }
}

