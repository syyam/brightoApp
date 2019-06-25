package com.example.syyam.jobsapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.Models.LikedProducts;
import com.example.syyam.jobsapp.Models.LikedShades;
import com.example.syyam.jobsapp.ProductFavAdapter;
import com.example.syyam.jobsapp.ShadeFavAdapter;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShadeFavFragment extends Fragment {

    private RecyclerView recyclerView;

    public ShadeFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shade_fav, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.shadeFavList);
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


        Call<LikedShades> countriesCall = retrofitController.getLikedShades(Config.getToken(getContext()));
        countriesCall.enqueue(new Callback<LikedShades>() {
            @Override
            public void onResponse(Call<LikedShades> call, Response<LikedShades> response) {

                LikedShades list = response.body();
                if (list.getData() != null) {
                    recyclerView.setAdapter(new ShadeFavAdapter(ShadeFavFragment.this, list.getData()));

                } else
                    Toast.makeText(getContext(), "No items to display", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LikedShades> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }
}
