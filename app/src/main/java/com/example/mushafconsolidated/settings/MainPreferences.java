package com.example.mushafconsolidated.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.mushafconsolidated.R;

public class MainPreferences extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
