package com.example.maximus.vitaminreminder.data.source;

import android.content.Context;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.local.TasksLocalDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VitaminRepository implements VitaminTaskRepository {

    private static VitaminRepository INSTANCE = null;

    public TasksLocalDataSource mTaskLocalDataSource;


    public VitaminRepository(Context context) {
        mTaskLocalDataSource = new TasksLocalDataSource(context);

    }

    // Create DB class or interface (Read about it) dont use room yet -- DONE
    // Create TaskLocalDataSource class or named something else that use a DB class -- DONE
    // Done with saveTask method -- DONE

//    public static VitaminRepository getInstance(TasksLocalDataSource tasksLocalDataSource) {
//        if (INSTANCE == null) {
//            INSTANCE = new VitaminRepository(tasksLocalDataSource);
//        }
//        return INSTANCE;
//    }

    @Override
    public void getTasks(final LoadTaskCallback callback) {

            mTaskLocalDataSource.getTasks(new LoadTaskCallback() {
                @Override
                public void onTaskLoaded(List<Task> tasks) {

                    callback.onTaskLoaded(tasks);
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
//        }
    }

    @Override
    public void getTask(String taskId, final GetTaskCallback callback) {
//        Task cachedTask = getTaskWithId(taskId);

        mTaskLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
//                task = new Task(task.getId(), task.getTitle(), task.getTime());
//                mCachedTasks.put(task.getId(), task);
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void saveTask(Task task) {
        mTaskLocalDataSource.saveTask(task);
//        mCachedTasks.put(task.getId(), task);


//        if (mCachedTasks == null) {
//            mCachedTasks = new LinkedHashMap<>();
//        }
//        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(String taskId) {
        mTaskLocalDataSource.deleteTask(taskId);
    }

    @Override
    public void completeTask(Task task) {

    }

    @Override
    public void completeTask(String taskId) {

    }

    @Override
    public void deleteAllTasks() {
        mTaskLocalDataSource.deleteAllTasks();
    }

    @Override
    public void setNotificationTime() {

    }


}
