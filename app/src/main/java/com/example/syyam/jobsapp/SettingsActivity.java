package com.example.syyam.jobsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {

    private EditText newPass, oldPass, confirmPass, fName, lName;
    private Button submitBtn;

    private String f_name, l_name, old_pass, new_pass, confirm_pass;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        recyclerView = (RecyclerView) findViewById(R.id.countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        f_name = fName.getText().toString();
        l_name = lName.getText().toString();
        old_pass = oldPass.getText().toString();
        new_pass = newPass.getText().toString();
        confirm_pass = confirmPass.getText().toString();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!TextUtils.isEmpty())
                sendData();
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
                recyclerView.setAdapter(new CountryAdapter(SettingsActivity.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Toast.makeText(SettingsActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sendData() {

    }
}
