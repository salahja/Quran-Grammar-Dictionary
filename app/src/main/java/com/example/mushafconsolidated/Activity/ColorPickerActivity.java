package com.example.mushafconsolidated.Activity;
/*
 * Copyright (C) 2017 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mushafconsolidated.R;
import com.example.utility.DarkThemeApplication;
import com.jaredrummler.android.colorpicker.ColorPanelView;
import com.jaredrummler.android.colorpicker.ColorPickerView;
import com.jaredrummler.android.colorpicker.ColorPickerView.OnColorChangedListener;

import static com.example.Constant.CHAPTER;

public class ColorPickerActivity extends AppCompatActivity implements OnColorChangedListener, View.OnClickListener {

    private ColorPickerView colorPickerView;
    private ColorPanelView newColorPanelView;
int setmasufoofblack,setmudhafblack,setsifatblack,setbrokenblack;
    int setmasufoofwhite,setmudhafwhite,setsifatwhite,setbrokenwhite;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.activity_color_picker);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int mausofColor = prefs.getInt("mausoofblack", 0xFF000000);
        int mudhafColor = prefs.getInt("mudhafblack", 0xFF000000);
        int sifatColor = prefs.getInt("sifatblack", 0xFF000000);
        int brokenPlurarColor = prefs.getInt("brokenblack", 0xFF000000);

        int mausofColorw = prefs.getInt("mausoofwhite", 0xFF000000);
        int mudhafColorw = prefs.getInt("mudhafwhite", 0xFF000000);
        int sifatColorw = prefs.getInt("sifatwhite", 0xFF000000);
        int brokenPlurarColorw = prefs.getInt("brokenwhite", 0xFF000000);


        Intent bundle = getIntent();

        if (bundle != null) {
          setmasufoofblack=      bundle.getIntExtra("setmausoofblack", 0);
          setmudhafblack=                        bundle.getIntExtra("setmudhafblack",0);
           setsifatblack=                 bundle.getIntExtra("setsifatblack",0);
            setbrokenblack=                  bundle.getIntExtra("setbrokenblack",0);
            setmasufoofwhite=      bundle.getIntExtra("setmausoofwhite", 0);
            setmudhafwhite=                        bundle.getIntExtra("setmudhafwhite",0);
            setsifatwhite=                 bundle.getIntExtra("setsifatwhite",0);
            setbrokenwhite=                  bundle.getIntExtra("setbrokenwhite",0);


        }
        colorPickerView = (ColorPickerView) findViewById(R.id.cpv_color_picker_view);
        ColorPanelView colorPanelView = (ColorPanelView) findViewById(R.id.cpv_color_panel_old);
        newColorPanelView = (ColorPanelView) findViewById(R.id.cpv_color_panel_new);

        Button btnOK = (Button) findViewById(R.id.okButton);
        Button btnCancel = (Button) findViewById(R.id.cancelButton);

        ((LinearLayout) colorPanelView.getParent()).setPadding(colorPickerView.getPaddingLeft(), 0,
                colorPickerView.getPaddingRight(), 0);

        colorPickerView.setOnColorChangedListener(this);
        colorPickerView.setColor(mausofColor, true);
        colorPanelView.setColor(mausofColor);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override public void onColorChanged(int newColor) {
        newColorPanelView.setColor(colorPickerView.getColor());
    }

    //-10913178
    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();

             if(setmasufoofblack==1){
                 edit.putInt("mausoofblack", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }else if(setmudhafblack==2){
                 edit.putInt("mudhafblack", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }
             else if(setsifatblack==3){
                 edit.putInt("sifatblack", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }else if(setbrokenblack==4){
                 edit.putInt("brokenblack", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }else
             if(setmasufoofwhite==5){
                 edit.putInt("mausoofwhite", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }else if(setmudhafwhite==6){
                 edit.putInt("mudhafwhite", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }
             else if(setsifatwhite==7){
                 edit.putInt("sifatwhite", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }else if(setbrokenwhite==8){
                 edit.putInt("brokenwhite", colorPickerView.getColor());
                 edit.apply();
                 finish();
             }



                break;
            case R.id.cancelButton:
                finish();
                break;
        }
    }
}
