package com.example.syyam.jobsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.Models.CategorySpecific;
import com.example.syyam.jobsapp.Models.FinishSpecific;
import com.example.syyam.jobsapp.Models.ProductType;
import com.example.syyam.jobsapp.Models.SurfaceSpecific;
import com.example.syyam.jobsapp.Models.params.CategoryParam;
import com.example.syyam.jobsapp.Models.params.ProjectTypeParam;
import com.example.syyam.jobsapp.Models.params.SurfaceParam;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FiltersActivity extends AppCompatActivity
        implements ProductTypesAdapter.AdapterCallback,
        ProductTypesSecondAdapter.AdapterCallback,
        ProductTypesThirdAdapter.AdapterCallback,
        ProductTypesFourthAdapter.AdapterCallback {

    RecyclerView recyclerView;
    Context mContext;
    private Toolbar toolbar;
    TextView sbtbtn;
    private ImageView firstBox, secondBox, thirdBox, fourthBox;
    private String SenderName = "";
    private int senderId;

    private int projectId, catergoryId, surfaceId, finishId;

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


    @Override
    public void onItemClicked(int position, Integer id, String sender) {
        // call back here


        SenderName = sender;
        senderId = id;
        //make a condition to use one of them by sending a parameter
        if (SenderName.equals("first")) {
            projectId = senderId; //param
            getSecondData(projectId); // CategorySpecific

//            secondBox.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//            secondBox.setImageResource(R.mipmap.pf_interior_selected);
        } else if (SenderName.equals("second")) {
            catergoryId = senderId; //param
            getThirdData(catergoryId); // SurfaceSpecific

//            thirdBox.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//            thirdBox.setImageResource(R.mipmap.pf_wall_selected);
        } else if (SenderName.equals("third")) {
            surfaceId = senderId;  //param
            getFourthData(surfaceId); // FininshSpecific

//            fourthBox.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//            fourthBox.setImageResource(R.mipmap.pf_matt_selected);
        } else if (SenderName.equals("fourth")) {
            sbtbtn.setVisibility(View.VISIBLE);
            finishId = senderId; //param
            Intent pf = new Intent(FiltersActivity.this, ProductFilter.class);
            pf.putExtra("projectId", projectId + "");
            pf.putExtra("catergoryId", catergoryId + "");
            pf.putExtra("surfaceId", surfaceId + "");
            pf.putExtra("finishId", finishId + "");
//            startActivity(pf);

        }


    }

    @Override
    public void onBackPressed() {


        // TODO: Solve it: back always takes onto first screen.

        if (SenderName.equals("third") && SenderName != null) {
            getThirdData(senderId); // FininshSpecific
            //fourthBox.setBackgroundColor(ContextCompat.getColor(this, R.color.GrayF2));
//            fourthBox.setBackground(getResources().getDrawable(R.drawable.grayf2_noleftbordered_button));
            SenderName = "second";

        }
        else if (SenderName.equals("second") && SenderName != null) {

            getSecondData(senderId); // SurfaceSpecific
            //thirdBox.setBackgroundColor(ContextCompat.getColor(this, R.color.GrayF2));
//            thirdBox.setBackground(getResources().getDrawable(R.drawable.grayf2_noleftbordered_button));
            SenderName = "first";
        }
        else if (SenderName.equals("first") && SenderName != null) {
            getData(); // CategorySpecific


            //secondBox.setBackgroundColor(ContextCompat.getColor(this, R.color.GrayF2));
//            secondBox.setBackground(getResources().getDrawable(R.drawable.grayf2_noleftbordered_button));
            SenderName = "zero";

        } else
            super.onBackPressed();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filteractivity);


        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;

        firstBox = (ImageView) findViewById(R.id.firstBox);
        secondBox = (ImageView) findViewById(R.id.secondBox);
        thirdBox = (ImageView) findViewById(R.id.thirdBox);
        fourthBox = (ImageView) findViewById(R.id.fourthBox);
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("Product Finder");
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
        sbtbtn = findViewById(R.id.submitBtn);
        sbtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pf = new Intent(FiltersActivity.this, ProductFilter.class);
                pf.putExtra("projectId", projectId + "");
                pf.putExtra("catergoryId", catergoryId + "");
                pf.putExtra("surfaceId", surfaceId + "");
                pf.putExtra("finishId", finishId + "");
                startActivity(pf);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.productTypesList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new FiltersActivity.GridSpacingItemDecoration(spanCount, spacing, includeEdge));


        getData();

    }


    /*
     * Project Type
     * */
    private void getData() {
        Extras.showLoader(FiltersActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


//        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
//        final int cid = prefs.getInt("countryId", 0); //0 is the default value.


        //Config.getToken(getContext())
        Call<ProductType> productsCall = retrofitController.getProductType();//empty because nothing is being sent in header
        productsCall.enqueue(new Callback<ProductType>() {
            @Override
            public void onResponse(Call<ProductType> call, Response<ProductType> response) {
                Extras.hideLoader();
                if (response != null) {
                    ProductType list = response.body();
                    recyclerView.setAdapter(new ProductTypesAdapter(FiltersActivity.this, list.getData(), (ProductTypesAdapter.AdapterCallback) FiltersActivity.this,1));
                    Extras.hideLoader();
                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductType> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(FiltersActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*
     * Category Specific
     * @Params=projectTypeId
     * */
    private void getSecondData(int id) {
        Extras.showLoader(FiltersActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


//        SharedPreferences prefs = mContext.getSharedPreferences("Country", mContext.MODE_PRIVATE);
//        final int cid = prefs.getInt("countryId", 0); //0 is the default value.

        ProjectTypeParam projectTypeParam = new ProjectTypeParam();
        projectTypeParam.setProject_type_id(id);

        //Config.getToken(getContext())
        Call<CategorySpecific> productsCall = retrofitController.getCategorySpecific(projectTypeParam);
        productsCall.enqueue(new Callback<CategorySpecific>() {
            @Override
            public void onResponse(Call<CategorySpecific> call, Response<CategorySpecific> response) {
                Extras.hideLoader();
                if (response != null) {
                    CategorySpecific list = response.body();
                    recyclerView.setAdapter(new ProductTypesSecondAdapter(FiltersActivity.this, list.getData(), (ProductTypesSecondAdapter.AdapterCallback) FiltersActivity.this,1));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CategorySpecific> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(FiltersActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*
     * Surface Specific
     * @Params=categoryId
     * */
    private void getThirdData(int id) {
        Extras.showLoader(FiltersActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        CategoryParam categoryParam = new CategoryParam();
        categoryParam.setCategory_id(id);

        Call<SurfaceSpecific> productsCall = retrofitController.getSurfaceSpecific(categoryParam);
        productsCall.enqueue(new Callback<SurfaceSpecific>() {
            @Override
            public void onResponse(Call<SurfaceSpecific> call, Response<SurfaceSpecific> response) {
                Extras.hideLoader();
                if (response != null) {
                    SurfaceSpecific list = response.body();
                    recyclerView.setAdapter(new ProductTypesThirdAdapter(FiltersActivity.this, list.getData(), (ProductTypesThirdAdapter.AdapterCallback) FiltersActivity.this,1));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SurfaceSpecific> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(FiltersActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*
     * Finish Specific
     * @Params=surfaceId
     * */
    private void getFourthData(int id) {
        Extras.showLoader(FiltersActivity.this);
        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        SurfaceParam surfaceParam = new SurfaceParam();
        surfaceParam.setSurface_id(id);

        Call<FinishSpecific> productsCall = retrofitController.getFinishSpecific(surfaceParam);
        productsCall.enqueue(new Callback<FinishSpecific>() {
            @Override
            public void onResponse(Call<FinishSpecific> call, Response<FinishSpecific> response) {
                Extras.hideLoader();
                if (response != null) {
                    FinishSpecific list = response.body();
                    recyclerView.setAdapter(new ProductTypesFourthAdapter(FiltersActivity.this, list.getData(), (ProductTypesFourthAdapter.AdapterCallback) FiltersActivity.this,1));

                    //Toast.makeText(getContext(), "success",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FinishSpecific> call, Throwable t) {
                Extras.hideLoader();
                Toast.makeText(FiltersActivity.this, "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
