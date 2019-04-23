package com.example.maximus.vitaminreminder.addedittask;

import com.example.maximus.vitaminreminder.BasePresenter;
import com.example.maximus.vitaminreminder.BaseView;

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter> {

        void showNotificationTime();

        void setTitle(String title);

        void setTime(int hour, int minute);

        void showEmptyTaskError();

        void showTasksList();




    }

    interface Presenter extends BasePresenter {

        void setNotificationTime();

        void saveTask(String title, String time);

        void createTask(String title, String time);

    }
}
