package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.syyam.jobsapp.Fragments.ProductFragment;
import com.example.syyam.jobsapp.Models.Products;
import com.example.syyam.jobsapp.Models.ShadesProduct.Product;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFilter extends AppCompatActivity {

    RecyclerView recyclerView;
    Context mContext;

    //https://stackoverflow.com/a/30701422/9980884
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
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int P_id, C_id, S_id, F_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_filter);


        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;


        recyclerView = (RecyclerView) findViewById(R.id.productList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new ProductFilter.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        if (getIntent().hasExtra("projectId") &&
                getIntent().hasExtra("catergoryId") &&
                getIntent().hasExtra("surfaceId") &&
                getIntent().hasExtra("finishId")) {

            String projectId = getIntent().getStringExtra("projectId");
            String catergoryId = getIntent().getStringExtra("catergoryId");
            String surfaceId = getIntent().getStringExtra("surfaceId");
            String finishId = getIntent().getStringExtra("finishId");


            if (!TextUtils.isEmpty(projectId)
                    && !TextUtils.isEmpty(projectId)
                    && !TextUtils.isEmpty(projectId)
                    && !TextUtils.isEmpty(projectId)) {
                P_id = Integer.parseInt(getIntent().getStringExtra("projectId"));
                C_id = Integer.parseInt(getIntent().getStringExtra("catergoryId"));
                S_id = Integer.parseInt(getIntent().getStringExtra("surfaceId"));
                F_id = Integer.parseInt(getIntent().getStringExtra("finishId"));

            }

        }// if end


        getData();

    }

    private void getData() {
        Extras.showLoader(ProductFilter.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


//        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
//        final int cid = prefs.getInt("countryId", 0); //0 is the default value.


        FilterParam filterParam = new FilterParam();
        filterParam.setCategory_id(C_id);
        filterParam.setFinish_type_id(F_id);
        filterParam.setProject_type_id(P_id);
        filterParam.setSurface_id(S_id);

        //Config.getToken(getContext())
        Call<Products> productsCall = retrofitController.getProductsFilter(filterParam);//empty because nothing is being sent in header
        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
Extras.hideLoader();
                if (response != null) {
                    Products list = response.body();
                    recyclerView.setAdapter(new ProductFilterAdapter(ProductFilter.this, list.getData()));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(ProductFilter.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
