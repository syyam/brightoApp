package com.example.syyam.jobsapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.ShadesFamily;
import com.example.syyam.jobsapp.Models.params.CountryFamilyParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;
import com.gw.swipeback.SwipeBackLayout;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorShadesActivity extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
//    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback;

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
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
      case BottomSheetBehavior.STATE_HIDDEN:
          Toast.makeText(getContext(), "STATE_HIDDEN", Toast.LENGTH_SHORT).show();
          dismiss();
          break;
      case BottomSheetBehavior.STATE_EXPANDED:
          Toast.makeText(getContext(), "STATE_EXPANDED", Toast.LENGTH_SHORT).show();
          break;
      case BottomSheetBehavior.STATE_COLLAPSED:
          Toast.makeText(getContext(), "STATE_COLLAPSED", Toast.LENGTH_SHORT).show();
          break;
      case BottomSheetBehavior.STATE_DRAGGING:
          Toast.makeText(getContext(), "STATE_DRAGGING", Toast.LENGTH_SHORT).show();
          break;
      case BottomSheetBehavior.STATE_SETTLING:
          Toast.makeText(getContext(), "STATE_SETTLING", Toast.LENGTH_SHORT).show();
          break;
    }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Toast.makeText(getContext(), slideOffset+"", Toast.LENGTH_SHORT).show();

        }
    };
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//
//        View view = View.inflate(getContext(), R.layout.activity_color_shades, null);
//        init(view);
//        dialog.setContentView(view);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
//        CoordinatorLayout.Behavior behavior = params.getBehavior();
//
//        if( behavior != null && behavior instanceof BottomSheetBehavior ) {
//            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
//        }
//        return  dialog;
//    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog,style);
//        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.activity_color_shades, null);
        init(contentView);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            ((BottomSheetBehavior) behavior).setState(BottomSheetBehavior.STATE_EXPANDED);
            ((BottomSheetBehavior) behavior).setPeekHeight(0);
        }

    }
    @Override
    public void onStart() {
        super.onStart();
//        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void init(View view) {

        int spanCount = 3; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = false;
        recyclerView = (RecyclerView) view.findViewById(R.id.colorShadesList);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        if (getArguments().getString("color_id") != null && getArguments().getString("country_id") != null) {

            String colorID = getArguments().getString("color_id");
            String countryID = getArguments().getString("country_id");

            //Toast.makeText(this, colorID+"",Toast.LENGTH_LONG).show();


            if (!TextUtils.isEmpty(colorID) && TextUtils.isDigitsOnly(colorID) && !TextUtils.isEmpty(countryID) && TextUtils.isDigitsOnly(countryID)) {
                color_id = Integer.parseInt(getArguments().getString("color_id"));
                country_id = Integer.parseInt(getArguments().getString("country_id"));
            }


            getData();
        }
    }


    private void getData() {
        Extras.showLoader(getContext());
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
                if (list != null)
                    recyclerView.setAdapter(new ColorShadesAdapter(getContext(), list.getData(), null));

                //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ShadesFamily> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }



}
