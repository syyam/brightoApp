package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.ShadesFamilyDatum;
import com.example.syyam.jobsapp.Models.params.CountryFamilyParam;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchShade extends AppCompatActivity implements ProductTypesAdapter.AdapterCallback {

    RecyclerView recyclerView;
    ShadesFamily list;
    int pos, pos2;

    @Override
    public void onItemClicked(int position, Integer id, String Sender) {
        ShadesFamilyDatum SFD = list.getData().get(position);
        final int R = SFD.getColor().getR();
        final int G = SFD.getColor().getG();
        final int B = SFD.getColor().getB();
        float dip = 70f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        Bitmap bitmap = Bitmap.createBitmap((int) px, (int) px, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(R, G, B));
        canvas.drawRect(0F, 0F, px, px, paint);
        MyProject.ProjectSet.get(pos).get(pos2).setImageView2(new BitmapDrawable(getResources(), bitmap));
        MyProject.ProjectSet.get(pos).get(pos2).setShadeID(id);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_shade);

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

    private void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SharedPreferences prefs = FetchShade.this.getSharedPreferences("Country", Context.MODE_PRIVATE);
        final int cid = prefs.getInt("countryId", -1); //0 is the default value.
        CountryFamilyParam countryFamilyParam = new CountryFamilyParam();
        countryFamilyParam.setFamily_id(MyProject.ProjectSet.get(pos).get(pos2).getProjectID());
        countryFamilyParam.setCountry_id(cid);

        API retrofitController = build.create(API.class);


        //,Config.getToken(ColorShadesActivity.this),
        Call<ShadesFamily> colorFinderCall = retrofitController.getColorShades(countryFamilyParam);//empty because nothing is being sent in header
        colorFinderCall.enqueue(new Callback<ShadesFamily>() {
            @Override
            public void onResponse(@NonNull Call<ShadesFamily> call, @NonNull Response<ShadesFamily> response) {

                list = response.body();
                assert list != null;
                recyclerView.setAdapter(new ColorShadesAdapter(FetchShade.this, list.getData(), FetchShade.this));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<ShadesFamily> call, @NonNull Throwable t) {
                Toast.makeText(FetchShade.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

}
