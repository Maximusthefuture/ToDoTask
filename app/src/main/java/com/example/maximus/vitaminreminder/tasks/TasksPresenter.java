package com.example.maximus.vitaminreminder.tasks;

import android.content.Context;
import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;
import java.util.ArrayList;
import java.util.List;


//TODO: Реализовать Presenter до конца
public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;
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

        mVitaminRepository.getTasks(new VitaminTaskRepository.LoadTaskCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                List<Task> taskToShow = new ArrayList<>();

                for (Task task : tasks) {
                    taskToShow.add(task);

                }
                processTasks(taskToShow);

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
    public void deleteAll(Context context) {
        mVitaminRepository.deleteAllTasks();
        loadTasks(context);

    }

    @Override
    public void completeTask(Task completedTask) {

    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public void start() {

    }
}
