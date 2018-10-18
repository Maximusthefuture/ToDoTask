package com.example.maximus.vitaminreminder;


;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.pref.SettingsActivity;
import com.example.maximus.vitaminreminder.sync.ReminderTask;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private ImageView vitamin;
    private ImageView vitaminComplete;
    private boolean isClicked;
    private SharedPreferences sharedPreferences;
    private MaterialCalendarView materialCalendarView;
    LocalData localData;
    ReminderTask reminderTask;
    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vitamin = findViewById(R.id.iv_vitamin);
        vitaminComplete = findViewById(R.id.iv_vitamin_complete);
        materialCalendarView = findViewById(R.id.calendarView);
        clickVitamin();
        currentDate();
        customCalendarView = new CustomCalendarView(Color.BLUE, new CalendarDay(), materialCalendarView);

    }

    public void currentDate() {
        Calendar calendar = Calendar.getInstance();
        materialCalendarView.setDateSelected(calendar, true);
    }

    public void clickVitamin() {
        vitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitamin.setVisibility(View.INVISIBLE);
                vitaminComplete.setVisibility(View.VISIBLE);
                isClicked = true;
                customCalendarView.addDotSpan(Color.BLACK);
//                Toast.makeText(MainActivity.this, "Youuuupiiiii", Toast.LENGTH_SHORT).show();
//                NotificationUtils.showNotification(MainActivity.this);

                    vitaminComplete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            isClickedVitamin();
                            Toast.makeText(MainActivity.this,"Test notifications", Toast.LENGTH_SHORT).show();
                            testNotification();

                        }
                    });
            }
        });
    }


    public void testNotification() {
        NotificationUtils.showNotification(this);
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
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
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
