package com.example.maximus.vitaminreminder.timepicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.tasks.TasksActivity;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class TimePickerActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TimePicker timePicker;
    private TextView setTime;
    private LocalData localData;
    private Button putTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_time_picker);
        timePicker = findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        setTime = findViewById(R.id.set_time);
        putTime = findViewById(R.id.put_time);
        localData = new LocalData(this);
        selectTime();
        setTime.setText(localData.getStringHour());
        setPutTime();
    }


//    public void getTime() {
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(TimePickerActivity.this);
//        setTime.setText(sharedPreferences.getString("timeSet", null));
//    }

    public void setPutTime() {
        putTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationUtils.setReminder(TimePickerActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMinute());
                Toast.makeText(TimePickerActivity.this, "Time set", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TimePickerActivity.this, TasksActivity.class);
                startActivity(intent);
            }
        });
    }


    public void selectTime() {
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                Toast.makeText(TimePickerActivity.this, "Selected time is \n" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
//                hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
//                minute = calendar.get(Calendar.MINUTE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    view.setHour(hourOfDay);
//                    view.setMinute(minute);
//                } else {
//                    view.setCurrentHour(hourOfDay);
//                    view.setCurrentMinute(minute);
//                }

                String timeSet = new StringBuilder()
                        .append(hourOfDay).append(":")
                        .append(minute).toString();

                setTime.setText(timeSet);
                localData.setStringHour(timeSet);
                localData.setHour(hourOfDay);
                localData.setMinute(minute);

            }
        });
    }




}
