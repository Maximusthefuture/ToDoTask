package com.example.maximus.vitaminreminder.tasks;

import android.content.Context;

import com.example.maximus.vitaminreminder.BasePresenter;
import com.example.maximus.vitaminreminder.BaseView;
import com.example.maximus.vitaminreminder.data.Task;

import java.util.ArrayList;
import java.util.List;

public interface TasksContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator();

        void showTasks(List<Task> tasks);

        void showTaskDetailUi(String taskId);

        void showAddTask();

        //???
        void setBottomNavigationView();

        void showNoTasks();

        void showNotification();


    }


    interface Presenter extends BasePresenter {

        void start(Context context);

        void result(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate, Context context);

        void addNewTask();

        void openTaskDetails(Task requestTask);

        void activeTask(Task activeTask);

        void deleteAll(Context context);

    }
}
