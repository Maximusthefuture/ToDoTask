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

    Map<String, Task> mCachedTasks;


    boolean mCacheIsDirty = false;

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
//        if (mCachedTasks != null && !mCacheIsDirty) {
//            callback.onTaskLoaded(new ArrayList<Task>());
//            return;
//        }
//
//        if (mCacheIsDirty) {
//
//        } else {
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
    public void saveTask(Task task) {
        mTaskLocalDataSource.saveTask(task);

//        if (mCachedTasks == null) {
//            mCachedTasks = new LinkedHashMap<>();
//        }
//        mCachedTasks.put(task.getId(), task);
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
}
