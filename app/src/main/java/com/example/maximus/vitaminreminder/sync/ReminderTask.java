package com.example.maximus.vitaminreminder.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;


import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class ReminderTask {
    public static final String KEY_COLOR = "color";
    public static final int DEFAULT_COLOR = Color.RED;

    public static final String ACTION_ADD_DOT_SPAN = "add-dot-span";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";


    public static void executeTask(Context context, String action) {
        if (action.equals(ACTION_ADD_DOT_SPAN)) {
            setActionAddDotSpan(context);
        } else if (action.equals(ACTION_DISMISS_NOTIFICATION)) {
            NotificationUtils.clearAllNotification(context);
        }
    }

    private static void setActionAddDotSpan(Context context) {
//        changeColor(context);
//        CustomCalendarView.addDotSpan(getColor(context));
        NotificationUtils.clearAllNotification(context);


    }

    public static int getColor(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(KEY_COLOR, DEFAULT_COLOR);
    }


    private static void setColor(Context context, int color) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_COLOR, color);
        editor.apply();
    }

    private static void changeColor(Context context) {
        int color = getColor(context);
        setColor(context, color);
    }



}
