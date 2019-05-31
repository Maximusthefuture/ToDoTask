package com.example.maximus.vitaminreminder.tasks;

import android.app.Activity;
import android.content.Context;

import com.example.maximus.vitaminreminder.addedittask.AddEditTaskActivity;
import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;
import java.util.ArrayList;
import java.util.List;



public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;

    private VitaminRepository mVitaminRepository;

    private boolean mFirstLoad = true;


    public TasksPresenter(TasksContract.View taskView, Context context) {
        mVitaminRepository = new VitaminRepository(context);
        mView = taskView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks();
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mView.showSuccessfullySavedMessage();
        }

    }

    @Override
    public void loadTasks() {
        mVitaminRepository.getTasks(new VitaminTaskRepository.LoadTaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                List<Task> taskToShow = new ArrayList<>();

                for (Task task : tasks) {
                    taskToShow.add(task);

                }

               checkTasks(taskToShow);

            }

            @Override
            public void onDataNotAvailable() {

            }

        });
    }


    @Override
    public void addNewTask() {
        mView.showAddTask();
    }

    @Override
    public void openTaskDetails(Task requestTask) {
        mView.showTaskDetailUi(requestTask.getId());
    }


    @Override
    public void deleteAll(Context context) {
        mVitaminRepository.deleteAllTasks();
        loadTasks();
    }



    @Override
    public void completeTask(Task completedTask) {

    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    private void checkTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            mView.showNoTasks();
        } else {
            mView.showTasks(tasks);
        }
    }


}
