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
    private String countryName;
    private int check = 0;

    private String[] strings = new String[2];

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onItemClicked(int position, Integer id, String name, String sender) {
        // call back here
        SenderName = sender;
        prevText = topText.getText().toString();
        Name = name;


//        if (!prevText.contains(name)) {
//            prevText = prevText + "  >  " + name;
//            topText.setText(prevText);
//        }

        for (int i = 0; i <= strings.length; i++) {

            if (!prevText.contains(name)) {
                if (check == 0) {
                    countryName = strings[i];
                }
                strings[i] = "  >  " + Name;  //jugaar at its finest :-)
                if (check == 0) {
                    countryName = strings[i];
                    check = 1;
                }

                prevText = prevText + strings[i];
                topText.setText(prevText);
            }


        }


        //make a condition to use one of them by sending a parameter
        if (SenderName.equals("first"))
            getCityData(id); // sending country_id
        else
            getCityDetailData(id); // sending city_id


    }

    public boolean onBackPressed() {
        //Toast.makeText(getContext(), prevText, Toast.LENGTH_LONG).show();

//            topText.setText("Select  >  "+ strings[strings.length-1]);


//        for (int i = strings.length - 1; i > 0; i--) {
//            strings[i] = "";
//
//
//        }
//        prevText = "Select > ";
//        for (int i = 0; i <= strings.length; i++) {
//
//            topText.setText(prevText);
//        }

        if (SenderName.equals("first") && SenderName != null) {
            getData(); // FininshSpecific
            SenderName = "zero";

            topText.setText("Select  ");
        }

        if (SenderName.equals("second") && SenderName != null) {
            getCityData(SenderId); // FininshSpecific
            SenderName = "first";

            topText.setText("Select " + countryName);
        }
        // return true if you want to consume back-pres sed event
        return true;
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
                if (list != null)
                    recyclerView.setAdapter(new StoreAdapter(getContext(), list.getData(), (StoreAdapter.AdapterCallback) StoreFragment.this));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_LONG).show();
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
                recyclerView.setAdapter(new StoreDealerCityAdapter(getContext(), list.getData(), null));

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
                recyclerView.setAdapter(new StoreCityAdapter(getContext(), list.getData(), (StoreCityAdapter.AdapterCallback) StoreFragment.this));

            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Extras.hideLoader();
                //Toast.makeText(CountryActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    public int getBackPriority() {
        // use apropriate priority here
        return NORMAL_BACK_PRIORITY;
    }
}
