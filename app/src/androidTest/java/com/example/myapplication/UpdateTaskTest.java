package com.example.myapplication;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UpdateTaskTest extends InstrumentationTestCase{
    UiDevice uiDevice = null;
    @Before
    public void setUp(){
        uiDevice = UiDevice.getInstance(getInstrumentation());
    }

    public void testUpdateTask() throws UiObjectNotFoundException {
        //open the app
        UiObject textViewApp = new UiObject(new UiSelector().index(23));
        textViewApp.clickAndWaitForNewWindow();

        //click long time on task
        UiObject textViewTask = new UiObject(new UiSelector().text("数学作业周五交"));
        textViewTask.clickAndWaitForNewWindow();
        //update the content of task
        UiObject edittext = new UiObject(new UiSelector().resourceId("com.example.myapplication:id/id_edtitext_add_task_content"));
        edittext.setText(edittext.getText()+"第二章:p2,p3,p4");
        //commit the task
        UiObject imageButtonCommit = new UiObject(new UiSelector().text("COMMIT"));
        imageButtonCommit.click();

        //back the man screen
        UiObject imageButtonBack = new UiObject(new UiSelector().className("android.widget.ImageButton"));
        imageButtonBack.clickAndWaitForNewWindow();
    }


}