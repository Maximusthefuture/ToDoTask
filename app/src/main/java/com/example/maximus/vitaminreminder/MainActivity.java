package com.example.maximus.vitaminreminder;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
;

import com.example.maximus.vitaminreminder.calendar.CalendarFragment;
import com.example.maximus.vitaminreminder.more_options.MoreOptionFragment;
import com.example.maximus.vitaminreminder.more_options.MoreOptionsActivity;
import com.example.maximus.vitaminreminder.news_list.NewsFragment;
import com.example.maximus.vitaminreminder.tasks.TasksFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    final Fragment tasksFragment = new TasksFragment();
    final Fragment calendarFragment = new CalendarFragment();
    final Fragment newsFragment = new NewsFragment();
    final Fragment settingFragment = new MoreOptionFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = tasksFragment;


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.nav_bottom_view);

//        disableShiftMode(bottomNavigationView);

        bottomNavigationViewTest();

        fm.beginTransaction().add(R.id.main_container, settingFragment, "4").hide(settingFragment).commit();
        fm.beginTransaction().add(R.id.main_container, newsFragment, "3").hide(newsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, calendarFragment, "2").hide(calendarFragment).commit();
        fm.beginTransaction().add(R.id.main_container, tasksFragment, "1").commit();
    }


    public void bottomNavigationViewTest() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_tasks:
                       fm.beginTransaction().hide(active).show(tasksFragment).commit();
                       active = tasksFragment;
                       return true;
                    case R.id.action_calendar:
                        fm.beginTransaction().hide(active).show(calendarFragment).commit();
                        active = calendarFragment;
                        return true;

                    case R.id.action_news:
                        fm.beginTransaction().hide(active).show(newsFragment).commit();
                        active = newsFragment;
                        return true;

                    case R.id.action_more:
                        fm.beginTransaction().hide(active).show(settingFragment).commit();
                        active = settingFragment;
//                        Intent intent = new Intent(MainActivity.this, MoreOptionsActivity.class);
//                        startActivity(intent);
                        return true;

                }
                return false;
            }
        });
    }

    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }



}
