package com.example.maximus.vitaminreminder.addedittask;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {


    private final AddEditTaskContract.View mAddTaskView;
    private final VitaminTaskRepository repository;

    @Nullable
    private String mTaskId;


    public AddEditTaskPresenter(AddEditTaskContract.View mAddTaskView, Context context) {
        this.mAddTaskView = mAddTaskView;
        repository = new VitaminRepository(context);
    }
    @Override
    public void setNotificationTime() {

    }

    @Override
    public void saveTask(String title, String time) {
        if (isNewTask()) {
            createTask(title, time);
        } else {
//            updateTask(title, time);

        }

    }

    @Override
    public void createTask(String title, String time) {
        Task newTask = new Task(title, time);
        if (newTask.isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        } else {
            repository.saveTask(newTask);
            mAddTaskView.showTasksList();
        }

    }

    private boolean isNewTask() {
        return mTaskId == null;
    }




    @Override
    public void onDestroy() {

    }

}
