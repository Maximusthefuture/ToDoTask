package com.example.maximus.vitaminreminder.addedittask;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maximus.vitaminreminder.R;

import java.util.Calendar;

public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    public static final String ARGUMENT_EXTRA_TASK_ID = "TASK_ID";

    private AddEditTaskContract.Presenter mPresenter;
    private Button saveButton;
    private Button deleteButton;
    private TextView mTitle;
    Calendar date = Calendar.getInstance();
    int hour;
    int minutes;
    private TextView tvTimePicker;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mListAdapter = new TasksFragment.TasksAdapter(tasks, taskItemListener);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.addtask_frag, container, false);

        mTitle = root.findViewById(R.id.text_name_vitamin);
        tvTimePicker = root.findViewById(R.id.time_picker);
        saveButton = root.findViewById(R.id.save_button);
        deleteButton = root.findViewById(R.id.delete_button);
        hour = Calendar.HOUR_OF_DAY;
        minutes = Calendar.MINUTE;

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mPresenter.saveTask(mTitle.getText().toString(), tvTimePicker.getText().toString(), getContext());
            }
        });


        tvTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setTimeInView(date, getContext(), tvTimePicker);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.deleteTask();

            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

//    @Override
//    public void setTime() {
//        mPresenter.setTimeInView(date, getContext(), tvTimePicker);
//    }

    @Override
    public void showNotificationTime() {

    }

    @Override
    public void showTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void showTime(String time) {
        tvTimePicker.setText(time);
    }

    @Override
    public void showEmptyTaskError() {

    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();

    }

    @Override
    public void showEditTask(String taskId) {

    }

    @Override
    public void setPresenter(AddEditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }



}
