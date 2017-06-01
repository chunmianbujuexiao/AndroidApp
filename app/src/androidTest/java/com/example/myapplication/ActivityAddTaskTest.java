package com.example.myapplication;

import android.app.UiAutomation;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/24.
 */
public class ActivityAddTaskTest extends InstrumentationTestCase {

    UiDevice uiDevice = null;
    @Before
    public void setUp() throws Exception {
        uiDevice = UiDevice.getInstance(getInstrumentation());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddTask() throws Exception {

        //open the app
        UiObject textViewApp = new UiObject(new UiSelector().index(23));
        textViewApp.clickAndWaitForNewWindow();

        //click add task button
        UiObject imageButton = new UiObject(new UiSelector().resourceId("com.example.myapplication:id/id_fab_main"));
        imageButton.clickAndWaitForNewWindow();

        //add the task
        UiObject editTextTitle = new UiObject(new UiSelector().text("任务标题"));
        editTextTitle.setText("数学作业周五交");
        UiObject editTextContext = new UiObject(new UiSelector().text("写下具体任务"));
        editTextContext.setText("作业第一章:p1,p3,p5");

        //add start time
        UiObject iamgeButtonStartTime = new UiObject(new UiSelector().resourceId("com.example.myapplication:id/id_imagebutton_add_task_set_start_time"));
        iamgeButtonStartTime.clickAndWaitForNewWindow();
        UiObject button1 = new UiObject(new UiSelector().text("确 定"));
        button1.clickAndWaitForNewWindow();

        //add end time
        UiObject iamgeButtonEntTime = new UiObject(new UiSelector().resourceId("com.example.myapplication:id/id_imagebutton_add_task_set_end_time"));
        iamgeButtonEntTime.clickAndWaitForNewWindow();
        UiObject viewDay25 = new UiObject(new UiSelector().text("25"));
        viewDay25.click();
        Thread.sleep(1000);
        UiObject button2 = new UiObject(new UiSelector().text("确 定"));
        button2.clickAndWaitForNewWindow();

        //commit task
        UiObject textViewCommit = new UiObject(new UiSelector().text("COMMIT"));
        textViewCommit.click();
        Thread.sleep(1000);
        //back to default fragment
        UiObject imageButtonBack = new UiObject(new UiSelector().className("android.widget.ImageButton"));
        imageButtonBack.clickAndWaitForNewWindow();


    }
}