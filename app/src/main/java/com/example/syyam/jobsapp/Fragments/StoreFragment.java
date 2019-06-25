package com.example.syyam.jobsapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Models.DealerCity;
import com.example.syyam.jobsapp.Models.params.CityParam;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.StoreAdapter;
import com.example.syyam.jobsapp.StoreCityAdapter;
import com.example.syyam.jobsapp.StoreDealerCityAdapter;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import net.skoumal.fragmentback.BackFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment implements StoreAdapter.AdapterCallback, StoreCityAdapter.AdapterCallback, BackFragment {


    RecyclerView recyclerView;
    Context mContext;
    TextView topText;

    private String SenderName = "zero", Name = "", prevText = "   ";
    private int SenderId = 0;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onItemClicked(int position, Integer id, String name, String sender) {
        // call back here
        SenderName = sender;
        prevText = topText.getText().toString();
        Name = name;

        if (!prevText.contains(name)) {
            prevText = prevText + "  >  " + name;
            topText.setText(prevText);
        }


        //make a condition to use one of them by sending a parameter
        if (SenderName.equals("first"))
            getCityData(id); // sending country_id
        else
            getCityDetailData(id); // sending city_id


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

        mContext = rootView.getContext();
        topText = (TextView) rootView.findViewById(R.id.topText);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.storeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        getData();


        return rootView;
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
            public void onResponse(Call<Countries> call, Response<Countries> response) {

                Countries list = response.body();
                recyclerView.setAdapter(new StoreAdapter(StoreFragment.this, list.getData(), (StoreAdapter.AdapterCallback) StoreFragment.this));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCityDetailData(Integer id) {
        Extras.showLoader(getContext());
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);

        CityParam cityParam = new CityParam();
        cityParam.setCity_id(id);

        SenderId = id;
        //Config.getToken(getContext())
        Call<DealerCity> citiesCall = retrofitController.getDealerCities(cityParam);
        citiesCall.enqueue(new Callback<DealerCity>() {
            @Override
            public void onResponse(Call<DealerCity> call, Response<DealerCity> response) {
                Extras.hideLoader();
                DealerCity list = response.body();
                recyclerView.invalidate();
                recyclerView.setAdapter(new StoreDealerCityAdapter(StoreFragment.this, list.getData()));

            }

            @Override
            public void onFailure(Call<DealerCity> call, Throwable t) {
                Extras.hideLoader();
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getCityData(Integer id) {
        Extras.showLoader(getContext());
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        API retrofitController = build.create(API.class);

        CountryParam countryParam = new CountryParam();
        countryParam.setCountry_id(id);

        SenderId = id;
        //Config.getToken(getContext())
        Call<Countries> citiesCall = retrofitController.getCities(countryParam);
        citiesCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Extras.hideLoader();
                Countries list = response.body();
                recyclerView.invalidate();
                recyclerView.setAdapter(new StoreCityAdapter(StoreFragment.this, list.getData(), (StoreCityAdapter.AdapterCallback) StoreFragment.this));

            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Extras.hideLoader();
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onBackPressed() {

        // -- your code --

        //Toast.makeText(getContext(), prevText, Toast.LENGTH_LONG).show();

//        for (int i = prevText.length(); i >= 0; i--) {
//            if(prevText.charAt(i). ){
//
//            }
//        }

        if (SenderName.equals("first") && SenderName != null) {
            getData(); // FininshSpecific
            SenderName = "zero";
        }

        if (SenderName.equals("second") && SenderName != null) {
            getCityData(SenderId); // FininshSpecific
            SenderName = "first";
        }
        // return true if you want to consume back-pres sed event
        return true;
    }

    public int getBackPriority() {
        // use apropriate priority here
        return NORMAL_BACK_PRIORITY;
    }
}
