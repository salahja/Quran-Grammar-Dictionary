package com.example.mushafconsolidated.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mushafconsolidated.Activity.ColorPickerActivity;
import com.example.mushafconsolidated.R;
import com.example.utility.DarkThemeApplication;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColorPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorPickerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String SURAHTAG = "surah";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mausoof;
    private Button mudhaf;
    private Button sifa;

    public ColorPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ColorPickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ColorPickerFragment newInstance(String param1, String param2) {
        ColorPickerFragment fragment = new ColorPickerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Fragment frg = getActivity().getSupportFragmentManager().findFragmentByTag(SURAHTAG);
            final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.commit();

            Log.i("IsRefresh", "Yes");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

 View view=inflater.inflate(R.layout.fragment_color_picker, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        int mausofColor = prefs.getInt("mausoof", 0xFF000000);
        mausoof=view.findViewById(R.id.mausoof);
        String hexString = Integer.toHexString(mausofColor);

        int greenColorValue = Color.parseColor("#"+hexString);
        //  mausoof.setBackgroundColor(Color.YELLOW);
        mausoof.setBackgroundColor(greenColorValue);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        int mausofColor = prefs.getInt("mausoof", 0xFF000000);
        mausoof=view.findViewById(R.id.mausoof);
        String hexString = Integer.toHexString(mausofColor);

        int greenColorValue = Color.parseColor("#"+hexString);
      //  mausoof.setBackgroundColor(Color.YELLOW);
        mausoof.setBackgroundColor(mausofColor);

        mausoof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("mausoof",mausofColor);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });

    }
}