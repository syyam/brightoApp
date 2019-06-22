package com.example.syyam.jobsapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DesignerPaletteDetails extends AppCompatActivity {

    private TextView name, likes;
    private LinearLayout LL;
    private View C1, C2, C3;
    private ImageView fav;


    String NAME, HEX1, HEX2, HEX3, LIKES, LIKE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_palette_details);

        name = (TextView) findViewById(R.id.name);
        likes = (TextView) findViewById(R.id.likes);

        LL = (LinearLayout) findViewById(R.id.LL);

        C1 = (View) findViewById(R.id.color1);
        C2 = (View) findViewById(R.id.color2);
        C3 = (View) findViewById(R.id.color3);

        fav = (ImageView) findViewById(R.id.fav);


        if (getIntent().hasExtra("name") &&
                getIntent().hasExtra("hex1") &&
                getIntent().hasExtra("hex2") &&
                getIntent().hasExtra("hex3") &&
                getIntent().hasExtra("likes") &&
                getIntent().hasExtra("Like")) {

            String N = getIntent().getStringExtra("name");
            String H1 = getIntent().getStringExtra("hex1");
            String H2 = getIntent().getStringExtra("hex2");
            String H3 = getIntent().getStringExtra("hex3");
            String Ls = getIntent().getStringExtra("likes");
            String L = getIntent().getStringExtra("Like");


            if (
                    !TextUtils.isEmpty(N) &&
                            !TextUtils.isEmpty(H1) &&
                            !TextUtils.isEmpty(H2) &&
                            !TextUtils.isEmpty(H3) &&
                            !TextUtils.isEmpty(Ls) &&
                            !TextUtils.isEmpty(L)
            ) {


                NAME = getIntent().getStringExtra("name");
                HEX1 = getIntent().getStringExtra("hex1");
                HEX2 = getIntent().getStringExtra("hex2");
                HEX3 = getIntent().getStringExtra("hex3");
                LIKES = getIntent().getStringExtra("likes");
                LIKE = getIntent().getStringExtra("Like");

            }


        }// if end

        likes.setText(LIKES + " Likes");
        name.setText(NAME);

        if (LIKE.equals("Like")) {
            fav.setImageResource(getResources().getIdentifier(R.mipmap.favorite_selected + "", "drawable", getPackageName()));
        }

        C1.setBackgroundColor(Color.parseColor(HEX1 + ""));
        C2.setBackgroundColor(Color.parseColor(HEX2 + ""));
        C3.setBackgroundColor(Color.parseColor(HEX3 + ""));


    }
}
