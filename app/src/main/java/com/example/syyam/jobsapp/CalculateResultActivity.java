package com.example.syyam.jobsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by huma on 20/05/2018.
 */

public class CalculateResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private double area;
    private TextView arearesult,nbrofcoat;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calresultactivity);
        area=getIntent().getDoubleExtra("area",0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        seekBar = findViewById(R.id.seekbar_interval);
        nbrofcoat = findViewById(R.id.nbrofcoat);
        nbrofcoat.setText("Number of coat: 1");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progress = progress / 10;
//                progress = progress * 10;

                arearesult.setText(String.format("%.2f",progress*area));
                nbrofcoat.setText("Number of coat: "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        arearesult =  findViewById(R.id.arearesult);
        arearesult.setText(String.format("%.2f",area));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }



}