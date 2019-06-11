package com.example.syyam.jobsapp;

import com.example.syyam.jobsapp.Models.ColorFinder;
import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.params.ShadesParam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface API {


    @GET("family")
    Call<ColorFinder> getColorFinder();

    @POST("shades/family")
    Call<ShadesFamily> getColorShades(@Body ShadesParam shadesParam);

}
