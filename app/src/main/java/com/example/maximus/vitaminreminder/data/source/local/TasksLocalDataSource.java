package com.example.maximus.vitaminreminder.data.source.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
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


    //TODO: STOPPED HERE!!!!!

    @Override
    public void getTasks(final LoadTaskCallback callback) {

         List<Task> tasks = mTaskDbHelper.getTasks();
         callback.onTaskLoaded(tasks);


    }

    @Override
    public void saveTask(Task task) {
        mTaskDbHelper.addTask(task);
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

}
