package com.example.jeremy.relicrecoveryscoringapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private int score = 0; //yes, long, cuz we will dominate...
    Listener listener = new Listener();

    public EditText autonomousGlyphStacked;
    public CheckBox redJewel, blueJewel, autonomousGlyphColumnBonus, autonomousInZone;
    public ToggleButton team;
    public TextView scoreTally;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Begin create", "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    break;
            }
            Log.d("update score", "");
            scoreTally.setText(Integer.toString(score));
        }
    }
}
