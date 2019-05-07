package com.example.maximus.vitaminreminder.sync;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.example.maximus.vitaminreminder.utils.NotificationUtils;

public class VitaminReminderIntentService extends IntentService {

    public VitaminReminderIntentService() {
        super(VitaminReminderIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

//        if (intent.getAction() != null &&  getApplicationContext()!= null) {
//            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
////                ReminderTask.executeTask(this, action);
//                LocalData localData = new LocalData(getApplicationContext());
//                NotificationUtils.setReminder(getApplicationContext(), AlarmReceiver.class, localData.getHour(), localData.getMinute());
//                return;
//            }
//
//        }
        NotificationUtils.showNotification(getApplicationContext());
    }
}
