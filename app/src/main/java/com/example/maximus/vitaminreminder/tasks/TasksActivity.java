package com.example.maximus.vitaminreminder;



import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.pref.SettingsActivity;
import com.example.maximus.vitaminreminder.sync.ReminderTask;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import java.util.Calendar;


//REFACTOR AND ADD NEW FEATURE
//TODO: 1: MVP
//TODO: 2: Add bottom navigation bar: DONE
//Add here 4 bottoms : Calendar, List of Vitamins, Weather, More(There is new Activity, that include settings and other feature): DONE
//TODO: Add a editable fragment to add add edit time and other things
//TODO: Нужно вместо Activity использовать Fragments, потому-что мы используем BotttomNavigationBar
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ImageView vitamin;
    private ImageView vitaminComplete;
    private boolean isClicked;
    private SharedPreferences sharedPreferences;
    private MaterialCalendarView materialCalendarView;
    LocalData localData;
    ReminderTask reminderTask;
    CustomCalendarView customCalendarView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
                "Костя", "Игорь", "Анна", "Денис", "Андрей" };


        ListView lvMain = findViewById(R.id.tasks_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        lvMain.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });




//        vitamin = findViewById(R.id.iv_vitamin);
//        vitaminComplete = findViewById(R.id.iv_vitamin_complete);
//        materialCalendarView = findViewById(R.id.calendarView);
//        clickVitamin();
//        currentDate();
//        customCalendarView = new CustomCalendarView(Color.BLUE, CalendarDay.today(), materialCalendarView);
//        updateVitaminIcon(customCalendarView);

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        prefs.registerOnSharedPreferenceChangeListener(this);

    }

//    public void currentDate() {
//        Calendar calendar = Calendar.getInstance();
//        materialCalendarView.setDateSelected(calendar, true);
//    }

//    public void clickVitamin() {
//        vitamin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vitamin.setVisibility(View.INVISIBLE);
//                vitaminComplete.setVisibility(View.VISIBLE);
//                isClicked = true;
////                customCalendarView.addDotSpan(Color.BLACK);
//                updateVitaminIcon(customCalendarView);
//
//
//                    vitaminComplete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            isClickedVitamin();
//                            Toast.makeText(MainActivity.this,"Test notifications", Toast.LENGTH_SHORT).show();
//                            testNotification();
//
//                        }
//                    });
//            }
//        });
//    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public boolean isClickedVitamin() {
        if (isClicked) {
            vitamin.setVisibility(View.VISIBLE);
            vitaminComplete.setVisibility(View.INVISIBLE );
        }
        return true;
    }

    //??????
    public void updateVitaminIcon(CustomCalendarView customCalendarView) {
        int color = ReminderTask.getColor(this);
        customCalendarView.addDotSpan(color);
        customCalendarView.shouldDecorate(CalendarDay.today());
        vitaminComplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (ReminderTask.KEY_COLOR.equals(key)) {
//            updateVitaminIcon(customCalendarView);
        }
    }
}
