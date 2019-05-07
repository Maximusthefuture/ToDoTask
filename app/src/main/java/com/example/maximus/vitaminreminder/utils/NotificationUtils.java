package com.example.maximus.vitaminreminder.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.example.maximus.vitaminreminder.tasks.TasksActivity;
import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.sync.ReminderTask;
import com.example.maximus.vitaminreminder.sync.VitaminReminderIntentService;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationUtils {

    private static final int PENDING_INTENT_ID = 21331;
    private static final String CHANNEL_ID = "drink-id";
    private static final int DRINKING_NOTIFICATION_ID = 111222;
    private static boolean isVitaminComplete = false;
    private static final String TAG = NotificationUtils.class.getSimpleName();


    public static void setReminderEveryTenMinutes() {


    }


    public static void setReminder(Context context, Class<?> cls, int hour, int min, String time) {

        Calendar calendar = Calendar.getInstance();

//
//        int minutes = Integer.parseInt(time.substring(1));
//        int hours = Integer.parseInt(time.substring(2));

//        Log.d(TAG, "hour: " + hour + "minutes : " + minutes);

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


        if (!isVitaminComplete) {
            int intervalTwoMin = 1000 * 60 * 1;
            am.setRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), intervalTwoMin, pendingIntent);
            Log.d(TAG, "Repeating in 2 min");
        } else {
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            isVitaminComplete = true;
            if (am != null) {
                am.cancel(pendingIntent);
            }
        }
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
            String description = "Time to drink vitamins";
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
        Intent intent = new Intent(context, TasksActivity.class);

        return PendingIntent.getActivity(context,
                PENDING_INTENT_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private static NotificationCompat.Action completeVitaminDrinking(Context context) {
        Intent intent = new Intent(context, TasksActivity.class);
        intent.setAction(ReminderTask.ACTION_DISMISS_NOTIFICATION);

        PendingIntent changeColorIntent =
                PendingIntent.getActivity(context, DRINKING_NOTIFICATION_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        NotificationCompat.Action vitaminDrinkComplete = new NotificationCompat.Action(
                R.drawable.ic_medicine__notification, "Drunk", changeColorIntent);
//        context.startActivity(intent);
        isVitaminComplete = false;
        Log.d(TAG, "isVitaminComplete: " + isVitaminComplete);
        return vitaminDrinkComplete;

    }

    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent intent = new Intent(context, VitaminReminderIntentService.class);
        intent.setAction(ReminderTask.ACTION_DISMISS_NOTIFICATION);

        PendingIntent dismissIntent =
                PendingIntent.getBroadcast(context, 5, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignore = new NotificationCompat.Action(
                R.drawable.ic_cancel_black_24px, "No, thanks", dismissIntent);

        isVitaminComplete = true;

        return ignore;
    }


    public static void cancelReminder(Context context, Class<?> cls) {
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DRINKING_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }


    private static Bitmap largeIcon(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_medicine);
    }
}
