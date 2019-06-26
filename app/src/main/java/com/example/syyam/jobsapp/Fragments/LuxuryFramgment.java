package com.example.syyam.jobsapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.LuxuryFinishesAdapter;
import com.example.syyam.jobsapp.Models.FurnishFinish;
import com.example.syyam.jobsapp.Models.LikedProducts;
import com.example.syyam.jobsapp.PalleteFavAdapter;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class LuxuryFramgment extends Fragment {


    private RecyclerView recyclerView;
    private LuxuryFinishesAdapter mAdapter;

    public LuxuryFramgment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_luxury, container, false);
        init(rootView);
        return rootView;

    }

    private void init(View rootView) {
        recyclerView=rootView.findViewById(R.id.recyclerView);
        getAllLuxureFinish();

    }



    public void getAllLuxureFinish() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);
        Call<FurnishFinish> countriesCall = retrofitController.getAllLuxuryFinish();
        countriesCall.enqueue(new Callback<FurnishFinish>() {
            @Override
            public void onResponse(Call<FurnishFinish> call, Response<FurnishFinish> response) {



                if (response.body().getData() != null) {

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mAdapter = new LuxuryFinishesAdapter(getContext(),response.body().getData());
                    recyclerView.setAdapter(mAdapter);

                }

                else
                    Toast.makeText(getContext(), "No items to display",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<FurnishFinish> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

}
