package org.sj.conjugator.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

public class FragmentStateHelper {


FragmentManager fragmentManager;

    public FragmentStateHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

   // mutableMapOf<String, Fragment.SavedState?>()
HashMap<String, Fragment.SavedState>    fragmentSavedStates  = new HashMap<>();



}
