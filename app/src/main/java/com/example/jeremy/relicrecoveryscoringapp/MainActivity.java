package com.example.jeremy.relicrecoveryscoringapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.example.jeremy.relicrecoveryscoringapp.MainActivity.ButtonMode.*;

public class MainActivity extends AppCompatActivity {

    private int score = 0; //yes, long, cuz we will dominate...
    Listener listener = new Listener();

    public EditText autonomousGlyphStacked;
    public CheckBox redJewel, blueJewel, autonomousGlyphColumnBonus, autonomousInZone, relic1, relic2, relic3, relicStanding, robotBalanced;
    public ToggleButton team;
    public ToggleButton toggle[] = new ToggleButton[12];
    public Button programReset;
    public enum ButtonMode {
        NONE, GRAY, BROWN
    }
    public ButtonMode mode[] = new ButtonMode[12];
    public TextView scoreTally;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Begin create", "");
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
        programReset = (Button) findViewById(R.id.button2);
        toggle[0] = (ToggleButton) findViewById(R.id.toggle1);
        toggle[1] = (ToggleButton) findViewById(R.id.toggle2);
        toggle[2] = (ToggleButton) findViewById(R.id.toggle3);
        toggle[3] = (ToggleButton) findViewById(R.id.toggle4);
        toggle[4] = (ToggleButton) findViewById(R.id.toggle5);
        toggle[5] = (ToggleButton) findViewById(R.id.toggle6);
        toggle[6] = (ToggleButton) findViewById(R.id.toggle7);
        toggle[7] = (ToggleButton) findViewById(R.id.toggle8);
        toggle[8] = (ToggleButton) findViewById(R.id.toggle9);
        toggle[9] = (ToggleButton) findViewById(R.id.toggle10);
        toggle[10] = (ToggleButton) findViewById(R.id.toggle11);
        toggle[11] = (ToggleButton) findViewById(R.id.toggle12);
        relic1 = (CheckBox) findViewById(R.id.Relic1);
        relic2 = (CheckBox) findViewById(R.id.Relic2);
        relic3 = (CheckBox) findViewById(R.id.Relic3);
        relicStanding = (CheckBox) findViewById(R.id.UprightRelic);
        robotBalanced = (CheckBox) findViewById(R.id.BalancedRobot);
        programReset.setOnClickListener(listener);
        team.setOnClickListener(listener);
        redJewel.setOnClickListener(listener);
        blueJewel.setOnClickListener(listener);
        relic1.setOnClickListener(listener);
        relic2.setOnClickListener(listener);
        relic3.setOnClickListener(listener);
        relicStanding.setOnClickListener(listener);
        robotBalanced.setOnClickListener(listener);
        autonomousGlyphColumnBonus.setOnClickListener(listener);
        autonomousGlyphStacked.setOnClickListener(listener);
        autonomousInZone.setOnClickListener(listener);

        for(int i=0; i<toggle.length; i++){
            toggle[i].setOnClickListener(listener);
        }
        for(int x=0; x<mode.length; x++){
            mode[x]=NONE;
            toggle[x].setBackgroundColor(Color.WHITE);
        }
    }
    private class Listener implements View.OnClickListener {
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
                    for(int x = 0; x <mode.length; x++){
                        if(toggle[x].isPressed()) {
                            switch (mode[x]) {
                                case NONE:
                                    mode[x]=GRAY;
                                    break;
                                case GRAY:
                                    mode[x]=BROWN;
                                    break;
                                case BROWN:
                                    mode[x]=NONE;
                                    break;
                            }
                            if(mode[x]==NONE){
                                toggle[x].setBackgroundColor(Color.WHITE);
                            }
                            if(mode[x]==GRAY){
                                toggle[x].setBackgroundColor(Color.GRAY);
                            }
                            if(mode[x]==BROWN){
                                toggle[x].setBackgroundColor(Color.parseColor("#664400"));
                            }
                            for(int i = 0; i < mode.length; i++){
                                if(mode[i] != NONE) score += 2;
                            }
                        }
                    }
                    if(mode[0]==GRAY && mode[1]==BROWN && mode[2]==GRAY && mode[3]==BROWN && mode[4]
                            ==GRAY && mode[5]==BROWN && mode[6]==GRAY && mode[7]==BROWN && mode[8]==
                            GRAY && mode[9]==BROWN && mode[10]==GRAY && mode[11]==BROWN){
                        score += 30;
                    }
                    if(mode[0]==BROWN && mode[1]==GRAY && mode[2]==BROWN && mode[3]==GRAY && mode[4]
                            ==BROWN && mode[5]==GRAY && mode[6]==BROWN && mode[7]==GRAY && mode[8]==
                            BROWN && mode[9]==GRAY && mode[10]==BROWN && mode[11]==GRAY){
                        score += 30;
                    }
                    if(mode[0]==GRAY && mode[1]==BROWN && mode[2]==GRAY && mode[3]==BROWN && mode[4]
                            ==GRAY && mode[5]==BROWN && mode[6]==BROWN && mode[7]==GRAY && mode[8]==
                            BROWN && mode[9]==GRAY && mode[10]==BROWN && mode[11]==GRAY){
                        score += 30;
                    }
                    if(mode[0]==BROWN && mode[1]==GRAY && mode[2]==BROWN && mode[3]==GRAY && mode[4]
                            ==BROWN && mode[5]==GRAY && mode[6]==GRAY && mode[7]==BROWN && mode[8]==
                            GRAY && mode[9]==BROWN && mode[10]==GRAY && mode[11]==BROWN){
                        score += 30;
                    }
                    if(mode[0]==BROWN && mode[1]==GRAY && mode[2]==GRAY && mode[3]==BROWN && mode[4]
                            ==BROWN && mode[5]==GRAY && mode[6]==GRAY && mode[7]==BROWN && mode[8]==
                            BROWN && mode[9]==GRAY && mode[10]==GRAY && mode[11]==BROWN){
                        score += 30;
                    }
                    if(mode[0]==GRAY && mode[1]==BROWN && mode[2]==BROWN && mode[3]==GRAY && mode[4]
                            ==GRAY && mode[5]==BROWN && mode[6]==BROWN && mode[7]==GRAY && mode[8]==
                            GRAY && mode[9]==BROWN && mode[10]==BROWN && mode[11]==GRAY){
                        score += 30;
                    }
                    if(mode[0] != NONE && mode[1] != NONE && mode[2] != NONE){
                        score += 10;
                    }
                    if(mode[3] != NONE && mode[4] != NONE && mode[5] != NONE){
                        score += 10;
                    }
                    if(mode[6] != NONE && mode[7] != NONE && mode[8] != NONE){
                        score += 10;
                    }
                    if(mode[9] != NONE && mode[10] != NONE && mode[11] != NONE){
                        score += 10;
                    }
                    if(mode[0] != NONE && mode[3] != NONE && mode[6] != NONE && mode[9] != NONE) {
                        score += 20;
                    }
                    if(mode[1] != NONE && mode[4] != NONE && mode[7] != NONE && mode[10] != NONE) {
                        score += 20;
                    }
                    if(mode[2] != NONE && mode[5] != NONE && mode[8] != NONE && mode[11] != NONE) {
                        score += 20;
                    }
                    if(relic1.isChecked() && !relic2.isChecked() && !relic3.isChecked()){
                        score += 10;
                    }
                    if(relic2.isChecked() && !relic3.isChecked()){
                        score += 20;
                    }
                    if(relic3.isChecked()){
                        score += 40;
                    }
                    if(relicStanding.isChecked()){
                        score += 15;
                    }
                    if(robotBalanced.isChecked()){
                        score += 20;
                    }
                if(programReset.isPressed()){
                    for(int x=0; x<mode.length; x++){
                        mode[x]=NONE;
                        toggle[x].setBackgroundColor(Color.WHITE);
                    }
                    relic1.setChecked(false);
                    relic2.setChecked(false);
                    relic3.setChecked(false);
                    relicStanding.setChecked(false);
                    robotBalanced.setChecked(false);
                    redJewel.setChecked(true);
                    blueJewel.setChecked(true);
                    autonomousGlyphColumnBonus.setChecked(false);
                    autonomousInZone.setChecked(false);
                    autonomousGlyphStacked.setText("");
                    score=0;
                }
                break;
            }
            scoreTally.setText(Integer.toString(score));
        }
    }
}