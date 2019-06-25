package com.example.syyam.jobsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Models.DesignerPalettesModel.DesignerPalettes;
import com.example.syyam.jobsapp.Models.Like;
import com.example.syyam.jobsapp.Models.params.PaletteParam;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DesignerPalettedActivity extends AppCompatActivity implements DesignerPalettesAdapter.AdapterCallback {

    private Boolean loved = false;
    private int p_id;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_paletted);

        recyclerView = (RecyclerView) findViewById(R.id.DPList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        Call<DesignerPalettes> countriesCall = retrofitController.getDesignerPalettes();
        countriesCall.enqueue(new Callback<DesignerPalettes>() {
            @Override
            public void onResponse(Call<DesignerPalettes> call, Response<DesignerPalettes> response) {

                DesignerPalettes list = response.body();
                recyclerView.setAdapter(new DesignerPalettesAdapter(DesignerPalettedActivity.this, list.getData(), (DesignerPalettesAdapter.AdapterCallback) DesignerPalettedActivity.this));


                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DesignerPalettes> call, Throwable t) {
                Toast.makeText(DesignerPalettedActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClicked(int position, Integer id, Boolean loved) {
        this.loved = loved;
        p_id = id;
        //make a condition to use one of them by sending a parameter


        String token;
        SharedPreferences prefs = getSharedPreferences(Config.MY_PREFS_NAME, MODE_PRIVATE);
        token = prefs.getString("token", null);

        if (token != null)  //if user is signed in only then make him like the product
        {
            if (!loved) {
                loved = true;
                loveCall("like", p_id);
            } else if (loved) {
                loved = false;
                loveCall("unlike", p_id);
            }
        } else
            Toast.makeText(this, "You need to sign in.", Toast.LENGTH_SHORT).show();


    }

    private void loveCall(String sender, int p_id) {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        PaletteParam paletteParam = new PaletteParam();
        paletteParam.setPallete_id(p_id);

        Call<Like> productsCall = null;
        if (sender.equals("like")) {
            productsCall = retrofitController.getLikePalettes(Config.getToken(this), paletteParam);
        }
        if (sender.equals("unlike")) {
            productsCall = retrofitController.getUnLikePalettes(Config.getToken(this), paletteParam);
        }


        //Config.getToken(getContext())

        productsCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {

                if (response != null) {
                    Like list = response.body();

                    if (list != null) {
                        Toast.makeText(DesignerPalettedActivity.this, list.getMessage(), Toast.LENGTH_LONG).show();

                        if (list.getMessage() == null) {
                            Toast.makeText(DesignerPalettedActivity.this, list.getErrors() + " You need to sign in.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(DesignerPalettedActivity.this, "Invalid operation", Toast.LENGTH_LONG).show();
                        return;
                    }

                }
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Toast.makeText(DesignerPalettedActivity.this, "Failure: " + t, Toast.LENGTH_LONG).show();
            }
        });
    }
}
