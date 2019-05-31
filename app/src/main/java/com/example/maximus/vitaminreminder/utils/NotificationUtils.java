package com.example.maximus.vitaminreminder.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.maximus.vitaminreminder.MainActivity;
import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.sync.VitaminReminderIntentService;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationUtils {

    private static final int PENDING_INTENT_ID = 21331;
    private static final String CHANNEL_ID = "drink-id";
    private static final int DRINKING_NOTIFICATION_ID = 111222;



    public static void setReminder(Context context, Class<?> cls, int hour, int min) {
        Calendar calendar = Calendar.getInstance();

        Calendar setCalendar = Calendar.getInstance();
        setCalendar.set(Calendar.HOUR_OF_DAY, hour);
        setCalendar.set(Calendar.MINUTE, min);
        setCalendar.set(Calendar.SECOND, 0);

        cancelReminder(context, cls);

        if (setCalendar.before(calendar)) {
            setCalendar.add(Calendar.DATE, 1);
        }

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, DRINKING_NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }


    public static void clearAllNotification(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static void showNotification(Context context) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Vitamin drinking";
            String description = "";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_medicine)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getResources().getString(R.string.vitamin_reminder_notification_title))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true)
                .addAction(completeVitaminDrinking(context))
                .setLargeIcon(largeIcon(context))
                .addAction(ignoreReminderAction(context));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(DRINKING_NOTIFICATION_ID, mBuilder.build());


    }


    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(context,
                PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private static NotificationCompat.Action completeVitaminDrinking(Context context) {
        Intent intent = new Intent(context, VitaminReminderIntentService.class);
        intent.setAction(null);


        NotificationCompat.Action vitaminDrinkComplete = new NotificationCompat.Action(
                R.drawable.ic_medicine, "Drunk", null);
        return vitaminDrinkComplete;
    }

    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent intent = new Intent(context, VitaminReminderIntentService.class);
        intent.setAction(null);


        NotificationCompat.Action ignore = new NotificationCompat.Action(
                android.R.drawable.ic_menu_close_clear_cancel, "No, thanks", null);
        return ignore;
    }


    public static void cancelReminder(Context context, Class<?> cls) {
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DRINKING_NOTIFICATION_ID, intent
        , PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }




    private static Bitmap largeIcon(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_medicine);
    }
}
