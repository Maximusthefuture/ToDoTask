package com.example.maximus.vitaminreminder.addedittask;

import android.content.Context;
import android.widget.TextView;

import com.example.maximus.vitaminreminder.BasePresenter;
import com.example.maximus.vitaminreminder.BaseView;

import java.util.Calendar;

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter> {

        void showNotificationTime();

        void showTitle(String title);

        void showTime(String time);

        void showEmptyTaskError();

        void showTasksList();

        void showEditTask(String taskId);

//        void setTime();





    }

    interface Presenter extends BasePresenter {

        void setNotificationTime();

        void saveTask(String title, String time, Context context);

        void createTask(String title, String time, Context context);

        void editTask();

        void setTimeInView(Calendar date, Context context, final TextView tvTimePicker);

        void deleteTask();




    }
}
