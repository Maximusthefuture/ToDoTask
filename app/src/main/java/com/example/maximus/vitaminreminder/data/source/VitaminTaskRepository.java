package com.example.maximus.vitaminreminder.data.source;

import com.example.maximus.vitaminreminder.data.Task;

import java.util.ArrayList;
import java.util.List;

public interface VitaminTaskRepository {


    interface LoadTaskCallback {

        void onTaskLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }


    void getTasks(LoadTaskCallback callback);

    void getTask(String taskId, GetTaskCallback callback);

    void saveTask(Task task);

    void completeTask(Task task);

    void completeTask(String taskId);

    void deleteAllTasks();

    void setNotificationTime();


}
