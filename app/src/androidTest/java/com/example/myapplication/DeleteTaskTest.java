package com.example.myapplication;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/24.
 */
public class DeleteTaskTest extends InstrumentationTestCase {
    UiDevice uiDevice = null;
    @Before
    public void setUp(){
        uiDevice = UiDevice.getInstance(getInstrumentation());
    }

    public void testDeleteTask() throws UiObjectNotFoundException, InterruptedException {
        //open the app
        UiObject textViewApp = new UiObject(new UiSelector().index(23));
        textViewApp.clickAndWaitForNewWindow();

        //click long time on task
        UiObject textViewTask = new UiObject(new UiSelector().text("数学作业周五交"));
        textViewTask.longClick();

        //select the ensure and back the main screen
        UiObject buttonEnsure = new UiObject(new UiSelector().text("确定"));
        buttonEnsure.clickAndWaitForNewWindow();


    }
}