package com.example.syyam.jobsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.syyam.CalculateActivity;
import com.example.syyam.jobsapp.Fragments.ProductFragment;
import com.example.syyam.jobsapp.Models.Like;
import com.example.syyam.jobsapp.Models.ProductDetailModel.Category;
import com.example.syyam.jobsapp.Models.ProductDetailModel.ProductDetailCity;
import com.example.syyam.jobsapp.Models.ProductDetailModel.ProductDetailDatum;
import com.example.syyam.jobsapp.Models.Products;
import com.example.syyam.jobsapp.Models.ShadesProduct.Product;
import com.example.syyam.jobsapp.Models.ShadesProduct.ShadesProduct;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.Models.params.ProductParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Menu mMenu;
    private Boolean loved = false;

    private List<ProductDetailDatum> mlist;
    private int color_id, country_id;
    private RecyclerView recyclerView;
    private int pid;

    private ImageView image, cover;
    private TextView topTextView, bottomTextView, details;
    private FloatingActionButton floatingActionButton;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private int spreadingValue;

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.White)));
        actionBar.setTitle("");
        actionBar.show();

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();


        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_Login) {
                    Intent L = new Intent(ProductDetails.this, LoginActivity.class);
                    startActivity(L);
                } else {
                    dl.closeDrawer(GravityCompat.START);
                    return true;
                }


                return true;

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationMenuView navMenuView = (NavigationMenuView) nv.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(ProductDetails.this, DividerItemDecoration.VERTICAL));

        int spanCount = 3; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = false;

        recyclerView = (RecyclerView) findViewById(R.id.productDetailList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new ProductDetails.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        image = (ImageView) findViewById(R.id.image);
        cover = (ImageView) findViewById(R.id.cover);
        topTextView = (TextView) findViewById(R.id.topTextView);
        bottomTextView = (TextView) findViewById(R.id.bottomTextView);
        details = (TextView) findViewById(R.id.details);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, CalculateActivity.class);
                intent.putExtra("spreading", spreadingValue);
                startActivity(intent);
            }
        });


        if (getIntent().hasExtra("product_id")) {

            String PID = getIntent().getStringExtra("product_id");


            if (!TextUtils.isEmpty(PID)) {
                pid = Integer.parseInt(getIntent().getStringExtra("product_id"));
            }

        }// if end

        getData(pid);
    }

    public void getData(final int p_id) {

        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        ProductParam productParam = new ProductParam();
        productParam.setProduct_id(p_id);

        //Config.getToken(getContext())
        Call<ProductDetailCity> productsCall = retrofitController.getProductDetail(productParam);
        productsCall.enqueue(new Callback<ProductDetailCity>() {
            @Override
            public void onResponse(Call<ProductDetailCity> call, Response<ProductDetailCity> response) {

                if (response != null) {
                    ProductDetailCity list = response.body();

                    spreadingValue = response.body().getData().get(0).getSpreading();
                    String getName = response.body().getData().get(0).getName();
                    Category category=null;
                    if(response.body().getData().get(0).getCategory()!=null) {
                        category = response.body().getData().get(0).getCategory();
                        bottomTextView.setText(category.getName());
                    }

                    String detail = response.body().getData().get(0).getDescription();
                    String imgurl = Config.IMAGE_URL + response.body().getData().get(0).getImage();
                    String cvr_imgurl = Config.IMAGE_URL + response.body().getData().get(0).getCoverImage();

                    topTextView.setText(getName);

                    details.setText(detail);
                    Glide.with(ProductDetails.this).load(imgurl).into(image);
                    Glide.with(ProductDetails.this).load(cvr_imgurl).into(cover);


                        getFurtherData(p_id);
//                    Toast.makeText(ProductDetails.this, "success"+data,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetailCity> call, Throwable t) {
                Toast.makeText(ProductDetails.this, "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFurtherData(final int p_id) {
        Extras.showLoader(ProductDetails.this);
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
                    ShadesProduct list = response.body();
                    recyclerView.setAdapter(new ProductDetailsAdapter(ProductDetails.this, list.getData(), p_id));

//                    Toast.makeText(ProductDetails.this, "success"+data,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShadesProduct> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(ProductDetails.this, "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void loveCall(String sender) {


//        String Bearertoken, token = "";
//        Config.getToken(this);
//        SharedPreferences prefs = getSharedPreferences(Config.MY_PREFS_NAME, MODE_PRIVATE);
//        Bearertoken = prefs.getString("bearerToken", "nothing");


        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);

        ProductParam productParam = new ProductParam();
        productParam.setProduct_id(pid);

        Call<Like> productsCall = null;
        if (sender.equals("loved")) {
            productsCall = retrofitController.getLike(Config.getToken(this), productParam);
        }
        if (sender.equals("not_loved")) {
            productsCall = retrofitController.getUnLike(Config.getToken(this), productParam);
        }


        //Config.getToken(getContext())

        productsCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {

                if (response != null) {
                    Like list = response.body();

                    if (list != null) {
                        Toast.makeText(ProductDetails.this, list.getMessage().toString(), Toast.LENGTH_LONG).show();

                        if (list.getMessage() == null) {
                            Toast.makeText(ProductDetails.this, list.getErrors().toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(ProductDetails.this, "An error occured", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Toast.makeText(ProductDetails.this, "Failure: " + t, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav_share_back, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.network_security_config.
        int id = item.getItemId();

        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.action_search) {
            Intent L = new Intent(this, SearchActivity.class);
            startActivity(L);
        }

        if (id == R.id.action_back) {
            finish();
        }
//        if (id == R.id.action_share) {
//
//        }
        if (id == R.id.action_fav) {

            MenuItem item_ = mMenu.findItem(R.id.action_fav);

            if (mMenu != null) {


                if (!loved) {
                    String token;
                    SharedPreferences prefs = getSharedPreferences(Config.MY_PREFS_NAME, MODE_PRIVATE);
                    token = prefs.getString("token", null);

                    if (token == null) { //if user is signed in only then make him like the product
                        Toast.makeText(ProductDetails.this, "You need to sign in.", Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_Login) {
            Intent L = new Intent(this, LoginActivity.class);
            startActivity(L);
        }
        if (id == R.id.nav_designerPalettes) {
            Intent L = new Intent(this, DesignerPalettedActivity.class);
            startActivity(L);
        } else {
            dl.closeDrawer(GravityCompat.START);
            return true;
        }

//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_search) {
//            Intent L = new Intent(this, SearchActivity.class);
//            startActivity(L);
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_rate) {
//
//        } else if (id == R.id.nav_exit) {
//
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        return true;
    }
}

