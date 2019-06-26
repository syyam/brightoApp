package com.example.syyam.jobsapp;

import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syyam.jobsapp.Models.DealerCityDatum;

import java.util.List;

public class StoreDealerCityAdapter extends RecyclerView.Adapter<StoreDealerCityAdapter.CF_ViewHolder> {

    private Context context;
    private List<DealerCityDatum> shades;
    StoreAdapter.AdapterCallback callback;


    public StoreDealerCityAdapter(Context context, List<DealerCityDatum> shades, StoreAdapter.AdapterCallback callback) {
        this.context = context;
        this.shades = shades;
        this.callback = callback;
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

        String name = dealercities.getName();

        holder.c_name.setText(name);
        holder.address.setText(dealercities.getAddress().toString());


        holder.countrycityLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MapActivity.class);
                intent.putExtra("lat",dealercities.getLatitude());
                intent.putExtra("lng",dealercities.getLongitude());
                context.startActivity(intent);
            }
        });
        if(callback == null) {
//        holder.countrycityLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        }
        else{
            holder.countrycityLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onItemClicked(position, dealercities.getId(), dealercities.getName(), "dealer");
                }
            });
        }

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
            countrycityLL = itemView.findViewById(R.id.countrycityLL);
            c_name = itemView.findViewById(R.id.c_name);
            address = itemView.findViewById(R.id.address);
            countrycityLL = itemView.findViewById(R.id.countrycityLL);
        }
    }
}
