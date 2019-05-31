package com.example.maximus.vitaminreminder.pref;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import android.widget.Toast;

import com.example.maximus.vitaminreminder.R;


public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);

            if (!(p instanceof CheckBoxPreference) && !(p instanceof TimePreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }

//        Preference preference = findPreference(getString(R.string.interval_key));
//        preference.setOnPreferenceChangeListener(this);

    }




    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            dialogFragment = TimePreferenceDialogFragmentCompat
                    .newInstance(preference.getKey());
        }

        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "android.support.v7.preference" + ".PreferenceFragment.DIALOG");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    public void setPreferenceSummary(Preference preferenceSummary, String value) {
        if (preferenceSummary instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preferenceSummary;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else
            if (preferenceSummary instanceof EditTextPreference) {
            preferenceSummary.setSummary(value);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference preference = findPreference(key);
            if (preference != null) {
                if (!(preference instanceof CheckBoxPreference) && !(preference instanceof TimePreference)) {
                    String value = sharedPreferences.getString(preference.getKey(), "");
                    setPreferenceSummary(preference, value);
                }
            }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Toast error = Toast.makeText(getContext(), "Error convert to float, please try again", Toast.LENGTH_SHORT);
        String sizeKey = getString(R.string.interval_key);
        if (preference.getKey().equals(sizeKey)) {
            String stringSize = (String) newValue;
            try {
                float size = Float.parseFloat(stringSize);
                if (size > 30 || size <= 0) {
                    error.show();
                    return false;
                }
            } catch (NumberFormatException ex) {
                error.show();
                return false;
            }

        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

}
