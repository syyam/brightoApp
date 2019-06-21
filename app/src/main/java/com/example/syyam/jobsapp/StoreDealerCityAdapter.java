package com.example.syyam.jobsapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Fragments.StoreFragment;
import com.example.syyam.jobsapp.Models.DealerCityDatum;

import java.util.List;

public class StoreDealerCityAdapter extends RecyclerView.Adapter<StoreDealerCityAdapter.CF_ViewHolder> {

    private StoreFragment context;
    private List<DealerCityDatum> shades;


    public StoreDealerCityAdapter(StoreFragment context, List<DealerCityDatum> shades) {
        this.context = context;
        this.shades = shades;

    }

    @NonNull
    @Override
    public CF_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_store_dealer, parent, false);
        return new CF_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CF_ViewHolder holder, final int position) {


        final DealerCityDatum dealercities = this.shades.get(position);

        String name = dealercities.getName().toString();

        holder.c_name.setText(name);
        holder.address.setText(dealercities.getAddress().toString());

//        holder.countrycityLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return shades.size();
    }

    public class CF_ViewHolder extends RecyclerView.ViewHolder {
        private TextView c_name, address;
        private LinearLayout countrycityLL;


        public CF_ViewHolder(View itemView) {
            super(itemView);
            c_name = itemView.findViewById(R.id.c_name);
            address = itemView.findViewById(R.id.address);
        }
    }
}
