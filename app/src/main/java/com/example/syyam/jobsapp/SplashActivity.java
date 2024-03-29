package com.example.syyam.jobsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mSkipBtn;

    private int mCurrentPage;
    private int check = 0;

    int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefs = getSharedPreferences("Country", MODE_PRIVATE);
        cid = prefs.getInt("countryId", -1); //0 is the default value.

        //Toast.makeText(this, cid + "hello", Toast.LENGTH_LONG).show();


        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mSkipBtn = (Button) findViewById(R.id.skipBtn);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
//
//            }
//        },3000);


        addDotsIndicatot(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);


                if (mCurrentPage == 3) {

                    check++;
                }

                if (check == 2) { // buggy, I hope on one take a note of it

                    check = 0;

                    Intent l;
                    if (cid != -1) { // if country is selected already, send to main
                        l = new Intent(SplashActivity.this, MainActivity.class);
                    } else
                        l = new Intent(SplashActivity.this, CountryActivity.class);

                    finish();
                    startActivity(l);

                }
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l;
                if (cid != -1) { // if country is selected already, send to main
                    l = new Intent(SplashActivity.this, MainActivity.class);
                } else
                    l = new Intent(SplashActivity.this, CountryActivity.class);
                finish();
                startActivity(l);
            }
        });
    }

    public void addDotsIndicatot(int position) {
        mDots = new TextView[4]; //hardcoded, change it with sliderAdapter slide_text.length

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.Gray96));

            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicatot(position);

            mCurrentPage = position;

            if (position == mDots.length - 1) {
                mNextBtn.setText("FINISH");

            } else {
                mNextBtn.setText("NEXT");
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
