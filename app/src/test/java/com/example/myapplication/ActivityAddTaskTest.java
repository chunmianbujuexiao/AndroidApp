package com.example.myapplication;


import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;



/**
 * Created by Administrator on 2017/5/24.
 */



public class ActivityAddTaskTest extends UiAutomatorTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
//    public static final String TAG = "U2Test";
//
//
//    @AfterClass
//    public static void afterClass(){
//        Log.d(TAG, "AfterClass");
//    }
//
//    @Before
//    public void before(){
//        Log.d(TAG, "Before");
//    }
//
//    @After
//    public void after(){
//        Log.d(TAG, "After");
//    }

    public void testAddTask() throws UiObjectNotFoundException {
        UiDevice.getInstance().pressHome();
        Log.e("UiTest","SUCCESS");
        System.out.println("TEST SUCCESS");
        sleep(2000);
        UiDevice.getInstance().pressMenu();
//        //click add task button
//        UiObject imageButton = new UiObject(new UiSelector().className("android.widget.ImageButton"));
//        imageButton.clickAndWaitForNewWindow();
//
//        //add the task
//        UiObject editTextTitle = new UiObject(new UiSelector().text("任务标题"));
//        editTextTitle.setText("数学作业周五交");
//        UiObject editTextContext = new UiObject(new UiSelector().text("写下具体任务"));
//        editTextContext.setText("作业第一章:p1,p3,p5");
//
//        //add start time
//        UiObject iamgeButtonStartTime = new UiObject(new UiSelector().resourceIdMatches("com.example.myapplication:id/id_imagebutton_add_task_set_start_time"));
//        iamgeButtonStartTime.clickAndWaitForNewWindow();
//        UiObject button1 = new UiObject(new UiSelector().text("确 定"));
//        button1.clickAndWaitForNewWindow();
//
//        //add end time
//        UiObject iamgeButtonEntTime = new UiObject(new UiSelector().resourceIdMatches("com.example.myapplication:id/id_imagebutton_add_task_set_start_time"));
//        iamgeButtonEntTime.clickAndWaitForNewWindow();
//        UiObject viewDay25 = new UiObject(new UiSelector().text("25"));
//        viewDay25.click();
//        sleep(100);
//        UiObject button2 = new UiObject(new UiSelector().text("确 定"));
//        button1.clickAndWaitForNewWindow();
//
//        //commit task
//        UiObject textViewCommit = new UiObject(new UiSelector().text("COMMIT"));
//        textViewCommit.click();
//        sleep(100);
//
//        //back to default fragment
//        UiObject imageButtonBack = new UiObject(new UiSelector().className("android.widget.ImageButton"));
//        imageButtonBack.clickAndWaitForNewWindow();
    }

//    @Test
//    public void test002() {
//        Log.d(TAG, "test002");
//    }
}