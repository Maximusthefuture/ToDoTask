package com.example.maximus.vitaminreminder.addedittask;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.data.source.VitaminTaskRepository;
import com.example.maximus.vitaminreminder.tasks.TasksContract;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements AddEditTaskContract.View {

    private TextView tvTimePicker;
    public static final String EXTRA_TASK_ID = "TASK_ID";
    public static final int REQUEST_ADD_TASK = 1;
    private AddEditTaskContract.Presenter mPresenter;
//    ??????????????
    private Button button;
    private TextView mTitle;
    Calendar date = Calendar.getInstance();

    //TODO: ADD EXTRA_TASK_ID!Set it to open task with this id!
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_frag);
        getSupportActionBar().setTitle("Добавить лекарство");
        mTitle = findViewById(R.id.text_name_vitamin);
        tvTimePicker = findViewById(R.id.time_picker);
        button = findViewById(R.id.done_button);
//        ? Repository?
        mPresenter = new AddEditTaskPresenter(this, this);
//        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);
//        Bundle arguments = new Bundle();
//        arguments.putString(EXTRA_TASK_ID, taskId);
        Intent mIntent = getIntent();
        int taskId = mIntent.getIntExtra(EXTRA_TASK_ID, 0);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(AddActivity.this, "Done", Toast.LENGTH_SHORT).show();
                mPresenter.saveTask(mTitle.getText().toString(), tvTimePicker.getText().toString());
            }
        });

        setTime();

    }

    public void setTime() {

        final TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                tvTimePicker.setText(DateUtils.formatDateTime(AddActivity.this,
                        date.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
            }
        };

        tvTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddActivity.this, t,
                        date.get(Calendar.HOUR_OF_DAY),
                        date.get(Calendar.MINUTE), true)
                        .show();
                tvTimePicker.setText((date.get(Calendar.HOUR_OF_DAY)) + " : " + (date.get(Calendar.MINUTE)));
            }
        });
    }

    @Override
    public void showNotificationTime() {

    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setTime(int hour, int minute) {
        tvTimePicker.setText(hour);
    }

    @Override
    public void showEmptyTaskError() {

    }

    @Override
    public void showTasksList() {
        setResult(Activity.RESULT_OK);
        finish();

    }

    @Override
    public void setPresenter(AddEditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
