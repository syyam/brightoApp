package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_country);

        SharedPreferences.Editor editor = getSharedPreferences("Country", Context.MODE_PRIVATE).edit();
        editor.putInt("countryId", 1); //default 1st id will be sent if no field is selected
        editor.apply();

        recyclerView = (RecyclerView) findViewById(R.id.countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(CountryActivity.this, MainActivity.class);
                startActivity(l);
            }
        });
        getData();
    }

    public void getData() {
        Extras.showLoader(CountryActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);


        // Config.getToken(CountryActivity.this)
        Call<Countries> countriesCall = retrofitController.getCountries();
        countriesCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Extras.hideLoader();
                Countries list = response.body();
                if (list != null)
                    recyclerView.setAdapter(new CountryAdapter(CountryActivity.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }
}
