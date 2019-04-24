package com.example.maximus.vitaminreminder.tasks;

import android.content.Context;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;
import com.example.maximus.vitaminreminder.data.source.local.TaskDbHelper;
import com.example.maximus.vitaminreminder.data.source.local.TasksLocalDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//TODO: Реализовать Presenter до конца
public class TasksPresenter implements TasksContract.Presenter {

    private  TasksContract.View mView;
    private VitaminRepository mVitaminRepository;


    public TasksPresenter(TasksContract.View taskView, Context context) {
        mVitaminRepository = new VitaminRepository(context);
        mView = taskView;
        mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        loadTasks(false, context);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate, Context context) {
        loadTasks(context);
    }

    private void loadTasks(final Context context) {
//        if (showLoadingUI) {
//            mView.setLoadingIndicator();
//        }
//        if (forceUpdate) {
//
//        }

        mVitaminRepository.getTasks(new VitaminTaskRepository.LoadTaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                //Нельзя логику держать в Presenter
                //WORKING

//                TaskDbHelper db = new TaskDbHelper(context);
//                List<Task> taskToShow = new ArrayList<>(db.getAllTasks());
//
//                processTasks(taskToShow);

//                -------------------------------------------

                List<Task> taskToShow = new ArrayList<>();

                for (Task task : tasks) {
                    taskToShow.add(task);

                }
                processTasks(taskToShow);
//
//
            }



            @Override
            public void onDataNotAvailable() {

            }


        });
    }

    private void processTasks(List<Task> tasks) {
        mView.showTasks(tasks);
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
    public void activeTask(Task activeTask) { }

    @Override
    public void onDestroy() {
        this.mView = null;
    }
}
