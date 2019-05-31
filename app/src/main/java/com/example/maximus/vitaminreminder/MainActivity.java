package com.example.maximus.vitaminreminder;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;


import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.maximus.vitaminreminder.calendar.CalendarFragment;
import com.example.maximus.vitaminreminder.more_options.MoreOptionFragment;
import com.example.maximus.vitaminreminder.more_options.MoreOptionsActivity;
import com.example.maximus.vitaminreminder.news_list.NewsFragment;
import com.example.maximus.vitaminreminder.tasks.TasksFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;


//TODO: ADD TESTING!
//TODO: Add authorization
//TODO: ADD Notification
//TODO: Add CustomCalendar
public class MainActivity extends AppCompatActivity {

    final Fragment tasksFragment = new TasksFragment();
    final Fragment calendarFragment = new CalendarFragment();
    final Fragment newsFragment = new NewsFragment();
    final Fragment settingFragment = new MoreOptionFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private LinearLayout linearLayoutToolbar;
    Fragment active = tasksFragment;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayoutToolbar = findViewById(R.id.linear_toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        bottomNavigationView = findViewById(R.id.nav_bottom_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);


//        disableShiftMode(bottomNavigationView);

        bottomNavigationViewTest();

        fm.beginTransaction().add(R.id.main_container, settingFragment, "4").hide(settingFragment).commit();
        fm.beginTransaction().add(R.id.main_container, newsFragment, "3").hide(newsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, calendarFragment, "2").hide(calendarFragment).commit();
        fm.beginTransaction().add(R.id.main_container, tasksFragment, "1").commit();


        openDrawerOnClickInAccount();
    }


    public void openDrawerOnClickInAccount() {
        linearLayoutToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

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
