package com.example.syyam.jobsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Models.DealerCity;
import com.example.syyam.jobsapp.Models.params.CityParam;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FetchDealer extends AppCompatActivity implements StoreAdapter.AdapterCallback, StoreCityAdapter.AdapterCallback {

    RecyclerView recyclerView;
    TextView topText;
    int pos, pos2;

    private String SenderName = "zero", prevText = "";

    @Override
    public void onItemClicked(int position, Integer id, String name, String sender) {
        // call back here
        SenderName = sender;

        if(topText.getText().toString().contains("Select"))
            topText.setText("");
        else {
            prevText = topText.getText().toString();
            prevText += "  >  ";
        }
        prevText += name;
        topText.setText(prevText);


        //make a condition to use one of them by sending a parameter
        if (SenderName.equals("first"))
            getCityData(id); // sending country_id
        else if(SenderName.equals("dealer")){
            MyProject.ProjectSet.get(pos).get(pos2).setImageTextView3(name);
            MyProject.ProjectSet.get(pos).get(pos2).setDealerSet(true);
            MyProject.ProjectSet.get(pos).get(pos2).setDealerID(id);
            finish();
        }
        else
            getCityDetailData(id); // sending city_id

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_dealer);

        Intent intent = getIntent();
        pos = intent.getIntExtra("Position", -1);
        pos2 = intent.getIntExtra("Position2", -1);

        topText = findViewById(R.id.topText);

        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        recyclerView = findViewById(R.id.storeList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new ProductTypes.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        getData();
    }

    private void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);

        //Config.getToken(getContext())
        Call<Countries> countriesCall = retrofitController.getCountries();
        countriesCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(@NonNull Call<Countries> call, @NonNull Response<Countries> response) {

                Countries list = response.body();
                assert list != null;
                recyclerView.setAdapter(new StoreAdapter(FetchDealer.this, list.getData(), FetchDealer.this));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Countries> call, @NonNull Throwable t) {
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCityDetailData(Integer id) {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);

        CityParam cityParam = new CityParam();
        cityParam.setCity_id(id);

        Call<DealerCity> citiesCall = retrofitController.getDealerCities(cityParam);
        citiesCall.enqueue(new Callback<DealerCity>() {
            @Override
            public void onResponse(@NonNull Call<DealerCity> call, @NonNull Response<DealerCity> response) {

                DealerCity list = response.body();
                recyclerView.invalidate();
                assert list != null;
                recyclerView.setAdapter(new StoreDealerCityAdapter(FetchDealer.this, list.getData(), FetchDealer.this));

            }

            @Override
            public void onFailure(@NonNull Call<DealerCity> call, @NonNull Throwable t) {
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getCityData(Integer id) {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);

        CountryParam countryParam = new CountryParam();
        countryParam.setCountry_id(id);

        //Config.getToken(getContext())
        Call<Countries> citiesCall = retrofitController.getCities(countryParam);
        citiesCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(@NonNull Call<Countries> call, @NonNull Response<Countries> response) {

                Countries list = response.body();
                recyclerView.invalidate();
                assert list != null;
                recyclerView.setAdapter(new StoreCityAdapter(FetchDealer.this, list.getData(), FetchDealer.this));

            }

            @Override
            public void onFailure(@NonNull Call<Countries> call, @NonNull Throwable t) {
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

}
