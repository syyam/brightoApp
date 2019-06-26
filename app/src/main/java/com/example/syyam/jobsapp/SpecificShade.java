package com.example.syyam.jobsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.Like;
import com.example.syyam.jobsapp.Models.SpecificShadeModel.SpecificShadeModel;
import com.example.syyam.jobsapp.Models.params.IdParam;
import com.example.syyam.jobsapp.Models.params.ProductParam;
import com.example.syyam.jobsapp.Models.params.ShadeParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecificShade extends AppCompatActivity  {

    private Menu mMenu;
    private Boolean loved = false;
    int r, g, b, s_id;
    String name, desc, itemCode;
    RelativeLayout linearLayout;
    TextView nameTV, descTV, itemcodeTV, productName;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_shade);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_cancel_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().hasExtra("R") &&
                getIntent().hasExtra("G") &&
                getIntent().hasExtra("B") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("desc") &&
                getIntent().hasExtra("sid") &&
                getIntent().hasExtra("itemCode")) {

            String R = getIntent().getStringExtra("R");
            String G = getIntent().getStringExtra("G");
            String B = getIntent().getStringExtra("B");
            String SID = getIntent().getStringExtra("sid");

            String getName = getIntent().getStringExtra("name");
            String getDesc = getIntent().getStringExtra("desc");
            String getItemCode = getIntent().getStringExtra("itemCode");


            if (
                    !TextUtils.isEmpty(R) &&
                            !TextUtils.isEmpty(G) &&
                            !TextUtils.isEmpty(B) &&
                            !TextUtils.isEmpty(getName) &&
                            !TextUtils.isEmpty(getDesc) &&
                            !TextUtils.isEmpty(SID) &&
                            !TextUtils.isEmpty(getItemCode)
            ) {

                r = Integer.parseInt(getIntent().getStringExtra("R"));
                g = Integer.parseInt(getIntent().getStringExtra("G"));
                b = Integer.parseInt(getIntent().getStringExtra("B"));
                s_id = Integer.parseInt(getIntent().getStringExtra("sid"));

                name = getIntent().getStringExtra("name");
                desc = getIntent().getStringExtra("desc");
                itemCode = getIntent().getStringExtra("itemCode");
            }


        }// if end


        linearLayout = (RelativeLayout) findViewById(R.id.backColor);
        nameTV = (TextView) findViewById(R.id.name);
        descTV = (TextView) findViewById(R.id.description);
        itemcodeTV = (TextView) findViewById(R.id.itemCode);
        productName = (TextView) findViewById(R.id.productName);

        linearLayout.setBackgroundColor(Color.rgb(r, g, b));
        nameTV.setText(name);
        descTV.setText(desc);
        itemcodeTV.setText(itemCode);

//        Data();

    }

    public void Data() {
        Extras.showLoader(SpecificShade.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


//        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
//        final int cid = prefs.getInt("countryId", 0); //0 is the default value.

        IdParam id = new IdParam();
        id.setId(s_id);

        //Config.getToken(getContext())

        Call<SpecificShadeModel> productsCall = retrofitController.getSpecificShade(id);
        productsCall.enqueue(new Callback<SpecificShadeModel>() {
            @Override
            public void onResponse(Call<SpecificShadeModel> call, Response<SpecificShadeModel> response) {
                Extras.hideLoader();
                if (response != null) {
                    SpecificShadeModel list = response.body();


                    int _r = list.getData().get(0).getColor().getR();
                    int _g = list.getData().get(0).getColor().getG();
                    int _b = list.getData().get(0).getColor().getB();

                    linearLayout.setBackgroundColor(Color.rgb(_r, _g, _b));
                    nameTV.setText(list.getData().get(0).getName().toString());
                    descTV.setText(list.getData().get(0).getDescription());
                    itemcodeTV.setText(list.getData().get(0).getItemCode());
                    descTV.setText(list.getData().get(0).getDescription());
                    if (list.getData().get(0).getProducts().size()!=0 )
                        productName.setText(list.getData().get(0).getProducts().get(0).getName());
                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SpecificShadeModel> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(SpecificShade.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loveCall(String sender) {
        Extras.showLoader(SpecificShade.this);

        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        ShadeParam shadeParam = new ShadeParam();
        shadeParam.setShade_id(s_id);

        Call<Like> productsCall = null;
        if (sender.equals("loved")) {
            productsCall = retrofitController.getLikeShade(Config.getToken(this), shadeParam);
        }
        if (sender.equals("not_loved")) {
            productsCall = retrofitController.getUnLikeShade(Config.getToken(this), shadeParam);
        }


        //Config.getToken(getContext())

        productsCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                Extras.hideLoader();
                if (response != null) {
                    Like list = response.body();

                    if (list != null) {
                        Toast.makeText(SpecificShade.this, list.getMessage().toString(), Toast.LENGTH_LONG).show();

                        if (list.getMessage() == null) {
                            Toast.makeText(SpecificShade.this, list.getErrors().toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SpecificShade.this, "An error occured", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(SpecificShade.this, "Failure: " + t, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        mMenu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent L = new Intent(this, SearchActivity.class);
            startActivity(L);
        }

        if (id == R.id.action_fav) {

            MenuItem item_ = mMenu.findItem(R.id.action_fav);

            if (mMenu != null) {


                if (!loved) {
                    String token;
                    SharedPreferences prefs = getSharedPreferences(Config.MY_PREFS_NAME, MODE_PRIVATE);
                    token = prefs.getString("token", null);

                    if (token == null) { //if user is signed in only then make him like the product
                        Toast.makeText(SpecificShade.this, "You need to sign in.", Toast.LENGTH_SHORT).show();

                    } else {
                        item_.setIcon(R.mipmap.favorite_selected);
                        loved = true;
                        loveCall("loved");
                        return true;
                    }


                }
                if (loved) {
                    item_.setIcon(R.mipmap.top_favourite);
                    loved = false;
                    loveCall("not_loved");
                    return true;
                }

            }

        }

        return super.onOptionsItemSelected(item);
    }




//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



}
