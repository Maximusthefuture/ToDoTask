package com.example.maximus.vitaminreminder.tasks;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.utils.ActivityUtils;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;


//REFACTOR AND ADD NEW FEATURE
//TODO: MVP
//TODO: Add a editable fragment to add add edit time and other things
//TODO: №1 Refactor all activities to fragments
public class TasksActivity extends AppCompatActivity {

    private static final String TAG = "TasksActivity";
    private static final int REQUEST_EDIT_TASK = 1;
    public static final String EXTRA_TASK_ID = "TASK_ID";
    private BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_frag);

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        if (tasksFragment == null) {
            tasksFragment = TasksFragment.newInstance(taskId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.contentFrame);

        }

//        bottomNavigationView = findViewById(R.id.nav_bottom_view);
//
//        disableShiftMode(bottomNavigationView);

//        bottomNavigationViewTest();

    }



    public void testNotification() {
        NotificationUtils.showNotification(this);
    }


}
