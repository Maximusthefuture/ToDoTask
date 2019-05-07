package com.example.maximus.vitaminreminder.timepicker;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.sync.ReminderTask;
import com.example.maximus.vitaminreminder.sync.VitaminReminderIntentService;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

import static android.content.Context.ALARM_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    String TAG = "AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
//            LocalData localData = new LocalData(context);
//            NotificationUtils.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMinute());
//
//
////        String action = intent.getAction();
////        ReminderTask.executeTask(context, action);
//        }
////        context.startService(new Intent(context, VitaminReminderIntentService.class));
//
//    }
    if (intent.getAction() != null && context != null) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d(TAG, "OnReceive: BOOT COMPLETED");

            LocalData localData = new LocalData(context);
            Task task = new Task();
            NotificationUtils.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMinute(), task.getTime());
            return;
        }
    }
    NotificationUtils.showNotification(context);

    }


}
