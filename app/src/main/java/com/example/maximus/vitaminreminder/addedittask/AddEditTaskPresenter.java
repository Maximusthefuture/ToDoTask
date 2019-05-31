package com.example.maximus.vitaminreminder.addedittask;

import android.app.TimePickerDialog;
import android.content.Context;
import androidx.annotation.Nullable;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.VitaminRepository;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;
import com.example.maximus.vitaminreminder.data.source.local.TasksLocalDataSource;

import java.util.Calendar;

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter, TasksLocalDataSource.GetTaskCallback {


    private AddEditTaskContract.View mAddTaskView;
    private final VitaminTaskRepository repository;

    @Nullable
    private String mTaskId;


    public AddEditTaskPresenter(String taskId, AddEditTaskContract.View mAddTaskView, Context context) {
        this.mAddTaskView = mAddTaskView;
        repository = new VitaminRepository(context);
        mTaskId = taskId;
        mAddTaskView.setPresenter(this);
    }


    @Override
    public void start() {
//        openTask();
        if (!isNewTask()) {
            populateTask();
        }

    }


    public void populateTask() {
        repository.getTask(mTaskId, this);
    }


    @Override
    public void setNotificationTime() {

    }

    @Override
    public void saveTask(String title, String time, Context context) {
        if (isNewTask()) {
            createTask(title, time, context);

        } else {

            updateTask(title, time);

        }
    }

    @Override
    public void createTask(String title, String time, Context context) {
        Task newTask = new Task(title, time);
        if (newTask.isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        } else {
            repository.saveTask(newTask);
            mAddTaskView.showTasksList();
//            LocalData localData = new LocalData(context);
//            NotificationUtils.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMinute(), newTask.getTime());
        }

    }

    @Override
    public void editTask() {
        mAddTaskView.showEditTask(mTaskId);
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    @Override
    public void onDestroy() {
        this.mAddTaskView = null;
    }

    @Override
    public void onTaskLoaded(Task task) {
        String title = task.getTitle();
        String time = task.getTime();
        mAddTaskView.showTitle(title);
        mAddTaskView.showTime(time);
    }

    @Override
    public void onDataNotAvailable() {

    }

    private void updateTask(String title, String time) {
        if (isNewTask()) {
            throw new RuntimeException("updateTask() was called but task is new");
        }
        repository.saveTask(new Task(mTaskId, title, time));
        mAddTaskView.showTasksList();
    }

    @Override
    public void setTimeInView(Calendar date, Context context, final TextView tvTimePicker) {
        final int hour = date.get(Calendar.HOUR_OF_DAY);
        final int minutes = date.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvTimePicker.setText(hourOfDay + " : " + minute);
            }
        }, hour, minutes, true);
        timePickerDialog.setTitle("Select time");
        timePickerDialog.show();
    }

    @Override
    public void deleteTask() {

        repository.deleteTask(mTaskId);
        mAddTaskView.showTasksList();

    }


}
