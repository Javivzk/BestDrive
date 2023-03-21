package com.svalero.bestread.view;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.svalero.bestread.R;

public class PreferencesFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey);
    }
}

