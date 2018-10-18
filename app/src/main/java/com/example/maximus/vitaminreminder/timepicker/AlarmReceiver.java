package com.example.maximus.vitaminreminder.timepicker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.sync.VitaminReminderIntentService;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, VitaminReminderIntentService.class));
    }


}
