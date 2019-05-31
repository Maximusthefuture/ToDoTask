package com.example.maximus.vitaminreminder.pref;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.tasks.TasksActivity;
import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.timepicker.AlarmReceiver;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;


public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    TimePicker mTimePicker;
    LocalData localData;

    @Override
    public void onDialogClosed(boolean positiveResult) {

        if (positiveResult) {
            int hours = mTimePicker.getCurrentHour();
            int minute = mTimePicker.getCurrentMinute();
            int minutesAfterMidnight = (hours * 60) + minute;

            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference
                        timePreference = ((TimePreference)preference);

                if (timePreference.callChangeListener(minutesAfterMidnight)) {
                    timePreference.setTime(minutesAfterMidnight);
                    Task task = new Task();
                    localData = new LocalData(getContext());
                    localData.setHour(hours);
                    localData.setMinute(minute);
                    NotificationUtils.setReminder(getContext(), AlarmReceiver.class, localData.getHour(), localData.getMinute(), task.getTime());
                    Intent intent = new Intent(getContext(), TasksActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Time changed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mTimePicker = view.findViewById(R.id.time_picker);

        if (mTimePicker == null) {
            throw new IllegalStateException("Dialog view ");
        }

        Integer minuteAfterMidnight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            minuteAfterMidnight
                    = ((TimePreference) preference).getTime();
        }

        if (minuteAfterMidnight != null) {
            int hours = minuteAfterMidnight / 60;
            int minutes = minuteAfterMidnight % 60;
            boolean is24hour = DateFormat.is24HourFormat(getContext());

            mTimePicker.setIs24HourView(true);
            mTimePicker.setCurrentHour(hours);
            mTimePicker.setCurrentMinute(minutes);

        }
    }

}
