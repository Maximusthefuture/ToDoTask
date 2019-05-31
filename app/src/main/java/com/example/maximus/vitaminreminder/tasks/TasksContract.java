package com.example.maximus.vitaminreminder.tasks;

import android.content.Context;

import com.example.maximus.vitaminreminder.BasePresenter;
import com.example.maximus.vitaminreminder.BaseView;
import com.example.maximus.vitaminreminder.data.Task;

import java.util.ArrayList;
import java.util.List;

public interface TasksContract {

    interface View extends BaseView<Presenter>{

        void showTasks(List<Task> tasks);

        void showTaskDetailUi(String taskId);

        void showAddTask();

        void showSuccessfullySavedMessage();

        void showNoTasks();

        //TODO
        void showNotification();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTasks();

        void addNewTask();

        void openTaskDetails(Task requestTask);

        void deleteAll(Context context);

        //TODO
        void completeTask(Task completedTask);

    }
}
