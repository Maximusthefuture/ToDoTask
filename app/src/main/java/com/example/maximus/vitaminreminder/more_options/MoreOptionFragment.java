package com.example.maximus.vitaminreminder.more_options;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.pref.SettingsFragment;


//TODO: По клику на боттомбар more выдается разделенное меню, сделать чтобы настройки вызывались по нажатию
//TODO: на настройки, засунуть их в другой класс?
public class MoreOptionFragment extends PreferenceFragmentCompat {

    public MoreOptionFragment() {

    }


    public static MoreOptionFragment newInstance() {
        return new MoreOptionFragment();
    }


    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }


//    @Override
//    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
////
//        Bundle args = pref.getExtras();
//
//        Fragment fragment = getFragmentManager().getFragmentFactory().instantiate(
//                getClass().getClassLoader(),
//                pref.getFragment(),
//                args);
//
//        fragment.setArguments(args);
//        fragment.setTargetFragment(caller, 0);
//        getFragmentManager().beginTransaction()
//                .replace(R.id.setting_container, fragment)
//                .addToBackStack(null)
//                .commit();
//
////        pref.setFragment(String.valueOf(new SettingsFragment()));
////
//        return true;
//    }
}
