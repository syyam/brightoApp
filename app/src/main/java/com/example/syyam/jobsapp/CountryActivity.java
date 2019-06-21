package com.example.syyam.jobsapp;

import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Object data;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_country);

        recyclerView = (RecyclerView) findViewById(R.id.countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        submitBtn=(Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(CountryActivity.this,MainActivity.class);
                startActivity(l);
            }
        });
        getData();
    }

    public void getData() {

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

                Countries list = response.body();
                recyclerView.setAdapter(new CountryAdapter(CountryActivity.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }
}
