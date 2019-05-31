package com.example.maximus.vitaminreminder.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private static final String SHARED_PREF = "VitaminReminderPref";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour = "hour";
    private static final String minute = "minute";
    private static final String timeString = "time";

    public LocalData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        this.prefEditor = sharedPreferences.edit();
    }


    public  boolean getReminderStatus() {
        return sharedPreferences.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status) {
        prefEditor.putBoolean(reminderStatus, status);
        prefEditor.commit();
    }

    public int getHour() {
        return sharedPreferences.getInt(hour, 8);
    }

    public void setHour(int h) {
        prefEditor.putInt(hour, h);
        prefEditor.commit();
    }

    public int getMinute() {
        return sharedPreferences.getInt(minute, 20);
    }

    public void setMinute(int min) {
        prefEditor.putInt(minute, min);
        prefEditor.commit();
    }

    public void reset() {
        prefEditor.clear();
        prefEditor.commit();
    }

    public void setStringHour(String time) {
        prefEditor.putString(timeString, time);
        prefEditor.commit();
    }

    public String getStringHour() {
        return sharedPreferences.getString(timeString, null);
    }
}
