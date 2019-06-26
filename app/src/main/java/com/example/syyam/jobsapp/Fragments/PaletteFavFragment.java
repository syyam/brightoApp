package com.example.syyam.jobsapp.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.Models.LikedProducts;
import com.example.syyam.jobsapp.PalleteFavAdapter;
import com.example.syyam.jobsapp.ProductFavAdapter;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaletteFavFragment extends Fragment
{
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.palletefacframgemt,null);
    init(view);
     return view;
    }

    private void init(View view) {
        recyclerView= view.findViewById(R.id.recyclerView);
        getData();

    }



    public void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);
        Call<LikedProducts> countriesCall = retrofitController.getLikePallets(Config.getToken(getContext()));
        countriesCall.enqueue(new Callback<LikedProducts>() {
            @Override
            public void onResponse(Call<LikedProducts> call, Response<LikedProducts> response) {

                LikedProducts list = response.body();
                if (list.getData() != null) {

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                   recyclerView.setAdapter(new PalleteFavAdapter(getContext(), list.getData()));

                }

                else
                    Toast.makeText(getContext(), "No items to display",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LikedProducts> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }
}
