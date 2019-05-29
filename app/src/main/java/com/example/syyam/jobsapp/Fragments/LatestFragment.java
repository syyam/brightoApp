package com.example.syyam.jobsapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.syyam.jobsapp.JobDetailActivity;
import com.example.syyam.jobsapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends Fragment {


    LinearLayout first,second,third,fourth,fifth;

    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView =inflater.inflate(R.layout.fragment_latest, container, false);


        first=(LinearLayout) rootView.findViewById(R.id.first);
        second=(LinearLayout) rootView.findViewById(R.id.second);
        third=(LinearLayout) rootView.findViewById(R.id.third);
        fourth=(LinearLayout) rootView.findViewById(R.id.fourth);
        fifth=(LinearLayout) rootView.findViewById(R.id.fifth);



        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getActivity(), JobDetailActivity.class);
                startActivity(l);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getActivity(), JobDetailActivity.class);
                startActivity(l);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getActivity(), JobDetailActivity.class);
                startActivity(l);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getActivity(), JobDetailActivity.class);
                startActivity(l);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getActivity(), JobDetailActivity.class);
                startActivity(l);
            }
        });

        return rootView;

    }

}
