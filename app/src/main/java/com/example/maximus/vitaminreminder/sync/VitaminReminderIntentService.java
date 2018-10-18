package com.example.maximus.vitaminreminder.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class VitaminReminderIntentService extends IntentService {

    public VitaminReminderIntentService() {
        super(VitaminReminderIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        ReminderTask.executeTask(this, action);
        if (intent.getAction() != null &&  getApplicationContext()!= null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                LocalData localData = new LocalData(getApplicationContext());
                NotificationUtils.setReminder(getApplicationContext(), VitaminReminderIntentService.class, localData.getHour(), localData.getMinute());
                return;
            }

        }
        NotificationUtils.showNotification(getApplicationContext());
    }
}
