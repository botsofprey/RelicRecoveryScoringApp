package com.example.jeremy.relicrecoveryscoringapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.example.jeremy.relicrecoveryscoringapp.MainActivity.ButtonMode.*;

public class MainActivity extends AppCompatActivity {

    private int score = 0; //yes, long, cuz we will dominate...
    Listener listener = new Listener();
    public enum ButtonMode {
        NONE, GRAY, BROWN
    }
    public ButtonMode mode[] = new ButtonMode[12];

    public EditText autonomousGlyphStacked;
    public CheckBox redJewel, blueJewel, autonomousGlyphColumnBonus, autonomousInZone;
    public ToggleButton team;
    public ToggleButton cryptoboxButtons[] = new ToggleButton[12];
    public TextView scoreTally;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Begin create", "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextMessage = (TextView) findViewById(R.id.message);
        cryptoboxButtons[0] = (ToggleButton) findViewById(R.id.toggle1);
        cryptoboxButtons[1] = (ToggleButton) findViewById(R.id.toggle2);
        cryptoboxButtons[2] = (ToggleButton) findViewById(R.id.toggle3);
        cryptoboxButtons[3] = (ToggleButton) findViewById(R.id.toggle4);
        cryptoboxButtons[4] = (ToggleButton) findViewById(R.id.toggle5);
        cryptoboxButtons[5] = (ToggleButton) findViewById(R.id.toggle6);
        cryptoboxButtons[6] = (ToggleButton) findViewById(R.id.toggle7);
        cryptoboxButtons[7] = (ToggleButton) findViewById(R.id.toggle8);
        cryptoboxButtons[8] = (ToggleButton) findViewById(R.id.toggle9);
        cryptoboxButtons[9] = (ToggleButton) findViewById(R.id.toggle10);
        cryptoboxButtons[10] = (ToggleButton) findViewById(R.id.toggle11);
        cryptoboxButtons[11] = (ToggleButton) findViewById(R.id.toggle12);
        scoreTally = (TextView) findViewById(R.id.scoreTally);
        autonomousGlyphStacked = (EditText) findViewById(R.id.autonomousGlyphsStacked);
        redJewel = (CheckBox) findViewById(R.id.redJewel);
        blueJewel = (CheckBox) findViewById(R.id.blueJewel);
        autonomousGlyphColumnBonus = (CheckBox) findViewById(R.id.glyphColumnBonus);
        autonomousInZone = (CheckBox) findViewById(R.id.autonomousInZone);
        team = (ToggleButton) findViewById(R.id.team);
        team.setOnClickListener(listener);
        redJewel.setOnClickListener(listener);
        blueJewel.setOnClickListener(listener);
        autonomousGlyphColumnBonus.setOnClickListener(listener);
        autonomousGlyphStacked.setOnClickListener(listener);
        autonomousInZone.setOnClickListener(listener);
        Log.d("Created", "");
    }

    public class Listener implements View.OnClickListener {

        Listener(){}

        @Override
        public void onClick(View view){
            score = 0;
            switch (view.getId()){
                default:
                    if(team.getText().toString().equals("Team Red") && redJewel.isChecked() && !blueJewel.isChecked()) score += 30;
                    else if(team.getText().toString().equals("Team Blue") && blueJewel.isChecked() && !redJewel.isChecked()) score += 30;
                    if(autonomousGlyphColumnBonus.isChecked()) score += 30;
                    if(autonomousInZone.isChecked()) score += 10;
                    try {
                        if(Integer.parseInt(autonomousGlyphStacked.getText().toString()) > 0){
                            score += Integer.parseInt(autonomousGlyphStacked.getText().toString())*15;
                        }
                    } catch (Exception e){
                        Log.e("Error", e.toString());
                    }
                    for(int i = 0; i < cryptoboxButtons.length; i++){
                        if(cryptoboxButtons[i].isPressed()){
                            switch (mode[i]) {
                                case NONE:
                                    mode[i] = GRAY;
                                    cryptoboxButtons[i].setBackgroundColor(Color.GRAY);
                                    break;
                                case GRAY:
                                    mode[i] = BROWN;
//                                    cryptoboxButtons[i].setBackgroundColor();
                                    break;
                                case BROWN:
                                    mode[i] = NONE;
                                    break;
                            }
                        }
                    }
                    if(boxFull()) {
                        if(checkPattern()) score += 30;
                    }
                    break;
            }
            Log.d("update score", "");
            scoreTally.setText(Integer.toString(score));
        }

        private boolean boxFull() {
            boolean full = false;
            if(!mode[0].equals(ButtonMode.NONE) && !mode[1].equals(ButtonMode.NONE) && !mode[2].equals(ButtonMode.NONE)
                    && !mode[3].equals(ButtonMode.NONE) && !mode[4].equals(ButtonMode.NONE) && !mode[5].equals(ButtonMode.NONE)
                    && !mode[6].equals(ButtonMode.NONE) && !mode[7].equals(ButtonMode.NONE) && !mode[8].equals(ButtonMode.NONE)
                    && !mode[9].equals(ButtonMode.NONE) && !mode[10].equals(ButtonMode.NONE) && !mode[11].equals(ButtonMode.NONE)){
                full = true;
            }
            return full;
        }

        private boolean checkPattern() {
            boolean pattern = false;
            if (mode[0].equals(ButtonMode.GRAY) && mode[1].equals(BROWN) && mode[2].equals(ButtonMode.GRAY)
                    && mode[3].equals(BROWN) && mode[4].equals(ButtonMode.GRAY) && mode[5].equals(BROWN)
                    && mode[6].equals(ButtonMode.GRAY) && mode[7].equals(BROWN) && mode[8].equals(ButtonMode.GRAY)
                    && mode[9].equals(BROWN) && mode[10].equals(ButtonMode.GRAY) && mode[11].equals(BROWN)) pattern = true;

            else if (mode[0].equals(BROWN) && mode[1].equals(ButtonMode.GRAY) && mode[2].equals(BROWN)
                    && mode[3].equals(ButtonMode.GRAY) && mode[4].equals(BROWN) && mode[5].equals(ButtonMode.GRAY)
                    && mode[6].equals(BROWN) && mode[7].equals(ButtonMode.GRAY) && mode[8].equals(BROWN)
                    && mode[9].equals(ButtonMode.GRAY) && mode[10].equals(BROWN) && mode[11].equals(ButtonMode.GRAY)) pattern = true;

            else if (mode[0].equals(ButtonMode.GRAY) && mode[1].equals(BROWN) && mode[2].equals(ButtonMode.GRAY)
                    && mode[3].equals(BROWN) && mode[4].equals(ButtonMode.GRAY) && mode[5].equals(BROWN)
                    && mode[6].equals(BROWN) && mode[7].equals(ButtonMode.GRAY) && mode[8].equals(BROWN)
                    && mode[9].equals(ButtonMode.GRAY) && mode[10].equals(BROWN) && mode[11].equals(ButtonMode.GRAY)) pattern = true;

            else if (mode[0].equals(BROWN) && mode[1].equals(ButtonMode.GRAY) && mode[2].equals(BROWN)
                    && mode[3].equals(ButtonMode.GRAY) && mode[4].equals(BROWN) && mode[5].equals(ButtonMode.GRAY)
                    && mode[6].equals(ButtonMode.GRAY) && mode[7].equals(BROWN) && mode[8].equals(ButtonMode.GRAY)
                    && mode[9].equals(BROWN) && mode[10].equals(ButtonMode.GRAY) && mode[11].equals(BROWN)) pattern = true;

            else if (mode[0].equals(ButtonMode.GRAY) && mode[1].equals(BROWN) && mode[2].equals(BROWN)
                    && mode[3].equals(ButtonMode.GRAY) && mode[4].equals(ButtonMode.GRAY) && mode[5].equals(BROWN)
                    && mode[6].equals(BROWN) && mode[7].equals(ButtonMode.GRAY) && mode[8].equals(ButtonMode.GRAY)
                    && mode[9].equals(BROWN) && mode[10].equals(BROWN) && mode[11].equals(ButtonMode.GRAY)) pattern = true;

            else if (mode[0].equals(BROWN) && mode[1].equals(ButtonMode.GRAY) && mode[2].equals(ButtonMode.GRAY)
                    && mode[3].equals(BROWN) && mode[4].equals(BROWN) && mode[5].equals(ButtonMode.GRAY)
                    && mode[6].equals(ButtonMode.GRAY) && mode[7].equals(BROWN) && mode[8].equals(BROWN)
                    && mode[9].equals(ButtonMode.GRAY) && mode[10].equals(ButtonMode.GRAY) && mode[11].equals(BROWN)) pattern = true;
            return pattern;
        }
    }
}
