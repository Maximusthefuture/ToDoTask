package com.example.maximus.vitaminreminder.more_options;


import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.pref.SettingsFragment;
import com.example.maximus.vitaminreminder.tasks.TasksFragment;
import com.example.maximus.vitaminreminder.utils.ActivityUtils;

public class MoreOptionsActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.more_option_activity);
//
//
//        MoreOptionFragment tasksFragment =
//                (MoreOptionFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//
//
//
//        if (tasksFragment == null) {
//            tasksFragment = MoreOptionFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
//
//        }

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MoreOptionFragment())
                .commit();

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


}
