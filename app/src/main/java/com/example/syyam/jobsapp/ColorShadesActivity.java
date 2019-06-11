package com.example.syyam.jobsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.params.ShadesParam;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorShadesActivity extends AppCompatActivity {

    private int color_id, country_id;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shades);


        recyclerView = (RecyclerView) findViewById(R.id.colorShadesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







        if (getIntent().hasExtra("color_id") && getIntent().hasExtra("country_id")) {

            String colorID=getIntent().getStringExtra("color_id");
            String countryID=getIntent().getStringExtra("country_id");

            //Toast.makeText(this, colorID+"",Toast.LENGTH_LONG).show();


            if (!TextUtils.isEmpty(colorID) && TextUtils.isDigitsOnly(colorID) && !TextUtils.isEmpty(countryID) && TextUtils.isDigitsOnly(countryID)) {
                color_id = Integer.parseInt(getIntent().getStringExtra("color_id"));
                country_id = Integer.parseInt(getIntent().getStringExtra("country_id"));
            }




            getData();
        }

    }

    private void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ShadesParam shadesParam=new ShadesParam();
        shadesParam.setFamily_id(1);
        shadesParam.setCountry_id(1);

        API retrofitController = build.create(API.class);

        Call<ShadesFamily> colorFinderCall=retrofitController.getColorShades(shadesParam);//empty because nothing is being sent in header
        colorFinderCall.enqueue(new Callback<ShadesFamily>() {
            @Override
            public void onResponse(Call<ShadesFamily> call, Response<ShadesFamily> response) {

                ShadesFamily list=response.body();
                recyclerView.setAdapter(new ColorShadesAdapter(ColorShadesActivity.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ShadesFamily> call, Throwable t) {
                Toast.makeText(ColorShadesActivity.this,"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }
}
