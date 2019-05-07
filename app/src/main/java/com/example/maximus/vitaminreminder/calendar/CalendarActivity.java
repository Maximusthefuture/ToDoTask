package com.example.maximus.vitaminreminder.calendar;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.utils.ActivityUtils;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        CalendarFragment calendarFragment =
                (CalendarFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        if (calendarFragment == null) {
            calendarFragment = CalendarFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), calendarFragment, R.id.contentFrame);

        }
    }
}
