package com.example.mushafconsolidated.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mushafconsolidated.R;
import com.example.utility.DarkThemeApplication;

public class Colorsettingactivity extends AppCompatActivity {

    private TextView mTextView;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //  String result=intent.getStringExtra("result");


//
        finish();
        overridePendingTransition(0, 0);
        Intent intents = getIntent();
        startActivity(intents);

        overridePendingTransition(0, 0);
        //   finish();
        //  overridePendingTransition(0, 0);
        // Intent intents=getIntent();
        // startActivity(intents);
        //  overridePendingTransition(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_color_picker);

        mTextView = (TextView) findViewById(R.id.text);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        int mausofColoragainstBlack = prefs.getInt("mausoofblack", 0xFF000000);
        int mudhafColoragainstBlack = prefs.getInt("mudhafblack", 0xFF000000);
        int sifatColoragainstBlack = prefs.getInt("sifatblack", 0xFF000000);
        int brokenPlurarColoragainstBlack = prefs.getInt("brokenblack", 0xFF000000);


        int mausofColoragainstWhitet = prefs.getInt("mausoofwhite", 0xFF000000);
        int mudhafColoragainstWhitet = prefs.getInt("mudhafwhite", 0xFF000000);
        int sifatColoragainstWhitet = prefs.getInt("sifatwhite", 0xFF000000);
        int brokenPlurarColoragainstWhitet = prefs.getInt("brokenwhite", 0xFF000000);


        Button mousfublackbtn = findViewById(R.id.mausoof);
        Button mudhafblackbtn = findViewById(R.id.mudhaf);
        Button sifablackbtn = findViewById(R.id.sifa);
        Button brokenblackbtn = findViewById(R.id.broken);


        Button mousufwhitebtn = findViewById(R.id.mausoofwhite);
        Button mudhafwhitebtn = findViewById(R.id.mudhafwhite);
        Button sifawhitebtn = findViewById(R.id.sifawhite);
        Button borkenwhitebtn = findViewById(R.id.brokenwhite);


        String hexString = Integer.toHexString(mausofColoragainstBlack);
        TextView mousufblacktv = findViewById(R.id.mousufagainstblack);
        TextView sifablacktv = findViewById(R.id.sifaagainstblack);
        TextView mudhafblacktv = findViewById(R.id.mudhafagainstblack);
        TextView brokenblacktv = findViewById(R.id.brokenagainstblack);


        TextView mousufwhitetv = findViewById(R.id.mousufagainstwhite);
        TextView sifawhitetv = findViewById(R.id.sifaagainstwhite);
        TextView mudhafwhitetv = findViewById(R.id.mudhafagainstwhite);
        TextView brokenwhitetv = findViewById(R.id.brokenagainstwhite);


        mousufblacktv.setTextColor(mausofColoragainstBlack);
        sifablacktv.setTextColor(sifatColoragainstBlack);
        mudhafblacktv.setTextColor(mudhafColoragainstBlack);
        brokenblacktv.setTextColor(brokenPlurarColoragainstBlack);

        mousfublackbtn.setBackgroundColor(mausofColoragainstBlack);
        sifablackbtn.setBackgroundColor(sifatColoragainstBlack);
        mudhafblackbtn.setBackgroundColor(mudhafColoragainstBlack);
        brokenblackbtn.setBackgroundColor(brokenPlurarColoragainstBlack);


        mousufwhitetv.setTextColor(mausofColoragainstWhitet);
        sifawhitetv.setTextColor(sifatColoragainstWhitet);
        mudhafwhitetv.setTextColor(mudhafColoragainstWhitet);
        brokenwhitetv.setTextColor(brokenPlurarColoragainstWhitet);


        mousufwhitebtn.setBackgroundColor(mausofColoragainstWhitet);
        sifawhitebtn.setBackgroundColor(sifatColoragainstWhitet);
        mudhafwhitebtn.setBackgroundColor(mudhafColoragainstWhitet);
        borkenwhitebtn.setBackgroundColor(brokenPlurarColoragainstWhitet);






        //  mausoof.setBackgroundColor(Color.YELLOW);
        mousfublackbtn.setBackgroundColor(mausofColoragainstBlack);
        mudhafblackbtn.setBackgroundColor(mudhafColoragainstBlack);
        sifablackbtn.setBackgroundColor(sifatColoragainstBlack);
        brokenblackbtn.setBackgroundColor(brokenPlurarColoragainstBlack);


        mousfublackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("mausoofblack", mausofColoragainstBlack);
                intent.putExtra("setmausoofblack", 1);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });


        mudhafblackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("mudhafblack", mudhafColoragainstBlack);
                intent.putExtra("setmudhafblack", 2);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });
        sifablackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("sifatblack", sifatColoragainstBlack);
                intent.putExtra("setsifatblack", 3);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });
        brokenblackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("brokenblack", brokenPlurarColoragainstBlack);
                intent.putExtra("setbrokenblack", 4);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });





        mousufwhitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("mausoofwhite", mausofColoragainstWhitet);
                intent.putExtra("setmausoofwhite", 5);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });


        mudhafwhitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("mudhafwhite", mudhafColoragainstWhitet);
                intent.putExtra("setmudhafwhite", 6);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });
        sifawhitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("sifatwhite", sifatColoragainstWhitet);
                intent.putExtra("setsifatwhite", 7);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });
        borkenwhitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent intent = new Intent(DarkThemeApplication.getContext(), ColorPickerActivity.class);
                intent.putExtra("brokewhite", brokenPlurarColoragainstWhitet);
                intent.putExtra("setbrokenwhite", 8);
                //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivityForResult(intent, 0);


            }
        });









    }
}