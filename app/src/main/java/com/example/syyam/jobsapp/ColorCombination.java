package com.example.syyam.jobsapp;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.syyam.jobsapp.Models.ShadesProduct.ShadesProduct;
import com.example.syyam.jobsapp.Models.params.ProductParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorCombination extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name, itemCode, prodcutName;
    private ImageView fav1, fav2, fav3;
    private LinearLayout top;
    private View shade1, shade2, shade3, shade11, shade22, shade33, shade111, shade222, shade333;

    private int p_id, _R, G, B;
    private String p_Sname, p_Name, p_itemCode;

    //ye data peeche se arha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_combination);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");


        name = (TextView) findViewById(R.id.name);
        itemCode = (TextView) findViewById(R.id.itemCode);
        prodcutName = (TextView) findViewById(R.id.prodcutName);
        fav1 = (ImageView) findViewById(R.id.fav1);
        fav2 = (ImageView) findViewById(R.id.fav2);
        fav3 = (ImageView) findViewById(R.id.fav3);
        shade1 = (View) findViewById(R.id.shade1);
        shade2 = (View) findViewById(R.id.shade2);
        shade3 = (View) findViewById(R.id.shade3);
        top = (LinearLayout) findViewById(R.id.top);


        shade11 = (View) findViewById(R.id.shade11);
        shade22 = (View) findViewById(R.id.shade22);
        shade33 = (View) findViewById(R.id.shade33);

        shade111 = (View) findViewById(R.id.shade111);
        shade222 = (View) findViewById(R.id.shade222);
        shade333 = (View) findViewById(R.id.shade333);

        if (getIntent().hasExtra("p_id") &&
                getIntent().hasExtra("r") &&
                getIntent().hasExtra("g") &&
                getIntent().hasExtra("b") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("itemCode") &&
                getIntent().hasExtra("productName")) {

            String pid = getIntent().getStringExtra("p_id");
            String r = getIntent().getStringExtra("r");
            String g = getIntent().getStringExtra("g");
            String b = getIntent().getStringExtra("b");

            p_Sname = getIntent().getStringExtra("name");
            p_itemCode = getIntent().getStringExtra("itemCode");
            p_Name = getIntent().getStringExtra("productName");

            if (!TextUtils.isEmpty(pid) && !TextUtils.isEmpty(r) && !TextUtils.isEmpty(g) && !TextUtils.isEmpty(b)) {
                p_id = Integer.parseInt(getIntent().getStringExtra("p_id"));
                _R = Integer.parseInt(getIntent().getStringExtra("r"));
                G = Integer.parseInt(getIntent().getStringExtra("g"));
                B = Integer.parseInt(getIntent().getStringExtra("b"));
            }

        }// if end

        name.setText(p_Sname);
        prodcutName.setText(p_Name);
        itemCode.setText(p_itemCode);
        top.setBackgroundColor(Color.rgb(_R, G, B));


        getData();
    }

    private void getData() {
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        ProductParam productParam = new ProductParam();
        productParam.setProduct_id(p_id);

        //Config.getToken(getContext())
        Call<ShadesProduct> productsCall = retrofitController.getShadesProduct(productParam);
        productsCall.enqueue(new Callback<ShadesProduct>() {
            @Override
            public void onResponse(Call<ShadesProduct> call, Response<ShadesProduct> response) {
                Extras.hideLoader();
                if (response != null) {
                    Random rand = new Random();

                    ShadesProduct list = response.body();
                    int dataSize = list.getData().size();
                    int randNo1 = rand.nextInt(dataSize);
                    int randNo2 = rand.nextInt(dataSize);
                    int randNo3 = rand.nextInt(dataSize);
                    int randNo4 = rand.nextInt(dataSize);
                    int randNo5 = rand.nextInt(dataSize);
                    int randNo6 = rand.nextInt(dataSize);
                    int randNo7 = rand.nextInt(dataSize);
                    int randNo8 = rand.nextInt(dataSize);
                    int randNo9 = rand.nextInt(dataSize);

                    int R1 = list.getData().get(randNo1).getColor().getR();
                    int R2 = list.getData().get(randNo2).getColor().getR();
                    int R3 = list.getData().get(randNo3).getColor().getR();
                    int R4 = list.getData().get(randNo4).getColor().getR();
                    int R5 = list.getData().get(randNo5).getColor().getR();
                    int R6 = list.getData().get(randNo6).getColor().getR();
                    int R7 = list.getData().get(randNo7).getColor().getR();
                    int R8 = list.getData().get(randNo8).getColor().getR();
                    int R9 = list.getData().get(randNo9).getColor().getR();


                    int G1 = list.getData().get(randNo1).getColor().getG();
                    int G2 = list.getData().get(randNo2).getColor().getG();
                    int G3 = list.getData().get(randNo3).getColor().getG();
                    int G4 = list.getData().get(randNo4).getColor().getG();
                    int G5 = list.getData().get(randNo5).getColor().getG();
                    int G6 = list.getData().get(randNo6).getColor().getG();
                    int G7 = list.getData().get(randNo7).getColor().getG();
                    int G8 = list.getData().get(randNo8).getColor().getG();
                    int G9 = list.getData().get(randNo9).getColor().getG();


                    int B1 = list.getData().get(randNo1).getColor().getB();
                    int B2 = list.getData().get(randNo2).getColor().getB();
                    int B3 = list.getData().get(randNo3).getColor().getB();
                    int B4 = list.getData().get(randNo4).getColor().getB();
                    int B5 = list.getData().get(randNo5).getColor().getB();
                    int B6 = list.getData().get(randNo6).getColor().getB();
                    int B7 = list.getData().get(randNo7).getColor().getB();
                    int B8 = list.getData().get(randNo8).getColor().getB();
                    int B9 = list.getData().get(randNo9).getColor().getB();


                    shade1.setBackgroundColor(Color.rgb(R1, G1, B1));
                    shade2.setBackgroundColor(Color.rgb(R2, G2, B2));
                    shade3.setBackgroundColor(Color.rgb(R3, G3, B3));

                    shade11.setBackgroundColor(Color.rgb(R4, G4, B4));
                    shade22.setBackgroundColor(Color.rgb(R5, G5, B5));
                    shade33.setBackgroundColor(Color.rgb(R6, G6, B6));

                    shade111.setBackgroundColor(Color.rgb(R7, G7, B7));
                    shade222.setBackgroundColor(Color.rgb(R8, G8, B8));
                    shade333.setBackgroundColor(Color.rgb(R9, G9, B9));


//                    Toast.makeText(ProductDetails.this, "success"+data,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShadesProduct> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(ColorCombination.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
