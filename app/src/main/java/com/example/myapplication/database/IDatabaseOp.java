package com.example.myapplication.database;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface IDatabaseOp {
    public Task queryById(DatabaseHelper d, Task t);
    public boolean updateTask(DatabaseHelper d, Task t);
    public boolean addTask(DatabaseHelper d, Task t);
    public boolean deleteTask(DatabaseHelper d, Task t);

}
