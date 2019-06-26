package com.example.syyam.jobsapp.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.ColorFinderAdapter;
import com.example.syyam.jobsapp.ColorShadesActivity;
import com.example.syyam.jobsapp.Models.ColorFinder;
import com.example.syyam.jobsapp.Models.Products;
import com.example.syyam.jobsapp.Models.params.CountryParam;
import com.example.syyam.jobsapp.ProductAdapter;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

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

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);


        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;

        mContext = rootView.getContext();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.productList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new ProductFragment.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


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


        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
        final int cid = prefs.getInt("countryId", -1); //0 is the default value.


        CountryParam countryParam = new CountryParam();
        countryParam.setCountry_id(cid);

        //Config.getToken(getContext())
        Call<Products> productsCall = retrofitController.getProducts(countryParam);
        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                if (response != null) {
                    Products list = response.body();
                    if (list != null)
                        recyclerView.setBackground(null);
                        recyclerView.setAdapter(new ProductAdapter(getContext(), list.getData(), cid, true, null));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
