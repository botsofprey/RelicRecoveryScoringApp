package com.example.jeremy.relicrecoveryscoringapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private long score; //yes, long cause we will dominate....

    public EditText autonomousGlyphStacked;
    public CheckBox redJewel, blueJewel, autonomousGlyphColoumnBonus, autonomousInZone;
    public ToggleButton team;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextMessage = (TextView) findViewById(R.id.message);
        autonomousGlyphStacked = (EditText) findViewById(R.id.autonomousGlyphsStacked);
        redJewel = (CheckBox) findViewById(R.id.redJewel);
        blueJewel = (CheckBox) findViewById(R.id.blueJewel);
        autonomousGlyphColoumnBonus = (CheckBox) findViewById(R.id.glyphColumnBonus);
        autonomousInZone = (CheckBox) findViewById(R.id.autonomousInZone);
        team = (ToggleButton) findViewById(R.id.team);

    }

    @Override
    public void onClick(View view) {
        //do scoring hear, just tally up points every time this is called...
        score = 0;
        if(team.getText() == "Team Blue"){

        }
        else if(team.getText() == "Team Red"){

        }

        //etc, etc
    }
}
