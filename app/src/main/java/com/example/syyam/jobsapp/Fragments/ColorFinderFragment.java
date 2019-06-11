package com.example.syyam.jobsapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.ColorFinderAdapter;
import com.example.syyam.jobsapp.Models.ColorFinder;
import com.example.syyam.jobsapp.Models.Datum;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFinderFragment extends Fragment {



    RecyclerView recyclerView;
    public ColorFinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View rootView = inflater.inflate(R.layout.fragment_colorfinder, container, false);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.colorFinderList);
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

        Call<ColorFinder> colorFinderCall=retrofitController.getColorFinder();//empty because nothing is being sent in header
        colorFinderCall.enqueue(new Callback<ColorFinder>() {
            @Override
            public void onResponse(Call<ColorFinder> call, Response<ColorFinder> response) {

                ColorFinder list=response.body();
                recyclerView.setAdapter(new ColorFinderAdapter(ColorFinderFragment.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ColorFinder> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

}
