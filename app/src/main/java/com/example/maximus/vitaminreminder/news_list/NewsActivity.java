package com.example.maximus.vitaminreminder.news_list;


import android.os.Bundle;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.utils.ActivityUtils;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setTitle(R.string.news_title_toolbar);
        }



        NewsFragment newsFragment =
                (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), newsFragment, R.id.contentFrame);

        }
    }
}
