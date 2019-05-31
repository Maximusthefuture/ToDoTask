package com.example.maximus.vitaminreminder.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class VitaminReminderIntentService extends IntentService {

    public VitaminReminderIntentService() {
        super(VitaminReminderIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
