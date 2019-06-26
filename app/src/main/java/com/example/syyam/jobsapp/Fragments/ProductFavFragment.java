package com.example.syyam.jobsapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.CountryActivity;
import com.example.syyam.jobsapp.CountryAdapter;
import com.example.syyam.jobsapp.Models.Countries;
import com.example.syyam.jobsapp.Models.LikedProducts;
import com.example.syyam.jobsapp.ProductFavAdapter;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductFavFragment extends Fragment {


    private RecyclerView recyclerView;

    public ProductFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_fav, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.productFavList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getData();


        return rootView;


    }

    public void getData() {

        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        // Config.getToken(CountryActivity.this)
        //"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTU2MTE0NDEyOX0.uQEiB8CyeHp5vsyF96W4CdYTRdzBgKPEsx5A4b0jg_8"
        Call<LikedProducts> countriesCall = retrofitController.getLikedProducts(Config.getToken(getContext()));
        countriesCall.enqueue(new Callback<LikedProducts>() {
            @Override
            public void onResponse(Call<LikedProducts> call, Response<LikedProducts> response) {

                LikedProducts list = response.body();
                if (list.getData() != null) {
                    recyclerView.setAdapter(new ProductFavAdapter(getContext(), list.getData()));

                }

                else
                    Toast.makeText(getContext(), "No items to display",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LikedProducts> call, Throwable t) {
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
