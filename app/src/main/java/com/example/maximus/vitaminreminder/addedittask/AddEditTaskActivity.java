package com.example.maximus.vitaminreminder.addedittask;

import android.os.Bundle;
;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.utils.ActivityUtils;

public class AddEditTaskActivity extends AppCompatActivity {

//    private TextView tvTimePicker;

    public static final int REQUEST_ADD_TASK = 1;
//    private AddEditTaskContract.Presenter mPresenter;
////    ??????????????
//    private Button button;
//    private TextView mTitle;
//    Calendar date = Calendar.getInstance();
//    int hour;
//    int minutes;

    private AddEditTaskPresenter mPresenter;


//  ADD ARGUMENT_EXTRA_TASK_ID!Set it to open task with this id! -- DONE
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_act);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Добавить лекарство");


        String taskId = getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EXTRA_TASK_ID);

        AddEditTaskFragment addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance();

            if (getIntent().hasExtra(AddEditTaskFragment.ARGUMENT_EXTRA_TASK_ID)) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditTaskFragment.ARGUMENT_EXTRA_TASK_ID, taskId);
                addEditTaskFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditTaskFragment, R.id.contentFrame);
        }

        mPresenter = new AddEditTaskPresenter(taskId ,addEditTaskFragment, getApplicationContext());



    }
}
