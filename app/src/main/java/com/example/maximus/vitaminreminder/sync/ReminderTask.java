package com.example.maximus.vitaminreminder.sync;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.maximus.vitaminreminder.utils.NotificationUtils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class ReminderTask {
    static MaterialCalendarView calendarView;

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
//        calendarView = clas.findViewById(R.id.calendarView);
//        CustomCalendarView.addDotSpan(Color.RED);

        NotificationUtils.clearAllNotification(context);

    }



}
