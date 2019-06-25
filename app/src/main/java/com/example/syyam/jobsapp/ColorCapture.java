package com.example.syyam.jobsapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.ProductType;
import com.example.syyam.jobsapp.Models.ShadesProduct.Datum;
import com.example.syyam.jobsapp.Models.ShadesProduct.ShadesProduct;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorCapture extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    private Bitmap bitmap;
    Toolbar toolbar;
    List<Datum> datumList = new ArrayList<>();
    List<Float> avgList = new ArrayList<>();

    HashMap<Integer, Float> avgMapList = new HashMap<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_capture);
        getData();
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("Color Capture");
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            toolbar.setNavigationIcon(R.mipmap.top_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageView.setImageBitmap(bmp);

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap = imageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                    int R = Color.red(pixel);
                    int G = Color.green(pixel);
                    int B = Color.blue(pixel);
                    showColor(R, G, B);

//                    textView.setBackgroundColor(Color.rgb(R, G, B));
//                    textView.setText(R + " " + G + " " + B);
                }
                return true;
            }
        });


    }

    private void getData() {
        Extras.showLoader(ColorCapture.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


//        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
//        final int cid = prefs.getInt("countryId", 0); //0 is the default value.


        //Config.getToken(getContext())
        Call<ShadesProduct> productsCall = retrofitController.getAllShades();//empty because nothing is being sent in header
        productsCall.enqueue(new Callback<ShadesProduct>() {
            @Override
            public void onResponse(Call<ShadesProduct> call, Response<ShadesProduct> response) {
                Extras.hideLoader();
                datumList = response.body().getData();

                if (response != null) {

//                    ProductType list = response.body();

//                    recyclerView.setAdapter(new ProductTypesAdapter(ProductTypes.this, list.getData(), (ProductTypesAdapter.AdapterCallback) ProductTypes.this,0));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShadesProduct> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(ColorCapture.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Subscription mTextWatchSubscription;
    private PublishSubject<String> mSearchResultsSubject;

    //     mSearchResultsSubject.onNext(s.toString());

    private void showColor(int r, int g, int b) {

        for (int i = 0; i < datumList.size(); i++) {
            int red = datumList.get(i).getColor().getR();
            int green = datumList.get(i).getColor().getG();
            int blue = datumList.get(i).getColor().getB();

            int rd = Math.abs(r - red);
            int gd = Math.abs(g - green);
            int bd = Math.abs(b - blue);

            float avg = (rd + gd + bd) / 3;
            avgMapList.put(i, avg);


        }
        avgMapList = sortByValue(avgMapList);

        Map.Entry<Integer, Float> entry=  avgMapList.entrySet().iterator().next();
        textView.setBackgroundColor(Color.rgb( datumList.get(entry.getKey()).getColor().getR(),  datumList.get(entry.getKey()).getColor().getG(),  datumList.get(entry.getKey()).getColor().getB()));



    }

    // function to sort hashmap by values
    public HashMap<Integer, Float> sortByValue(HashMap<Integer, Float> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Float>> list = new LinkedList<Map.Entry<Integer, Float>>(hm.entrySet());


        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Float>>() {
            public int compare(Map.Entry<Integer, Float> o1,
                               Map.Entry<Integer, Float> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Float> temp = new LinkedHashMap<Integer, Float>();
        for (Map.Entry<Integer, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


}
