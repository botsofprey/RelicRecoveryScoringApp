package com.example.jeremy.relicrecoveryscoringapp;

import android.content.Context;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int score; //yes, long, cuz we will dominate...

    public EditText autonomousGlyphStacked;
    public CheckBox redJewel, blueJewel, autonomousGlyphColumnBonus, autonomousInZone;
    public ToggleButton team;
    public TextView scoreTally;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextMessage = (TextView) findViewById(R.id.message);
        scoreTally = (TextView) findViewById(R.id.scoreTally);
        autonomousGlyphStacked = (EditText) findViewById(R.id.autonomousGlyphsStacked);
        redJewel = (CheckBox) findViewById(R.id.redJewel);
        blueJewel = (CheckBox) findViewById(R.id.blueJewel);
        autonomousGlyphColumnBonus = (CheckBox) findViewById(R.id.glyphColumnBonus);
        autonomousInZone = (CheckBox) findViewById(R.id.autonomousInZone);
        team = (ToggleButton) findViewById(R.id.team);
        Log.d("Created", "");
    }

    @Override
    public void onClick(View view) {
        //do scoring here, just tally up points every time this is called...
        score = 0;
        if(team.getText() == "Team Blue"){
            if(autonomousGlyphColumnBonus.isChecked()){
                score = score + 30;
            }
            if(autonomousInZone.isChecked()){
                score = score + 10;
            }
            if(blueJewel.isChecked() && !redJewel.isChecked()){
                score = score + 30;
            }
            for(int x=0; x<1; x++){
                String newString = autonomousGlyphStacked.getText().toString();
                Integer result = Integer.valueOf(newString);
                score = score + result;
            }

        }
        else if(team.getText() == "Team Red"){
            if(autonomousGlyphColumnBonus.isChecked()){
                score = score + 30;
            }
            if(autonomousInZone.isChecked()){
                score = score + 10;
            }
            if(redJewel.isChecked() && !blueJewel.isChecked()){
                score = score + 30;
            }
            for(int x=0; x<1; x++){
                String newString = autonomousGlyphStacked.getText().toString();
                Integer result = Integer.valueOf(newString);
                score = score + result;
            }
        }
        scoreTally.setText(Integer.toString(score));
        Log.d("score:",Integer.toString(score));
    }
}
