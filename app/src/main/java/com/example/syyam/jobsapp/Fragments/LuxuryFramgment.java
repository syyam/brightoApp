package com.example.syyam.jobsapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.syyam.jobsapp.LuxuryFinishesAdapter;
import com.example.syyam.jobsapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LuxuryFramgment extends Fragment {


    private RecyclerView recyclerView;
    private LuxuryFinishesAdapter mAdapter;

    public LuxuryFramgment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_luxury, container, false);
        init(rootView);
        setUpRecyclerview();
        return rootView;

    }

    private void init(View rootView) {
        recyclerView=rootView.findViewById(R.id.recyclerView);
        setUpRecyclerview();
    }

    private void setUpRecyclerview() {
        List<String> list=new ArrayList<>();
        list.add("Foil Luxury Finish");
        list.add("Pesrllescent Luxury Finish");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new LuxuryFinishesAdapter(getContext(),list);
        recyclerView.setAdapter(mAdapter);
    }

}
