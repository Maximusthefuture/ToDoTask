package com.example.maximus.vitaminreminder;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maximus.vitaminreminder.pref.SettingsActivity;
import com.example.maximus.vitaminreminder.pref.TimePreference;
import com.example.maximus.vitaminreminder.timepicker.TimePickerActivity;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView timeWatch;
    private ImageView vitamin;
    private ImageView vitaminComplete;
    private boolean isClicked;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        timeWatch = findViewById(R.id.time_watch);
        vitamin = findViewById(R.id.iv_vitamin);
        vitaminComplete = findViewById(R.id.iv_vitamin_complete);
        clickVitamin();

    }

    public void chooseTime(View view) {
        Calendar mCurrentTime = Calendar.getInstance();
        final int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minutes = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeWatch.setText(hourOfDay + " : " + minute);
            }
        }, hour, minutes, true);
        timePickerDialog.setTitle("Select time");
        timePickerDialog.show();
    }

    public void clickVitamin() {
        vitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitamin.setVisibility(View.INVISIBLE);
                vitaminComplete.setVisibility(View.VISIBLE);
                isClicked = true;
//                Toast.makeText(MainActivity.this, "Youuuupiiiii", Toast.LENGTH_SHORT).show();
//                NotificationUtils.showNotification(MainActivity.this);

                    vitaminComplete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isClickedVitamin();
                            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            String pref = sharedPreferences.getString("timeSet", null);
                            Toast.makeText(MainActivity.this,"Settings time is : "+ pref, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, TimePickerActivity.class);
                startActivity(intent);

                default:
                    super.onOptionsItemSelected(item);
        }
        return true;
    }

    public boolean isClickedVitamin() {
        if (isClicked) {
            vitamin.setVisibility(View.VISIBLE);
            vitaminComplete.setVisibility(View.INVISIBLE );
        }
        return true;
    }
}
