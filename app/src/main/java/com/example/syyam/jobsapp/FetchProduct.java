package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.syyam.jobsapp.Models.Products;
import com.example.syyam.jobsapp.Models.ProductsDatum;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchProduct extends AppCompatActivity implements ProductTypesAdapter.AdapterCallback {

    RecyclerView recyclerView;
    Products list;
    int pos, pos2;

    @Override
    public void onItemClicked(int position, final Integer id, String Sender) {
        ProductsDatum PTD = list.getData().get(position);
        String imgurl = Config.IMAGE_URL + PTD.getImage();
        String imgName = PTD.getName();
        Glide.with(getApplicationContext()).asBitmap().load(imgurl).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if(pos != -1){
                    MyProject.ProjectSet.get(pos).get(pos2).setImageView1(new BitmapDrawable(getResources(), resource));
                    MyProject.ProjectSet.get(pos).get(pos2).setProjectID(id);
                }
                finish();
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_product);

        Intent intent = getIntent();
        pos = intent.getIntExtra("Position", -1);
        pos2 = intent.getIntExtra("Position2", -1);

        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        recyclerView = findViewById(R.id.productTypesList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new ProductTypes.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        getData();
    }

    /*
     * Project Type
     * */
    private void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        SharedPreferences prefs = FetchProduct.this.getSharedPreferences("Country", Context.MODE_PRIVATE);
        final int cid = prefs.getInt("countryId", 0); //0 is the default value.


        CountryParam countryParam = new CountryParam();
        countryParam.setCountry_id(cid);

        //Config.getToken(getContext())
        Call<Products> productsCall = retrofitController.getProducts(countryParam);
        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {

                list = response.body();
                assert list != null;
                recyclerView.setAdapter(new ProductAdapter(FetchProduct.this, list.getData(), cid, false, FetchProduct.this));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                Toast.makeText(FetchProduct.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
