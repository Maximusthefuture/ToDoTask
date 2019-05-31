package com.example.maximus.vitaminreminder.pref;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.preference.DialogPreference;

import com.example.maximus.vitaminreminder.R;

public class TimePreference extends DialogPreference {
   private int mTime;
   private int mDialogLayoutResId = R.layout.pref_dialog_time;



    public  int getTime() {
       return mTime;
    }

    public void setTime(int time) {
        mTime = time;
        persistInt(time);
    }

    public TimePreference(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");


    }
    

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setTime(restorePersistedValue ? getPersistedInt(mTime) : (int) defaultValue);
    }


    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }

}
