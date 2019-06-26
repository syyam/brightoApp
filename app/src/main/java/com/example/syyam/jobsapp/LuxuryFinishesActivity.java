package com.example.syyam.jobsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.syyam.jobsapp.Adapters.SlidingImage_Adapter;
import com.example.syyam.jobsapp.Models.FurnishFinish;
import com.example.syyam.jobsapp.Utils.Config;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huma on 12/04/2018.
 */

public class LuxuryFinishesActivity extends AppCompatActivity implements View.OnClickListener, YouTubePlayer.OnInitializedListener {

    private TextView textViewMainTitle;
    private ImageView image;
    private TextView title, description;
    FurnishFinish.Datum data;
    private ViewPager mPager;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luxuryfinishesactivity);
        intailizeYoutube();
        String d = getIntent().getStringExtra("data");
        Gson gson = new Gson();
        data = gson.fromJson(d, FurnishFinish.Datum.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewMainTitle = (TextView) findViewById(R.id.textViewTitle);
        image = (ImageView) findViewById(R.id.imagebar);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewMainTitle.setText(data.getName());
        title.setText(data.getName());
        description.setText(data.getDescription());
        String imgurl = Config.IMAGE_URL + data.getCoverImage();
        Glide.with(LuxuryFinishesActivity.this).load(imgurl).placeholder(getResources().getDrawable(R.drawable.background_image)).into(image);
        sliderImage();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_fav) {

            return true;
        }

        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sliderImage() {
        List<String> imageList = new ArrayList<>();
        imageList.add(data.getImage1());
        imageList.add(data.getImage2());
        imageList.add(data.getImage2());
        imageList.add(data.getImage2());
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(LuxuryFinishesActivity.this, imageList));
    }


    private void intailizeYoutube() {
        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize("AIzaSyBn5OOUzH27W_DQpiBC_mjXIUFXQ-PMv_w",
                this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo("9WdOvBAinn8");

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
