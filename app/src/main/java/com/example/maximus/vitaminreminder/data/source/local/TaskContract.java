package com.example.maximus.vitaminreminder.data.source.local;

import android.provider.BaseColumns;

public class TaskContract {

    private TaskContract() {}

    public class TaskEntry implements BaseColumns {

        public static final String TABLE_NAME = "vitamin";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_TIME = "time";

    }
}
