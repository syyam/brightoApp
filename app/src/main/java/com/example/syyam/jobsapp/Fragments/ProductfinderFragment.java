package com.example.syyam.jobsapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.syyam.jobsapp.ProductTypes;
import com.example.syyam.jobsapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductfinderFragment extends Fragment {

    private Button button;

    public ProductfinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_productfinder, container, false);


        button=(Button) rootView.findViewById(R.id.begin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getContext(),ProductTypes.class);
                startActivity(l);
            }
        });
        return rootView;
    }

}
