package com.example.maximus.vitaminreminder.timepicker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                LocalData localData = new LocalData(context);
                NotificationUtils.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMinute());
                return;
            }
        }
        NotificationUtils.showNotification(context);
    }


}
