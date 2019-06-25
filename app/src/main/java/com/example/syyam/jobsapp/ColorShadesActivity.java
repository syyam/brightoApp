package com.example.syyam.jobsapp;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.params.CountryFamilyParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorShadesActivity extends AppCompatActivity {

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = spacing; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int color_id, country_id;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shades);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.White)));
        actionBar.setTitle("");
        actionBar.show();

        int spanCount = 3; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = false;


        recyclerView = (RecyclerView) findViewById(R.id.colorShadesList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        if (getIntent().hasExtra("color_id") && getIntent().hasExtra("country_id")) {

            String colorID = getIntent().getStringExtra("color_id");
            String countryID = getIntent().getStringExtra("country_id");

            //Toast.makeText(this, colorID+"",Toast.LENGTH_LONG).show();


            if (!TextUtils.isEmpty(colorID) && TextUtils.isDigitsOnly(colorID) && !TextUtils.isEmpty(countryID) && TextUtils.isDigitsOnly(countryID)) {
                color_id = Integer.parseInt(getIntent().getStringExtra("color_id"));
                country_id = Integer.parseInt(getIntent().getStringExtra("country_id"));
            }


            getData();
        }

    }

    private void getData() {
        Extras.showLoader(ColorShadesActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        CountryFamilyParam countryFamilyParam = new CountryFamilyParam();
        countryFamilyParam.setFamily_id(color_id);
        countryFamilyParam.setCountry_id(country_id); //isko hardcode nae karna

        API retrofitController = build.create(API.class);


        //,Config.getToken(ColorShadesActivity.this),
        Call<ShadesFamily> colorFinderCall = retrofitController.getColorShades(countryFamilyParam);//empty because nothing is being sent in header
        colorFinderCall.enqueue(new Callback<ShadesFamily>() {
            @Override
            public void onResponse(Call<ShadesFamily> call, Response<ShadesFamily> response) {
                Extras.hideLoader();
                ShadesFamily list = response.body();
                recyclerView.setAdapter(new ColorShadesAdapter(ColorShadesActivity.this, list.getData()));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ShadesFamily> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(ColorShadesActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
