package com.example.maximus.vitaminreminder.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.local.TaskContract.*;

import java.util.ArrayList;
import java.util.List;


public class TaskDbHelper extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                    TaskEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TaskEntry.COLUMN_NAME_TIME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;



    public TaskDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskEntry.COLUMN_NAME_TITLE, task.getTitle());
        values.put(TaskEntry.COLUMN_NAME_TIME, task.getTime());

        db.insert(TaskEntry.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public List<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TaskEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(String.valueOf(cursor.getColumnIndex(TaskEntry._ID)));
                task.setTitle(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TITLE)));
                task.setTime(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TIME)));
                tasks.add(task);
            } while (cursor.moveToNext());
            }

        cursor.close();

        return tasks;

    }

    @Override
    public Task getTaskById(String taskId) {
        return null;
    }

    @Override
    public int deleteTaskById(String taskId) {
        return 0;
    }

    @Override
    public void deleteTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TaskEntry.TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public int deleteCompletedTasks() {
        return 0;
    }

    @Override
    public void updateCompleted(String taskId, boolean completed) {

    }
}
