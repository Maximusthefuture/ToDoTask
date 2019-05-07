package com.example.maximus.vitaminreminder.data.source.local;

import android.content.Context;

import com.example.maximus.vitaminreminder.data.Task;

import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TasksLocalDataSource  implements VitaminTaskRepository {

    private static volatile TasksLocalDataSource INSTANCE;

    private TaskDbHelper mTaskDbHelper;




    public TasksLocalDataSource(Context context) {
        mTaskDbHelper = new TaskDbHelper(context);
    }

//    public static TasksLocalDataSource getInstance(TaskDbHelper taskDbHelper) {
//        if (INSTANCE == null) {
//            synchronized (TasksLocalDataSource.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new TasksLocalDataSource(taskDbHelper);
//                }
//            }
//        }
//        return INSTANCE;
//    }


    @Override
    public void getTasks(final LoadTaskCallback callback) {

         List<Task> tasks = mTaskDbHelper.getAllTasks();
         callback.onTaskLoaded(tasks);

    }

    @Override
    public void getTask(String taskId, GetTaskCallback callback) {
        Task task = mTaskDbHelper.getTaskById(taskId);
        callback.onTaskLoaded(task);
    }

    @Override
    public void saveTask(Task task) {
        if (task.getId() == null) {
            mTaskDbHelper.addTask(task);
        } else {
            mTaskDbHelper.updateTask(task);
        }

    }

    @Override
    public void completeTask(Task task) {

    }

    @Override
    public void completeTask(String taskId) {

    }

    @Override
    public void deleteAllTasks() {
        mTaskDbHelper.deleteTasks();
    }

    @Override
    public void setNotificationTime() {

    }

}
