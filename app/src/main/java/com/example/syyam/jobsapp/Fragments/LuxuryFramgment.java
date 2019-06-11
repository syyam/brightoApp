package com.example.syyam.jobsapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.syyam.jobsapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LuxuryFramgment extends Fragment {




    public LuxuryFramgment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView =inflater.inflate(R.layout.fragment_luxury, container, false);


        return rootView;

    }

}
