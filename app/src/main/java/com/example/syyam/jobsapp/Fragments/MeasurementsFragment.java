package com.example.syyam.jobsapp.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.syyam.CalculateActivity;
import com.example.syyam.jobsapp.CalculateResultActivity;
import com.example.syyam.jobsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huma on 22/04/2018.
 */

public class MeasurementsFragment extends Fragment implements View.OnClickListener {

    private View view;
    String param;
    private Dialog dialogInvoicesDateRange, dialogSearchInvoices, dialogDatePicker;

    private Button buttonBack, buttonRight;
    String sDate, eDate;
    private EditText height, width, door, window;
    private ProgressBar progressBar;
    private ImageView imageViewRefresh;
    //
    private RecyclerView recyclerView;
//    private List<ColorFinderModel> noticesList = new ArrayList<>();
//    private AdapterColorFinder mAdapter;


    public MeasurementsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.measurementsfragment, container, false);
        height = view.findViewById(R.id.height);
        width = view.findViewById(R.id.width);
        door = view.findViewById(R.id.door);
        window = view.findViewById(R.id.window);
        setHasOptionsMenu(true);

        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculateArea();
//                Intent ProductDetailActivity = new Intent(getActivity(), CalculateResultActivity.class);
//
//                startActivity(ProductDetailActivity);
            }
        });
//
//        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
////        imageViewRefresh = (ImageView) view.findViewById(R.id.imageViewRefresh);
////        imageViewRefresh.setOnClickListener(this);
//
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//
//
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerViewClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Intent ColorFinder = new Intent(getActivity(), ColorFinderActivity.class);
//
//                startActivity(ColorFinder);
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
//
//
//
//        List<ColorFinderModel> list = new ArrayList<>();
//
//        final String[] colorName = getResources().getStringArray(R.array.colorsName);
//
//
//        int[] coloritems = getActivity().getResources().getIntArray(R.array.colors);
//        //  System.out.println("test token" + coloritems[1]);
//
//        for (int k = 0; k < coloritems.length; k++) {
//            int colorcode = coloritems[k];
//            String name = colorName[k];
//            ColorFinderModel model = new ColorFinderModel();
//
//            model.setcolorCode(colorcode);
//            model.setcolorName(name);
//            //   System.out.println("test token" + test);
//            list.add(model);
//        }
//
//        noticesList = list;
//        mAdapter = new AdapterColorFinder(noticesList);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
        return view;
    }

    private void calculateArea() {
        try {
            double h = Double.parseDouble(height.getText().toString());
            double w = Double.parseDouble(width.getText().toString());
            double dor = Double.parseDouble(door.getText().toString());
            double win = Double.parseDouble(window.getText().toString());
            double area = ((h * w) - (dor + win)) / CalculateActivity.getInstance().spreadingValue;
            Intent ProductDetailActivity = new Intent(getActivity(), CalculateResultActivity.class);
            ProductDetailActivity.putExtra("area", area);
            startActivity(ProductDetailActivity);

        } catch (Exception ex) {
            Toast.makeText(CalculateActivity.getInstance(), "Type Area", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }


}
