package com.example.maximus.vitaminreminder;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private TextView tvTimePicker;
    Calendar date = Calendar.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_frag);
        getSupportActionBar().setTitle("Добавить лекарство");
        tvTimePicker = findViewById(R.id.time_picker);

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
            }
        });



    }
}
